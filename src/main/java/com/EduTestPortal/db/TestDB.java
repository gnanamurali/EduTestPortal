package com.EduTestPortal.db;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("🎉 Connection test passed!");
        } else {
            System.out.println("⚠️ Connection test failed.");
        }
    }
}
