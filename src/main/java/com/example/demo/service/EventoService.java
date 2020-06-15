package com.example.demo.service;

import com.example.demo.model.Evento;
import com.example.demo.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public Evento crearEvento (Evento evento) { return eventoRepository.save(evento); }

    public Optional<Evento> getEvento (Integer id) { return eventoRepository.findById(id); }

    public Iterable<Evento> getAllEvento () { return eventoRepository.findAll(); }
}
