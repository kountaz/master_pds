package org.openid.hs.costime.bean;


import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.TimeRequestService;
import org.openid.hs.costime.ntp.NTPConnector;


@WebService(endpointInterface = "org.openid.hs.costime.TimeRequestService")
public class TimeRequestServiceImpl implements TimeRequestService
{

	private NTPConnector connector;


	public TimeRequestServiceImpl() 
	{
		this.connector = new NTPConnector();
	}
	
	
	@WebMethod
	public Date getTimeReference() 
	{
		Date timeReference = null;
		LoggerHelper.info("********************************************************");
		LoggerHelper.info("Calling on this method by the client.");
		
		connector = new NTPConnector();
		timeReference = connector.getTime();
		
		LoggerHelper.info("Ending of call on this method by the client.");
		LoggerHelper.info("********************************************************");
		
		return timeReference;
	}
}
