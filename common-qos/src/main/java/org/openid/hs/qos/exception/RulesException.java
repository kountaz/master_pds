package org.openid.hs.qos.exception;

/**
 * @see RulesException.
 * @version R2
 * @author Victor
 *
 */

public class RulesException extends Exception
{
	private static final long serialVersionUID = 1L;

	public RulesException() 
	{
		super();
	}
	
	public RulesException(String pMessage) 
	{
		super(pMessage);
	}
	
	public RulesException(Throwable pCause) 
	{
		super(pCause);
	}
	
	public RulesException(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	public RulesException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}