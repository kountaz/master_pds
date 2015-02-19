package org.openid.hs.MmeHssCommon;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * RequestInfoMME interface that defines methods to expose to HSS entity
 * That concerns informations about the UE call
 * @version R2
 * @author Mériem
 *
 */
public interface RequestInfoMME extends Serializable{
	/**
	 * return the ip address of the MME
	 * @return String ip.
	 */
	public String getIpMMe();
	/**
	 * return the port of the MME
	 * @return int port.
	 */
	public int getPortMMe();
	/**
	 * return the ip address of the eNodeb which sends
	 * the call information to the MME entity
	 * @return String ip.
	 */
	public String getIpEnodeb();
	/**
	 * return the port of the eNodeb which sends
	 * the call information to the MME entity
	 * @return int port.
	 */
	public int getPortEnodeb();
	/**
	 * return the Id Ue 
	 * @return String id.
	 */
	public String getIdUE();
	/**
	 * return the Ue status 
	 * @return int id.
	 */
	public int getIdStatus();
	/**
	 * return the Start Call time of the Ue 
	 * @return Timestamp.
	 */
	public Timestamp getStartCallTime();
}
