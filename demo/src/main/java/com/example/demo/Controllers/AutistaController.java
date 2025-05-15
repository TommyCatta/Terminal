package com.example.demo.Controllers;

import com.example.demo.Classi.BuonoConsegna;
import com.example.demo.Classi.Camion;
import com.example.demo.Classi.Consegna;
import com.example.demo.DAO.BuonoConsegnaDAO;
import com.example.demo.DAO.CamionDAO;
import com.example.demo.DAO.ConsegnaDAO;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/autista")
@CrossOrigin(origins = "http://localhost")
public class AutistaController
{
    private ConsegnaDAO consegnaDAO = new ConsegnaDAO();
    private CamionDAO camionDAO       = new CamionDAO();
    private BuonoConsegnaDAO buonoDAO = new BuonoConsegnaDAO();

    @PostMapping("/consegna")
    public String registraConsegna(@RequestBody Consegna consegna)
    {
        boolean success = consegnaDAO.inserisciConsegna(consegna);
        return success ? "Consegna registrata correttamente"
                       : "Errore durante la registrazione della consegna";
    }

    @GetMapping("/camion")
    public List<Camion> getCamion() {
        return camionDAO.getTuttiICamion();
    }

    @GetMapping("/buoni")
    public List<BuonoConsegna> getBuoniByAutista(@RequestParam int idAutista) {
        return buonoDAO.getBuoniByAutista(idAutista);
    }

    // NUOVO: restituisce le consegne già registrate
    @GetMapping("/consegne")
    public List<Consegna> getConsegneByAutista(@RequestParam int idAutista) {
        return consegnaDAO.getConsegneByAutista(idAutista);
    }
}
