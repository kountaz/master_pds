package org.openid.hs.datagrid.bean;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import net.spy.memcached.MemcachedClient;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.bean.DataBean;
import org.openid.hs.datagrid.bean.DatagridBean;
import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.embeddedprocess.EmbeddedProcessHelper;
import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;
import org.powermock.api.mockito.PowerMockito;

import com.mongodb.DBCollection;

public class DatagridBeanTest {
	private static MemcachedClient memcached;
	private static DataEngine engine;
	private static DBCollection storage;
	private static Datagrid grid;
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
		grid = new DatagridBean(storage, engine);
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
	public void setTest() throws Exception {
		System.out.println(">> TEST: setTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		String testKey2 = "k2";
		Integer testValue2 = 2;
		
		grid.set(testKey, testValue);
		grid.set(testKey2, testValue2);
		
		Mockito.verify(memcached, Mockito.times(2)).set(Mockito.anyString(), Mockito.anyInt(), Mockito.any(DataBean.class));
		
		Set<String> gridKeys = grid.getKeys();
		assertTrue(
					gridKeys.size() == 2 
					&& gridKeys.contains(testKey) 
					&& gridKeys.contains(testKey2)
				);
		
		grid.remove(testKey);
		grid.remove(testKey2);
	}
	@Test
	public void getTest() throws Exception {
		System.out.println(">> TEST: getTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		Integer testExpiration = 2;
		
		boolean thrown = false;
		try {
			grid.get(testKey);
		} catch (DatagridException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
		
		grid.set(testKey, testValue, testExpiration);
		
		assertTrue(grid.get(testKey).equals(testValue));
		Thread.sleep(testExpiration*1500);
		assertNull(grid.get(testKey));
		
		grid.remove(testKey);
	}
	@Test
	public void removeTest() throws Exception {
		System.out.println(">> TEST: removeTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		Integer testExpiration = 2;
		
		grid.set(testKey, testValue, testExpiration);
		assertTrue(grid.getKeys().size() == 1);
		
		Mockito.reset(memcached);
		grid.remove(testKey);
		Mockito.verify(memcached, Mockito.times(1)).delete(Mockito.anyString());		
		
		assertTrue(grid.getKeys().size() == 0);
		
		boolean thrown = false;
		try {
			grid.remove(testKey);
		} catch (DatagridException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
	}
}