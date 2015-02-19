package org.openid.hs.MmeHssCommon.bean;

import org.openid.hs.MmeHssCommon.ConsumptionInfoMME;
/**
 * ConsumptionInfoMMEBean class that implements ConsumptionInfoMME
 * @version R2
 * @author Mériem
 *
 */
public class ConsumptionInfoMMEBean implements ConsumptionInfoMME {

	private String idUE;
	private int callTimeConsumed;
	

	public ConsumptionInfoMMEBean(String idUE, int callTimeConsumed ) {
		this.idUE = idUE;
		this.callTimeConsumed = callTimeConsumed ;
	}

	public String getIdUE() {
		return idUE;
	}

	public void setIdUE(String idUE) {
		this.idUE = idUE;
	}

	public int getCallTimeConsumed() {
		return callTimeConsumed;
	}

	public void setCallTimeConsumed(int callTimeConsumed) {
		this.callTimeConsumed = callTimeConsumed;
	}

}
