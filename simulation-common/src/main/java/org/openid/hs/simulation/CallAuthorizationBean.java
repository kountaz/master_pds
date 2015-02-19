package org.openid.hs.simulation;

import java.io.Serializable;




/**
 * CallAuthorizationBean hss.
 * @see CallAuthorizationBean.
 * @version R2
 * @author Fanta
 *
 */
public class CallAuthorizationBean implements Serializable{
	
	/**
	 * number phone.
	 */
	private String phone1;
	
	/**
	 * service requested.
	 */
	private String mmmeRequest;
	
	/**
	 * response entity.
	 */
	private String hssEntity;
	
	public CallAuthorizationBean(String phone1,String mmmeRequest){
		this.phone1 = phone1;
		this.mmmeRequest = mmmeRequest;
	}
	public CallAuthorizationBean(String phone1,String mmmeRequest,String hssEntity){
		this.phone1 = phone1;
		this.mmmeRequest = mmmeRequest;
		this.hssEntity = hssEntity;
	}
	
	
	
}
