package org.openid.hs.core.exception;

public class BuildingException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public BuildingException() {
		super();
	}
	public BuildingException(String pMessage) {
		super(pMessage);
	}
	public BuildingException(Throwable pCause) {
		super(pCause);
	}
	public BuildingException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public BuildingException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
