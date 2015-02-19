package org.openid.hs.core;

import java.util.Set;

import org.openid.hs.core.exception.ProfileException;

/**
 * Interface of a Profile object which manages a set of services.
 * @version R2
 * @author Steven
 * 
 */
public interface Profile {
	/**
	 * Returns true if the profile has a service that matches 
	 * to the given name.
	 * @return True if the profile has a service that matches 
	 * to the given name.
	 */
	public boolean hasService(String pServiceName);
	/**
	 * Adds a service in the profile.
	 * @param pService A service to add in the profile.
	 * @throws ProfileException When the profile already has 
	 * a service matches with pService.
	 */
	public void addService(Service pService) 
			throws ProfileException;
	/**
	 * Adds a service at a given position in the profile.
	 * @param pService A service to add in the profile.
	 * @param pIndex Position to insert pService in the profile.
	 * @throws ProfileException When the profile already has 
	 * a service matches with pService.
	 */
	public void addServiceAt(Service pService, int pIndex) 
			throws ProfileException;
	/**
	 * Adds a service before an exists service in the profile.
	 * @param pService A service to add in the profile.
	 * @param pReference Name of the service to insert before it.
	 * @throws ProfileException When the profile already has 
	 * a service matches with pService.
	 */
	public void addServiceBefore(Service pService, String pReference) 
			throws ProfileException;
	/**
	 * Adds a service after an exists service in the profile.
	 * @param pService A service to add in the profile.
	 * @param pReference Name of the service to insert after it.
	 * @throws ProfileException When the profile already has 
	 * a service matches with pService.
	 */
	public void addServiceAfter(Service pService, String pReference) 
			throws ProfileException;
	/**
	 * Adds many services in the profile.
	 * @param pServices Collection of services to insert in the profile.
	 * @throws ProfileException When the profile already has a service  
	 * matches with a service of the given collection.
	 */
	public void addServices(Service ...pServices) 
			throws ProfileException;
	/**
	 * Removes a service in the profile.
	 * @param pServiceName Name of a service to remove 
	 * in the profile.
	 * @throws ProfileException When the profile hasn't a service 
	 * matches to the given name.
	 */
	public void removeService(String pServiceName) 
			throws ProfileException;
	/**
	 * Returns a service of the profile.
	 * @param pServiceName Name of a service to collect.
	 * @throws ProfileException When the profile hasn't a service 
	 * matches to the given name.
	 */
	public Service getService(String pServiceName) 
			throws ProfileException;
	/**
	 * Returns service set of the profile.
	 * @return Service set of the profile.
	 */
	public Set<String> getServiceSet();
}
