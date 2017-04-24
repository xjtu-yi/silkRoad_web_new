package org.silkroad.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.silkroad.utility.MongoConn;
import org.silkroad.utility.MySQLConn;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
* @author : wuke
* @date   : 2017年4月24日上午11:31:36
* Title   : CompleteUserViewedResInfo
* Description : Complete the information of resource in user_viewed_company, user_viewed_country...
* 没有加入观看次数
* 没有考虑增量计算
*/
public class CompleteUserViewedResInfo {
	private static final String[] RESOURCE_TYPE =  {"company", "country", "pebook", "regulation", "uansr", "uebook"};
	private static final Connection MYSQLCONN = MySQLConn.getConn();
	
	public static void main(String[] args) {
		CompleteUserViewedResInfo.deleteOldData();
		
		long start = System.currentTimeMillis();
		CompleteUserViewedResInfo.completeAllKindsResInfo();
		long end = System.currentTimeMillis();
		System.out.println("Cost " + (end - start) / 1000 + " seconds!");
	}
	
	/**
	 * 
	 */
	public static void completeAllKindsResInfo() {
		for(String res_type : RESOURCE_TYPE)
			CompleteUserViewedResInfo.completeOneKindResInfo(res_type);
	}
	
	/**
	 * 
	 * @param res_type
	 */
	private static void completeOneKindResInfo(String res_type) {
		String collectionName = "user_viewed_" + res_type;
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		MongoCursor<Document> cursor = collection.find().iterator();
		
		Document doc = null;
		switch(res_type) {
		case "company":			
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completeCompanyInfo(doc);
			}
			break;
		case "country":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completeCountryInfo(doc);
			}
			break;
		case "pebook":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completePebookInfo(doc);
			}
			break;
		case "regulation":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completeRegulationInfo(doc);
			}
			break;
		case "uansr":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completeUansrInfo(doc);
			}
			break;
		case "uebook":
			while(cursor.hasNext()) {
				doc = cursor.next();
				CompleteUserViewedResInfo.completeUebookInfo(doc);
			}
		}
	}
	
	/**
	 * Complete the information of the company
	 * @param doc
	 * {
	 *     "_id" : ObjectId("58fc5ed3d1ad214d60a13fef"),
	 *     "user_id" : "908",
	 *     "companySet" : {
	 *         "0014776367997089a447e3ef7874d82b5015f3316b4b829000" : NumberInt("1"),
	 *         "001477636798918cc8557669e3d4c27a5f03f3b09b16724000" : NumberInt("1"),
	 *         "0014776367988093e75c709322d4576a176fa7467b647b5000" : NumberInt("1")
	 *     }
	 * }
	 */
	private static void completeCompanyInfo(Document doc) {
		// get user_id and companySet
		String user_id = doc.getString("user_id");
		Document companySet = (Document) doc.get("companySet");
		
		// get all res_id
		//Set<String> strs = companySet.keySet();
		Set<Entry<String, Object>> entrySet = companySet.entrySet();
		
		String tableName = "companies_repo";

		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
			    sql = "select name, summary, location, country, doc_url, rankings from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
				while(rs.next()) {
					tem.append("name", rs.getString(1));
					tem.append("summary", rs.getString(2));
					tem.append("location", rs.getString(3));
					tem.append("country", rs.getString(4));
					tem.append("doc_url", rs.getString(5));
					tem.append("rankings", rs.getString(6));
				}
				
				// store the Document
				CompleteUserViewedResInfo.storeOneDoc("company", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the country
	 * @param doc
	 */
	private static void completeCountryInfo(Document doc) {
		// get user_id and countrySet
		String user_id = doc.getString("user_id");
		Document countrySet = (Document) doc.get("countrySet");
		
		// get all res_id
		//Set<String> strs = countrySet.keySet();
		Set<Entry<String, Object>> entrySet = countrySet.entrySet();
		
		String tableName = "countries_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
				sql = "select title, abstract, publish_place, pub_date, doc_url, subjects from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
				while(rs.next()) {
					tem.append("title", rs.getString(1));
					tem.append("abstract", rs.getString(2));
					tem.append("publish_place", rs.getString(3));
					tem.append("pub_date", rs.getString(4));
					tem.append("doc_url", rs.getString(5));
					tem.append("subjects", rs.getString(6));
				}
				
				// store the Document
				CompleteUserViewedResInfo.storeOneDoc("country", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the pebook
	 * @param doc
	 */
	private static void completePebookInfo(Document doc) {
		// get user_id and pebookSet
		String user_id = doc.getString("user_id");
		Document pebookSet = (Document) doc.get("pebookSet");
		
		// get all res_id
		//Set<String> strs = pebookSet.keySet();
		Set<Entry<String, Object>> entrySet = pebookSet.entrySet();
		
		String tableName = "pebook_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
				sql = "select title, doi, date_publication, summary, text_url from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
				while(rs.next()) {
					tem.append("title", rs.getString(1));
					tem.append("doi", rs.getString(2));
					tem.append("date_publication", rs.getString(3));
					tem.append("summary", rs.getString(4));
					tem.append("text_url", rs.getString(5));
				}
				
				// store the Document
				CompleteUserViewedResInfo.storeOneDoc("pebook", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the regulation
	 * @param doc
	 */
	private static void completeRegulationInfo(Document doc) {		
		// get user_id and regulationSet
		String user_id = doc.getString("user_id");
		Document regulationSet = (Document) doc.get("regulationSet");
		
		// get all res_id
		//Set<String> strs = regulationSet.keySet();
		Set<Entry<String, Object>> entrySet = regulationSet.entrySet();
		
		String tableName = "regulation_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
				sql = "select doi, url, title, abstract, scope, purpose, date_publication, full_text from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
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
				CompleteUserViewedResInfo.storeOneDoc("regulation", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the uansr
	 * @param doc
	 */
	private static void completeUansrInfo(Document doc) {		
		// get user_id and uansrSet
		String user_id = doc.getString("user_id");
		Document uansrSet = (Document) doc.get("uansrSet");
		
		// get all res_id
		//Set<String> strs = uansrSet.keySet();
		Set<Entry<String, Object>> entrySet = uansrSet.entrySet();

		String tableName = "uansr_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
				sql = "select author, title, year, doi, url, summary from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
				while(rs.next()) {
					tem.append("author", rs.getString(1));
					tem.append("title", rs.getString(2));
					tem.append("year", rs.getString(3));
					tem.append("doi", rs.getString(4));
					tem.append("url", rs.getString(5));
					tem.append("summary", rs.getString(6));
				}
				
				// store the Document
				CompleteUserViewedResInfo.storeOneDoc("uansr", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Complete the information of the uebook
	 * @param doc
	 */
	private static void completeUebookInfo(Document doc) {
		// get user_id and uebookSet
		String user_id = doc.getString("user_id");
		Document uebookSet = (Document) doc.get("uebookSet");
		
		// get all res_id
		//Set<String> strs = uebookSet.keySet();
		Set<Entry<String, Object>> entrySet = uebookSet.entrySet();
		
		String tableName = "uebook_repo";
		
		String sql = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Document tem = null;
		String res_id = null;
		Object times = null;
		try {
			for(Entry<String, Object> entry : entrySet) {
			//for(String res_id : strs) {
				res_id = entry.getKey();
				times = entry.getValue();
				
				sql = "select author, title, year, url, img_url, summary, nation from " + tableName + " where id=\"" + res_id + "\"";
				statement = MYSQLCONN.prepareStatement(sql);
				rs = statement.executeQuery(sql);
				
				// complete one Document
				tem = new Document();
				tem.append("user_id", user_id);
				tem.append("res_id", res_id);
				tem.append("times", times);
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
				CompleteUserViewedResInfo.storeOneDoc("uebook", tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param res_type
	 * @param doc
	 */
	private static void storeOneDoc(String res_type, Document doc) {
		String collectionName = "user_viewed_" + res_type + "_complete";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		collection.insertOne(doc);
	}
	
	/**
	 * delete the old data
	 */
	private static void deleteOldData() {
		String[] collectionNames = {"user_viewed_company_complete", "user_viewed_country_complete",
				"user_viewed_pebook_complete", "user_viewed_regulation_complete",
				"user_viewed_uansr_complete", "user_viewed_uebook_complete"};
		
		MongoCollection<Document> collection = null;
		for(String collectionName : collectionNames) {
		 collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		 collection.drop();
		}
	}
}