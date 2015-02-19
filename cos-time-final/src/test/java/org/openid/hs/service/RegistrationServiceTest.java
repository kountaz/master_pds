package org.openid.hs.service;


import java.rmi.RemoteException;
import org.junit.Test;
import org.openid.hs.costime.RegistrationService;
import org.openid.hs.costime.bean.RegistrationServiceImpl;
import org.openid.hs.costime.client.ClientCallbackServiceImpl;
import org.openid.hs.costime.exceptions.IncompatibleClassException;

public class RegistrationServiceTest 
{
	
	@Test(expected = IncompatibleClassException.class)
	public void registerTest() throws IncompatibleClassException, RemoteException
	{
		RegistrationService registration = new RegistrationServiceImpl();
		ClientCallbackServiceImpl client = null;
		registration.registerClient(client);
	}
	
	
	@Test(expected = IncompatibleClassException.class)
	public void unregisterTest() throws IncompatibleClassException, RemoteException
	{
		RegistrationService registration = new RegistrationServiceImpl();
		ClientCallbackServiceImpl client = null;
		registration.unregisterClient(client);
	}
}
