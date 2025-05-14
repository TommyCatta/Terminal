package com.example.demo.Controllers;

import com.example.demo.Classi.BuonoConsegna;
import com.example.demo.Classi.PolizzaCarico;
import com.example.demo.DAO.BuonoConsegnaDAO;
import com.example.demo.DAO.PolizzaCaricoDAO;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost")
public class ClienteController
{
    private final PolizzaCaricoDAO polizzaDAO = new PolizzaCaricoDAO();
    private final BuonoConsegnaDAO buonoDAO = new BuonoConsegnaDAO();

    @GetMapping("/polizzeArrivate")
    public List<PolizzaCarico> getPolizzeArrivate()
    {
        return polizzaDAO.getPolizzeArrivate();
    }

    @PostMapping("/richiedi-buono")
    public boolean richiediBuono(
        @RequestParam int idCliente,
        @RequestParam int idPolizza,
        @RequestParam BigDecimal peso,
        @RequestParam String dataEmissione)
    {
        BuonoConsegna buono = new BuonoConsegna(
            0,
            idPolizza,
            idCliente,
            peso,
            LocalDate.parse(dataEmissione)
        );

        return buonoDAO.inserisciBuono(buono);
    }

    @GetMapping("/buoni-consegna")
    public List<BuonoConsegna> getBuoniConsegna(@RequestParam int idCliente)
    {
        return buonoDAO.getBuoniByCliente(idCliente);
    }
}
