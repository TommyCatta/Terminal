package com.example.demo.DAO;

import com.example.demo.Classi.PolizzaCarico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolizzaCaricoDAO
{
    private final String connectionString = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String dbUser = "root";
    private final String dbPass = "";

    public List<PolizzaCarico> getPolizzeByFornitore(int idFornitore)
    {
        List<PolizzaCarico> polizze = new ArrayList<>();

        String query = "SELECT * FROM polizzacarico WHERE IDFornitore = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, idFornitore);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                PolizzaCarico p = new PolizzaCarico(
                    rs.getInt("ID"),
                    rs.getInt("IDViaggio"),
                    rs.getInt("IDPortoCarico"),
                    rs.getInt("IDPortoDestinazione"),
                    rs.getString("Tipologia"),
                    rs.getBigDecimal("PesoKg"),
                    rs.getInt("IDFornitore"),
                    rs.getInt("GiorniFranchigia"),
                    rs.getBigDecimal("TariffaGiornaliera"),
                    rs.getBoolean("Arrivato")
                );
                
                polizze.add(p);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return polizze;
    }

    public boolean inserisciPolizza(PolizzaCarico polizza)
    {
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass))
        {
            int idPortoCarico = 0;
            int idPortoDestinazione = 0;
    
            String queryViaggio = "SELECT IDPortoPartenza, IDPortoArrivo FROM viaggio WHERE ID = ?";
            try (PreparedStatement stmtViaggio = conn.prepareStatement(queryViaggio))
            {
                stmtViaggio.setInt(1, polizza.getIdViaggio());
                ResultSet rs = stmtViaggio.executeQuery();
                if (rs.next())
                {
                    idPortoCarico = rs.getInt("IDPortoPartenza");
                    idPortoDestinazione = rs.getInt("IDPortoArrivo");
                }
                else
                {
                    return false;
                }
            }
    
            String queryInserimento = "INSERT INTO polizzacarico (IDViaggio, IDPortoCarico, IDPortoDestinazione, Tipologia, PesoKg, IDFornitore, GiorniFranchigia, TariffaGiornaliera, Arrivato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            try (PreparedStatement stmt = conn.prepareStatement(queryInserimento))
            {
                stmt.setInt(1, polizza.getIdViaggio());
                stmt.setInt(2, idPortoCarico);
                stmt.setInt(3, idPortoDestinazione);
                stmt.setString(4, polizza.getTipologia());
                stmt.setBigDecimal(5, polizza.getPesoKg());
                stmt.setInt(6, polizza.getIdFornitore());
                stmt.setInt(7, polizza.getGiorniFranchigia());
                stmt.setBigDecimal(8, polizza.getTariffaGiornaliera());
                stmt.setBoolean(9, false);
    
                int righeInserite = stmt.executeUpdate();
                return righeInserite > 0;
            }
    
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        return false;
    }
    

    public List<PolizzaCarico> getTutteLePolizze()
    {
        List<PolizzaCarico> polizze = new ArrayList<>();

        String query = """
            SELECT pc.ID, pc.IDViaggio, pc.IDPortoCarico, pc.IDPortoDestinazione,
                   pc.Tipologia, pc.PesoKg, pc.IDFornitore, pc.GiorniFranchigia, pc.TariffaGiornaliera,
                   pc.Arrivato,
                   v.ID AS idViaggio, n.Nome AS nomeNave,
                   p1.Nome AS nomePortoCarico, p2.Nome AS nomePortoDestinazione,
                   f.Nome AS nomeFornitore, f.Cognome AS cognomeFornitore
            FROM polizzacarico pc
            JOIN viaggio v ON pc.IDViaggio = v.ID
            JOIN nave n ON v.IDNave = n.ID
            JOIN porto p1 ON pc.IDPortoCarico = p1.ID
            JOIN porto p2 ON pc.IDPortoDestinazione = p2.ID
            JOIN fornitore f ON pc.IDFornitore = f.ID
        """;

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                PolizzaCarico p = new PolizzaCarico(
                    rs.getInt("ID"),
                    rs.getInt("IDViaggio"),
                    rs.getInt("IDPortoCarico"),
                    rs.getInt("IDPortoDestinazione"),
                    rs.getString("Tipologia"),
                    rs.getBigDecimal("PesoKg"),
                    rs.getInt("IDFornitore"),
                    rs.getInt("GiorniFranchigia"),
                    rs.getBigDecimal("TariffaGiornaliera"),
                    rs.getBoolean("Arrivato")
                );
                

                p.setNomeNave(rs.getString("nomeNave"));
                p.setNomePortoCarico(rs.getString("nomePortoCarico"));
                p.setNomePortoDestinazione(rs.getString("nomePortoDestinazione"));
                p.setNomeFornitore(rs.getString("nomeFornitore") + " " + rs.getString("cognomeFornitore"));

                polizze.add(p);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return polizze;
    }


    public List<PolizzaCarico> getPolizzeArrivate()
    {
        List<PolizzaCarico> polizze = new ArrayList<>();
    
        String query = """
            SELECT pc.ID, pc.IDViaggio, pc.IDPortoCarico, pc.IDPortoDestinazione,
                   pc.Tipologia, pc.PesoKg, pc.IDFornitore, pc.GiorniFranchigia, pc.TariffaGiornaliera,
                   pc.Arrivato,
                   v.ID AS idViaggio, n.Nome AS nomeNave,
                   p1.Nome AS nomePortoCarico, p2.Nome AS nomePortoDestinazione,
                   f.Nome AS nomeFornitore, f.Cognome AS cognomeFornitore
            FROM polizzacarico pc
            JOIN viaggio v ON pc.IDViaggio = v.ID
            JOIN nave n ON v.IDNave = n.ID
            JOIN porto p1 ON pc.IDPortoCarico = p1.ID
            JOIN porto p2 ON pc.IDPortoDestinazione = p2.ID
            JOIN fornitore f ON pc.IDFornitore = f.ID
            WHERE pc.Arrivato = 1
        """;
    
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                PolizzaCarico p = new PolizzaCarico(
                    rs.getInt("ID"),
                    rs.getInt("IDViaggio"),
                    rs.getInt("IDPortoCarico"),
                    rs.getInt("IDPortoDestinazione"),
                    rs.getString("Tipologia"),
                    rs.getBigDecimal("PesoKg"),
                    rs.getInt("IDFornitore"),
                    rs.getInt("GiorniFranchigia"),
                    rs.getBigDecimal("TariffaGiornaliera"),
                    rs.getBoolean("Arrivato")
                );
    
                p.setNomeNave(rs.getString("nomeNave"));
                p.setNomePortoCarico(rs.getString("nomePortoCarico"));
                p.setNomePortoDestinazione(rs.getString("nomePortoDestinazione"));
                p.setNomeFornitore(rs.getString("nomeFornitore") + " " + rs.getString("cognomeFornitore"));
    
                polizze.add(p);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        return polizze;
    }

    public boolean deleteByViaggio(int idViaggio) {
        String sql = "DELETE FROM polizzacarico WHERE IDViaggio = ?";
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idViaggio);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}