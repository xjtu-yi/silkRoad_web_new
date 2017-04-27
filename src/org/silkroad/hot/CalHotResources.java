package org.silkroad.hot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
* @author : wuke
* @date   : 2017年4月22日下午5:35:45
* Title   : CalHotResources
* Description : 
*/
public class CalHotResources {
	private static final String[] ROLES =  {"student", "engineer", "scholar", "other"};
	private static final String[] RESOURCE_TYPE =  {"company", "country", "pebook", "regulation", "uansr", "uebook"};
	
	public static void main(String[] args) {
		CalHotResources.calAllRolesHotRes();
	}
	
	/**
	 * 
	 */
	public static void calAllRolesHotRes() {
		for(int i = 0; i < ROLES.length; i++)
			CalHotResources.calOneRole(ROLES[i]);
	}
	
	/**
	 * 
	 * @param role
	 */
	public static void calOneRole(String role) {
		String collectionName = role + "_logs";
		MongoCollection<Document> logs = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		MongoCursor<Document> cursor = logs.find().iterator();
		
		// HashMap, store res_id and click_times
		Map<String, Integer> company_times = new HashMap<String, Integer> ();
		Map<String, Integer> country_times = new HashMap<String, Integer> ();
		Map<String, Integer> pebook_times = new HashMap<String, Integer> ();
		Map<String, Integer> regulation_times = new HashMap<String, Integer> ();
		Map<String, Integer> uansr_times = new HashMap<String, Integer> ();
		Map<String, Integer> uebook_times = new HashMap<String, Integer> ();
				
		Document doc = null;
		String res_type = null;
		while(cursor.hasNext()) { // iterate through the logs
			doc = cursor.next();
			res_type = doc.getString("res_type");
			
			switch(res_type) {
			case "Fortune Global 500":
				CalHotResources.addOneRecord(company_times, doc);
				break;
			case "Nation Conditions":
				CalHotResources.addOneRecord(country_times, doc);
				break;
			case "pebook":
				CalHotResources.addOneRecord(pebook_times, doc);
				break;
			case "ieee":
				CalHotResources.addOneRecord(regulation_times, doc);
				break;
			case "Education Of Science":
				CalHotResources.addOneRecord(uansr_times, doc);
				break;
			case "History And Culture":
				CalHotResources.addOneRecord(uebook_times, doc);
				break;
			}
		}
		
		// Store the six HashMap into MongoDB
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[0], company_times);
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[1], country_times);
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[2], pebook_times);
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[3], regulation_times);
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[4], uansr_times);
		CalHotResources.storeHotResources(role, RESOURCE_TYPE[5], uebook_times);
	}
	
	/**
	 * 
	 * @param records
	 * @param doc
	 */
	public static void addOneRecord(Map<String, Integer> records, Document doc) {
		String res_id = doc.getString("res_id");
		
		if(records.containsKey(res_id))
			records.put(res_id, records.get(res_id) + 1);
		else
			records.put(res_id, 1);
	}
	
	/**
	 * 
	 * @param role
	 * @param res_type
	 * @param records
	 */
	public static void storeHotResources(String role, String res_type, Map<String, Integer> records) {
		String collectionName = role + "_" + res_type + "_" + "times";
		MongoCollection<Document> logs = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		logs.drop(); // delete the old data
		logs = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		Document doc = null;
		for(Entry<String, Integer> entry: records.entrySet()) {
			doc = new Document();
			doc.append("res_id", entry.getKey());
			doc.append("times", entry.getValue());
			
			logs.insertOne(doc);
		}
	}
}
 