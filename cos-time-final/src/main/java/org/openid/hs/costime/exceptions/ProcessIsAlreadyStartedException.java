package org.openid.hs.costime.exceptions;

public class ProcessIsAlreadyStartedException extends Exception 
{
	
	/**
	 * @author: OPENID
	 * @version: 1.0
	 * Registration service allows to register the callback objects which are instantiated by clients. 
	 */
	private static final long serialVersionUID = -6153375418820400797L;
	

	public ProcessIsAlreadyStartedException()
	{
		super();
	}
	
	
	public ProcessIsAlreadyStartedException(String pMessage) 
	{
		super(pMessage);
	}
	
	
	public ProcessIsAlreadyStartedException(Throwable pCause) 
	{
		super(pCause);
	}
	
	
	public ProcessIsAlreadyStartedException(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	
	public ProcessIsAlreadyStartedException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
