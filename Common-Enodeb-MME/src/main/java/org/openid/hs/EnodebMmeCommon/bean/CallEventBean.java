package org.openid.hs.EnodebMmeCommon.bean;
import java.io.Serializable;
import java.sql.Timestamp;

import org.openid.hs.EnodebMmeCommon.CallEvent;


/**
 * CallEventBean class represents the implementation of the 
 * CallEvent interface, this class can set all parameters of UE call event 
 * that the MME can uses to control the activity of the mobile call
 * @see CallEvent
 * @version R2
 * @author Mériem
 *
 */
public class CallEventBean implements CallEvent{
	public int idEvent;
	public Timestamp timeEvent;
	/**
	 * Constructor defines the idEvent, the timeEvent of an UE Call
	 * @param int idEvent, Timestamp timeEvent.
	 */
	public CallEventBean(int idEvent, java.sql.Timestamp timeEvent){
		this.idEvent = idEvent;
		this.timeEvent = timeEvent;
	}

	public int getIdEvent() {
		return this.idEvent;
	}
	/**
	 * Defines idEvent of the call communication.
	 * @param int idEvent.
	 */
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public Timestamp getTimeEvent() {
		return timeEvent;
	}

	/**
	 * Defines timeEvent of the call communication.
	 * @param Timestamp timeEvent.
	 */
	public void setTimeEvent(Timestamp timeEvent) {
		this.timeEvent = timeEvent;
	}
}
