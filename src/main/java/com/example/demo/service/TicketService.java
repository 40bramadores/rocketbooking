package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService {

   @Autowired
   TicketRepository ticketRepository;

   public Ticket createTicket (Ticket ticket)
   {
      return ticketRepository.save(ticket);
   }

   public Collection<Ticket> getAvailableTickets (Integer idEvent)
   {
      return ticketRepository.findByReservationIsNull(idEvent);
   }

   public Collection<Ticket>  getAvailableTickets (Integer idEvent, Integer quantity)
   {
      return ticketRepository.findByReservationIsNull(idEvent, quantity);
   }

}
