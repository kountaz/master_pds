package org.openid.hs.wc;

import org.openid.hs.core.Service;

public interface DiscoveryService extends Service/*, ILoaderMaterial*/ {
	/**
	 * Defines 
	 * @param pDatabasePort 
	 */
	public void setReferential(String pFilePath);
	/**
	 * Returns 
	 * @return 
	 */
	public String getReferential();
}
