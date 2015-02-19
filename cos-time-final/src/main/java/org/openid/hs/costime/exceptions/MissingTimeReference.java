package org.openid.hs.costime.exceptions;



public class MissingTimeReference extends Exception
{

	/**
	 * @author: OPENID - Patrick
	 * @version: 1.0
	 * Registration service allows to register the callback objects which are instantiated by clients. 
	 */
	private static final long serialVersionUID = 2276157960882921600L;
	
	public MissingTimeReference()
	{
		super();
	}
	
	
	public MissingTimeReference(String pMessage) 
	{
		super(pMessage);
	}
	
	
	public MissingTimeReference(Throwable pCause) 
	{
		super(pCause);
	}
	
	
	public MissingTimeReference(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	
	public MissingTimeReference(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
	
}
