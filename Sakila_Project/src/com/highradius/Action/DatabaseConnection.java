package com.highradius.Action;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection initialize() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila",
					"root", "root");
		} catch (Exception e) {
			System.out.println("Error Connecting to Database " + e.getMessage());
		}
		return con;
	}
}
