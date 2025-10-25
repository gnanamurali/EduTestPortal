package com.EduTestPortal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Read values from environment variables (Render injects these)
            String url = System.getenv("DB_URL");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

            // Fallback in case environment variables are not set (for local testing)
            if (url == null || username == null || password == null) {
                url = "jdbc:mysql://mysql-edutestportal.alwaysdata.net:3306/edutestportal_db";
                username = "437194";
                password = "edutest123";
                System.out.println("[DBConnection] Using local fallback credentials");
            } else {
                System.out.println("[DBConnection] Using Render environment variables");
            }

            // Establish the connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("[DBConnection] Database connection established successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("[DBConnection] MySQL driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("[DBConnection] Database connection failed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[DBConnection] Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
