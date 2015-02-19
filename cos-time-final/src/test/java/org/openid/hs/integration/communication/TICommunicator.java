package org.openid.hs.integration.communication;

import java.util.Date;
import java.util.Properties;
import javax.jms.TextMessage;
import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.CommunicationClientAPI;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;


public class TICommunicator 
{
	
	public static boolean runIntegrationTest()
	{
		boolean flag = false;
		try
		{
			int pBrokerPort = 0;
			Date currentDate = new Date();
			Properties properties = ResourceHelper.getProperties("/org/openid/hs/costime/configuration/configuration.properties");
			CommunicationAPI communicationApi = CommunicationAPI.init(pBrokerPort);
			CommunicationClientAPI clientCommunicationApi = new CommunicationClientAPI(
																						properties.getProperty("CommunicationAPI.Address")
																	                    + ":" + properties.getProperty("CommunicationAPI.Port"), 
																						  properties.getProperty("CommunicationAPI.InputQueuename"), 
																						  properties.getProperty("CommunicationAPI.OutputQueuename")
																					  );
			TextMessage message = clientCommunicationApi.createMessage("TimeReference", Long.toString(currentDate.getTime()));
			clientCommunicationApi.sendMessage(message);
			CommunicationAPI.destroy();
			flag = true;
		}
		catch(Exception ex)
		{
			LoggerHelper.error("An error is occured while the runtime: ", ex);
		}
		
		return flag;
	}
	
	
	public static void main(String[] args) 
	{
		boolean isGood = runIntegrationTest();
		if(isGood) 
			LoggerHelper.info("Integration test result: OK");
		else
			LoggerHelper.info("Integration test result: KO");
	}
}
