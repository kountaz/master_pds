package org.openid.hs.datagrid.controller;

import java.io.IOException;

import net.spy.memcached.MemcachedClient;

import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Data engine.
 * @version R3
 * @author Steven, Victor
 *
 */
public final class DataEngine {
	/**
	 * Mongodb.
	 */
	private MongoClient mongodb;
	/**
	 * Database.
	 */
	private DB database;
	/**
	 * Memcached.
	 */
	private MemcachedClient memcached;
	
	public DataEngine(int pMongodbPort, String pDatabase, int pMemcachedPort) throws IOException {
		this(
				MongoEmbeddedProcess.getClient(pMongodbPort),
				pDatabase,
				MemcachedEmbeddedProcess.getClient(pMemcachedPort)
			);
	}
	public DataEngine(MongoClient pMongodb, String pDatabase, MemcachedClient pMemcached) {
		mongodb = pMongodb;
		database = mongodb.getDB(pDatabase);
		memcached = pMemcached;
	}
	/**
	 * Returns mongodb.
	 * @return Mongodb.
	 */
	public MongoClient getMongodb() {
		return mongodb;
	}
	/**
	 * Returns database.
	 * @return Database.
	 */
	public DB getDatabase() {
		return database;
	}
	/**
	 * Returns memcached.
	 * @return Memcached.
	 */
	public MemcachedClient getMemcached() {
		return memcached;
	}
}
