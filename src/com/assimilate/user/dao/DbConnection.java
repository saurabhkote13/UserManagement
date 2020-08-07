package com.assimilate.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sys";
			Connection conn = DriverManager.getConnection(url, "root", "root");
			System.out.println("Connection successful..... " + conn);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Connection getConnectionFromDriver(String driver, String dbUrl, 
			String user, String password) {
		try {
			Class.forName(driver);
			String url = dbUrl;
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection successful..... " + conn);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
