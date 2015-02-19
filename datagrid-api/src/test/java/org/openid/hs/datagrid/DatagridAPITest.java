package org.openid.hs.datagrid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

public class DatagridAPITest {
	@BeforeClass
	public static void setUp() throws Exception {
		DatagridAPI.nolog();
	}
	@AfterClass
	public static void tearDown() throws Exception {
		if (DatagridAPI.isInit()) {
			DatagridAPI.destroy();
		}
	}
	@Test
	public void initTest() throws Exception {
		System.out.println(">> TEST: initTest()");
		DatagridAPI.init();
		boolean thrown = false;
		try {
			DatagridAPI.init();
		} catch (DatagridException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
		DatagridAPI.destroy();
	}
	@Test
	public void isInitTest() throws Exception {
		System.out.println(">> TEST: isInitTest()");
		assertFalse(DatagridAPI.isInit());
		DatagridAPI.init();
		assertTrue(DatagridAPI.isInit());
		DatagridAPI.destroy();
	}
	@Test
	public void getTest() throws Exception {
		System.out.println(">> TEST: getTest()");
		boolean thrown = false;
		try {
			DatagridAPI.get();
		} catch (DatagridException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
		DatagridAPI.init();
		assertTrue(DatagridAPI.get() instanceof DatagridAPI);
		DatagridAPI.destroy();
	}
	@Test
	public void destroyTest() throws Exception {
		System.out.println(">> TEST: destroyTest()");
		DatagridAPI.init();
		DatagridAPI.destroy();
		assertFalse(DatagridAPI.isInit());
	}
	@Test
	public void integrationTest() throws Exception {
		String testGridName = "test";
		String testKey = "k1";
		Integer testValue = 1;
		int testExpiration = 1;
		
		DatagridAPI api = DatagridAPI.init();
		Datagrid grid = api.getDatagrid(testGridName);
		assertTrue(grid instanceof Datagrid);
		
		grid.set(testKey, testValue, testExpiration);
		api.persistDatagrid(grid);
		assertTrue(grid.get(testKey).equals(testValue));
		
		Thread.sleep(testExpiration*1500);
		assertNull(grid.get(testKey));
		
		api.loadDatagrid(grid.getName());
		assertTrue(grid.get(testKey).equals(testValue));
		
		Thread.sleep(testExpiration*1500);
		assertNull(grid.get(testKey));
		
		api.resetDatagrid(grid);
		boolean thrown = false;
		try {
			grid.get(testKey);
		} catch (DatagridException e) {
			thrown = true;
		} finally {
			assertTrue(thrown);
		}
		
		api.synchronizeDatagrid(grid);
		assertTrue(api.isCached(grid));
		assertTrue(api.isPersisted(grid));
		
		api.resetDatagrid(grid);
		api.persistDatagrid(grid);
		assertTrue(api.isEmpty(grid));
		
		DatagridAPI.destroy();
	}
}
