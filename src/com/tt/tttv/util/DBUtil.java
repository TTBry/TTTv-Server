package com.tt.tttv.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	public static Connection getConnection() throws Exception{
		Properties properties = new Properties();
		InputStream is = DBUtil.class.getResourceAsStream("jdbc.properties");
		properties.load(is);
		
		String driverName = properties.getProperty("driverName");
		String dbUrl = properties.getProperty("dbUrl");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		
		return conn;
	}
	
	public static void close(Connection conn, Statement statement, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
