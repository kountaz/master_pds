package org.openid.hs.wc;

import org.openid.hs.core.Status;

/**
 * Interface of a WorkerComponent status.
 * @version R2
 * @author Steven
 *
 */
public interface WCStatus extends Status {
	/**
	 * Returns WorkerComponent object associated with the status.
	 * @return WorkerComponent object associated with the status.
	 */
	public WorkerComponent getWc();
}
