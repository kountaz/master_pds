package org.openid.hs.wc;

import org.openid.hs.core.Service;

/**
 *
 * @version R2
 * @author Yannis, Steven
 *
 */
public interface FailDetectorService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "faildetector";
	
	public void setPort(Integer pPort);
	public Integer getPort();
}
