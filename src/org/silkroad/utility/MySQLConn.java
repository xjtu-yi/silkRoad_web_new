package org.silkroad.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: wuke 
 * @date  : 20161125 20:53:20
 * Title  : MySQLConn
 * Description : 
 */
public class MySQLConn {
	public static void main(String[] args) {
		MySQLConn.getConn();
	}
	
	public static Connection getConn() {
		Connection conn = null;
		String driver = GetProperty.getPropertyByName("mysql_driver");
		String url = GetProperty.getPropertyByName("mysql_url");
		String username = GetProperty.getPropertyByName("mysql_username");
		String password = GetProperty.getPropertyByName("mysql_password");
		
		try {		    
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
