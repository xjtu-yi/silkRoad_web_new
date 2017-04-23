package org.silkroad.abandon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.silkroad.utility.MongoConn;

/**
 * userViewedResources 
 * [
 *   { "user_id":"admin", 
 *     "res_list":[{"res_id":001480498142667f892532c57b04eb98ecabda967df271d000},{"res_id"}...] 
 *   },
 *   ...
 * ]
 * 要修改，未完成，20170417
 */
public class GenUserViewedResources {
	
	public static void main(String[] args) {
	}
	
	/**
	 * Read all the logs from dbName.collectionName, return a ArrayList of Documents
	 * @return logsDocuments List<Document> 
	 */
	static List<Document> readLogsFromMongodb(String dbName, String collectionName) {
		List<Document> logsDocuments = new ArrayList<Document>();
		
		// connect to Mongodb, get collection mooc.logs
		MongoCollection<Document> collection = MongoConn.getMongoCollection(dbName, collectionName);
			
		MongoCursor<Document> cursor = collection.find().iterator();
		Document doc = new Document();
		try {
			while(cursor.hasNext()) {
				doc = cursor.next();
				
				// just keep the "user_id" and "res_id"
				Document temp = new Document();
				temp.append("user_id", doc.getString("user_id"));
				temp.append("res_id", doc.getString("res_id"));
				logsDocuments.add(temp);
			}
		} finally {
			cursor.close();
		}
			
	    System.out.println("Successfully read the all the logs!");
		
		return logsDocuments;
	}
	
	/**
	 * Read one day's logs from dbName.collectionName, return a ArrayList of Documents
	 * @param date
	 * @return logsDocuments List<Document> 
	 */
	static List<Document> readOneDayLogsFromMongodb(String dbName, String collectionName, String date) {
		List<Document> logsDocuments = new ArrayList<Document>();
		
		// connect to Mongodb, get collection mooc.logs
		MongoCollection<Document> collection = MongoConn.getMongoCollection(dbName, collectionName);
			
		Pattern pattern = Pattern.compile("^" + date + ".*$"); // 左匹配
		BasicDBObject query = new BasicDBObject();
        query.put("click_time",pattern);
        
		MongoCursor<Document> cursor = collection.find(query).iterator();
	    
		Document doc = new Document();
		try {
			while(cursor.hasNext()) {
				doc = cursor.next();
				
				// just keep the "user_id" and "res_id"
				Document temp = new Document();
				temp.append("user_id", doc.getString("user_id"));
				temp.append("res_id", doc.getString("res_id"));
				logsDocuments.add(temp);
			}
		} finally {
			cursor.close();
		}
		System.out.println(logsDocuments.size());
	    System.out.println("Successfully read " + date + " logs!");
		
		return logsDocuments;
	}
	
	/**
	 * process one Document list, store the result into "Map<String, TreeSet<String>> userResourcesMap"
	 * @param logsDocuments 
	 * @return userResourcesMap Map<String, TreeSet<String>>
	 */
    static Map<String, TreeSet<String>> processLogsDocuments(List<Document> logsDocuments) {
    	Map<String, TreeSet<String>> userResourcesMap = new HashMap<String, TreeSet<String>>();
    	
    	TreeSet<String> resourceSet = null;
    	
    	String user_id = "";
    	String res_id = "";
    	for(Document doc : logsDocuments) {
    		user_id = (String) doc.get("user_id");
    		res_id = (String) doc.get("res_id");
    		
    		if((user_id != null) && (res_id != null)) {
				if (userResourcesMap.containsKey(user_id)) { // old user
					// update the course set
					resourceSet = userResourcesMap.get(user_id);
					resourceSet.add(res_id);
					
					userResourcesMap.put(user_id, resourceSet);				
				} else { // new user
					resourceSet = new TreeSet<String>();
					resourceSet.add(res_id);
					
					userResourcesMap.put(user_id, resourceSet);
				}
    		}
    	}
    	
    	System.out.println("Successfully processLogsDocuments()!");
    	return userResourcesMap;
    }
    
	/**
	 * read history records, user_viewd_resources collection from MongoDB
	 * store into one HashMap historyUserResourcesMap
	 * notice : TreeSet<String> has changed to ArrayList<String>
	 * @return historyUserResourcesMap Map<String, ArrayList<String>>
	 */
	static Map<String, ArrayList<String>> readHistoryUserResourcesFromMongodb(String dbName, String collectionName) {
		Map<String, ArrayList<String>> historyUserResourcesMap = new HashMap<String, ArrayList<String>>();
		
		// connect to MongoDB, get collection dbName.collectionName
		MongoCollection<Document> collection = MongoConn.getMongoCollection(dbName, collectionName);
		
		MongoCursor<Document> cursor = collection.find().iterator();
		Document doc = new Document();
		try {
			while(cursor.hasNext()) {
				doc = cursor.next();
				historyUserResourcesMap.put(doc.getString("user_id"), (ArrayList<String>) doc.get("resourceSet"));
			}
		} finally {
			cursor.close();
		}
		
    	System.out.println("Successfully read the old records of user_viewd_resources!");
		return historyUserResourcesMap;
	}
	
	/**
	 * merge the new and old records of user_viewd_resources
	 * @param historyUserResourcesMap
	 * @param userResourcesMap
	 */
	static Map<String, ArrayList<String>> mergeUserResources(Map<String, ArrayList<String>> historyUserResourcesMap, 
			Map<String, TreeSet<String>> userResourcesMap) {
		Map<String, ArrayList<String>> newUserResourcesMap = null;
		
		ArrayList<String> arrayList = null;
		TreeSet<String> treeSet = null;
		String key = "";
		// iterate "Map<String, TreeSet<String>> userResourcesMap"
		for(Map.Entry<String, TreeSet<String>> entry : userResourcesMap.entrySet()) {
			key = entry.getKey();
			
			if(historyUserResourcesMap.containsKey(key)) { // old user
				// first merge the ArrayList into TreeSet, then put the result TreeSet into the ArrayList 
				arrayList = historyUserResourcesMap.get(key);
				treeSet = entry.getValue();
				
				treeSet.addAll(arrayList);
				
				arrayList.clear();
				arrayList.addAll(treeSet);
				
				historyUserResourcesMap.put(key, arrayList);
			} else { // new user
				// directly store into ArrayList
				treeSet = entry.getValue();
				
				arrayList = new ArrayList<String>();
				arrayList.addAll(treeSet);
				
				historyUserResourcesMap.put(key, arrayList);
			}
		}
		
    	System.out.println("Successfully merge the old and new records of user-learned-courses!");
    	newUserResourcesMap = historyUserResourcesMap;
		return newUserResourcesMap;
	}
	
	/**
	 * store new user_viewed_resources records into MongoDB
	 * @param userResourcesMap Map<String, ArrayList<String>>
	 */
	static void storeUserResourcesIntoMongodb(String dbName, String collectionName, Map<String, ArrayList<String>> userResourcesMap) {
		MongoCollection<Document> collection = MongoConn.getMongoCollection(dbName, collectionName);
		
		collection.drop(); // delete the old data
		
		collection = MongoConn.getMongoCollection(dbName, collectionName);
		
		Document doc = null;
		for(Map.Entry<String, ArrayList<String>> entry : userResourcesMap.entrySet()) {
			doc = new Document(); // need new Document object every time, because ObjectId
			doc.append("userId", entry.getKey());
			doc.append("resourcesSet", entry.getValue());
			
			collection.insertOne(doc);
		}
		
    	System.out.println("Successfully store the merged records of user_viewed_resources!");
	}
}
