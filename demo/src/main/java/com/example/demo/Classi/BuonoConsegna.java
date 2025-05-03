package com.example.demo.Classi;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BuonoConsegna
{
    private int id;
    private int idPolizza;
    private int idCliente;
    private BigDecimal pesoRitirabileKg;
    private LocalDate dataEmissione;

    public BuonoConsegna(int id, int idPolizza, int idCliente, BigDecimal pesoRitirabileKg, LocalDate dataEmissione)
    {
        this.id = id;
        this.idPolizza = idPolizza;
        this.idCliente = idCliente;
        this.pesoRitirabileKg = pesoRitirabileKg;
        this.dataEmissione = dataEmissione;
    }

    public int getId()
    {
        return id;
    }

    public int getIdPolizza()
    {
        return idPolizza;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public BigDecimal getPesoRitirabileKg()
    {
        return pesoRitirabileKg;
    }

    public LocalDate getDataEmissione()
    {
        return dataEmissione;
    }
}
