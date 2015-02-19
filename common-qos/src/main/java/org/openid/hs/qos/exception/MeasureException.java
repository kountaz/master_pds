package org.openid.hs.qos.exception;

/**
 * @see MeasureException.
 * @version R2
 * @author Victor
 *
 */

public class MeasureException extends Exception
{
	private static final long serialVersionUID = 1L;

	public MeasureException() 
	{
		super();
	}
	
	public MeasureException(String pMessage) 
	{
		super(pMessage);
	}
	
	public MeasureException(Throwable pCause) 
	{
		super(pCause);
	}
	
	public MeasureException(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	public MeasureException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}