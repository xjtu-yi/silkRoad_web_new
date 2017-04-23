package org.silkroad.abandon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.silkroad.processing.logs.ClassifyLogsByRole;
import org.silkroad.utility.MongoConn;

/**
 * @author: wuke 
 * @date  :  
 * Title  : ExtractSearchKeyWords
 * Description : Extract the search logs, in which "action" is "search", one example is shown as below:
 * {
	"_id" : "0014779049561086254b035f0fc47689a6562ce15153e96000",
	"explorer" : "Mozilla/5.0 (Windows NT 6.1",
	"action" : "search",
	"click_time" : "2016-10-31 16:59:35",
	"keywords" : "system",
	"res_id" : "",
	"user_id" : "admin",
	"operation" : " WOW64",
	"item_id" : "00147787837563752350c504f854aec9623f36bf73ce412000",
	"res_type" : "",
	"ip" : "113.140.16.30"
   } 
 */

public class ExtractSearchKeyWords {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String databaseName = "logs";
		
		// for first time using, to process all of the logs
		/*String collectionName = "items";
		extractManyDayLogs(databaseName, collectionName);*/
		
		// process yesterday's logs
		String dateStr = "";
		dateStr = GetYesterdayDate.getYesterDayDate();
		String collectionName = "logs";
		extractOneDay(dateStr, databaseName, collectionName);
	}
	
	/**
	 * extract many days' logs by calling the function extractOneDay()
	 * @param databaseName "logs"
	 * @param collectionName "items", an example is shown as below
	 * {"_id" : "00147787837563752350c504f854aec9623f36bf73ce412000","date" : "2016-10-31"}
	 */
	public static void extractManyDayLogs(String databaseName, String collectionName) {
		MongoCollection<Document> logsCollection = MongoConn.getMongoCollection(databaseName, collectionName);
		
		MongoCursor<Document> cursor = logsCollection.find().iterator();
		String dateStr = "";
		while(cursor.hasNext()) {
			dateStr = cursor.next().getString("date");
			// "logs" & "logs"
			extractOneDay(dateStr, databaseName, "logs");
		}
	}
	
	/**
	 * 
	 * @param dateStr
	 * @param databaseName
	 * @param collectionName
	 */
	public static void extractOneDay(String dateStr, String databaseName, String collectionName) {
		// databaseName = "logs" & collectionName = "logs";
		MongoCollection<Document> logsCollection = MongoConn.getMongoCollection(databaseName, collectionName);
		
		String regex = "^" + dateStr + ".*$";
		Pattern pattern = Pattern.compile(regex);
		
		BasicDBObject query = new BasicDBObject();
		query.put("click_time", pattern);
		MongoCursor<Document> cursor = logsCollection.find(query).iterator();
		
		List<Document> searchWordsDocuments = new ArrayList<Document>();
		
		Document temp = null;
		String operation = "";
		try {
			while(cursor.hasNext()) {
				temp = cursor.next();
				operation = temp.getString("action");
				
				if(operation.equalsIgnoreCase("search")) {
					searchWordsDocuments.add(temp);
				}
			}
		} finally {
			cursor.close();
		}
		
		// "logs" & "searchLogs"
		ClassifyLogsByRole.storeMongo("searchLogs", searchWordsDocuments);
	}
}
