package com.example.demo.DAO;

import java.sql.*;

public class ClienteDAO
{
    public int verificaCredenziali(String username, String password)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/terminalmarittimo", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM cliente WHERE username = ? AND password = ?"))
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
}
