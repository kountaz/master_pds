package org.openid.hs.EnodebMmeCommon.bean;
import java.io.Serializable;
import java.net.InetAddress;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;


/**
 * CommunicationCallActivityBean class represents the implementation of the 
 * CommunicationCallActivity interface, this class can set all parameters of UE Call 
 * that the MME can uses to control the activity of the mobile call
 * @see CommunicationCallActivity
 * @version R2
 * @author Mériem
 *
 */
public class CommunicationCallActivityBean implements CommunicationCallActivity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ip;
	private int port;
	private String idUE;
	private int status;
	private CallEventBean event;
	/**
	 * Constructor defines the idUE, the status and the event of an UE Call
	 * @param String idUE, int status, CallEvent event.
	 */
	public CommunicationCallActivityBean(String ip,int port,String idUE, int status, CallEventBean event){
		this.ip = ip;
		this.port = port;
		this.idUE = idUE;
		this.status = status;
		this.event = event;
	}
	public int getActivity() {
		return status;
	}
	/**
	 * Defines status of the call communication.
	 * @param int status.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIdUE() {
		return idUE;
	}
	/**
	 * Defines idUE of the UE in the call communication.
	 * @param int idUE.
	 */
	public void setIdUE(String idUE) {
		this.idUE = idUE;
	}
	
	public CallEventBean getEvenement() {
		return event;
	}
	/**
	 * Defines event  of the call communication of the UE .
	 * @param CallEvent event.
	 */
	public void setEvenement(CallEventBean event) {
		this.event = event;
	}
	
	public int getPort() {
		return this.port;
	}
	/**
	 * Defines port of the Enodeb .
	 * @param int port.
	 */
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return this.ip;
	}
	/**
	 * Defines ip address of the Enodeb .
	 * @param InetAddress ip.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
