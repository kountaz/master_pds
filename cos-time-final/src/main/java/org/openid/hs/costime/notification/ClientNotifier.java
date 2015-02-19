package org.openid.hs.costime.notification;

import java.lang.reflect.Proxy;
import java.util.Date;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.client.ClientCallbackService;

/**
 * @author: OPENID
 * @version: 1.0
 * ClientNotifier has the duty to transmit the timeReference 
 * to the Back Office using the message queue. 
 */

public class ClientNotifier 
{	
	/**
	 * The ClientNotifier constructor instantiate the private member.
	 */
	public ClientNotifier()
	{
		
	}
	
	
	/**
	 * The notifyClients method uses the list of registered client callback in order to warn.
	 * @param Date which is going to send to the clients.
	 * @param ClientCallbackServiceImpl array which will be used by this method to send time reference to clients.
	 */
	public void notifyClients(Date timeReference, ClientCallbackService[] clients)
	{
		try 
		{
			LoggerHelper.info("Notify the client components...");
			if(clients.length == 0) LoggerHelper.info("There is not a client in the register...");
			ClientCallbackService proxy = null;
			
			for(int y = 0; y < clients.length; y++)
			{
				proxy = (ClientCallbackService) Proxy.newProxyInstance(ClientCallbackService.class.getClassLoader(),
														               new Class<?>[] { ClientCallbackService.class },
														               new ClientProxy(clients[y])); 
				proxy.notifyClient(timeReference);
			}
		} 
		catch(Exception ex)
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		}
	}
}
