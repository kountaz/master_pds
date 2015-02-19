package org.openid.hs.activitydata.bean;

/**
 * ConsumptionInfo class contains consumption info about an Ue Call
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class ConsumptionCallInfo {

	private String idUE;
	private int CallTimeConsumed;
	private int CallTimeAllowed;

	public ConsumptionCallInfo(String idUE, int callTimeConsumed,
			int callTimeAllowed) {

		this.idUE = idUE;
		CallTimeConsumed = callTimeConsumed;
		CallTimeAllowed = callTimeAllowed;
	}
	/**
	 * Getters and setters
	 * 
	 */
	public String getIdUE() {
		return idUE;
	}

	public void setIdUE(String idUE) {
		this.idUE = idUE;
	}

	public int getCallTimeConsumed() {
		return CallTimeConsumed;
	}

	public void setCallTimeConsumed(int callTimeConsumed) {
		CallTimeConsumed = callTimeConsumed;
	}

	public int getCallTimeAllowed() {
		return CallTimeAllowed;
	}

	public void setCallTimeAllowed(int callTimeAllowed) {
		CallTimeAllowed = callTimeAllowed;
	}

}
