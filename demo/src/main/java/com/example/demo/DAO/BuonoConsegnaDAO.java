package com.example.demo.DAO;

import com.example.demo.Classi.BuonoConsegna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuonoConsegnaDAO
{
    private final String connectionString = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String dbUser = "root";
    private final String dbPass = "";

    public boolean inserisciBuono(BuonoConsegna buono)
    {
        String sqlInserisci = "INSERT INTO buonoconsegna (IDCliente, IDPolizza, PesoRitirabileKg, DataEmissione) VALUES (?, ?, ?, ?)";
        String sqlAggiornaPeso = "UPDATE polizzacarico SET PesoKg = PesoKg - ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass))
        {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtInserisci = conn.prepareStatement(sqlInserisci);
                 PreparedStatement stmtAggiorna = conn.prepareStatement(sqlAggiornaPeso))
            {
                stmtInserisci.setInt(1, buono.getIdCliente());
                stmtInserisci.setInt(2, buono.getIdPolizza());
                stmtInserisci.setBigDecimal(3, buono.getPesoRitirabileKg());
                stmtInserisci.setDate(4, Date.valueOf(buono.getDataEmissione()));

                int righeInserite = stmtInserisci.executeUpdate();

                if (righeInserite == 0)
                {
                    conn.rollback();
                    return false;
                }

                stmtAggiorna.setBigDecimal(1, buono.getPesoRitirabileKg());
                stmtAggiorna.setInt(2, buono.getIdPolizza());

                int righeAggiornate = stmtAggiorna.executeUpdate();

                if (righeAggiornate == 0)
                {
                    conn.rollback();
                    return false;
                }

                conn.commit();
                return true;
            }
            catch (Exception e)
            {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
            finally
            {
                conn.setAutoCommit(true);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public List<BuonoConsegna> getBuoniByCliente(int idCliente)
    {
        List<BuonoConsegna> lista = new ArrayList<>();

        String sql = "SELECT * FROM buonoconsegna WHERE IDCliente = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                BuonoConsegna b = new BuonoConsegna(
                    rs.getInt("ID"),
                    rs.getInt("IDPolizza"),
                    rs.getInt("IDCliente"),
                    rs.getBigDecimal("PesoRitirabileKg"),
                    rs.getDate("DataEmissione").toLocalDate()
                );

                lista.add(b);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    public List<BuonoConsegna> getBuoniByAutista(int idAutista)
    {
        List<BuonoConsegna> lista = new ArrayList<>();
    
        // Modifica: usa LEFT JOIN oppure togli join per far comparire anche i buoni senza consegna
        // Se vuoi solo i buoni assegnati al cliente associato all'autista, devi passare idCliente, non idAutista.
        // Qui assumiamo di voler prendere tutti i buoni di consegna senza filtro su consegne (per semplicità).
    
        // Esempio 1: prendi tutti i buoni senza join con consegna
        String sql = "SELECT bc.ID, bc.IDPolizza, bc.IDCliente, bc.PesoRitirabileKg, bc.DataEmissione " +
                     "FROM buonoconsegna bc " +
                     "WHERE bc.IDCliente IN (SELECT IDCliente FROM cliente WHERE ID = (SELECT IDCliente FROM autista WHERE ID = ?))";
    
        // Se non esiste relazione diretta tra autista e cliente, questa query va adattata. 
        // Se invece vuoi tutti i buoni (indipendentemente dall'autista), puoi togliere filtro.
    
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idAutista);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next())
            {
                BuonoConsegna b = new BuonoConsegna(
                    rs.getInt("ID"),
                    rs.getInt("IDPolizza"),
                    rs.getInt("IDCliente"),
                    rs.getBigDecimal("PesoRitirabileKg"),
                    rs.getDate("DataEmissione").toLocalDate()
                );
    
                lista.add(b);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        return lista;
    }

    public boolean deleteByViaggio(int idViaggio) {
        String sqlFetch = "SELECT ID FROM polizzacarico WHERE IDViaggio = ?";
        String sqlDelete = "DELETE FROM buonoconsegna WHERE IDPolizza = ?";
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement psFetch = conn.prepareStatement(sqlFetch);
             PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {

            psFetch.setInt(1, idViaggio);
            ResultSet rs = psFetch.executeQuery();
            while (rs.next()) {
                int idPolizza = rs.getInt("ID");
                psDelete.setInt(1, idPolizza);
                psDelete.executeUpdate();
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
