package com.arugan.restaurant.mongodb;

import java.net.UnknownHostException;

import com.arugan.restaurant.constant.Constant;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDB {

	private static MongoDB mongoDB;

	private DB db;
	private DBCollection target;
	private DBCollection restaurant;
	private DBCollection log;


	private MongoDB() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("vm-cluster-node1", 27017);
		db = mongoClient.getDB(Constant.DB_NAME);
		target = db.getCollection("target");
		restaurant = db.getCollection("restaurant");
	}

	public static MongoDB getInstance() {
		if (mongoDB == null) {
			try {
				mongoDB = new MongoDB();
			} catch (UnknownHostException e) {
			}
		}
		return mongoDB;
	}

	public DBCollection getRestaurantDBCollection() {
		return restaurant;
	}

	public DBCollection getTargetDBCollection() {
		return target;
	}

	public DBCollection getLogDBCollection() {
		return log;
	}

}
