package com.example.demo.controller;

import com.example.demo.controller.modelRequest.ReservationCreationRequest;
import com.example.demo.controller.modelRequest.ReservationCreationSuccess;
import com.example.demo.model.Client;
import com.example.demo.model.Reservation;
import com.example.demo.model.Ticket;
import com.example.demo.service.ClientService;
import com.example.demo.service.EventService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/reservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    TicketService ticketService;

    @Autowired
    ClientService clientService;

    @Autowired
    EventService eventService;

    @Autowired
    MessageSource messageSource;

    @PostMapping
    @ResponseBody
    public ResponseEntity createReservation (@NonNull @RequestBody ReservationCreationRequest reservationCreationRequest)
    {
        if (ticketService.getAvailableTickets(reservationCreationRequest.getIdEvent()).size() >= reservationCreationRequest.getNumberOfTickets())
        {
            Client client = clientService.findByNameAndEmailEquals(reservationCreationRequest.getName(),
                                                                    reservationCreationRequest.getEmail())
                                                                    .orElse(null);
            if (client == null)
            {
                client = clientService.createClient(new Client(reservationCreationRequest.getName(), reservationCreationRequest.getEmail()));
            }
            Reservation reservation = new Reservation(null, client);

            Collection<Ticket> availableTickets = ticketService.getAvailableTickets(reservationCreationRequest.getIdEvent(), reservationCreationRequest.getNumberOfTickets());
            Double finalPrice = reservationService.calculateFinalPrice((List<Ticket>) availableTickets);
            ReservationCreationSuccess reservationCreationSuccess = new ReservationCreationSuccess(finalPrice, null);

            for (Ticket ticket : availableTickets)
            {
                ticket.setReservation(reservation);
                ticketService.createTicket(ticket);
            }

            //TODO Ver si es necesario usar reserva creation success

            reservationCreationSuccess.setTicketsReserved((ArrayList<Ticket>) availableTickets);
            reservationService.createReservation(reservation);

            return new ResponseEntity(reservationCreationSuccess, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest()
                        .body(messageSource.getMessage("noTicketsAvailable", null, LocaleContextHolder.getLocale()));
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity getReservation (@PathVariable("id") Integer id)
    {
        Optional<Reservation> reservationFound = reservationService.getReservation(id);

        Double finalPrice = reservationService.calculateFinalPrice(reservationFound.get().getTickets());
        reservationFound.get().setFinalPrice(finalPrice);

        return new ResponseEntity(reservationFound, HttpStatus.OK);
    }

    @GetMapping(path = "client{id}")
    @ResponseBody
    public ResponseEntity getReservationsByClientId (@PathVariable("id") Integer id)
    {
        Optional<Client> client = clientService.getClient(id);
        if (client.isPresent())
        {
            return ResponseEntity.ok(reservationService.getReservationsByClientId(id));
        }
        return ResponseEntity.badRequest().body(messageSource.getMessage("noClientFound", null, LocaleContextHolder.getLocale()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteReservation (@PathVariable("id") Integer id)
    {
        Optional<Reservation> reservationFound = reservationService.getReservation(id);

        if (reservationFound.isPresent())
        {
            for (Ticket ticket : reservationFound.get().getTickets())
            {
                ticket.setReservation(null);
            }
            reservationService.deleteReservation(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(messageSource.getMessage("noReservationFound", null, LocaleContextHolder.getLocale()));
    }
}
