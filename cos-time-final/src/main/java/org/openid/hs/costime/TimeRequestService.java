package org.openid.hs.costime;


import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author: OPENID
 * @version: 1.0
 * TimeRequestService allows to synchronize the clients in the framework. 
 */


@WebService
public interface TimeRequestService
{
	/**
	 * The getTimeReference method allows to cause the broadcast of time to the clients on demand one of them.
	 *  
	 */
	
	@WebMethod
	Date getTimeReference(); 
}
