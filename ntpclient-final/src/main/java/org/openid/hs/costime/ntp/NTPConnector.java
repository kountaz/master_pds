package org.openid.hs.costime.ntp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.time.TimeAdapter;


/**
 * @author: OPENID
 * @version: 1.0
 * NTPConnector class must obtain the date to the NTP servers.
 */


public class NTPConnector
{
	
	/**
	 * Constructor of this class.
	 */
	public NTPConnector()
	{
		
	}
	
	/**
	 * The getTime method returns the instance of Date. 
	 * 
	 */
	public Date getTime()
	{
		int i = 0;
		boolean isSuccess = false;
		Date timeReference = null;
		InetAddress address = null;
		String addresses[] = {"ntp.unice.fr", "ntp.deuza.net", "ntp.uhb.fr", "ntp.univ-angers.fr"};
		while(!isSuccess)
		{
			try
			{
				LoggerHelper.info("Requesting the reference time to " + addresses[i] + "...");
				NTPUDPClient client = new NTPUDPClient();
				client.setDefaultTimeout(10000);
				client.open();
				address = InetAddress.getByName(addresses[i]);
				TimeInfo info = client.getTime(address);
				NtpV3Packet message = info.getMessage();
				TimeAdapter adapter = new TimeAdapter();
				timeReference = adapter.convertToDate(message);
				
				isSuccess = true;
				i++;		
			}
			catch(SocketTimeoutException ex)
			{
				i++;
				if(i == addresses.length) 
				{
					timeReference = new Date();
					isSuccess = true;
				}
			}
			catch(IOException ex)
			{
				LoggerHelper.error("An error occured while the processing: ", ex);
				i++;
				if(i == addresses.length) 
				{
					timeReference = new Date();
					isSuccess = true;
				}
			}
		}
		
		LoggerHelper.info("Date is going to be returned: " + timeReference);
		return timeReference;
	}
}
