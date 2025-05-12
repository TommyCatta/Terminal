package com.example.demo.Controllers;

import com.example.demo.Classi.PolizzaCarico;
import com.example.demo.DAO.PolizzaCaricoDAO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornitore")
@CrossOrigin(origins = "http://localhost")
public class FornitoreController
{
    private PolizzaCaricoDAO polizzaCaricoDAO = new PolizzaCaricoDAO();

    @GetMapping("/polizze")
    public List<PolizzaCarico> getPolizzeByFornitore(@RequestParam int idFornitore)
    {
        return polizzaCaricoDAO.getPolizzeByFornitore(idFornitore);
    }

    @PostMapping("/polizze")
    public String inserisciPolizza(@RequestBody PolizzaCarico polizza)
    {
        boolean success = polizzaCaricoDAO.inserisciPolizza(polizza);

        if (success)
        {
            return "Polizza inserita correttamente";
        }
        else
        {
            return "Errore durante l'inserimento della polizza";
        }
    }
}
