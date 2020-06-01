package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
@Entity
@Table(name = "Ticket")
public class Ticket {

    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Id
    @Column
    private Integer id;

    @Column (name = "precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne(cascade = CascadeType.PERSIST    )
    @JoinColumn (name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn (name = "evento_id")
    private Evento evento;

    @Autowired
    public Ticket(Venue venue, double precio, Reserva reserva, Evento evento) {
        this.venue = venue;
        this.precio = precio;
        this.reserva = reserva;
        this.evento = evento;
    }

    @Autowired
    public Ticket () {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
