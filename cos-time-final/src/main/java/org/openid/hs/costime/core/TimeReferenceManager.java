package org.openid.hs.costime.core;


import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import javax.xml.ws.Endpoint;

import org.openid.hs.core.Service;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.costime.RegistrationService;
import org.openid.hs.costime.bean.TimeRequestServiceImpl;
import org.openid.hs.costime.bean.RegistrationServiceImpl;
import org.openid.hs.costime.controller.TaskScheduler;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;

/**
 * @author: OPENID - Patrick
 * @version: 1.0
 * TimeReferenceManager contains the different servers which will be started and shutdown. 
 */

public class TimeReferenceManager implements Service
{
	/**
	 * The endpointRegistration publishes the service of registration supplied by the RegistrationService interface.
	 */
	private Endpoint endpointRegistration;
	/**
	 * The endpointTimeRequest publishes the service of time query supplied by the TimeRequestService interface.
	 */
	private Endpoint endpointTimeRequest;
	/**
	 * The scheduler manages the task of time broadcast.
	 */
	private TaskScheduler scheduler;
	/**
	 * Properties supplies the parameters in the properties file.
	 */
	private Properties properties;
	
	/**
	 * isStarted allows to check when the process is already started.
	 */
	private boolean isStarted;
	/**
	 * isMaster property reports if this TimeReferenceManager is the master process started.
	 */
	private boolean isMaster;
	/**
	 * identifier property represents if the unique ID of this process.
	 */
	private UUID identifier;
	
	
	
	
	
	
	/**
	 * The TimeReferenceManager method is private It will be used only in itself.
	 */
	public TimeReferenceManager() 
	{
		identifier = UUID.randomUUID();
	}
	
	
	/**
	 * The start method launches the set of process.
	 */
	public void start() 
	{
		try 
		{
			LoggerHelper.info("Starting time server...");
			if(isStarted)
				throw new ProcessIsAlreadyStartedException("Il y a déjà un service de temps démarré");
			
			properties = ResourceHelper.getProperties("/configuration.properties");
			
			
			RegistrationService rsi = new RegistrationServiceImpl();
			endpointRegistration = Endpoint.create(rsi);
			endpointRegistration.publish(properties.getProperty("EndpointRegistration.Address") 
                                 + ":" + properties.getProperty("EndpointRegistration.Port") 
                                 + "/" + properties.getProperty("EndpointRegistration.ID"));
			
			TimeRequestServiceImpl trsi = new TimeRequestServiceImpl();
			endpointTimeRequest = Endpoint.publish(properties.getProperty("EndpointTimeRequest.Address") 
								                    + ":" + properties.getProperty("EndpointTimeRequest.Port") 
								                    + "/" + properties.getProperty("EndpointTimeRequest.ID"), trsi);
			scheduler = TaskScheduler.getInstance();
			LoggerHelper.info("Retrieving the time reference...");
			scheduler.initialize((RegistrationServiceImpl) rsi);
			scheduler.launch();
			isStarted = true;
			LoggerHelper.info("Time server started.");
		} 
		catch (ProcessIsAlreadyStartedException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		catch (IOException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		
	}
	
	
	/**
	 * The stop method turns off the set of process.
	 */
	public void stop()
	{
		LoggerHelper.info("shutdown time server...");
		endpointRegistration.stop();
		endpointTimeRequest.stop();
		scheduler.shutdown();
		isStarted = false;
		LoggerHelper.info("Time server stopped.");
	}
	
	
	/**
	 * The getIdentifier method returns UUID of process.
	 */
	public UUID getIdentifier()
	{
		return identifier;
	}
	
	
	/**
	 * The isMaster method returns true if this process is the master.
	 */
	public boolean isMaster()
	{
		return isMaster;
	}


	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isStarted() 
	{
		return isStarted;
	}
}
