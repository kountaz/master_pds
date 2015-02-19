package org.openid.hs.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import org.junit.Test;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.costime.core.TimeReferenceManager;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;


public class TimeReferenceManagerTest 
{
	@Test(expected = ProcessIsAlreadyStartedException.class)
	public void launchManagerTest() throws ProcessIsAlreadyStartedException, IOException
	{
		TimeReferenceManager server = new TimeReferenceManager();
		TimeReferenceManager server2 = new TimeReferenceManager();
		server.start();
		server2.start();
	}
	
	
	@Test(expected = ProcessIsAlreadyStartedException.class)
	public void startTest() throws ProcessIsAlreadyStartedException, IOException
	{
		TimeReferenceManager server = new TimeReferenceManager();
		server.start();
		server.start();
	}
}
