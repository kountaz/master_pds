package org.openid.hs.embeddedprocess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.spy.memcached.MemcachedClient;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class EmbeddedProcessHelperTest {
	@Test
	public void memcachedEmbeddedProcessTest() throws Exception {
		int testPort = 1501;
		String testKey = "k1";
		Integer testValue = 1;
		int testExpiration = 0;
		assertTrue(EmbeddedProcessHelper.checkPortAvailability(testPort));
		EmbeddedProcessHelper.startServer(MemcachedEmbeddedProcess.TYPE, testPort);
		assertFalse(EmbeddedProcessHelper.checkPortAvailability(testPort));
		MemcachedClient client = MemcachedEmbeddedProcess.getClient(testPort);
		client.set(testKey, testExpiration, testValue);
		assertTrue(client.get(testKey).equals(testValue));
		client.delete(testKey);
		assertNull(client.get(testKey));
		EmbeddedProcessHelper.stopServer(testPort);
		assertTrue(EmbeddedProcessHelper.checkPortAvailability(testPort));
	}
	@Test
	public void mongoEmbeddedProcessTest() throws Exception {
		int testPort = 1501;
		String testDb = "test";
		String testTable = "test";
		String testKey = "k1";
		Integer testValue = 1;
		assertTrue(EmbeddedProcessHelper.checkPortAvailability(testPort));
		EmbeddedProcessHelper.startServer(MongoEmbeddedProcess.TYPE, testPort);
		assertFalse(EmbeddedProcessHelper.checkPortAvailability(testPort));
		MongoClient client = MongoEmbeddedProcess.getClient(testPort);
		DBCollection table = client.getDB(testDb).getCollection(testTable);
		BasicDBObject o = new BasicDBObject(testKey, testValue);
		DBCursor result = table.find(o);
		assertTrue(result.count() == 0);
		table.insert(o);
		result = table.find(o);
		assertTrue(result.count() == 1);
		table.remove(o);
		assertTrue(result.count() == 0);
		EmbeddedProcessHelper.stopServer(testPort);
		assertTrue(EmbeddedProcessHelper.checkPortAvailability(testPort));
	}
}
