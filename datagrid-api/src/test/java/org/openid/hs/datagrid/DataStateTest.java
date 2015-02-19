package org.openid.hs.datagrid;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.datagrid.DataState;

public class DataStateTest {
	private static byte testByte1;
	private static byte testByte2;
	private static byte []testState;
	@BeforeClass
	public static void setUp() {
		testByte1 = 1;
		testByte2 = 0;
		testState = new byte[]{testByte1,testByte2};
	}
	@Test
	public void createTest() {
		byte []state = DataState.create(true, false);
		assertTrue(state[0] == testState[0] && state[1] == testState[1]);
	}
	@Test
	public void isTest() {
		assertTrue(DataState.is(testState, DataState.CACHED_UNPERSISTED));
		byte []state = DataState.create(true, true);
		assertFalse(DataState.is(state, DataState.CACHED_UNPERSISTED));
	}
	@Test
	public void isCachedUnpersistedTest() {
		byte []state = DataState.create(true, false);
		assertTrue(DataState.isCachedUnpersisted(state));
	}
	@Test
	public void isCachedPersistedTest() {
		byte []state = DataState.create(true, true);
		assertTrue(DataState.isCachedPersisted(state));
	}
	@Test
	public void isExpiredUnpersistedTest() {
		byte []state = DataState.create(false, false);
		assertTrue(DataState.isExpiredUnpersisted(state));
	}
	@Test
	public void isExpiredPersistedTest() {
		byte []state = DataState.create(false, true);
		assertTrue(DataState.isExpiredPersisted(state));
	}
	@Test
	public void toStringTest() {
		assertTrue(DataState.toString(testState).equals(DataState.CACHED_UNPERSISTED_STRING));
	}
}
