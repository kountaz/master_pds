package org.openid.hs.costime.client;


import java.rmi.RemoteException;
import java.util.Date;
import org.openid.hs.core.helper.LoggerHelper;



public class ClientCallbackServiceImpl implements ClientCallbackService
{
	
	private volatile Date timeReference;
	
	public ClientCallbackServiceImpl() throws RemoteException 
	{
		
	}

	
	public void notifyClient(Date date) throws RemoteException
	{
		timeReference = date;
		LoggerHelper.info(timeReference.toString());
	}
	
	
	public Date getTimeReference() throws RemoteException
	{
		return timeReference;
	}
}
