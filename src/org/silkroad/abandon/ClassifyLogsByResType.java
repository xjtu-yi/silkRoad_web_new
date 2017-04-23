package org.silkroad.abandon;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.silkroad.utility.MongoConn;

public class ClassifyLogsByResType {
	public static void main(String[] args) {
		String databaseName = "logs";
		
		// first time use, process all the logs
		/*String collectionName = "items";
		processManyDaysLogs(databaseName, collectionName);*/
		
		// process one day's logs
		String dateStr = "";
		dateStr = GetYesterdayDate.getYesterDayDate();
		String collectionName = "logs";
		classifyOneDayLogs(dateStr, databaseName, collectionName);
	}
	
	// "logs" & "items"
	static void processManyDaysLogs(String databaseName, String collectionName) {
		MongoCollection<Document> logsCollection = MongoConn.getMongoCollection(databaseName, collectionName);
		
		MongoCursor<Document> cursor = logsCollection.find().iterator();
		String dateStr = "";
		while(cursor.hasNext()) {
			dateStr = cursor.next().getString("date");
			// "logs" & "logs"
			classifyOneDayLogs(dateStr, databaseName, "logs");
		}
	}
	
	/**
	 * 
	 */
	static void classifyOneDayLogs(String dateStr, String databaseName, String collectionName) {
		// databaseName = "logs" & collectionName = "logs";
		MongoCollection<Document> logsCollection = MongoConn.getMongoCollection(databaseName, collectionName);
		
        /*
        Pattern pattern0 = Pattern.compile("^name$"); //ÍêÈ«Æ¥Åä    
        Pattern pattern1 = Pattern.compile("^.*name$");//ÓÒÆ¥Åä
        Pattern pattern2 = Pattern.compile("^name.*$"); //×óÆ¥Åä   
        Pattern pattern3 = Pattern.compile("^.*name.*$");//Ä£ºýÆ¥Åä
		Pattern pattern = Pattern.compile("^2016-10-31.*$");
		*/
		String regex = "^" + dateStr+ ".*$";
		Pattern pattern = Pattern.compile(regex);
		
		BasicDBObject query = new BasicDBObject();
		query.put("click_time", pattern);
		
		//MongoCursor<Document> cursor = logsCollection.find().iterator();
		MongoCursor<Document> cursor = logsCollection.find(query).iterator();
		
		String res_type = "";
		ArrayList<Document> newsDocuments = new ArrayList<Document>();
		ArrayList<Document> uansrDocuments = new ArrayList<Document>();
		ArrayList<Document> ebookDocuments = new ArrayList<Document>();
		ArrayList<Document> patentDocuments = new ArrayList<Document>();
		ArrayList<Document> dissertationDocuments = new ArrayList<Document>();
		ArrayList<Document> confDocuments = new ArrayList<Document>();
		ArrayList<Document> courseDocuments = new ArrayList<Document>();
		ArrayList<Document> journalDocuments = new ArrayList<Document>();
		ArrayList<Document> oaDocuments = new ArrayList<Document>();
		ArrayList<Document> companyDocuments = new ArrayList<Document>();
		ArrayList<Document> countryDocuments = new ArrayList<Document>();
		ArrayList<Document> symposiumDocuments = new ArrayList<Document>();
		ArrayList<Document> educationDocuments = new ArrayList<Document>();
		ArrayList<Document> historyDocuments = new ArrayList<Document>();
		ArrayList<Document> policyDocuments = new ArrayList<Document>();
		
		Document temp = null;
		try {
			while(cursor.hasNext()) {
				//System.out.println(cursor.next().toJson());
				temp = cursor.next();
				res_type = temp.getString("res_type");
				
				switch(res_type) {
				case "news":
					newsDocuments.add(temp);
					break;
				case "uansr":
					uansrDocuments.add(temp);
					break;
				case "ebook":
					ebookDocuments.add(temp);
					break;
				case "patent":
					patentDocuments.add(temp);
					break;
				case "dissertation":
					dissertationDocuments.add(temp);
					break;
				case "conf":
					confDocuments.add(temp);
					break;
				case "course":
					courseDocuments.add(temp);
					break;
				case "journal":
					journalDocuments.add(temp);
					break;
				case "oa":
					oaDocuments.add(temp);
					break;
				case "company":
					companyDocuments.add(temp);
					break;
				case "country":
					countryDocuments.add(temp);
					break;
				case "symposium":
					symposiumDocuments.add(temp);
					break;
				case "education":
					educationDocuments.add(temp);
					break;
				case "history":
					historyDocuments.add(temp);
					break;
				case "policy":
					policyDocuments.add(temp);
					break;
				}
			}
		} finally {
			cursor.close();
		}
		
		// store all the ArrayList<Document> into MongoDB, totally 15
		storeMongodb(databaseName, "newsLogs", newsDocuments);
		storeMongodb(databaseName, "uansrLogs", uansrDocuments);
		storeMongodb(databaseName, "ebookLogs", ebookDocuments);
		storeMongodb(databaseName, "patentLogs", patentDocuments);
		storeMongodb(databaseName, "dissertationLogs", dissertationDocuments);
		storeMongodb(databaseName, "confLogs", confDocuments);
		storeMongodb(databaseName, "courseLogs", courseDocuments);
		storeMongodb(databaseName, "journalLogs", journalDocuments);
		storeMongodb(databaseName, "oaLogs", oaDocuments);
		storeMongodb(databaseName, "companyLogs", companyDocuments);
		storeMongodb(databaseName, "countryLogs", countryDocuments);
		storeMongodb(databaseName, "symposiumLogs", symposiumDocuments);
		storeMongodb(databaseName, "educationLogs", educationDocuments);
		storeMongodb(databaseName, "historyLogs", historyDocuments);
		storeMongodb(databaseName, "policyLogs", policyDocuments);
		
		System.out.println("Successfully classify and store " + dateStr + " logs!");
	}
	
	/**
	 * get the present date
	 * not use
	 */
	static String getPresentDate() {
		Date dateNow = new Date();
		
		DateFormat dateFormat = DateFormat.getDateInstance();      // 2016-12-2
		//DateFormat dateFormat0 = DateFormat.getDateTimeInstance(); // 2016-12-2 10:45:04
		//DateFormat dateFormat1 = DateFormat.getTimeInstance();     // 10:43:12
		//DateFormat dateFormat2 = DateFormat.getInstance();         // 16-12-2 ÉÏÎç10:44
		
		//String dateStr = dateNow.toString(); // Fri Dec 02 10:45:56 CST 2016
		String dateStr = dateFormat.format(dateNow);
		
		/*
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour=calendar.get(Calendar.HOUR);
		int minute=calendar.get(Calendar.MINUTE);	    
	    int second=calendar.get(Calendar.SECOND);
	    int WeekOfYear = calendar.get(Calendar.DAY_OF_WEEK);
		
		StringBuilder dateStr = new StringBuilder();
		dateStr.append(year);dateStr.append('-');
		dateStr.append(month);dateStr.append('-');
		dateStr.append(day);
		*/
		
		return dateStr;
	}
	
	/**
	 * store MongoCollection into one specific Mongodb collection
	 */
	static void storeMongodb(String databaseName, String collectionName, ArrayList<Document> documents) {
		MongoCollection<Document> collection = MongoConn.getMongoCollection(databaseName, collectionName);
		if(documents.size() > 0) {
		    collection.insertMany(documents);
		}
	}
}
