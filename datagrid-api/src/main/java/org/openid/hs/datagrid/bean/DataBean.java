package org.openid.hs.datagrid.bean;

import org.openid.hs.datagrid.Data;

/**
 * Concrete data.
 * @see Data
 * @version R3
 * @author steven
 *
 */
public class DataBean implements Data {
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -727211778635950070L;
	/**
	 * Key string of this data.
	 */
	private String key;
	/**
	 * Value object of this data.
	 */
	private Object value;
	/**
	 * Expiration delay of this data.
	 */
	private int expiration;
	
	public DataBean(String pKey, Object pValue, int pExpiration) {
		key = pKey;
		value = pValue;
		expiration = pExpiration;
	}
	@Override
	public String getKey() {
		return key;
	}
	@Override
	public Object getValue() {
		return value;
	}
	@Override
	public int getExpiration() {
		return expiration;
	}
	@Override
	public String toString() {
		return String.format("(%s) %s", value.getClass().getName(), value.toString());
	}
}
