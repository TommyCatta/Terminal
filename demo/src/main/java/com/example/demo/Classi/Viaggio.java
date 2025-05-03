package com.example.demo.Classi;

import java.sql.Date;

public class Viaggio
{
    private int id;
    private int idNave;
    private int idPortoPartenza;
    private int idPortoArrivo;
    private Date dataPartenza;
    private Date dataArrivo;
    private String portoPartenza;
    private String portoArrivo;
    private String nomeNave;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIdNave()
    {
        return idNave;
    }

    public void setIdNave(int idNave)
    {
        this.idNave = idNave;
    }

    public int getIdPortoPartenza()
    {
        return idPortoPartenza;
    }

    public void setIdPortoPartenza(int idPortoPartenza)
    {
        this.idPortoPartenza = idPortoPartenza;
    }

    public int getIdPortoArrivo()
    {
        return idPortoArrivo;
    }

    public void setIdPortoArrivo(int idPortoArrivo)
    {
        this.idPortoArrivo = idPortoArrivo;
    }

    public Date getDataPartenza()
    {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza)
    {
        this.dataPartenza = dataPartenza;
    }

    public Date getDataArrivo()
    {
        return dataArrivo;
    }

    public void setDataArrivo(Date dataArrivo)
    {
        this.dataArrivo = dataArrivo;
    }

    public String getPortoPartenza()
    {
        return portoPartenza;
    }

    public void setPortoPartenza(String portoPartenza)
    {
        this.portoPartenza = portoPartenza;
    }

    public String getPortoArrivo()
    {
        return portoArrivo;
    }

    public void setPortoArrivo(String portoArrivo)
    {
        this.portoArrivo = portoArrivo;
    }

    public String getNomeNave()
    {
        return nomeNave;
    }

    public void setNomeNave(String nomeNave)
    {
        this.nomeNave = nomeNave;
    }
}
