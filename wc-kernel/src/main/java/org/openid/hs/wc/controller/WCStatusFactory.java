package org.openid.hs.wc.controller;

import org.openid.hs.core.Status;
import org.openid.hs.wc.WCStatus;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.bean.WCStatusBean;

/**
 * WC status factory.
 * @version R2
 * @author Steven
 *
 */
public class WCStatusFactory {
	/**
	 * Creates new initialization status.
	 * @param pWc Worker component of the new status.
	 * @return New initialization status.
	 */
	public static WCStatus createInitStatus(WorkerComponent pWc) {
		return new WCStatusBean(pWc, Status.INIT);
	}
	/**
	 * Creates new start status.
	 * @param pWc Worker component of the new status.
	 * @return New start status.
	 */
	public static WCStatus createStartStatus(WorkerComponent pWc) {
		return new WCStatusBean(pWc, Status.START);
	}
	/**
	 * Creates new stop status.
	 * @param pWc Worker component of the new status.
	 * @return New stop status.
	 */
	public static WCStatus createStopStatus(WorkerComponent pWc) {
		return new WCStatusBean(pWc, Status.STOP);
	}
	private WCStatusFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
