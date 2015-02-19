package org.openid.hs.core;

import org.openid.hs.core.exception.ServiceException;

/**
 * Interface of a Service object which can be managed by a Profile 
 * object and launched by a ServiceManager object.
 * @version R2
 * @author Steven
 *
 */
public interface Service {
	/**
	 * Returns name of the service.
	 * @return Name of the service.
	 */
	public String getName();
	/**
	 * Starts the service.
	 * @throws ServiceException When the service can't be started.
	 */
	public void start() throws ServiceException;
	/**
	 * Stops the service.
	 * @throws ServiceException When the service can't be stopped.
	 */
	public void stop() throws ServiceException;
	/**
	 * Returns true if the service is started.
	 * @return True if the service is started.
	 */
	public boolean isStarted();
}
