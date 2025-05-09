package com.example.demo.DAO;

import com.example.demo.Classi.Porto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortoDAO {
    private final String url = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String user = "root";
    private final String password = "";

    public List<Porto> getTuttiIPorti() {
        List<Porto> porti = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM porto")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Porto p = new Porto();
                p.setId(rs.getInt("ID"));
                p.setNome(rs.getString("Nome"));
                p.setNazione(rs.getString("Nazione"));
                porti.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return porti;
    }
}
