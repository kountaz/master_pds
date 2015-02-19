package org.openid.hs.embed.exceptions;

public class EmbedServerIsAlreadyStarted extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4037025882978148755L;


	public EmbedServerIsAlreadyStarted() 
	{
		super();
	}
	
	
	public EmbedServerIsAlreadyStarted(String pMessage) 
	{
		super(pMessage);
	}
	
	
	public EmbedServerIsAlreadyStarted(Throwable pCause) 
	{
		super(pCause);
	}
	
	
	public EmbedServerIsAlreadyStarted(String pMessage, Throwable pCause) 
	{
		super(pMessage, pCause);
	}
	
	
	public EmbedServerIsAlreadyStarted(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) 
	{
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
	}
}
