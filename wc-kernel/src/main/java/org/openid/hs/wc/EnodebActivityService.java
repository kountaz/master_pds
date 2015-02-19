package org.openid.hs.wc;

import org.openid.hs.core.Service;

/**
 *
 * @version R2
 * @author Meryem, Steven
 *
 */
public interface EnodebActivityService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "activity.enodeb";
	/**
	 * Defines 
	 * @param pPort 
	 */
	public void setPort(Integer pPort);
	/**
	 * Returns 
	 * @return 
	 */
	public Integer getPort();
}
