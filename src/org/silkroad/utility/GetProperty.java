package org.silkroad.utility;

import java.util.Properties;

/**
* @author : wuke
* @date   : 20170523 17:17:11
* Title   : GetProperty
* Description : 
*/
public class GetProperty {
	public static void main(String[] args) {
		System.out.println(GetProperty.getPropertyByName("logs_path"));
	}
	
	/**
	 * Get Property by name.
	 * @param name
	 * @return
	 */
	public static String getPropertyByName(String name) {
		String result = "";
		
		Properties properties = new Properties();
		try {
			properties.load(GetProperty.class.getResource("/config.properties").openStream());
			result = properties.getProperty(name);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
