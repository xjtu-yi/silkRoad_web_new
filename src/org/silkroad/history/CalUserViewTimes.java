package org.silkroad.history;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

/**
* @author : wuke
* @date   : 2017年4月23日上午11:04:41
* Title   : CalUserViewedTimes
* Description : Calculate user's view tiems of different type resources, store in silkRoad.user_viewed_times
* an example
{
	"_id" : ObjectId("58fc5fb0d1ad2143601beba2"),
	"user_id" : "100",
	"company" : NumberInt("1"),
	"country" : NumberInt("38"),
	"pebook" : NumberInt("0"),
	"regulation" : NumberInt("11"),
	"uansr" : NumberInt("49"),
	"uebook" : NumberInt("17")
}
*/
public class CalUserViewTimes {
	private static final String[] RESOURCE_TYPE =  {"company", "country", "pebook", "regulation", "uansr", "uebook"};
	
	public static void main(String[] args) {
		CalUserViewTimes.processAllUsers();
	}
	
	/**
	 * 
	 */
	public static void processAllUsers() {
		/*String collectionName = "user";
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);*/
		
		for(Integer i = 100; i < 1000; i++) {
			CalUserViewTimes.storeUserViewedTimes(i.toString());
		}
	}
	
	/**
	 * Store one user's view times into MongoDB silkRoad.userViewedTimes
	 * @param user_id
	 */
	private static void storeUserViewedTimes(String user_id) {
		String collectionName = "user_viewed_times";
		int[] timesArr = null;;
		
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		timesArr = CalUserViewTimes.calUserViewedTimesAll(user_id);
		
		Document doc = new Document();
		doc.append("user_id", user_id);
		for(int i = 0; i < 6; i++)
			doc.append(RESOURCE_TYPE[i], timesArr[i]);
		
		collection.insertOne(doc);
	}
	
	/**
	 * Give a user_id, calculate the user's view times of all the six type resources
	 * @param user_id
	 * @return timesArr
	 */
	private static int[] calUserViewedTimesAll(String user_id) {
		int[] timesArr = new int[6];
		
		for(int i = 0; i < 6; i++) {
			timesArr[i] = CalUserViewTimes.calUserViewedTimesOne(user_id, RESOURCE_TYPE[i]);
		}
		
		return timesArr;
	}
	
	/**
	 * Give a user_id and res_type, calculate the user's view times of the res_type's resources 
	 * @param user_id
	 * @param res_type
	 * @return times
	 */
	private static int calUserViewedTimesOne(String user_id, String res_type) {		
		String collectionName = "user_viewed_" + res_type;
		int times = 0; // default 0
		
		MongoCollection<Document> collection = MongoConn.getMongoCollection("silkRoad", collectionName);
		
		Document doc = collection.find(eq("user_id", user_id)).first();
		if(doc != null) {
		    Document resTimes = (Document) doc.get(res_type + "Set");
		    times = resTimes.size();
		}
		
		return times;
	}
}