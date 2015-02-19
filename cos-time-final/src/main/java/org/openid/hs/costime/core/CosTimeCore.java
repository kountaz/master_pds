package org.openid.hs.costime.core;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
//import java.net.MalformedURLException;
//import java.net.URL;
//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.costime.controller.ElectionCandidate;
//import org.openid.hs.costime.RegistrationService;
//import org.openid.hs.costime.client.ClientCallbackService;
//import org.openid.hs.costime.client.ClientCallbackServiceImpl;
import org.openid.hs.costime.controller.ElectionTask;
//import org.openid.hs.costime.exceptions.IncompatibleClassException;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * @author: OPENID
 * @version: 1.0
 * CosTimeCore class represents the process which is going to manage the time reference. 
 */

public class CosTimeCore 
{
	/**
	 * The process starts here. 
	 */
	public static void main(String[] args ) 
    {
		try 
		{
			/*
			//Launching client.
			URL wsdlUrl = new URL("http://localhost:8081/Registration");
			QName qnameService = new QName("http://bean.costime.hs.openid.org/", "RegistrationServiceImplService");
			QName qnamePort = new QName("http://bean.costime.hs.openid.org/", "RegistrationServiceImplPort");
			//Create the service.
			Service service = Service.create(wsdlUrl, qnameService);
			RegistrationService registration = (RegistrationService) service.getPort(qnamePort, RegistrationService.class);
			ClientCallbackService client = new ClientCallbackServiceImpl();
			ClientCallbackService client2 = new ClientCallbackServiceImpl();
			ClientCallbackService client3 = new ClientCallbackServiceImpl();
			registration.registerClient(client);
			registration.registerClient(client2);
			registration.registerClient(client3);*/
			
			LoggerHelper.info("-----------------------------Start----------------------------------");
			//Configuring Logger...
			BasicConfigurator.configure();
			//Creating Manager...
			if(!DatagridAPI.isInit())
			{
				DatagridAPI.nolog();
				DatagridAPI.init();
			}
			
			Properties properties = ResourceHelper.getProperties("/configuration.properties");
			TimeReferenceManager manager = new TimeReferenceManager(),
					             manager2 = new TimeReferenceManager(),
					             manager3 = new TimeReferenceManager();
			//Listening...
			Thread tElection = new Thread(new ElectionTask(manager, Integer.parseInt(properties.getProperty("Election.currentNode.Port"))));
			tElection.start();
			Thread tElection2 = new Thread(new ElectionTask(manager2, Integer.parseInt(properties.getProperty("Election.currentNode.Port2"))));
			tElection2.start();
			Thread tElection3 = new Thread(new ElectionTask(manager3, Integer.parseInt(properties.getProperty("Election.currentNode.Port3"))));
			tElection3.start();
			
			//Requesting...
			ElectionCandidate client = new ElectionCandidate(manager);
			client.prepareStartUp();
			LoggerHelper.info("-----------------------------End------------------------------------");

			//LoggerHelper.info(client.getTimeReference().toString());
		} 
		catch (ProcessIsAlreadyStartedException ex)
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} 
		/*catch (MalformedURLException ex)
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} 
		catch (RemoteException ex) 
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} */
		/*catch (IncompatibleClassException ex) 
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} */
		catch (IOException ex) 
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} 
		catch (InterruptedException ex) 
		{
			LoggerHelper.error("L'objet fourni qui implémente ClientCallbackService ne respecte pas le contrat", ex);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
