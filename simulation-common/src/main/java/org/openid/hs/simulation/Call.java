package org.openid.hs.simulation;


import java.sql.Timestamp;


/**
 * Interface for call begin and call end.
 * @see Call.
 * @version R2
 * @author Fanta
 *
 */
public interface Call {
	
	public String getPhonenumber1() ;
	
	public String getPhonenumber2() ;
	
	public Timestamp getTimestamp() ;
	
	public int getEmittedBytes() ;
	
	public int getReceivedBytes() ;
	
	public String getType();
	
}
