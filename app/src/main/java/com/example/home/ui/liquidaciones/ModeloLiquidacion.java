package com.example.home.ui.liquidaciones;

public class ModeloLiquidacion {
    private int idLiquidacion;
    private int estado;
    private int idSalida;

    public ModeloLiquidacion(int idLiquidacion, int estado, int idSalida) {
        this.idLiquidacion = idLiquidacion;
        this.estado = estado;
        this.idSalida = idSalida;
    }

    public ModeloLiquidacion(int estado, int idSalida) {
        this.estado = estado;
        this.idSalida = idSalida;
    }

    public int getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(int idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
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
