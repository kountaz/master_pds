package org.openid.hs.hss.exception;

public class HssException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public HssException() {
		super();
	}
	public HssException(String pMessage) {
		super(pMessage);
	}
	public HssException(Throwable pCause) {
		super(pCause);
	}
	public HssException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public HssException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
