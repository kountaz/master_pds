package org.openid.hs.costime.client;


import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.RegistrationService;
import org.openid.hs.costime.exceptions.IncompatibleClassException;



public class Client 
{
	public static void main(String args[]) throws InterruptedException
	{
		
		try
		{
			URL wsdlUrl = new URL("http://localhost:8081/Registration");
			QName qnameService = new QName("http://bean.costime.hs.openid.org/", "RegistrationServiceImplService");
			QName qnamePort = new QName("http://bean.costime.hs.openid.org/", "RegistrationServiceImplPort");
			//Create the service.
			Service service = Service.create(wsdlUrl, qnameService);
			RegistrationService registration = (RegistrationService) service.getPort(qnamePort, RegistrationService.class);
			ClientCallbackService client = (ClientCallbackService) UnicastRemoteObject.exportObject(new ClientCallbackServiceImpl(), 1094);
			ClientCallbackService client2 = (ClientCallbackService) UnicastRemoteObject.exportObject(new ClientCallbackServiceImpl(), 1095);
			ClientCallbackService client3 = (ClientCallbackService) UnicastRemoteObject.exportObject(new ClientCallbackServiceImpl(), 1096);
			registration.registerClient(client);
			registration.registerClient(client2);
			registration.registerClient(client3);
			
		} 
		catch (MalformedURLException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		catch (RemoteException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		catch (IncompatibleClassException ex) 
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} 
		
	}
}
