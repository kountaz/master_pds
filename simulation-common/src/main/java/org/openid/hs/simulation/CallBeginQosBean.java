package org.openid.hs.simulation;

import java.sql.Timestamp;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.simulation.Call;


/**
 * Describe the call begin bean to sent to Qos layer.
 * @see CallBeginQosBean.
 * @version R2
 * @author Fanta
 *
 */
public class CallBeginQosBean implements Call{
	
	private String phonenumber1;
	private String phonenumber2;
	private Timestamp timestamp;
	private String type;
	
	public CallBeginQosBean(String phonenumber1, String phonenumber2, String type){
		this.phonenumber1 = phonenumber1;
		this.phonenumber2 = phonenumber2;
		this.type = type;
	}
	public String getPhonenumber1() {
		return phonenumber1;
	}
	public void setPhonenumber1(String phonenumber1) {
		this.phonenumber1 = phonenumber1;
	}
	public String getPhonenumber2() {
		return phonenumber2;
	}
	public void setPhonenumber2(String phonenumber2) {
		this.phonenumber2 = phonenumber2;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public int getEmittedBytes() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getReceivedBytes() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String tp){
		this.type = tp;
	}
	
	
	

}
