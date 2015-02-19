package org.openid.hs.MaterialZone;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.UnknownHostException;

import org.junit.Test;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.*;

public class ZoneHandlerTestCase {
	ZoneHandler zhand;
	DBCursor curs = mock(DBCursor.class);
	BasicDBObject doc;
	public ZoneHandlerTestCase() throws UnknownHostException, DatagridException
	{
		DatagridAPI.init();
		zhand = new ZoneHandler();
		doc = new BasicDBObject("id", 001)
		.append("libelle", "Paris").append("type", "HSS")
		.append("MasterAdress", "127.0.0.1")
		.append("MasterPort", "7777");
		when(curs.next()).thenReturn(doc);
	}
	@Test
	public void testSaveNewMaster() {
		zhand.SaveNewMaster(doc);
		assertTrue(true);
	}

	@Test
	public void testGetZone() throws UnknownHostException, DatagridException {
		Zone z = zhand.getZone(doc);
		assertSame(z.id.toString(),doc.get("id").toString());
	}

}
