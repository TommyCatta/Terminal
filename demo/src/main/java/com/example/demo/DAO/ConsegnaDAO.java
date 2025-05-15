package com.example.demo.DAO;

import com.example.demo.Classi.Consegna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsegnaDAO
{
    private final String url = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String user = "root";
    private final String password = "";

    public boolean inserisciConsegna(Consegna consegna)
    {
        String sql = "INSERT INTO consegna (IDBuono, Targa, IDAutista, PesoConsegnatoKg, DataConsegna) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, consegna.getIdBuono());
            ps.setString(2, consegna.getTarga());
            ps.setInt(3, consegna.getIdAutista());
            ps.setDouble(4, consegna.getPesoConsegnatoKg());
            ps.setDate(5, Date.valueOf(consegna.getDataConsegna()));
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<Consegna> getConsegneByAutista(int idAutista)
    {
        List<Consegna> lista = new ArrayList<>();
        String sql = "SELECT ID, IDBuono, Targa, IDAutista, PesoConsegnatoKg, DataConsegna FROM consegna WHERE IDAutista = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, idAutista);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Consegna c = new Consegna();
                c.setId(rs.getInt("ID"));
                c.setIdBuono(rs.getInt("IDBuono"));
                c.setTargaCamion(rs.getString("Targa"));
                c.setIdAutista(rs.getInt("IDAutista"));
                c.setPesoConsegnatoKg(rs.getDouble("PesoConsegnatoKg"));
                c.setDataConsegna(rs.getDate("DataConsegna").toLocalDate());
                lista.add(c);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }
}
