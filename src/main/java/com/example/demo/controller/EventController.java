package com.example.demo.controller;

import com.example.demo.controller.modelRequest.EventCreationRequest;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.Venue;
import com.example.demo.service.EventService;
import com.example.demo.service.TicketService;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/${api.version}/event")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    TicketService ticketService;

    @Autowired
    VenueService venueService;

    @Autowired
    MessageSource messageSource;

    public EventController(EventService eventService, TicketService ticketService, VenueService venueService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent (@NonNull @RequestBody EventCreationRequest eventCreationRequest)
    {
        Optional<Venue> venue = venueService.getVenue(eventCreationRequest.getVenueId());

        Event newEvent = new Event(eventCreationRequest.getEvent().getDate(),
                                        venue.get(),
                                        eventCreationRequest.getEvent().getDescription());

        Event eventCreated = eventService.createEvent(newEvent);

        for (int i = 0; i < eventCreationRequest.getCapacity(); i++)
        {
            Ticket ticket = new Ticket (eventCreationRequest.getPrice(),  null, eventCreated);
            ticketService.createTicket(ticket);
        }

        return new ResponseEntity(eventCreated, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<?> getEvent (@PathVariable("id") Integer id)
    {
        Optional<Event> eventFound = eventService.getEvent(id);
        if (eventFound.isPresent())
        {
            return new ResponseEntity(eventFound, HttpStatus.OK);
        }
        return ResponseEntity.badRequest()
                .body(messageSource.getMessage("noEventFound", null, LocaleContextHolder.getLocale()));
    }

    @GetMapping(path = "{id}/tickets")
    @ResponseBody
    public ResponseEntity getAvailableTickets (@PathVariable("id") Integer id)
    {
        if (!ticketService.getAvailableTickets(id).isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ticketService.getAvailableTickets(id));
        }
        return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketsAvailable",
                                                                        null, LocaleContextHolder.getLocale()));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getAllEvents ()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.getAllEvents());
    }
}
