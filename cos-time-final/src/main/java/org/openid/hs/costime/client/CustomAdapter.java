package org.openid.hs.costime.client;

import java.rmi.server.UnicastRemoteObject;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CustomAdapter extends XmlAdapter<ClientCallbackServiceImpl, ClientCallbackService> 
{

	
	
	
	public ClientCallbackServiceImpl marshal(ClientCallbackService v) throws Exception 
	{
		return (ClientCallbackServiceImpl) v;
	}

	

	public ClientCallbackService unmarshal(ClientCallbackServiceImpl v) throws Exception 
	{
		return (ClientCallbackService) UnicastRemoteObject.exportObject(v, 1097);
	}

}
