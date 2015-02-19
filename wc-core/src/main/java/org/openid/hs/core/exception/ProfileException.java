package org.openid.hs.core.exception;

public class ProfileException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public ProfileException() {
		super();
	}
	public ProfileException(String pMessage) {
		super(pMessage);
	}
	public ProfileException(Throwable pCause) {
		super(pCause);
	}
	public ProfileException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public ProfileException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
