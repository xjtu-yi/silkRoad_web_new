package org.silkroad.hot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.silkroad.utility.MongoConn;
import org.silkroad.utility.MySQLConn;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
* @author : wuke
* @date   : 2017年4月24日上午11:31:36
* Title   : CompleteHotResInfo
* Description : Complete the information of hot resources
*/
public class CompleteHotResInfo {
	private static final String[] RESOURCE_TYPE =  {"company", "country", "pebook", "regulation", "uansr", "uebook"};
	private static final String[] ROLES =  {"student", "engineer", "scholar", "other"};
	private static final Connection MYSQLCONN = MySQLConn.getConn();
	
	public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
		CompleteHotResInfo.completeAllHotResInfo();
		
		long end = System.currentTimeMillis();
		System.out.println("Cost " + (end - start) / 1000 + " seconds!");
	}
	
	/**
	 * 
	 */
	public static void completeAllHotResInfo() {
		CompleteHotResInfo.deleteOldData();
		
		for(String role : ROLES)
		    for(String res_type : RESOURCE_TYPE)
			    CompleteHotResInfo.completeOneRoleOneTypeResInfo(role, res_type);
	}
	
	/**
	 * 
	 * @param role
	 * @param res_type
	 */
	private static void completeOneRoleOneTypeResInfo(String role, String res_type) {
		//String collectionName = "user_viewed_" + res_type;
		String collectionName = role + "_" + res_type + "_times";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("times", -1); // -1, descending
		
		MongoCursor<Document> cursor = collection.find().sort(sort).limit(5).iterator();
		
		Document doc = null;
		switch(res_type) {
		case "company":			
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completeCompanyInfo(role, doc);
			}
			break;
		case "country":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completeCountryInfo(role, doc);
			}
			break;
		case "pebook":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completePebookInfo(role, doc);
			}
			break;
		case "regulation":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completeRegulationInfo(role, doc);
			}
			break;
		case "uansr":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completeUansrInfo(role, doc);
			}
			break;
		case "uebook":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteHotResInfo.completeUebookInfo(role, doc);
			}
		}
	}
	
	/**
	 * Complete the information of the company, according to the res_id
	 * @param role
	 * @param doc
	 * {
	 *     "_id" : ObjectId("58fc7a43d1ad2109849473e0"),
	 *     "res_id" : "0014776367995891185b75ad3544633a33d96db14f98c15000",
	 *     "times" : NumberInt("5")
	 * }
	 */
	private static void completeCompanyInfo(String role, Document doc) {
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");
		
		String tableName = "companies_repo";

		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {				
			    sql = "select name, summary, location, country, doc_url, rankings from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "company");
				while(rs.next()) {
					tem.append("name", rs.getString(1));
					tem.append("summary", rs.getString(2));
					tem.append("location", rs.getString(3));
					tem.append("country", rs.getString(4));
					tem.append("doc_url", rs.getString(5));
					tem.append("rankings", rs.getString(6));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the country
	 * @param role
	 * @param doc
	 */
	private static void completeCountryInfo(String role, Document doc) {
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");
		
		String tableName = "countries_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {			
				sql = "select title, abstract, publish_place, pub_date, doc_url, subjects from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "country");
				while(rs.next()) {
					tem.append("title", rs.getString(1));
					tem.append("abstract", rs.getString(2));
					tem.append("publish_place", rs.getString(3));
					tem.append("pub_date", rs.getString(4));
					tem.append("doc_url", rs.getString(5));
					tem.append("subjects", rs.getString(6));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the pebook
	 * @param role
	 * @param doc
	 */
	private static void completePebookInfo(String role, Document doc) {
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");
		
		String tableName = "pebook_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {
			    sql = "select title, doi, date_publication, summary, text_url from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "pebook");
				while(rs.next()) {
					tem.append("title", rs.getString(1));
					tem.append("doi", rs.getString(2));
					tem.append("date_publication", rs.getString(3));
					tem.append("summary", rs.getString(4));
					tem.append("text_url", rs.getString(5));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the regulation
	 * @param role
	 * @param doc
	 */
	private static void completeRegulationInfo(String role, Document doc) {		
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");
		
		String tableName = "regulation_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {
			    sql = "select doi, url, title, abstract, scope, purpose, date_publication, full_text from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "regulation");
				while(rs.next()) {
					tem.append("doi", rs.getString(1));
					tem.append("url", rs.getString(2));
					tem.append("title", rs.getString(3));
					tem.append("abstract", rs.getString(4));
					tem.append("scope", rs.getString(5));
					tem.append("purpose", rs.getString(6));
					tem.append("date_publication", rs.getString(7));
					tem.append("full_text", rs.getString(8));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the uansr
	 * @param role
	 * @param doc
	 */
	private static void completeUansrInfo(String role, Document doc) {		
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");

		String tableName = "uansr_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {
			    sql = "select author, title, year, doi, url, summary from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "uansr");
				while(rs.next()) {
					tem.append("author", rs.getString(1));
					tem.append("title", rs.getString(2));
					tem.append("year", rs.getString(3));
					tem.append("doi", rs.getString(4));
					tem.append("url", rs.getString(5));
					tem.append("summary", rs.getString(6));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the uebook
	 * @param role
	 * @param doc
	 */
	private static void completeUebookInfo(String role, Document doc) {
		// get res_id and times
		String res_id = doc.getString("res_id");
		Integer times = doc.getInteger("times");
		
		String tableName = "uebook_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		try {
			    sql = "select author, title, year, url, img_url, summary, nation from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				
				tem.append("res_id", res_id);
				tem.append("times", times);
				tem.append("res_type", "uebook");
				while(rs.next()) {
					tem.append("author", rs.getString(1));
					tem.append("titile", rs.getString(2));
					tem.append("year", rs.getString(3));
					tem.append("url", rs.getString(4));
					tem.append("img_url", rs.getString(5));
					tem.append("summary", rs.getString(6));
					tem.append("nation", rs.getString(7));
				}
				
				// store the Document
				CompleteHotResInfo.storeOneDoc(role, tem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param role
	 * @param doc
	 */
	private static void storeOneDoc(String role, Document doc) {
		String collectionName = role + "_hot_complete";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		collection.insertOne(doc);
	}
	
	/**
	 * delete the old data
	 */
	private static void deleteOldData() {
		String collectionName = null;
		MongoCollection<Document> collection = null;
		for(String role : ROLES) {
			collectionName = role + "_hot_complete";
			collection = MongoConn.getMongoCollection("silkRoad", collectionName);
			collection.drop();
		}
	}
}