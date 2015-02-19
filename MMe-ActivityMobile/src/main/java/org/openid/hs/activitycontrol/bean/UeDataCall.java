package org.openid.hs.activitycontrol.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.openid.hs.activitymobile.controller.ControlerCallTime;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * UeDataCall class is the class that will contain all the information on the
 * consumption of a call sent by the eNodeb Entity
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class UeDataCall implements Serializable {
	/**
	 * ipEnodeb is the ip address of the Enodeb that sent the Communication Call
	 * Activity
	 */
	private String ipEnodeb;
	/**
	 * portEnodeb is the port of the Enodeb that sent the Communication Call
	 * Activity
	 */
	private int portEnodeb;
	private String idUE;
	private int status;
	private int callTimeAllowed;
	private int callTimeConsumed;
	private ControlerCallTime controler;

	private boolean finished = false;
	private boolean blocked = false;
	private boolean stopped = false;

	/**
	 * Constructor with ip, port, idUE, status, callTimeAllowed,
	 * callTimeConsumed, startCallTime parameters
	 */
	public UeDataCall(String ip, int port, String idUE, int status,
			int callTimeAllowed, int callTimeConsumed, Timestamp startCallTime) {
		this.ipEnodeb = ip;
		this.portEnodeb = port;
		this.idUE = idUE;
		this.status = status;
		this.callTimeAllowed = callTimeAllowed;
		this.callTimeConsumed = callTimeConsumed;
		this.controler = new ControlerCallTime(this, startCallTime);
	}

	/**
	 * return finished boolean
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * return blocked boolean
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * return stopped boolean
	 */
	public boolean isStopped() {
		return stopped;
	}

	/**
	 * getter and setters
	 */
	public String getIp() {
		return ipEnodeb;
	}

	public void setIp(String ip) {
		this.ipEnodeb = ip;
	}

	public int getPort() {
		return portEnodeb;
	}

	public void setPort(int port) {
		this.portEnodeb = port;
	}

	public String getIdUE() {
		return idUE;
	}

	public void setIdUE(String idUE) {
		this.idUE = idUE;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCallTimeAllowed() {
		return callTimeAllowed;
	}

	public void setCallTimeAllowed(int callTimeAllowed) {
		this.callTimeAllowed = callTimeAllowed;
	}

	public int getCallTimeConsumed() {
		return callTimeConsumed;
	}

	public void setCallTimeConsumed(int callTimeConsumed) {
		this.callTimeConsumed = callTimeConsumed;
	}

	public ControlerCallTime getControler() {
		return controler;
	}

	public void setControler(ControlerCallTime controler) {
		this.controler = controler;
	}

	/**
	 * create CounterCallTime
	 * 
	 * @param startCallTime
	 */
	public void creatControlerCallTime(Timestamp startCallTime) {
		LoggerHelper
				.info("MME ENTITY : UeDataCall >> create a consumption call controller for the Ue number: "
						+ idUE);
		this.controler = new ControlerCallTime(this, startCallTime);
	}

	/**
	 * Method to start the controller
	 */
	public void startControler() {
		LoggerHelper
				.info("MME ENTITY : UeDataCall >> Start the consumption Controller for the Ue number: "
						+ idUE);
		this.controler.start();
	}

	/**
	 * method that stops the ControllerCallTime to count consumption of a UE
	 */
	public void StopControler() {
		LoggerHelper
				.info("MME ENTITY : UeDataCall >> Stop the consumption Controller for the Ue number: "
						+ idUE);
		this.controler.stop();
	}

	/*	*//**
	 * Create a ShutOff instruction with instruction factory get the sender
	 * and send the instruction created
	 */
	/*
	 * public void toSendShutOffInstruction(MMEInstruction instruction, String
	 * ip, int port){
	 * 
	 * }
	 *//**
	 * Create a block instruction with instruction factory get the sender and
	 * send the instruction created
	 */
	/*
	 * public void toBlockInstruction(MMEInstruction instruction, String ip, int
	 * port){
	 * 
	 * }
	 */
}
