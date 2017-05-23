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
 * @date : 20161123 16:01:53 
 * Title : MongoConn 
 * Description : 
 */
public class MongoConn {
	private static MongoClient MONGOCLIENT = null;

	/**
	 * Return MongoCollection
	 * 
	 * @param databaseName
	 * @param mongodbCollectionName
	 * @return mongoCollection
	 */
	public static MongoCollection<Document> getMongoCollection(String collectionName) {
		String host = GetProperty.getPropertyByName("mongo_host");
		int port = Integer.parseInt(GetProperty.getPropertyByName("mongo_port"));
		String databaseName = GetProperty.getPropertyByName("mongo_database");
		
		MongoDatabase mongoDatabase = null;
		MongoCollection<Document> mongoCollection = null;

		// Double Check Lock
		if (MONGOCLIENT == null) {
			synchronized (MongoConn.class) {
				if (MONGOCLIENT == null) {
					MongoConn.initMongoClient(host, port);
					// MongoConn.initMongoClientNoAuthentication(host, port);
				}
			}
		}
		mongoDatabase = MONGOCLIENT.getDatabase(databaseName);
		mongoCollection = mongoDatabase.getCollection(collectionName);
		
		return mongoCollection;
	}

	/**
	 * Get MongoClient with authentication.
	 */
	private static void initMongoClient(String host, int port) {
		String username = GetProperty.getPropertyByName("mongo_username");
		String password = GetProperty.getPropertyByName("mongo_password");
		String databaseName = GetProperty.getPropertyByName("mongo_database");
		
		// String databaseName = "silkRoad";
		// String username = "silkRoad";
		// String password = "silkRoad123";
		
		// ServerAddress ip = new ServerAddress("localhost", 27017);
		ServerAddress ip = new ServerAddress(host, port);
		try {			
			MongoCredential credential = MongoCredential.createCredential(username, databaseName, password.toCharArray());
			MONGOCLIENT = new MongoClient(ip, Arrays.asList(credential));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get MongoClient with no authentication.
	 */
	public static void initMongoClientNoAuthentication(String host, int port) {
		try {
			// MONGOCLIENT = new MongoClient("localhost", 27017);
			// MONGOCLIENT = new MongoClient("personalize-mongo", 27017);
			MONGOCLIENT = new MongoClient(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}