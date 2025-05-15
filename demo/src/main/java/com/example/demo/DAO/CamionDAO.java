package com.example.demo.DAO;

import com.example.demo.Classi.Camion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionDAO
{
    private final String connectionString = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String dbUser = "root";
    private final String dbPass = "";

    public List<Camion> getTuttiICamion()
    {
        List<Camion> camionList = new ArrayList<>();
        String sql = "SELECT * FROM camion";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Camion c = new Camion(
                    rs.getString("Targa"),
                    rs.getString("Marca"),
                    rs.getString("Modello")
                );
                camionList.add(c);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return camionList;
    }
}
