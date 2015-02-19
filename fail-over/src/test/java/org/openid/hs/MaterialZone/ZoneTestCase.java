package org.openid.hs.MaterialZone;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.BasicDBObject;

import static org.mockito.Mockito.*;
public class ZoneTestCase {
	ZoneHandler zhand = mock(ZoneHandler.class);
	Zone z;
	public ZoneTestCase() throws UnknownHostException, DatagridException
	{
		BasicDBObject dbo = new BasicDBObject("libelle", "Paris").append("type", "HSS");
		z = new Zone("Paris", "HSS","127.0.01","7001");
		when(zhand.getZone(dbo)).thenReturn(z);
	}
	@Test
	public void testSetMasterAdress() {
		try {
			z.setMasterAdress();
		} catch (DatagridException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void testGetZoneBefore() throws UnknownHostException, DatagridException {
		Zone test =z.getZoneBefore();
		assertSame(z.id-1,test.id);
	}
	@Test
	public void testGetZoneNext() throws UnknownHostException, DatagridException {
		Zone test =z.getZoneNext();
		assertSame(z.id+1,test.id);
	}

}
