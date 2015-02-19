package org.openid.hs.MmeHssCommon.bean;

import java.sql.Timestamp;

import org.openid.hs.MmeHssCommon.UeBannedInfo;

/**
 * UeBannedInfoBean class implements UeBannedInfo represents the Ue which are prohibited to call
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class UeBannedInfoBean implements UeBannedInfo{
	private String idUE;
	private Timestamp time;

	public UeBannedInfoBean(String idUE, Timestamp time) {
		this.idUE = idUE;
		this.time = time;
	}

	public String getIdUE() {
		return idUE;
	}

	public void setIdUE(String idUE) {
		this.idUE = idUE;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}
