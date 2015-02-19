package org.openid.hs.communication.exception;

public class CommunicationException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public CommunicationException() {
		super();
	}
	public CommunicationException(String pMessage) {
		super(pMessage);
	}
	public CommunicationException(Throwable pCause) {
		super(pCause);
	}
	public CommunicationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public CommunicationException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
