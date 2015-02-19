package org.openid.hs.replication.bean;

import java.io.Serializable;

public class ReplicationObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String WC_ID;
	private String key;
	private Object context;
	
	public ReplicationObject(String pkey , Object pContext , String WC_ID)	{
		this.key = pkey;
		this.context = pContext;
		this.WC_ID = WC_ID;
	}

	public String getWC_ID() {
		return WC_ID;
	}

	public void setWC_ID(String wC_ID) {
		WC_ID = wC_ID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}
	
	
}
