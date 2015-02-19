package org.openid.hs.MmeHssCommon;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * ConsumptionInfoMME interface that defines methods to expose to HSS entity
 * That concerns informations about the current UE call consumption
 * @version R2
 * @author Mériem
 *
 */
public interface ConsumptionInfoMME extends Serializable{
	/**
	 * return the Id Ue 
	 * @return String id.
	 */
	public String getIdUE();
	/**
	 * return the Call time consumed of the Ue 
	 * @return int.
	 */
	public int getCallTimeConsumed();
}
