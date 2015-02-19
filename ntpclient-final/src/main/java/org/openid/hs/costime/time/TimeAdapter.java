package org.openid.hs.costime.time;

import java.util.Date;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeStamp;

/**
 * @author: OPENID
 * @version: 1.0
 * TimeAdapter converts the timestamp which was given by NTP server to Date instance. 
 */

public class TimeAdapter 
{

	/**
	 * Constructor of this class.
	 */
	public TimeAdapter()
	{
		
	}
	
	/**
	 * The convertToDate method converts the timestamp to Date instance and returns it. 
	 * @param NtpV3Packet object which contains the timestamp.
	 */
	
	public Date convertToDate(NtpV3Packet message)
	{
		Date timeReference = null;
		TimeStamp transmitNtpTime = message.getTransmitTimeStamp();
		timeReference = transmitNtpTime.getDate();
		
		return timeReference;
	}
}
