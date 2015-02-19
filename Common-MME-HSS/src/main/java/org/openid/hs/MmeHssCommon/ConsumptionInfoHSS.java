package org.openid.hs.MmeHssCommon;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * ConsumptionInfoHSS interface that defines methods to expose to MME entity
 * That concerns informations about the UE call consumption
 * @version R2
 * @author Mériem
 *
 */
public interface ConsumptionInfoHSS extends Serializable{
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
	/**
	 * return the Call time allowed of the Ue 
	 * @return int.
	 */
	public int getCallTimeAllowed();
	/**
	 * return the Call time consumed of the Ue 
	 * @return int.
	 */
	public int getCallTimeConsumed();
}
