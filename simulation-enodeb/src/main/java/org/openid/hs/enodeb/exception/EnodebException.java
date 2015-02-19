package org.openid.hs.enodeb.exception;

public class EnodebException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public EnodebException() {
		super();
	}
	public EnodebException(String pMessage) {
		super(pMessage);
	}
	public EnodebException(Throwable pCause) {
		super(pCause);
	}
	public EnodebException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public EnodebException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
