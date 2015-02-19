package org.openid.hs.costime.controller;

import java.util.Date;
import java.util.TimerTask;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.bean.RegistrationServiceImpl;
import org.openid.hs.costime.notification.ClientNotifier;
import org.openid.hs.costime.ntp.NTPConnector;

/**
 * @author: OPENID
 * @version: 1.0
 * TimeRetriever is a job will be associate to a trigger.
 */

public class TimeRetriever extends TimerTask
{
	private NTPConnector connector;
	private Date timeReference;
	private ClientNotifier notifier;
	private RegistrationServiceImpl service;
	
	public TimeRetriever(RegistrationServiceImpl rsi)
	{
		connector = new NTPConnector(); 
		notifier = new ClientNotifier();
		service = rsi;
	}
	
	
	public void run() 
	{
		LoggerHelper.info("--------------------------------------------------------");
		timeReference = connector.getTime();
		if(service.getAllClients().length > 0)
		{
			notifier.notifyClients(timeReference, service.getAllClients()); 
		}
		else
			LoggerHelper.info("There is not a client in the register");
		LoggerHelper.info("--------------------------------------------------------");
	}
}