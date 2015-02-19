package org.openid.hs.faildetector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.ILoaderMaterial;
import org.openid.hs.discovery.exception.InvalidParameters;
import org.openid.hs.faildetector.bean.FailDetector;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
public class FailDetectorTestCase {
	ILoaderMaterial loadmat = mock(ILoaderMaterial.class);
	Socket sock = mock(Socket.class);
	FailDetector FD;
	public FailDetectorTestCase() throws InvalidParameters, UnknownHostException, DatagridException
	{
		FD = new FailDetector("127.0.0.1", "7777", "HSS", "001", loadmat);
		DBCursor curs = mock(DBCursor.class);
		when(loadmat.selectDataMaterial(anyString(),anyString(),anyString(),anyString())).thenReturn(curs);
		
	}
	@Test
	public void testWhichWatch() {
		List<DBObject> rep = FD.WhichWatch();
		assertNotNull(rep);
	}

	@Test
	public void testWatchOverIt() {
		FD.WatchOverIt(0);
		assertTrue(true);
	}

	@Test
	public void testMasterDeclaration() {
		FD.masterDeclaration();
		assertTrue(true);
	}

	@Test
	public void testCallMaster() {
		FD.callMaster("", "");
		assertTrue(true);
	}

}
