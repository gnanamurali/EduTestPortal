package com.EduTestPortal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String url="jdbc:mysql://localhost:3306/edutestportal";
	private static final String username="root";
	private static final String password="root";
	
	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("Database connection established");	
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Mysql driver not found :"+e.getMessage());
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();	
		}
		catch (Exception e) {
			System.out.println("Some error occured");
			e.printStackTrace();
		}
		return conn;
	}

}
