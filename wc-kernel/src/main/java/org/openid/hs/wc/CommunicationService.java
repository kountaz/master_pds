package org.openid.hs.wc;

import org.openid.hs.communication.Communication;
import org.openid.hs.core.Service;

/**
 * Communication service which used to dialog with a back office.
 * @see Communication
 * @version R2
 * @author Steven
 *
 */
public interface CommunicationService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "communication";
	
	/**
	 * Defines bind port of the communication service.
	 * @param pBindPort Bind port.
	 */
	public void setBindPort(Integer pBindPort);
	/**
	 * Returns bind port of the communication service.
	 * @return Bind port of the communication service.
	 */
	public int getBindPort();
	/**
	 * Defines discovery address of the communication service.
	 * @param pDiscoveryAddress Address like multicast://IP/
	 */
	public void setDiscoveryAddress(String pDiscoveryAddress);
	/**
	 * Returns discovery address of the communication service.
	 * @return Discovery address of the communication service.
	 */
	public String getDiscoveryAddress();
}
