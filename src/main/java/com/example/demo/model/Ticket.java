package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn (name = "evento_id")
    private Evento evento;

    @Autowired
    public Ticket(double precio, Reserva reserva, Evento evento) {
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
