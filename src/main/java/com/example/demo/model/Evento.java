package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Evento")
public class Evento {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fechaDeRealizacion", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @Column(name = "descripcion")
    private String descripcion;



    @Autowired
    public Evento(LocalDate fecha, Venue venue, String descripcion) {
        this.fecha = fecha;
        this.venue = venue;
        this.descripcion = descripcion;
    }

    @Autowired
    public Evento () {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
