package com.example.demo.Classi;

import java.math.BigDecimal;

public class PolizzaCarico
{
    private int id;
    private int idViaggio;
    private int idPortoCarico;
    private int idPortoDestinazione;
    private String tipologia;
    private BigDecimal pesoKg;
    private int idFornitore;
    private int giorniFranchigia;
    private BigDecimal tariffaGiornaliera;
    private boolean arrivato;

    // Campi per informazioni aggiuntive (non in DB)
    private String nomeNave;
    private String nomePortoCarico;
    private String nomePortoDestinazione;
    private String nomeFornitore;

    public PolizzaCarico(
        int id,
        int idViaggio,
        int idPortoCarico,
        int idPortoDestinazione,
        String tipologia,
        BigDecimal pesoKg,
        int idFornitore,
        int giorniFranchigia,
        BigDecimal tariffaGiornaliera,
        boolean arrivato)
    {
        this.id = id;
        this.idViaggio = idViaggio;
        this.idPortoCarico = idPortoCarico;
        this.idPortoDestinazione = idPortoDestinazione;
        this.tipologia = tipologia;
        this.pesoKg = pesoKg;
        this.idFornitore = idFornitore;
        this.giorniFranchigia = giorniFranchigia;
        this.tariffaGiornaliera = tariffaGiornaliera;
        this.arrivato = arrivato;
    }

    // GETTERS

    public int getId()
    {
        return id;
    }

    public int getIdViaggio()
    {
        return idViaggio;
    }

    public int getIdPortoCarico()
    {
        return idPortoCarico;
    }

    public int getIdPortoDestinazione()
    {
        return idPortoDestinazione;
    }

    public String getTipologia()
    {
        return tipologia;
    }

    public BigDecimal getPesoKg()
    {
        return pesoKg;
    }

    public int getIdFornitore()
    {
        return idFornitore;
    }

    public int getGiorniFranchigia()
    {
        return giorniFranchigia;
    }

    public BigDecimal getTariffaGiornaliera()
    {
        return tariffaGiornaliera;
    }

    public boolean isArrivato()
    {
        return arrivato;
    }

    public String getNomeNave()
    {
        return nomeNave;
    }

    public String getNomePortoCarico()
    {
        return nomePortoCarico;
    }

    public String getNomePortoDestinazione()
    {
        return nomePortoDestinazione;
    }

    public String getNomeFornitore()
    {
        return nomeFornitore;
    }

    // SETTERS per campi aggiuntivi

    public void setNomeNave(String nomeNave)
    {
        this.nomeNave = nomeNave;
    }

    public void setNomePortoCarico(String nomePortoCarico)
    {
        this.nomePortoCarico = nomePortoCarico;
    }

    public void setNomePortoDestinazione(String nomePortoDestinazione)
    {
        this.nomePortoDestinazione = nomePortoDestinazione;
    }

    public void setNomeFornitore(String nomeFornitore)
    {
        this.nomeFornitore = nomeFornitore;
    }
}
