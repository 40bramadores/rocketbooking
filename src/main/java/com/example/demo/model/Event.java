package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @Column(name = "description")
    private String description;

    @Autowired
    public Event(LocalDate date, Venue venue, String description) {
        this.date = date;
        this.venue = venue;
        this.description = description;
    }

    @Autowired
    public Event() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
