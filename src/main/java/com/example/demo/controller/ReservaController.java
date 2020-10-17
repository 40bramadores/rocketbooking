package com.example.demo.controller;

import com.example.demo.controller.modelRequest.ReservaCreationRequest;
import com.example.demo.controller.modelRequest.ReservaCreationSuccess;
import com.example.demo.model.Cliente;
import com.example.demo.model.Reserva;
import com.example.demo.model.Ticket;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EventoService;
import com.example.demo.service.ReservaService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RequestMapping("api/v1/reserva")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Autowired
    TicketService ticketService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EventoService eventoService;

    @PostMapping
    @ResponseBody
    public ResponseEntity crearReserva (@NonNull @RequestBody ReservaCreationRequest reservaCreationRequest)
    {
        if (ticketService.getCantidadDeTicketsDisponibles(reservaCreationRequest.getIdEvento()).size() >= reservaCreationRequest.getCantidadDeTickets())
        {
            Cliente cliente = clienteService.findByNombreAndEmailEquals(reservaCreationRequest.getNombre(), reservaCreationRequest.getEmail()).orElse(null);
            if (cliente == null)
            {
                Cliente clienteNuevo = new Cliente(reservaCreationRequest.getNombre(), reservaCreationRequest.getEmail());
                clienteService.crearCliente(clienteNuevo);
                cliente = clienteNuevo;
            }
            Reserva reserva = new Reserva (null, cliente);
;
            //TODO Cambiar nombre de metodo de tickets disponibles

            Collection<Ticket> ticketsDisponibles = ticketService.getTicketsDisponibles(reservaCreationRequest.getIdEvento(), reservaCreationRequest.getCantidadDeTickets());
            Double precioFinal = 0d;
            ReservaCreationSuccess reservaCreationSuccess = new ReservaCreationSuccess(0d, null);

            for (Ticket ticket : ticketsDisponibles)
            {
                precioFinal += ticket.getPrecio();
                ticket.setReserva(reserva);
                ticketService.crearTicket(ticket);
            }

            //TODO Ver si es necesario usar reserva creation success

            reservaCreationSuccess.setTicketsComprados((ArrayList<Ticket>) ticketsDisponibles);
            reservaCreationSuccess.setPrecioTotal(precioFinal);
            reserva.setPrecioFinal(precioFinal);
            reservaService.crearReserva(reserva);

            return new ResponseEntity(reservaCreationSuccess, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest()
                        .body("No hay tickets disponibles");
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity getReserva (@PathVariable("id") Integer id)
    {
        Optional<Reserva> reservaObtenido = reservaService.getReserva(id);

        Double precioFinal = reservaService.calculateFinalPrice(reservaObtenido.get().getTickets());
        reservaObtenido.get().setPrecioFinal(precioFinal);

        return new ResponseEntity(reservaObtenido, HttpStatus.OK);
    }

    @GetMapping(path = "cliente{id}")
    @ResponseBody
    public ResponseEntity getReservationsByClientId (@PathVariable("id") Integer id)
    {
        Optional<Cliente> cliente = clienteService.getCliente(id);
        if (cliente.isPresent())
        {
            return ResponseEntity.ok(reservaService.getReservationsByClientId(id));
        }
        return ResponseEntity.badRequest().body("No existe ese cliente");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity borrarReserva (@PathVariable("id") Integer id)
    {
        Optional<Reserva> reservaObtenido = reservaService.getReserva(id);

        if (reservaObtenido.isPresent())
        {
            for (Ticket ticket : reservaObtenido.get().getTickets())
            {
                ticket.setReserva(null);
            }
            reservaService.borrarReserva(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("No existe esa reserva");
    }
}
