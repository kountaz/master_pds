package org.openid.hs.rt.support.exception;

public class PriorityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PriorityException() {
		super();
	}
	public PriorityException(String pMessage) {
		super(pMessage);
	}
	public PriorityException(Throwable pCause) {
		super(pCause);
	}
	public PriorityException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public PriorityException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
