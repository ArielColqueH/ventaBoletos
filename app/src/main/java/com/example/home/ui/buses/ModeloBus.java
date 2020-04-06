package com.example.home.ui.buses;

public class ModeloBus {
    private int idBus;
    private String placa;
    private int capacidad;
    private String tipoBus;
    private int estado;
    private int duenio;

    public ModeloBus(int idBus, String placa, int capacidad, String tipoBus, int estado, int duenio) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidad = capacidad;
        this.tipoBus = tipoBus;
        this.estado = estado;
        this.duenio = duenio;
    }
    public ModeloBus(String placa, int capacidad, String tipoBus, int estado, int duenio) {
        this.placa = placa;
        this.capacidad = capacidad;
        this.tipoBus = tipoBus;
        this.estado = estado;
        this.duenio = duenio;
    }

    public ModeloBus(int idBus, String placa) {
        this.idBus = idBus;
        this.placa = placa;
    }

    public ModeloBus(int idBus, String placa, int capacidad, String tipoBus, int duenio) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidad = capacidad;
        this.tipoBus = tipoBus;
        this.duenio = duenio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipoBus() {
        return tipoBus;
    }

    public void setTipoBus(String tipoBus) {
        this.tipoBus = tipoBus;
    }

    public int getDuenio() {
        return duenio;
    }

    public void setDuenio(int duenio) {
        this.duenio = duenio;
    }
}
