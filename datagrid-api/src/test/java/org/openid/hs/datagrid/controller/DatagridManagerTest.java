package org.openid.hs.datagrid.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.spy.memcached.MemcachedClient;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.openid.hs.datagrid.bean.DataBean;
import org.openid.hs.embeddedprocess.EmbeddedProcessHelper;
import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;
import org.powermock.api.mockito.PowerMockito;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class DatagridManagerTest {
	private static MemcachedClient memcached;
	private static DataEngine engine;
	private static DBCollection storage;
	private static DatagridManager grid;
	@BeforeClass
	public static void setup() throws Exception {
		int pMongodbPort = 1501;
		String pDatabase = "hs_datagrids";
		int pMemcachedPort = 1502;
		String pGridName = "test";

		EmbeddedProcessHelper.silentLogging();
		
		EmbeddedProcessHelper.startServer(MongoEmbeddedProcess.TYPE, pMongodbPort);
		EmbeddedProcessHelper.startServer(MemcachedEmbeddedProcess.TYPE, pMemcachedPort);
		 
		memcached = PowerMockito.spy(MemcachedEmbeddedProcess.getClient(pMemcachedPort));
		engine = new DataEngine(MongoEmbeddedProcess.getClient(pMongodbPort), pDatabase, memcached);
		storage = PowerMockito.spy(engine.getDatabase().getCollection(pGridName));
		grid = new DatagridManager(storage, engine);
	}
	@AfterClass
	public static void tearDown() throws Exception {
		EmbeddedProcessHelper.stopAllServers();
		System.out.println("-- END");
	}
	@Before
	public void before() {
		Mockito.reset(memcached);
		Mockito.reset(storage);
	}
	@Test
	public void isCachedTest() throws Exception {
		System.out.println(">> TEST: persistTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		
		assertTrue(grid.isCached());
		
		grid.remove(testKey);
		grid.remove(testKey2);
	}
	@Test
	public void isPersistedTest() throws Exception {
		System.out.println(">> TEST: persistTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		grid.persist();
		
		assertTrue(grid.isPersisted());
		
		grid.remove(testKey);
		grid.remove(testKey2);
	}
	@Test
	public void persistTest() throws Exception {
		System.out.println(">> TEST: persistTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		grid.persist();
		
		Mockito.verify(storage, Mockito.times(2)).insert(Mockito.any(BasicDBObject.class));
		Mockito.reset(storage);
		
		grid.remove(testKey);
		grid.remove(testKey2);
		grid.persist();
		
		Mockito.verify(storage, Mockito.times(2)).remove(Mockito.any(BasicDBObject.class));
	}
	@Test
	public void restoreTest() throws Exception {
		System.out.println(">> TEST: restoreTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		grid.persist();
		grid.remove(testKey);
		grid.remove(testKey2);
		
		assertTrue(grid.getKeys().size() == 0);
		
		Mockito.reset(memcached);
		grid.restore();
		
		Mockito.verify(memcached, Mockito.times(2)).set(Mockito.anyString(), Mockito.anyInt(), Mockito.any(DataBean.class));
		assertTrue(grid.getKeys().size() == 2);
	}
	@Test
	public void resetTest() {
		System.out.println(">> TEST: resetTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		grid.persist();
		
		Mockito.reset(memcached);
		Mockito.reset(storage);
		
		grid.reset();
		Mockito.verify(memcached, Mockito.times(2)).delete(Mockito.anyString());
		
		grid.persist();
		Mockito.verify(storage, Mockito.times(2)).remove(Mockito.any(BasicDBObject.class));
	}
	@Test
	public void synchronizeTest() throws Exception {
		System.out.println(">> TEST: persistTest()");
		
		int testExpiration = 1;
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue, testExpiration);
		grid.set(testKey2, testValue2, testExpiration);
		grid.synchronize();
		Thread.sleep(testExpiration*1500);
		
		assertFalse(grid.isCached());
		assertTrue(grid.isPersisted());
		
		grid.synchronize();
		
		assertTrue(grid.isCached());		
		grid.remove(testKey);
		grid.remove(testKey2);
		grid.persist();
	}
}