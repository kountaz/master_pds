package org.openid.hs.embeddedprocess;

public class EmbeddedProcessException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public EmbeddedProcessException() {
		super();
	}
	public EmbeddedProcessException(String pMessage) {
		super(pMessage);
	}
	public EmbeddedProcessException(Throwable pCause) {
		super(pCause);
	}
	public EmbeddedProcessException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public EmbeddedProcessException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
