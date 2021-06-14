package com.mvc.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection con = null;

		try {
			String url = "jdbc:mysql://3.36.186.85:3306/PROJECT";
			String id = "jihyunkim90";
			String pass = "1234";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con=DriverManager.getConnection(url,id,pass);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
