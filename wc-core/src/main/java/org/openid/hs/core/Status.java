package org.openid.hs.core;

import java.sql.Timestamp;

/**
 * Interface of a Status object.
 * @version R2
 * @author Steven
 *
 */
public interface Status {
	/**
	 * Default name of initialization status.
	 */
	public static final String INIT = "init";
	/**
	 * Default name of start status.
	 */
	public static final String START = "start";
	/**
	 * Default name of stop status.
	 */
	public static final String STOP = "stop";
	/**
	 * Returns name of the status.
	 * @return Name of the status.
	 */
	public String getName();
	/**
	 * Returns timestamp of the status.
	 * @return Timestamp of the status.
	 */
	public Timestamp getTimestamp();
}
