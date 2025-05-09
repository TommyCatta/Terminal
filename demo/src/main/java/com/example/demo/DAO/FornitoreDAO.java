package com.example.demo.DAO;

import com.example.demo.Classi.Fornitore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornitoreDAO
{
    public int verificaCredenziali(String username, String password)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/terminalmarittimo", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM fornitore WHERE username = ? AND password = ?"))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt("ID");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Fornitore> getTuttiIFornitori()
    {
        List<Fornitore> fornitori = new ArrayList<>();

        String query = "SELECT * FROM fornitore";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/terminalmarittimo", "root", "");
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Fornitore f = new Fornitore(
                    rs.getInt("ID"),
                    rs.getString("Nome"),
                    rs.getString("Cognome"),
                    rs.getString("Telefono"),
                    rs.getString("Indirizzo"),
                    rs.getString("Email"),
                    rs.getString("Username"),
                    rs.getString("Password")
                );

                fornitori.add(f);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return fornitori;
    }
}
