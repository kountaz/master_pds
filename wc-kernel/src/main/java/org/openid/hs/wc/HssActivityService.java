package org.openid.hs.wc;

import org.openid.hs.core.Service;

/**
 *
 * @version R2
 * @author Meryem, Steven
 *
 */
public interface HssActivityService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "activity.hss";

	void setPort(Integer pPort);

	Integer getPort();
}
