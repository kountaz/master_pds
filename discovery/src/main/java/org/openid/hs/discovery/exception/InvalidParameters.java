package org.openid.hs.discovery.exception;

public class InvalidParameters extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidParameters() {}
 
	public InvalidParameters(String message) {
		super(message);
	}
	
	public InvalidParameters(Throwable cause) {  
		super(cause); 
	}
 
	public InvalidParameters(String message, Throwable cause) {  
		super(message, cause); 
	} 
}
