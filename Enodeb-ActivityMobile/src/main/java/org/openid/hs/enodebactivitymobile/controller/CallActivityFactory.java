package org.openid.hs.enodebactivitymobile.controller;

import java.net.InetAddress;
import java.sql.Timestamp;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;
import org.openid.hs.EnodebMmeCommon.bean.CallEventBean;
import org.openid.hs.EnodebMmeCommon.bean.CommunicationCallActivityBean;


/**
 * CallActivityFactory Class that will generate a communicationCallActivity 
 * with all its parameter to send the MME will also detect if it is a
 * beginning or end call and calculate the time of each event.
 * @see CommunicationCallActivity
 * @version R2
 * @author Mériem
 */

public class CallActivityFactory {
	private String idUe ;
	private int idActivity;
	private int idEvent;
	private Timestamp timeEvent ;
	
	public CommunicationCallActivity callActivity;
	
	String info;
	/**
	 * Constructor that generates the communicationCallActivity Object with
	 * the infoCall String.
	 */
	public CallActivityFactory(String address, int port,String infoCall){
		/**
		 * splits the string to find CallActivity attributes
		 */
		String str[]=infoCall.split("-");
		this.idUe = str[0];
		this.idActivity = Integer.parseInt(str[1]);
		this.idEvent = Integer.parseInt(str[2]);
		String timeS = str[3];
		long timeL = Long.parseLong(timeS ); 
		this.timeEvent = new Timestamp(timeL);
		/**
		 *Creates the event associated with the UE call
		 */
		CallEventBean event = new CallEventBean(idEvent, timeEvent);
		/**
		 *Creates the CommunicationCallActivity associated with the UE call
		 */
		callActivity = new CommunicationCallActivityBean(address, port,idUe, idActivity,event);

	}
	/**
	 * return the CommunicationCallActivity of a UE Call.
	 * @return CommunicationCallActivity callActivity.
	 */
	public CommunicationCallActivity getCommunicationCallActivity(){
		return callActivity;
	}
	/**
	 * Defines CommunicationCallActivity of a UE call.
	 * @param CommunicationCallActivity callActivity.
	 */
	public void setCommunicationCallActivity(CommunicationCallActivityBean callActivity){
		this.callActivity = callActivity;
	}
}
