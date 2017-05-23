package org.silkroad.history;

import org.bson.Document;
import org.silkroad.utility.MongoConn;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

/**
* @author : wuke
* @date   : 20170423 11:04:41
* Title   : CalUserViewedTimes
* Description : Calculate user's view tiems of different type resources, store in silkRoad.user_viewed_times
* an example
{
	"_id" : ObjectId("58fc5ed3d1ad214d60a141ae"),
	"user_id" : "100",
	"companySet" : {
		"001477636798286a9c328e5a86b4084ae61dd168375405b000" : NumberInt("1")
	}
} ->
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
		CalUserViewTimes.calAllUsersViewedTimes();
	}
	
	/**
	 * 
	 */
	public static void calAllUsersViewedTimes() {
		MongoCollection<Document> collection = MongoConn.getMongoCollection("user_viewed_times");
		collection.drop(); // delete the old data
		collection = MongoConn.getMongoCollection("user_viewed_times");
		
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
		
		MongoCollection<Document> collection = MongoConn.getMongoCollection(collectionName);
		
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
	private static int calUserViewedTimesOne(String user_id, String res_type) {		
		String collectionName = "user_viewed_" + res_type;
		int times = 0; // default 0
		
		MongoCollection<Document> collection = MongoConn.getMongoCollection(collectionName);
		
		Document doc = collection.find(eq("user_id", user_id)).first();
		if(doc != null) {
		    Document resTimes = (Document) doc.get(res_type + "Set"); // Object -> Document
		    times = resTimes.size();
		}
		
		return times;
	}
}