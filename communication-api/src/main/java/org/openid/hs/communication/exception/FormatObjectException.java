package org.openid.hs.communication.exception;

public class FormatObjectException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public FormatObjectException() {
		super();
	}
	public FormatObjectException(String pMessage) {
		super(pMessage);
	}
	public FormatObjectException(Throwable pCause) {
		super(pCause);
	}
	public FormatObjectException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public FormatObjectException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
