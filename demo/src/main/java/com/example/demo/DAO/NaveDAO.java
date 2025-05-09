package com.example.demo.DAO;

import com.example.demo.Classi.Nave;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NaveDAO {
    private final String url = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String user = "root";
    private final String password = "";

    public List<Nave> getTutteLeNavi() {
        List<Nave> navi = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM nave")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nave n = new Nave();
                n.setId(rs.getInt("ID"));
                n.setNome(rs.getString("Nome"));
                navi.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return navi;
    }
}
