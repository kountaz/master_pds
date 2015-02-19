package org.openid.hs.simulation;

import java.io.Serializable;


/**
 * Describe the the request for a call.
 * @see CallConnexion.
 * @version R2
 * @author Fanta
 *
 */
public class CallConnexion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String phone1;
	
	private String callstate;
	
	private String entityResponse;
	
	public CallConnexion(String phone1,String callstate,String entityResponse){
		this.phone1 = phone1;
		this.callstate = callstate;
		this.entityResponse = entityResponse;
	}
	
	
	
	
	public CallConnexion(String phone1){
		this.phone1 = phone1;
	}
	

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getEntityResponse() {
		return phone1;
	}

	public void setentityResponse(String entityResponse) {
		this.entityResponse = entityResponse;
	}
	

}
