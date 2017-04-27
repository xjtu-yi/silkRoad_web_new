package org.silkroad.processing.logs;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.silkroad.utility.MongoConn;

/**
* @author : wuke
* @date   : 2017年4月21日下午10:30:46
* Title   : ClassifyLogsByRole
* Description : Classify logs by role
*/
public class ClassifyLogsByRole {
	private static final String DATABASENAME = "silkRoad";
	private static final String[] ROLES =  {"student", "engineer", "scholar", "other"};
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		ClassifyLogsByRole.classifyLogsByRole();
		
		long cost = System.currentTimeMillis() - start;
		
		System.out.println("Cost " + (cost / 1000) + " seconds!");
	}
	
	public static void classifyLogsByRole() {
		MongoCollection<Document> logs = MongoConn.getMongoCollection(DATABASENAME, "logs");
		
		List<Document> studentDocs = new ArrayList<Document>();
		List<Document> engineerDocs = new ArrayList<Document>();
		List<Document> scholarDocs = new ArrayList<Document>();
		List<Document> otherDocs = new ArrayList<Document>();
		
		MongoCursor<Document> cursor = logs.find().iterator();
		Document tem = null;
		String role = null;
		try {
			while(cursor.hasNext()) {
				tem = cursor.next();
				role = tem.getString("role");
				
				switch(role) {
				case "student":
					studentDocs.add(tem);
					break;
				case "engineer":
					engineerDocs.add(tem);
					break;
				case "scholar":
					scholarDocs.add(tem);
					break;
				case "other":
					otherDocs.add(tem);
				}
		    }
		} finally {
			cursor.close();
		}
		
		ClassifyLogsByRole.storeMongo("student_logs", studentDocs);
		ClassifyLogsByRole.storeMongo("engineer_logs", engineerDocs);
		ClassifyLogsByRole.storeMongo("scholar_logs", scholarDocs);
		ClassifyLogsByRole.storeMongo("other_logs", otherDocs);
	}
	
	/**
	 * Store Documents into MongoDB
	 * @param collectionName
	 * @param documents
	 */
	public static void storeMongo(String collectionName, List<Document> documents) {
		MongoCollection<Document> collection = MongoConn.getMongoCollection(DATABASENAME, collectionName);
		
		collection.drop(); // delete the old data
		collection = MongoConn.getMongoCollection(DATABASENAME, collectionName);
		
		if(documents.size() > 0) {
		    collection.insertMany(documents);
		}
	}
}
