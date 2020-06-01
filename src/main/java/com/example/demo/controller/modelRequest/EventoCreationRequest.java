package com.example.demo.controller.modelRequest;

import com.example.demo.model.Evento;

public class EventoCreationRequest {

    private Integer venueId;
    private Integer capacidad;
    private Integer precio;
    private Evento evento;

    public EventoCreationRequest(Integer venueId, Integer capacidad, Integer precio, Evento evento) {
        this.venueId = venueId;
        this.capacidad = capacidad;
        this.precio = precio;
        this.evento = evento;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
