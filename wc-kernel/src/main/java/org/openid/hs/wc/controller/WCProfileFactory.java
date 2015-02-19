package org.openid.hs.wc.controller;

import org.openid.hs.core.Profile;
import org.openid.hs.core.Service;
import org.openid.hs.core.controller.ProfileFactory;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.bean.KernelServiceBean;

/**
 * Worker Component Profile factory.
 * @version R2
 * @author Steven
 *
 */
public class WCProfileFactory {
	/**
	 * Creates a default worker component profile.
	 * @return A new profile with default services.
	 * @throws ProfileException
	 */
	public static Profile createDefaultProfile() 
			throws ProfileException {
		
		KernelServiceBean kernel = new KernelServiceBean();
		Profile profile = ProfileFactory.createProfile(kernel);
		return profile;
	}
	/**
	 * Creates a new worker component profile which contains given services.
	 * @param pServices Given services.
	 * @return A new worker component profile which contains given services.
	 * @throws ProfileException When a service was given two times or more.
	 */
	public static Profile createProfile(Service ...pServices) 
			throws ProfileException {
		
		Profile profile = ProfileFactory.createProfile(pServices);
		forceKernel(profile);
		return profile;
	}
	
	public static void forceKernel(Profile pProfile) throws ProfileException {
		KernelService kernel;
		if (!pProfile.hasService(KernelService.SERVICE_NAME)) {
			kernel = new KernelServiceBean();
		} else {
			kernel = (KernelService) pProfile.getService(KernelService.SERVICE_NAME);
			pProfile.removeService(KernelService.SERVICE_NAME);
		}
		pProfile.addServiceAt(kernel, 0);
	}
	
	private WCProfileFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
