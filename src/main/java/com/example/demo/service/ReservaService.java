package com.example.demo.service;

import com.example.demo.model.Reserva;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    public Reserva crearReserva (Reserva reserva) { return reservaRepository.save(reserva); }

    public Optional<Reserva> getReserva (Integer id) { return reservaRepository.findById(id); }

    public void borrarReserva (Integer id)
    {
        reservaRepository.deleteById(id);
    }

    public Collection<Reserva> getReservationsByClientId (Integer id)
    {
        Collection<Reserva> reservations = reservaRepository.findReservasByCliente_Id(id);
        for (Reserva reserva : reservations)
        {
            Double precioFinal = calculateFinalPrice(reserva.getTickets());
            reserva.setPrecioFinal(precioFinal);
        }
        return reservations;
    }

    public Double calculateFinalPrice (List<Ticket> tickets) {
        Double precioFinal = 0d;
        for (Ticket ticket : tickets)
        {
            precioFinal += ticket.getPrecio();
        }
        return precioFinal;
    }
}
