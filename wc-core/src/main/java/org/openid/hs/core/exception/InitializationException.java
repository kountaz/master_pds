package org.openid.hs.core.exception;

public class InitializationException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public InitializationException() {
		super();
	}
	public InitializationException(String pMessage) {
		super(pMessage);
	}
	public InitializationException(Throwable pCause) {
		super(pCause);
	}
	public InitializationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public InitializationException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
