package com.example.demo.Classi;

public class Fornitore
{
    private int id;
    private String nome;
    private String cognome;
    private String telefono;
    private String indirizzo;
    private String email;
    private String username;
    private String password;

    public Fornitore(int id, String nome, String cognome, String telefono, String indirizzo, String email, String username, String password)
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getIndirizzo()
    {
        return indirizzo;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
