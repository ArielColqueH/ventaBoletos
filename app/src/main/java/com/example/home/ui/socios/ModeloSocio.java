package com.example.home.ui.socios;

public class ModeloSocio {
    private int idSoc;
    private String nomSoc;
    private String apeSoc;
    private int estSoc;

    public ModeloSocio(int idSoc, String nomSoc, String apeSoc,int estSoc) {
        this.idSoc = idSoc;
        this.nomSoc = nomSoc;
        this.apeSoc = apeSoc;
        this.estSoc = estSoc;
    }
    public ModeloSocio(int idSoc,int estSoc) {
        this.idSoc = idSoc;
        this.estSoc = estSoc;
    }
    public ModeloSocio(int idSoc, String nomSoc, String apeSoc) {
        this.idSoc = idSoc;
        this.nomSoc = nomSoc;
        this.apeSoc = apeSoc;
    }
    public ModeloSocio(String nomSoc, String apeSoc,int estSoc) {
        this.nomSoc = nomSoc;
        this.apeSoc = apeSoc;
        this.estSoc=estSoc;
    }
    public int getEstSoc() {
        return estSoc;
    }

    public void setEstSoc(int estSoc) {
        this.estSoc = estSoc;
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
