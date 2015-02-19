package org.openid.hs.feature.bean;

import java.io.Serializable;

public class ObjectForSIGNALSTOP implements Serializable{

	private String signal="stop";
	public ObjectForSIGNALSTOP() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

}
