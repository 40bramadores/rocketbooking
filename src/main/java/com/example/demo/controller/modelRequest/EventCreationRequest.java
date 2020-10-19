package com.example.demo.controller.modelRequest;

import com.example.demo.model.Event;

public class EventCreationRequest {

    private Integer venueId;
    private Integer capacity;
    private Integer price;
    private Event event;

    public EventCreationRequest(Integer venueId, Integer capacity, Integer price, Event event) {
        this.venueId = venueId;
        this.capacity = capacity;
        this.price = price;
        this.event = event;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
