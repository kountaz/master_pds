package org.openid.hs.core;

import java.net.UnknownServiceException;

import org.openid.hs.core.exception.ServiceException;

/**
 * Interface of a ServiceManager object which can start and stop 
 * services managed by a Profile object.
 * @version R2
 * @author Steven
 * 
 */
public interface ServiceManager {
	/**
	 * Returns Profile object of the ServiceManager.
	 * @return Profile object of the ServiceManager.
	 */
	public Profile getProfile();
	/**
	 * Starts a profile service's matches to the given name.
	 * @param pServiceName Name of a profile service's to start.
	 * @throws ServiceException When the service can't be started.
	 * @throws UnknownServiceException When no profile service's 
	 * matches to the given name.
	 */
	public void startService(String pServiceName) 
			throws ServiceException;
	/**
	 * Stops a profile service's matches to the given name.
	 * @param pServiceName Name of a profile service's to stop.
	 * @throws ServiceException When the service can't be stopped.
	 * @throws UnknownServiceException When no profile service's 
	 * matches to the given name.
	 */
	public void stopService(String pServiceName) 
			throws ServiceException;
	/**
	 * Defines a parameter of a service.
	 * @param pServiceName Name of a service.
	 * @param pKey Name of a parameter.
	 * @param pValue Value to define the parameter.
	 * @throws ServiceException When the parameter can't be 
	 * defined.
	 */
	public void setParameter(String pServiceName, String pKey, Object pValue) 
			throws ServiceException;
	/**
	 * Returns a parameter value of a service.
	 * @param pServiceName Name of a service.
	 * @param pKey Name of a parameter.
	 * @return A parameter value of a service.
	 * @throws ServiceException When the parameter can't be 
	 * collected.
	 */
	public Object getParameter(String pServiceName, String pKey) 
			throws ServiceException;
}
