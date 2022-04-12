package com.example.projetj2e.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {
    public static Connection getConnexion() throws SQLException, ClassNotFoundException {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "SYSTEM");
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
}