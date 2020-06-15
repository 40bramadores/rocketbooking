package com.example.demo.controller;

import com.example.demo.controller.modelRequest.EventoCreationRequest;
import com.example.demo.model.Evento;
import com.example.demo.model.Ticket;
import com.example.demo.model.Venue;
import com.example.demo.service.EventoService;
import com.example.demo.service.TicketService;
import com.example.demo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/evento")
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class EventoController {

    private final EventoService eventoService;
    private final TicketService ticketService;
    private final VenueService venueService;

    public EventoController(EventoService eventoService, TicketService ticketService, VenueService venueService) {
        this.eventoService = eventoService;
        this.ticketService = ticketService;
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento (@NonNull @RequestBody EventoCreationRequest eventCreationRequest)
    {
        Optional<Venue> venueDelEvento = venueService.getVenue(eventCreationRequest.getVenueId());

        Evento nuevoEvento = new Evento (eventCreationRequest.getEvento().getFecha(),
                                        venueDelEvento.get(),
                                        eventCreationRequest.getEvento().getDescripcion());

        Evento eventoCreado = eventoService.crearEvento(nuevoEvento);

        for (int i = 0; i < eventCreationRequest.getCapacidad(); i++)
        {
            Ticket ticket = new Ticket (eventCreationRequest.getPrecio(),  null, eventoCreado);
            ticketService.crearTicket(ticket);
        }

        return new ResponseEntity(eventoCreado, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<?> getEvento (@PathVariable("id") Integer id)
    {
        Optional<Evento> eventoObtenido = eventoService.getEvento(id);
        return new ResponseEntity(eventoObtenido, HttpStatus.OK);
    }

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

    @GetMapping
    @ResponseBody
    public ResponseEntity getAllEvento ()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventoService.getAllEvento());
    }
}
