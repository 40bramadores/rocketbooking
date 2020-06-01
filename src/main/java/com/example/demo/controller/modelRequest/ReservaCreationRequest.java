package com.example.demo.controller.modelRequest;

public class ReservaCreationRequest {

    private Integer idCliente;
    private Integer idEvento;
    private Integer cantidadDeTickets;

    public ReservaCreationRequest(Integer idCliente, Integer idEvento, Integer cantidadDeTickets) {
        this.idCliente = idCliente;
        this.idEvento = idEvento;
        this.cantidadDeTickets = cantidadDeTickets;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getCantidadDeTickets() {
        return cantidadDeTickets;
    }

    public void setCantidadDeTickets(Integer cantidadDeTickets) {
        this.cantidadDeTickets = cantidadDeTickets;
    }
}
