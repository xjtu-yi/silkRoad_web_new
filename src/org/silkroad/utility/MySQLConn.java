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
	public static void main(String[] args) {
		MySQLConn.getConn();
	}
	
	public static Connection getConn() {
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/silkroadresource_new?characterEncoding=utf8&useSSL=false&autoReconnect=true";
		String username = "root";
		String password = "1234";
		
		/*url = "jdbc:mysql://personalize-mysql:3306/silkroad_personalize?characterEncoding=utf8&useSSL=false";
		username = "personalize";
		password = "P0$sW0rD$Sp";*/
		try {		    
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
