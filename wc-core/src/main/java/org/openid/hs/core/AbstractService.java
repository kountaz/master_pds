package org.openid.hs.core;

import org.openid.hs.core.exception.ServiceException;

/**
 * Abstract implementation of Service which defines default 
 * method implementations.
 * @see Service
 * @version R2
 * @author Steven
 *
 */
public class AbstractService implements Service {
	/**
	 * Name of this service.
	 */
	private String name;
	/**
	 * Indicates if this service is running.
	 */
	private boolean started;
	
	public AbstractService(String pName) {
		name = pName;
		started = false;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void start() throws ServiceException {
		started = true;
	}
	@Override
	public void stop() throws ServiceException {
		started = false;
	}
	@Override
	public boolean isStarted() {
		return started == true;
	}
}
