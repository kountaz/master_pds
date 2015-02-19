package org.openid.hs.wc;

import org.openid.hs.core.Service;

/**
 * 
 * @version R2
 * @author Victor, Steven
 *
 */
public interface QosService extends Service {
	/**
	 * Defines 
	 * @param pPath
	 */
	public void setRulesFile(String pPath);
	/**
	 * Returns 
	 * @return 
	 */
	public String getRulesFile();

}
