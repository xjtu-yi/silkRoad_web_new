package org.silkroad.history;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
* @author : wuke
* @date   : 2017年4月22日下午9:03:06
* Title   : GenUserResources
* Description : Generate user viewed resources, store in silkRoad.user_viewed_company, 
*     silkRoad.user_viewed_country...
* An example in user_viewed_company,
{
	"_id" : ObjectId("58fc5ed3d1ad214d60a13fef"),
	"user_id" : "908",
	"companySet" : {
		"0014776367997089a447e3ef7874d82b5015f3316b4b829000" : NumberInt("1"),
		"001477636798918cc8557669e3d4c27a5f03f3b09b16724000" : NumberInt("1"),
		"0014776367988093e75c709322d4576a176fa7467b647b5000" : NumberInt("1")
	}
}
*/
public class GenUserViewedRes {

	public static void main(String[] args) {
		GenUserViewedRes.readLogs();
	}
	
	/**
	 * 增量计算，待补充
	 */
	static void readHistoryRecords() {
		
	}
	
	/**
	 * 增量计算，待补充
	 */
	static void readOneLogs() {
		
	}
	
	public static void readLogs() {
		HashMap<String, HashMap<String, Integer>> company = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, HashMap<String, Integer>> country = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, HashMap<String, Integer>> pebook = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, HashMap<String, Integer>> regulation = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, HashMap<String, Integer>> uansr = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, HashMap<String, Integer>> uebook = new HashMap<String, HashMap<String, Integer>>();
		
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", "logs");
		MongoCursor<Document> cursor = collection.find().iterator();
		
		Document doc = null;
		String res_id = null;
		String res_type = null;
		String user_id = null;
		//int i = 0;
		while(cursor.hasNext()) {
			doc = cursor.next();
			
			res_id = doc.getString("res_id");
			res_type = doc.getString("res_type");
			user_id = doc.getInteger("user_id").toString();
			
			
		    //System.out.println(i++);
			
			switch(res_type) {
			case "Fortune Global 500":
				addOneRecord(res_id, user_id, company);
				break;
			case "Nation Conditions":
				addOneRecord(res_id, user_id, country);
				break;
			case "pebook":
				addOneRecord(res_id, user_id, pebook);
				break;
			case "ieee":
				addOneRecord(res_id, user_id, regulation);
				break;
			case "Education Of Science":
				addOneRecord(res_id, user_id, uansr);
				break;
			case "History And Culture":
				addOneRecord(res_id, user_id, uebook);
				break;
			}
		}
		
		// Store into MongoDB		
		GenUserViewedRes.storeUserViewedResources(company, "company");
		GenUserViewedRes.storeUserViewedResources(country, "country");
		GenUserViewedRes.storeUserViewedResources(pebook, "pebook");
		GenUserViewedRes.storeUserViewedResources(regulation, "regulation");
		GenUserViewedRes.storeUserViewedResources(uansr, "uansr");
		GenUserViewedRes.storeUserViewedResources(uebook, "uebook");
	}
	
	/**
	 * 
	 * @param res_id
	 * @param user_id
	 * @param records
	 */
	private static void addOneRecord(String res_id, String user_id, 
			HashMap<String, HashMap<String, Integer>> records) {
		HashMap<String, Integer> tem;
		if(records.containsKey(user_id)) { // old user
			tem = records.get(user_id);
			if(tem.containsKey(res_id)) { // viewd resource
				tem.put(res_id, tem.get(res_id) + 1); // view times plus one				
			} else {
				tem.put(res_id, 1); // add new resource
			}
			
			records.put(user_id, tem); // cover the old record
		} else { // new user
			tem = new HashMap<String, Integer>();
			tem.put(res_id, 1);
			
			records.put(user_id, tem); // insert the new record
		}
	}
	
	/**
	 * 
	 * @param records
	 * @param res_type
	 */
	private static void storeUserViewedResources(HashMap<String, HashMap<String, Integer>> records, String res_type) {
		String collectionName = "user_viewed_" + res_type;
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
	    collection.drop(); // delete the old data
	    collection = MongoConn.getMongoCollection("silkRoad", collectionName);
	    
		String user_id = null;
		HashMap<String, Integer> tem = null;
		Document doc = null;
		for(Entry<String, HashMap<String, Integer>> entry : records.entrySet()) {
			user_id = entry.getKey();
			tem = entry.getValue();
			
			doc = new Document();
			doc.append("user_id", user_id);
			doc.append(res_type + "Set", tem);
			
			collection.insertOne(doc);
		}
	}
}
