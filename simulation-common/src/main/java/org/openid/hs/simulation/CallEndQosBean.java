package org.openid.hs.simulation;

import java.sql.Timestamp;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.simulation.Call;


/**
 * Describe the call end bean to sent to Qos layer.
 * @see CallEndQosBean.
 * @version R2
 * @author Fanta
 *
 */
public class CallEndQosBean implements Call{
	
	private String numberphone1;
	
	private String numberphone2;
	
	private Timestamp timestamp;
	
	private int emittedBytes;
	
	private int receivedBytes;
	
	private String type;
	
	
	public CallEndQosBean(String numberphone1, String numberphone2, int emittedBytes, int receivedBytes,String type){
		this.numberphone1 = numberphone1;
		
		this.numberphone2 = numberphone2;
		
		this.emittedBytes = emittedBytes;
		
		this.receivedBytes = receivedBytes;
		
		this.type = type;
	}
	

	public String getPhonenumber1() {
		return numberphone1;
	}

	public void setNumberphone1(String numberphone1) {
		this.numberphone1 = numberphone1;
	}

	public String getPhonenumber2() {
		return numberphone2;
	}

	public void setNumberphone2(String numberphone2) {
		this.numberphone2 = numberphone2;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getEmittedBytes() {
		return emittedBytes;
	}

	public void setEmittedBytes(int emittedBytes) {
		
		this.emittedBytes = emittedBytes;
	}

	public int getReceivedBytes() {
		return receivedBytes;
	}

	public void setReceivedBytes(int receivedBytes) {
		this.receivedBytes = receivedBytes;
	}


	@Override
	public String getType() 
	{
				
		return this.type;
	}
	
	public void setType(String tp){
		this.type = tp;
	}
	

}
