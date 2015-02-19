package org.openid.hs.core.bean;

import java.sql.Timestamp;
import java.util.Date;

import org.openid.hs.core.Status;

/**
 * Concrete Status.
 * @see Status
 * @version R2
 * @author Steven
 *
 */
public class StatusBean implements Status {
	/**
	 * Name of this status.
	 */
	private String name;
	/**
	 * Timestamp of this status.
	 */
	private Timestamp timestamp;
	
	public StatusBean(String pName) {
		name = pName;
		timestamp = new Timestamp(new Date().getTime());
	}
	@Override
	public String getName() {
		return name;
	}
	/**
	 * Defines the name of this status.
	 * @param pName The new name for this status.
	 */
	public void setName(String pName) {
		name = pName;
	}
	@Override
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * Defines the timestamp of this status.
	 * @param pName The timestamp name for this status.
	 */
	public void setTimestamp(Timestamp pTimestamp) {
		timestamp = pTimestamp;
	}
}
