package org.openid.hs.costime;


import java.rmi.RemoteException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.openid.hs.costime.client.ClientCallbackService;
import org.openid.hs.costime.exceptions.IncompatibleClassException;

/**
 * @author: OPENID
 * @version: 1.0
 * Registration service allows to register the callback objects which are instantiated by clients. 
 */

@WebService
public interface RegistrationService 
{
	/**
	 * The registerClient method adds the callback client to the collection of server.
	 * @param ClientCallbackServiceImpl is instantiated and given by the client. 
	 */
	
	@WebMethod
	void registerClient(ClientCallbackService client) throws  IncompatibleClassException, RemoteException; 
	
	/**
	 * The unsubscribeClient method adds the callback client to the collection of server.
	 * @param ClientCallbackServiceImpl is instantiated and given by the client. 
	 */
	@WebMethod
	void unregisterClient(ClientCallbackService client) throws IncompatibleClassException, RemoteException; 
}
