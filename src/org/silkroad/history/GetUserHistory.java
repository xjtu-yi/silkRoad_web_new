package org.silkroad.history;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

/**
* @author : wuke
* @date   : 2017年4月25日下午9:52:11
* Title   : GetUserHistory
* Description : using user_id and res_type, select user history from MongoDB
*/
public class GetUserHistory {

	public static void main(String[] args) {
		
	}
	
	public static List<Document> getHistory(String user_id, String res_type) {
		List<Document> docsArr = null;
		
		// System.out.println("GetUserHistory " + user_id);
		
		// Query from MongoDB
		String collectionName = "user_viewed_" + res_type + "_complete";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("times", -1); // descending by "times"

		docsArr = collection.find(eq("user_id", user_id)).sort(sort).limit(5).into(new ArrayList<Document>());
		
		return docsArr;
	}
}
