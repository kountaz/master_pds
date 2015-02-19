package org.openid.hs.simulation;

import java.io.Serializable;
import java.sql.Timestamp;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.simulation.Call;


/**
 * Describe the informations of call end.
 * @see CallEndBean.
 * @version R2
 * @author Fanta
 *
 */
public class CallEndBean implements Serializable{
	
	private String numberphone1;
	

	private String numberphone2;
	
	private int emittedByte;
	
	private String entityResponseE;
	private String entityResponseM;
	private String entityResponseH;
	
	public CallEndBean(String numberphone1,String numberphone2,int emittedByte){
		this.numberphone1 = numberphone1;
		this.numberphone2 = numberphone2;
		this.emittedByte = emittedByte;
	}



	public String getNumberphone1() {
		return numberphone1;
	}



	public void setNumberphone1(String numberphone1) {
		this.numberphone1 = numberphone1;
	}



	public String getNumberphone2() {
		return numberphone2;
	}



	public void setNumberphone2(String numberphone2) {
		this.numberphone2 = numberphone2;
	}



	public int getEmittedByte() {
		return emittedByte;
	}



	public void setEmittedByte(int emittedByte) {
		this.emittedByte = emittedByte;
	}



	public String getEntityResponseE() {
		return entityResponseE;
	}



	public void setEntityResponseE(String entityResponseE) {
		this.entityResponseE = entityResponseE;
	}



	public String getEntityResponseM() {
		return entityResponseM;
	}



	public void setEntityResponseM(String entityResponseM) {
		this.entityResponseM = entityResponseM;
	}



	public String getEntityResponseH() {
		return entityResponseH;
	}



	public void setEntityResponseH(String entityResponseH) {
		this.entityResponseH = entityResponseH;
	}



	


}
