package com.example.demo.service;

import com.example.demo.model.Reserva;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    public Reserva crearReserva (Reserva reserva) { return reservaRepository.save(reserva); }

    public Optional<Reserva> getReserva (Integer id) { return reservaRepository.findById(id); }
}
