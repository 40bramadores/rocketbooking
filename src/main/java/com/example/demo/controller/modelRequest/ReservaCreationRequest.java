package com.example.demo.controller.modelRequest;

public class ReservaCreationRequest {

    private String nombre;
    private String email;
    private Integer idEvento;
    private Integer cantidadDeTickets;

    public ReservaCreationRequest(String nombre, String email, Integer idEvento, Integer cantidadDeTickets) {
        this.nombre = nombre;
        this.email = email;
        this.idEvento = idEvento;
        this.cantidadDeTickets = cantidadDeTickets;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
