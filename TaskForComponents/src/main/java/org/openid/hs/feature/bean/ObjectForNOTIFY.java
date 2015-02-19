package org.openid.hs.feature.bean;

import java.io.Serializable;

public class ObjectForNOTIFY implements Serializable{
	private Integer id= 0;
	private String notify ="ok";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public ObjectForNOTIFY() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
