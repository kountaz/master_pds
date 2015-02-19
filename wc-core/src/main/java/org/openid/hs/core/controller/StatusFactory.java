package org.openid.hs.core.controller;

import org.openid.hs.core.Status;
import org.openid.hs.core.bean.StatusBean;

/**
 * Status factory.
 * @version R2
 * @author Steven
 *
 */
public class StatusFactory {
	/**
	 * Creates a new status.
	 * @param pName Name of the new status.
	 * @return A new status.
	 */
	public static Status createStatus(String pName) {
		return new StatusBean(pName);
	}
	private StatusFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
