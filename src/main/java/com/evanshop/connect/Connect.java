package com.evanshop.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	
	private static Connection conn;
	
	public Connection getConn() {
		return this.conn;
	}
	
	public static void conn() {
		//tai lop trinh dieu khien jdbc
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		//tao chuoi ket noi den mysql
		String url = "jdbc:mysql://localhost:3310/evanshop";
		String user = "root";
		String password ="123456";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			if(conn != null) {
				System.out.println("Kết nối thành công!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		} 
	}
}
