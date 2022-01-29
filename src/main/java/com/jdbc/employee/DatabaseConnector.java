package com.jdbc.employee;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DatabaseConnector {
	private static Connection connection;

	private DatabaseConnector() {

	}

	public static Connection getconnection() {
		if (connection == null) {
			String jdbcurl = "jdbc:mysql://localhost:3306/payroll_service";
			String username = "root";
			String pwd = "root";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("JDBC Driver is Loaded");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			listDbDrivers();

			try {
				connection = DriverManager.getConnection(jdbcurl, username, pwd);
				System.out.println("Connection Establised SuccessFully");
			} catch (SQLException e) {
				System.out.println("Connection Establise Failed");
				e.printStackTrace();
			}
		}
		return connection;
	}

	private static void listDbDrivers() {

		Enumeration<Driver> listDrivers = DriverManager.getDrivers();
		while (listDrivers.hasMoreElements()) {
			Driver d = listDrivers.nextElement();
			System.out.println(d.getClass().getName());
		}
	}

	public static void main(String[] args) {
		getconnection();
		listDbDrivers();
	}
}
