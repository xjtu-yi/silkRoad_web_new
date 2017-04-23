package org.silkroad.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: wuke 
 * @date  : 2016年11月25日 下午8:53:20
 * Title  : MySQLConn
 * Description : 
 */
public class MySQLConn {
	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/silkroadresource_new";
		String username = "root";
		String password = "1234";
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			
			// System.out.println("Successfully connect to MySQL silkroadresources!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
