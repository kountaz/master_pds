package org.openid.hs.costime.controller;

import java.util.Date;
import java.util.TimerTask;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.notification.TimeReferenceTransmitter;
import org.openid.hs.costime.ntp.NTPConnector;


/**Author: OpenID - Patrick
 * The BOTimeDispatcher class supplies the client notification feature.
 */
public class BOTimeDispatcher extends TimerTask
{
	
	/**
	 * The TimeReferenceTransmitter member transmits time reference to Back Office.
	 */
	private TimeReferenceTransmitter transmitter;
	/**
	 * The NTPConnector member gets time reference to NTP server.
	 */
	private NTPConnector connector;
	/**
	 * The Date member represents time reference.
	 */
	private Date timeReference;
	
	
	/**
	 * The BOTimeDispatcher constructor make NTPConnector and TimeReferenceTransmitter instances.
	 */
	public BOTimeDispatcher()
	{
		connector = new NTPConnector();
		transmitter = new TimeReferenceTransmitter();
	}

	
	/**
	 * The run method dispatches the time reference to the Back Office.
	 */
	public void run() 
	{
		LoggerHelper.info("-----------------------BOTimeDispatcher-------------------------------");
		timeReference = connector.getTime();
		LoggerHelper.info("Notify the Back Office managers...");
		transmitter.writeMessage(timeReference);
		LoggerHelper.info("-------------------------------END------------------------------------");
	}
}
