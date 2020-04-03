package com.example.home.ui.socios;

public class ModeloSocio {
    private int idSoc;
    private String nomSoc;
    private String apeSoc;

    public ModeloSocio(int idSoc, String nomSoc, String apeSoc) {
        this.idSoc = idSoc;
        this.nomSoc = nomSoc;
        this.apeSoc = apeSoc;
    }
    public ModeloSocio(String nomSoc, String apeSoc) {
        this.nomSoc = nomSoc;
        this.apeSoc = apeSoc;
    }

    public int getIdSoc() {
        return idSoc;
    }

    public void setIdSoc(int idSoc) {
        this.idSoc = idSoc;
    }

    public String getNomSoc() {
        return nomSoc;
    }

    public void setNomSoc(String nomSoc) {
        this.nomSoc = nomSoc;
    }

    public String getApeSoc() {
        return apeSoc;
    }

    public void setApeSoc(String apeSoc) {
        this.apeSoc = apeSoc;
    }
}
