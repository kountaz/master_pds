package org.openid.hs.costime.bean;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.openid.hs.costime.RegistrationService;
import org.openid.hs.costime.client.ClientCallbackService;
import org.openid.hs.costime.exceptions.IncompatibleClassException;

/**
 * @author: OPENID
 * @version: 1.0
 * RegistrationServiceImpl contains the different servers which will be started and shutdown. 
 */

@WebService(endpointInterface = "org.openid.hs.costime.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService
{

	
	private Set<ClientCallbackService> clients;
	
	
	public RegistrationServiceImpl() 
	{
		clients = Collections.synchronizedSet(new HashSet<ClientCallbackService>());
	}
	
	
	@WebMethod
	public void registerClient(ClientCallbackService client) throws IncompatibleClassException
	{
		if(client != null)
			clients.add(client);
		else
			throw new IncompatibleClassException();
		
	}


	@WebMethod
	public void unregisterClient(ClientCallbackService client) throws IncompatibleClassException
	{
		if(client != null)
			clients.remove(client);
		else
			throw new IncompatibleClassException();
	}
	
	
	
	@WebMethod(exclude = true)
	public ClientCallbackService[] getAllClients()
	{
		ClientCallbackService[] aClients = new ClientCallbackService[clients.size()];
		
		return clients.toArray(aClients);
	}
}
