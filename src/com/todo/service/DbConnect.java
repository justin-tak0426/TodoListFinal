package com.todo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	private static Connection conn = null;
	
	public static void closeConnection() {
		if(conn != null) {
			try { 
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + "todolist.db");
				System.out.println("todolist.db의 데이터를 불러왔습니다.\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
