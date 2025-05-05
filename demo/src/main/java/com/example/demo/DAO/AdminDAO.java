package com.example.demo.DAO;

import java.sql.*;

public class AdminDAO
{
    public int verificaCredenziali(String username, String password)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/terminalmarittimo", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM admin WHERE username = ? AND password = ?"))
        {
            System.out.println("Verifica credenziali - username: " + username + ", password: " + password);
    
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
