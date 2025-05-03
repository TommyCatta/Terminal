package com.example.demo.Classi;

import java.time.LocalDate;

public class Consegna
{
    private int id;
    private int idBuono;
    private String targa;
    private int idAutista;
    private double pesoConsegnatoKg;
    private LocalDate dataConsegna;

    public Consegna(int id, int idBuono, String targa, int idAutista, double pesoConsegnatoKg, LocalDate dataConsegna)
    {
        this.id = id;
        this.idBuono = idBuono;
        this.targa = targa;
        this.idAutista = idAutista;
        this.pesoConsegnatoKg = pesoConsegnatoKg;
        this.dataConsegna = dataConsegna;
    }

    public Consegna()
    {
    }

    public int getId()
    {
        return id;
    }

    public int getIdBuono()
    {
        return idBuono;
    }

    public String getTarga()
    {
        return targa;
    }

    public int getIdAutista()
    {
        return idAutista;
    }

    public double getPesoConsegnatoKg()
    {
        return pesoConsegnatoKg;
    }

    public LocalDate getDataConsegna()
    {
        return dataConsegna;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setIdBuono(int idBuono)
    {
        this.idBuono = idBuono;
    }

    public void setTargaCamion(String targa)
    {
        this.targa = targa;
    }

    public void setIdAutista(int idAutista)
    {
        this.idAutista = idAutista;
    }

    public void setPesoConsegnatoKg(double pesoConsegnatoKg)
    {
        this.pesoConsegnatoKg = pesoConsegnatoKg;
    }

    public void setDataConsegna(LocalDate dataConsegna)
    {
        this.dataConsegna = dataConsegna;
    }
}
