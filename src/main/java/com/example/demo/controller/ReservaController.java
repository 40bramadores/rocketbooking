package com.example.demo.controller;

import com.example.demo.controller.modelRequest.ReservaCreationRequest;
import com.example.demo.controller.modelRequest.ReservaCreationSuccess;
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
@CrossOrigin(origins = {"http://localhost:3000"})
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

    @GetMapping(path = "{id}/tickets")
    @ResponseBody
    public ResponseEntity getTicketDisponibles (@PathVariable("id") Integer id)
    {
        if (!ticketService.getCantidadDeTicketsDisponibles(id).isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ticketService.getCantidadDeTicketsDisponibles(id));
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity crearReserva (@NonNull @RequestBody ReservaCreationRequest reservaCreationRequest)
    {
        if (ticketService.getCantidadDeTicketsDisponibles(reservaCreationRequest.getIdEvento()).size() >= reservaCreationRequest.getCantidadDeTickets())
        {
            Reserva reserva = new Reserva (null,
                                            clienteService.getCliente(reservaCreationRequest.getIdCliente()).get(),
                                            eventoService.getEvento(reservaCreationRequest.getIdEvento()).get());
            Collection<Ticket> ticketsDisponibles = ticketService.getTicketsDisponibles(reservaCreationRequest.getIdEvento(), reservaCreationRequest.getCantidadDeTickets());
            Double precioFinal = 0d;
            ReservaCreationSuccess reservaCreationSuccess = new ReservaCreationSuccess(0d, null);
            for (Ticket ticket : ticketsDisponibles)
            {
                precioFinal += ticket.getPrecio();
                ticket.setReserva(reserva);
                ticketService.crearTicket(ticket);
            }
            reservaCreationSuccess.setTicketsComprados((ArrayList<Ticket>) ticketsDisponibles);
            reservaCreationSuccess.setPrecioTotal(precioFinal);
            reserva.setPrecio(precioFinal);
            reservaService.crearReserva(reserva);

            return new ResponseEntity(reservaCreationSuccess, HttpStatus.CREATED);
        }
        else
            {
                return ResponseEntity.noContent().build();
            }
    }

}
