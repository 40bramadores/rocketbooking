package com.example.demo.controller.modelRequest;

import com.example.demo.model.Ticket;

import java.util.ArrayList;

public class ReservaCreationSuccess {

    private Double precioTotal;
    private ArrayList<Ticket> ticketsComprados;

    public ReservaCreationSuccess(Double precioTotal, ArrayList<Ticket> ticketsComprados) {
        this.precioTotal = precioTotal;
        this.ticketsComprados = ticketsComprados;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ArrayList<Ticket> getTicketsComprados() {
        return ticketsComprados;
    }

    public void setTicketsComprados(ArrayList<Ticket> ticketsComprados) {
        this.ticketsComprados = ticketsComprados;
    }
}
