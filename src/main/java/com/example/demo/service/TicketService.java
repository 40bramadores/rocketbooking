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

   public Ticket crearTicket (Ticket ticket) { return ticketRepository.save(ticket); }

   public Optional<Ticket> getTicket (Integer id) { return ticketRepository.findById(id); }

   public Collection<Ticket> getCantidadDeTicketsDisponibles (Integer idEvento)
   {
      return ticketRepository.findByReservaIsNull(idEvento);
   }

   public Collection<Ticket>  getTicketsDisponibles (Integer idEvento, Integer cantidad)
   {
      return ticketRepository.findXByReservaIsNull(idEvento, cantidad);
   }

}
