package com.example.demo.Classi;

public class Camion
{
    private String targa;
    private String marca;
    private String modello;

    public Camion(String targa, String marca, String modello)
    {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
    }

    public String getTarga()
    {
        return targa;
    }

    public String getMarca()
    {
        return marca;
    }

    public String getModello()
    {
        return modello;
    }
}
