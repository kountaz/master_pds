package org.openid.hs.wc.controller;

import org.openid.hs.core.Profile;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.bean.WorkerComponentBean;

/**
 * Worker component factory.
 * @version R2
 * @author Steven
 *
 */
public class WCFactory {
	public static WorkerComponent createWC(String pName) 
			throws ProfileException {
		
		return createWC(pName, WCProfileFactory.createDefaultProfile());
	}
	/**
	 * Creates a new worker component.
	 * @param pName Name of the new worker component.
	 * @param pProfile Profile of the new worker component.
	 * @return A new worker component.
	 * @throws ProfileException
	 */
	public static WorkerComponent createWC(String pName, Profile pProfile) 
			throws ProfileException {
		
		return new WorkerComponentBean(pName, pProfile);
	}
	private WCFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
