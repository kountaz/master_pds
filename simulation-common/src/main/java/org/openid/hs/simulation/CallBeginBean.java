package org.openid.hs.simulation;

import java.io.Serializable;

/**
 * Describe the informations of call begin.
 * @see CallBeginBean.
 * @version R2
 * @author Fanta
 *
 */
public class CallBeginBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phonenumber1;
	private String phonenumber2;
	private String entityResponse;
	
	public String getEntityResponse() {
		return entityResponse;
	}
	public CallBeginBean(String phonenumber1, String phonenumber2){
		this.phonenumber1 = phonenumber1;
		this.phonenumber2 = phonenumber2;
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
	
	
	public void setEntityResponse(String response) {
		this.entityResponse = response;
	}
	
	

}
