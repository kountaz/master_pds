package org.openid.hs.MmeHssCommon;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ConsumptionInfoHSS interface that defines methods to expose to HSS entity
 * That concerns informations about the Ue which are prohibited to call
 * @version R2
 * @author Mériem
 *
 */
public interface UeBannedInfo extends Serializable{

	public String getIdUE();
	public Timestamp getTime();
}
