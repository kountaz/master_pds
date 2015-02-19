package org.openid.hs.core.controller;

import org.openid.hs.core.Profile;
import org.openid.hs.core.Service;
import org.openid.hs.core.bean.ProfileBean;
import org.openid.hs.core.exception.ProfileException;

/**
 * Profile factory.
 * @version R2
 * @author Steven
 *
 */
public class ProfileFactory {
	/**
	 * Creates a profile contains given services.
	 * @param services Many services which will be contains 
	 * in the new profile.
	 * @return A new profile.
	 * @throws ProfileException When a service was given two times or more.
	 */
	public static Profile createProfile(Service ...pServices) 
			throws ProfileException {
		
		Profile profile = new ProfileBean();
		profile.addServices(pServices);
		return profile;
	}
	private ProfileFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
