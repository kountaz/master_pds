package org.openid.hs.wc;

import org.openid.hs.core.Service;

/**
 * 
 * @version R3
 * @author Fanta, Steven
 *
 */
public interface FrontEndService extends Service {
	public void setPort(Integer pPort);
	public int getPort();
	public void setPortReceiver(Integer pPort);
	public int getPortReceiver();
	public void setTarget(String pTarget);
	public String getTarget();
}
