package org.openid.hs.datagrid.exception;

public class DatagridException extends Exception {
	private static final long serialVersionUID = -7791907904056382388L;
	public DatagridException() {
		super();
	}
	public DatagridException(String pMessage) {
		super(pMessage);
	}
	public DatagridException(Throwable pCause) {
		super(pCause);
	}
	public DatagridException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
	public DatagridException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
