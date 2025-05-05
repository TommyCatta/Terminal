package com.example.demo.Controllers;

import com.example.demo.DAO.*;
import com.example.demo.Classi.Utente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
//Consente qualsiasi origine di sviluppo in sostanza
@CrossOrigin(origins = "*")
public class ControllerLogin {

    private final AutistaDAO autistaDAO = new AutistaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FornitoreDAO fornitoreDAO = new FornitoreDAO();
    private final AdminDAO adminDAO = new AdminDAO();

    @GetMapping
    public Utente login(@RequestParam String username, @RequestParam String password) {
        int id;

        id = autistaDAO.verificaCredenziali(username, password);
        if (id != -1) return new Utente(id, username, "Autista");

        id = clienteDAO.verificaCredenziali(username, password);
        if (id != -1) return new Utente(id, username, "Cliente");

        id = fornitoreDAO.verificaCredenziali(username, password);
        if (id != -1) return new Utente(id, username, "Fornitore");

        id = adminDAO.verificaCredenziali(username, password);
        if (id != -1) return new Utente(id, username, "Admin");

        return new Utente(-1, "null", "null");
    }
}

