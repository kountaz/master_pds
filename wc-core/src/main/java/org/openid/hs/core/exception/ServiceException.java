package org.openid.hs.core.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public ServiceException() {
		super();
	}
	public ServiceException(String pMessage) {
		super(pMessage);
	}
	public ServiceException(Throwable pCause) {
		super(pCause);
	}
	public ServiceException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public ServiceException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
