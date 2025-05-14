package com.example.demo.Controllers;

import com.example.demo.DAO.ViaggioDAO;
import com.example.demo.Classi.Viaggio;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController
{
    private ViaggioDAO viaggioDAO = new ViaggioDAO();

    @GetMapping("/disponibili")
    public List<Viaggio> getViaggiDisponibili()
    {
        return viaggioDAO.getTuttiIViaggi();
    }
}