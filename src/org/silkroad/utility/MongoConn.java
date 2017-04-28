package org.silkroad.utility;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author : wuke
 * @date : 2016年11月23日下午4:01:53 
 * Title : MongoConn 
 * Description :
 */
public class MongoConn {
	private static MongoClient MONGOCLIENT = null;
	private static String USERNAME = "silkRoad";
	private static String PASSWORD = "silkRoad123";

	/**
	 * Return MongoCollection
	 * 
	 * @param databaseName
	 * @param mongodbCollectionName
	 * @return mongoCollection
	 */
	public static MongoCollection<Document> getMongoCollection(String databaseName, String collectionName) {
		MongoDatabase mongoDatabase = null;
		MongoCollection<Document> mongoCollection = null;

		// Double Check Lock
		if (MONGOCLIENT == null) {
			synchronized (MongoConn.class) {
				if (MONGOCLIENT == null) {
					MongoConn.initMongoClient();
					// MongoConn.initMongoClientNoAuthentication();
				}
			}
		}
		mongoDatabase = MONGOCLIENT.getDatabase(databaseName);
		mongoCollection = mongoDatabase.getCollection(collectionName);

		// System.out.println("Successfully get collection " +
		// mongodbCollectionName + "!");
		return mongoCollection;
	}

	/**
	 * Get MongoClient with authentication
	 */
	private static void initMongoClient() {
		ServerAddress ip = new ServerAddress("localhost", 27017);
		String databaseName = "silkRoad";
		try {
			MongoCredential credential = MongoCredential.createCredential(USERNAME, databaseName,
					PASSWORD.toCharArray());
			MONGOCLIENT = new MongoClient(ip, Arrays.asList(credential));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get MongoClient with no authentication
	 */
	public static void initMongoClientNoAuthentication() {
		try {
			MONGOCLIENT = new MongoClient("localhost", 27017);
			// MONGOCLIENT = new MongoClient("personalize-mongo", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}