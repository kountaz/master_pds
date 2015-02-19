package org.openid.hs.wc.bean;

import org.openid.hs.core.bean.StatusBean;
import org.openid.hs.wc.WCStatus;
import org.openid.hs.wc.WorkerComponent;

/**
 * Concrete WorkerComponent status.
 * @see WCStatus
 * @version R2
 * @author Steven
 *
 */
public class WCStatusBean extends StatusBean implements WCStatus {
	/**
	 * WorkerComponent of the status.
	 */
	private WorkerComponent wc;
	
	public WCStatusBean(WorkerComponent pWc, String pName) {
		super(pName);
		wc = pWc;
	}
	/**
	 * Defines WorkerComponent of the status.
	 * @param pWc
	 */
	public void setWc(WorkerComponent pWc) {
		wc = pWc;
	}
	@Override
	public WorkerComponent getWc() {
		return wc;
	}
	@Override
	public String toString() {
		return String.format("WCStatus of %s : %s at %s", wc.getName(), getName(), getTimestamp());
	}
}
