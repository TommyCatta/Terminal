package com.example.demo.Controllers;

import com.example.demo.Classi.Viaggio;
import com.example.demo.Classi.Nave;
import com.example.demo.Classi.Porto;
import com.example.demo.Classi.Fornitore;
import com.example.demo.Classi.PolizzaCarico;

import com.example.demo.DAO.ViaggioDAO;
import com.example.demo.DAO.NaveDAO;
import com.example.demo.DAO.PortoDAO;
import com.example.demo.DAO.FornitoreDAO;
import com.example.demo.DAO.PolizzaCaricoDAO;
    
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController 
{
    private final ViaggioDAO viaggioDAO = new ViaggioDAO();
    private final NaveDAO naveDAO = new NaveDAO();
    private final PortoDAO portoDAO = new PortoDAO();
    private final FornitoreDAO fornitoreDAO = new FornitoreDAO();
    private final PolizzaCaricoDAO polizzaCaricoDAO = new PolizzaCaricoDAO();

    @GetMapping("/viaggi")
    public List<Viaggio> getViaggi() 
    {
        return viaggioDAO.getTuttiIViaggi();
    }

    @PostMapping("/viaggi")
    public boolean inserisciViaggio(@RequestBody Viaggio v) 
    {
        return viaggioDAO.inserisciViaggio(v);
    }

    @DeleteMapping("/viaggi/{id}")
    public boolean cancellaViaggio(@PathVariable int id) 
    {
        return viaggioDAO.cancellaViaggio(id);
    }

    @GetMapping("/navi")
    public List<Nave> getNavi() 
    {
        return naveDAO.getTutteLeNavi();
    }

    @GetMapping("/porti")
    public List<Porto> getPorti() 
    {
        return portoDAO.getTuttiIPorti();
    }

    @GetMapping("/fornitori")
    public List<Fornitore> getFornitori() 
    {
        return fornitoreDAO.getTuttiIFornitori();
    }

    @GetMapping("/polizze")
    public List<PolizzaCarico> getPolizze() 
    {
        return polizzaCaricoDAO.getTutteLePolizze();
    }
}
