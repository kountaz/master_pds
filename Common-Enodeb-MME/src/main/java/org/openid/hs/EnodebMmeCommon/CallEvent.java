package org.openid.hs.EnodebMmeCommon;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * CallEvent interface that defines methods to expose to MME entity
 * That concerns the event of a call if it's beginning or end of call
 * @version R2
 * @author Mériem
 *
 */
public interface CallEvent extends Serializable{
	/**
	 * return the idEvent of a UE Call.
	 * @return int idEvent.
	 */
	public int getIdEvent();
	/**
	 * return the event time of a UE Call.
	 * @return TimesStamp idEvent.
	 */
	public Timestamp getTimeEvent();
}
