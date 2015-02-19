package org.openid.hs.datagrid;

import java.io.Serializable;

/**
 * Interface of data.
 * @version R3
 * @author Steven, Victor
 *
 */
public interface Data extends Serializable {
	/**
	 * Returns key of the data.
	 * @return Key string.
	 */
	public String getKey();
	/**
	 * Returns value of the data.
	 * @return Value object.
	 */
	public Object getValue();
	/**
	 * Returns expiration delay.
	 * @return Expiration delay in seconds.
	 */
	public int getExpiration();
}
