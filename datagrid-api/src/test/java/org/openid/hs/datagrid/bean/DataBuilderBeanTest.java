package org.openid.hs.datagrid.bean;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.spy.memcached.MemcachedClient;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.openid.hs.datagrid.Data;
import org.openid.hs.datagrid.DataBuilder;
import org.openid.hs.datagrid.DataState;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.embeddedprocess.EmbeddedProcessHelper;
import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;
import org.powermock.api.mockito.PowerMockito;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class DataBuilderBeanTest {
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
	public void stateTest() throws Exception {
		System.out.println(">> TEST: stateTest()");
		
		String testKey = "k1";
		Integer testValue = 1;
		int testExpiration = 2;

		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.remove();
		assertTrue(DataState.isExpiredUnpersisted(builder.getState()));
		
		builder.set(testValue);
		assertTrue(DataState.isCachedUnpersisted(builder.getState()));
		
		Thread.sleep(testExpiration*1500);
		builder.restore();
		assertTrue(DataState.isExpiredUnpersisted(builder.getState()));
		
		builder.set(testValue);
		builder.persist();
		assertTrue(DataState.isCachedPersisted(builder.getState()));;
		
		Thread.sleep(testExpiration*1500);
		assertTrue(DataState.isExpiredPersisted(builder.getState()));
		
		builder.remove();
		assertTrue(DataState.isExpiredUnpersisted(builder.getState()));
	}
	@Test
	public void buildTest() {
		System.out.println(">> TEST: buildTest()");
		
		String testKey = "k2";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		Data data = builder.build(testValue);
		
		assertTrue(data.getKey().equals(testKey) && data.getValue().equals(testValue) && data.getExpiration() == testExpiration);
	}
	@Test
	public void setTest() {
		System.out.println(">> TEST: setTest()");
		
		String testKey = "k3";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		
		Mockito.verify(memcached, Mockito.times(1)).set(Mockito.anyString(), Mockito.anyInt(), Mockito.any(DataBean.class));
	}
	@Test
	public void getTest() throws Exception {
		System.out.println(">> TEST: getTest()");
		
		String testKey = "k4";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		
		assertTrue(builder.get().equals(testValue));
		Thread.sleep(testExpiration*1500);
		assertNull(builder.get());
	}
	@Test
	public void unsetTest() {
		System.out.println(">> TEST: unsetTest()");
		
		String testKey = "k5";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		builder.unset();
		
		Mockito.verify(memcached, Mockito.times(1)).delete(Mockito.anyString());
	}
	@Test
	public void persistTest() {
		System.out.println(">> TEST: persistTest()");
		
		String testKey = "k6";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		builder.persist();
		
		Mockito.verify(storage, Mockito.times(1)).insert(Mockito.any(BasicDBObject.class));
		
		builder.remove();
	}
	@Test
	public void unpersistTest() {
		System.out.println(">> TEST: unpersistTest()");
		
		String testKey = "k7";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		builder.persist();
		Mockito.verify(storage, Mockito.times(1)).remove(Mockito.any(BasicDBObject.class));
		
		Mockito.reset(storage);
		builder.unpersist();
		Mockito.verify(storage, Mockito.times(1)).remove(Mockito.any(BasicDBObject.class));
	}
	@Test
	public void restoreTest() {
		System.out.println(">> TEST: restoreTest()");
		
		String testKey = "k8";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = new DataBuilderBean(testKey, testExpiration, grid);
		builder.set(testValue);
		builder.persist();
		builder.restore();
		
		Mockito.verify(storage, Mockito.times(1)).find(Mockito.any(BasicDBObject.class));
		Mockito.verify(memcached, Mockito.times(2)).set(Mockito.anyString(), Mockito.anyInt(), Mockito.any(DataBean.class));
		
		builder.remove();
	}
	@Test
	public void removeTest() {
		System.out.println(">> TEST: removeTest()");
		
		String testKey = "k9";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = PowerMockito.spy(new DataBuilderBean(testKey, testExpiration, grid));
		builder.set(testValue);
		builder.persist();
		builder.remove();
		
		Mockito.verify(builder, Mockito.times(1)).unset();
		Mockito.verify(builder, Mockito.times(1)).unpersist();
	}
	@Test
	public void synchronizeTest() throws Exception {
		System.out.println(">> TEST: synchronizeTest()");
		
		String testKey = "k10";
		Integer testValue = 1;
		int testExpiration = 2;
		
		DataBuilder builder = PowerMockito.spy(new DataBuilderBean(testKey, testExpiration, grid));
		builder.synchronize();
		builder.set(testValue);
		builder.synchronize();
		
		assertTrue(DataState.isCachedPersisted(builder.getState()));
		Thread.sleep(testExpiration*1500);
		assertTrue(DataState.isExpiredPersisted(builder.getState()));
		
		builder.synchronize();
		
		Mockito.verify(builder, Mockito.times(1)).persist();
		Mockito.verify(builder, Mockito.times(1)).restore();
	}
}
