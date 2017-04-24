package org.silkroad.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bson.Document;
import org.silkroad.utility.MySQLConn;

/**
* @author : wuke
* @date   : 2017年4月24日上午11:31:36
* Title   : CompleteUserViewedResInfo
* Description : 
*/
public class CompleteUserViewedResInfo {

	/**
	 * Complete the information of the resource
	 * @param doc
	 * @param res_type
	 * @return
	 */
	private static Document getCompleteInfo(Document doc, String res_type) {
		if (res_type == "company")
			res_type = "companies";
		else if (res_type == "country")
			res_type = "countries";

		String tableName = res_type + "_repo";

		Connection conn = MySQLConn.getConn();

		String res_id = doc.getString("res_id");

		String sql = null;
		
		switch(tableName) {
		case "companies_repo":
			break;
		case "countries_repo":
			break;
		case "pebook_repo":
			break;
		case "regulation_repo":
			break;
		case "uansr_repo":
		    break;
		case "uebook_repo":
			break;
		}
		
		sql = "select name, summary, location, country, doc_url, rankings from " + tableName + " where id=" + res_id;
		sql = "select title, abstract, publish_place, pub_date, doc_url, subjects from " + tableName + " where id=" + res_id;
		sql = "select title, doi, date_publication, summary, text_url from " + tableName + " where id=" + res_id;
		sql = "select doi, url, title, abstract, scope, purpose, date_publication, full_text from " + tableName + " where id=" + res_id;
		sql = "select author, titile, year, doi, url, summary from " + tableName + " where id=" + res_id;
		sql = "select author, title, year, url, img_url, summary, nation from " + tableName + " where id=" + res_id;
		
		ResultSet rs = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return doc;
	}
}
