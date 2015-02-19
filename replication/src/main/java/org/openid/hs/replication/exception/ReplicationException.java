package org.openid.hs.replication.exception;

public class ReplicationException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public ReplicationException() {
		super();
	}
	public ReplicationException(String pMessage) {
		super(pMessage);
	}
	public ReplicationException(Throwable pCause) {
		super(pCause);
	}
	public ReplicationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public ReplicationException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
