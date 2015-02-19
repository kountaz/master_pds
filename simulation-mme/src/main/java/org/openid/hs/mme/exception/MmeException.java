package org.openid.hs.mme.exception;

public class MmeException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public MmeException() {
		super();
	}
	public MmeException(String pMessage) {
		super(pMessage);
	}
	public MmeException(Throwable pCause) {
		super(pCause);
	}
	public MmeException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public MmeException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
