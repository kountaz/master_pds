package org.openid.hs.core.bean;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.openid.hs.core.Profile;
import org.openid.hs.core.Service;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.core.helper.CollectionHelper;

/**
 * Concrete Profile.
 * @see Profile
 * @version R2
 * @author Steven
 *
 */
public class ProfileBean implements Profile {
	/**
	 * Services of this profile.
	 */
	private Map<String, Service> services;
	
	public ProfileBean() {
		services = new LinkedHashMap<String, Service>();
	}
	@Override
	public boolean hasService(String pServiceName) {
		return services.containsKey(pServiceName);
	}
	@Override
	public void addService(Service pService) 
			throws ProfileException {
		
		if (hasService(pService.getName()))
			throw new ProfileException("The profile already has "
					+ "a service matches to the given name " + pService.getName());
		
		services.put(pService.getName(), pService);
	}
	@Override
	public void addServiceAt(Service pService, int pIndex)
			throws ProfileException {
		
		if (hasService(pService.getName()))
			throw new ProfileException("The profile already has "
					+ "a service matches to the given name");
		
		CollectionHelper.putAt((LinkedHashMap<String, Service>) services, pIndex, pService.getName(), pService);
	}
	@Override
	public void addServiceBefore(Service pService, String pReference)
			throws ProfileException {
		
		if (hasService(pService.getName()))
			throw new ProfileException("The profile already has "
					+ "a service matches to the given name");
		
		CollectionHelper.putBefore((LinkedHashMap<String, Service>) services, pReference, pService.getName(), pService);
	}
	@Override
	public void addServiceAfter(Service pService, String pReference)
			throws ProfileException {
		
		if (hasService(pService.getName()))
			throw new ProfileException("The profile already has "
					+ "a service matches to the given name");
		
		CollectionHelper.putAfter((LinkedHashMap<String, Service>) services, pReference, pService.getName(), pService);
	}
	@Override
	public void addServices(Service ...pServices) 
			throws ProfileException {
		
		for (Service s : pServices)
			addService(s);
	}
	@Override
	public void removeService(String pServiceName) 
			throws ProfileException {
		
		if (!hasService(pServiceName))
			throw new ProfileException("The profile hasn't "
					+ "a service matches to the given name");
		
		services.remove(pServiceName);
	}
	@Override
	public Service getService(String pServiceName) 
			throws ProfileException {
		
		if (!hasService(pServiceName))
			throw new ProfileException("The profile hasn't "
					+ "a service matches to the given name");
		
		return services.get(pServiceName);
	}
	@Override
	public Set<String> getServiceSet() {
		Set<String> set = new LinkedHashSet<String>();
		for (Service s : services.values()) {
			set.add(s.getName());
		}
		return set;
	}
}
