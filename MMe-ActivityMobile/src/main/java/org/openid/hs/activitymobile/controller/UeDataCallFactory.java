package org.openid.hs.activitymobile.controller;

import java.sql.Timestamp;

import org.openid.hs.MmeHssCommon.ConsumptionInfoHSS;
import org.openid.hs.activitycontrol.bean.UeDataCall;
import org.openid.hs.datagrid.Datagrid;

/**
 * UeDataCallFactory Class that will generate a UeDataCall 
 * with all its parameter to start the control of the communication
 * of the UE associated
 * @see UeDataCall
 * @version R2
 * @author Mériem
 */

public class UeDataCallFactory {

	private UeDataCall ued;
	private ConsumptionInfoHSS infoHss;
	private String ip;
	private int port;
	private String idUE;
	private int status;
	private int callTimeAllowed;
	private int callTimeConsumed;
	private Timestamp startCallTime;
	private Datagrid grid;
	/**
	 * Constructor initialize UeDataCallFactory attributes with the ConsumptionInfoHSS data
	 */
	public UeDataCallFactory(ConsumptionInfoHSS infoHss) {
		
		this.infoHss = infoHss;
		this.ip = this.infoHss.getIpEnodeb();
		this.port = this.infoHss.getPortEnodeb();
		this.idUE = this.infoHss.getIdUE();
		this.status = this.infoHss.getIdStatus();
		this.callTimeAllowed = this.infoHss.getCallTimeAllowed();
		this.callTimeConsumed = this.infoHss.getCallTimeConsumed();
		this.startCallTime = this.infoHss.getStartCallTime();
	}
	/**
	 * method that create UeDataCall Object with the ConsumptionInfoHss
	 * informations
	 * @return UeDataCall 
	 */
	public UeDataCall getUeDataCall(){
		ued = new UeDataCall(ip, port, idUE, status, callTimeAllowed, callTimeConsumed,startCallTime);
		return ued;
	}
	/**
	 * Method that put the UeCallData in the datagrid
	 */
	public void toPut(){
		//mettre une exception si la UeDataCall est null
			DataLoader data;
			data = DataLoader.getInstance("UeDataCall");
			grid = data.getData("UeDataCall");
			grid.set(this.idUE, this.ued);

	}
}
