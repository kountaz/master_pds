package org.openid.hs.EnodebMmeCommon;

import java.io.Serializable;

/**
 * CommunicationCallActivity interface that defines methods to expose to MME entity
 * That concerns informations about the UE call
 * @version R2
 * @author Mériem
 *
 */
public interface CommunicationCallActivity extends Serializable{
	/**
	 * return the status of a UE Call.
	 * @return int status.
	 */
	public String getIp();
	/**
	 * return the ip of a enodeb entity.
	 * @return String idUE.
	 */
	public int getPort();
	/**
	 * return the port of a UE Call.
	 * @return int status.
	 */
	public int getActivity();
	/**
	 * return the idUE of a UE Call.
	 * @return String idUE.
	 */
	public String getIdUE();
	/**
	 * return the event of a UE Call.
	 * @return CallEvent.
	 */
	public CallEvent getEvenement();
}
