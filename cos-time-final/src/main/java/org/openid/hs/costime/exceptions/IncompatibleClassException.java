package org.openid.hs.costime.exceptions;



public class IncompatibleClassException extends Exception
{

	/**
	 * @author: OPENID
	 * @version: 1.0
	 * Registration service allows to register the callback objects which are instantiated by clients. 
	 */
	private static final long serialVersionUID = -3663540318907872901L;
	

	public IncompatibleClassException()
	{
		super();
	}
	
	
	public IncompatibleClassException(String pMessage) 
	{
		super(pMessage);
	}
	
	
	public IncompatibleClassException(Throwable pCause) 
	{
		super(pCause);
	}
	
	
	public IncompatibleClassException(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	
	public IncompatibleClassException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
