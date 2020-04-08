package com.example.home.ui.boleto;

public class ModeloBoleto {
    private int idBoleto;
    private String nombrePasajero;
    private String nitPasajero;
    private int asiento;
    private double precio;
    private int estado;
    private int idSalida;

    public ModeloBoleto(int idBoleto, String nombrePasajero, String nitPasajero, int asiento, double precio, int estado, int idSalida) {
        this.idBoleto = idBoleto;
        this.nombrePasajero = nombrePasajero;
        this.nitPasajero = nitPasajero;
        this.asiento = asiento;
        this.precio = precio;
        this.estado = estado;
        this.idSalida = idSalida;
    }
    public ModeloBoleto(int idBoleto, String nombrePasajero, String nitPasajero, int asiento, double precio, int idSalida) {
        this.idBoleto = idBoleto;
        this.nombrePasajero = nombrePasajero;
        this.nitPasajero = nitPasajero;
        this.asiento = asiento;
        this.precio = precio;
        this.idSalida = idSalida;
    }

    public ModeloBoleto(String nombrePasajero, String nitPasajero, int asiento, double precio, int estado, int idSalida) {
        this.nombrePasajero = nombrePasajero;
        this.nitPasajero = nitPasajero;
        this.asiento = asiento;
        this.precio = precio;
        this.estado = estado;
        this.idSalida = idSalida;
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getNitPasajero() {
        return nitPasajero;
    }

    public void setNitPasajero(String nitPasajero) {
        this.nitPasajero = nitPasajero;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }
}
