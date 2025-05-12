package com.example.demo.DAO;

import com.example.demo.Classi.Viaggio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViaggioDAO {

    private final String url      = "jdbc:mysql://localhost:3306/terminalmarittimo";
    private final String user     = "root";
    private final String password = "";

    public List<Viaggio> getTuttiIViaggi() {
        List<Viaggio> viaggi = new ArrayList<>();
        String sql = """
            SELECT v.ID, v.IDNave, v.IDPortoPartenza, v.IDPortoArrivo, v.DataPartenza, v.DataArrivo,
                   p1.nome AS portoPartenza, p2.nome AS portoArrivo, n.nome AS nomeNave
              FROM viaggio v
              JOIN porto p1 ON v.IDPortoPartenza = p1.ID
              JOIN porto p2 ON v.IDPortoArrivo   = p2.ID
              JOIN nave  n  ON v.IDNave          = n.ID
        """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Viaggio v = new Viaggio();
                v.setId(rs.getInt("ID"));
                v.setIdNave(rs.getInt("IDNave"));
                v.setIdPortoPartenza(rs.getInt("IDPortoPartenza"));
                v.setIdPortoArrivo(rs.getInt("IDPortoArrivo"));
                v.setDataPartenza(rs.getDate("DataPartenza"));
                v.setDataArrivo(rs.getDate("DataArrivo"));
                v.setPortoPartenza(rs.getString("portoPartenza"));
                v.setPortoArrivo(rs.getString("portoArrivo"));
                v.setNomeNave(rs.getString("nomeNave"));
                viaggi.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viaggi;
    }

    public boolean inserisciViaggio(Viaggio v) {
        String sql = "INSERT INTO viaggio (IDNave, IDPortoPartenza, IDPortoArrivo, DataPartenza, DataArrivo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, v.getIdNave());
            stmt.setInt(2, v.getIdPortoPartenza());
            stmt.setInt(3, v.getIdPortoArrivo());
            stmt.setDate(4, v.getDataPartenza());
            stmt.setDate(5, v.getDataArrivo());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancellaViaggio(int id) {
        String sql = "DELETE FROM viaggio WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
