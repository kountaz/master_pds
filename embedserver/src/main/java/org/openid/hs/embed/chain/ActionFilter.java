package org.openid.hs.embed.chain;

import java.io.IOException;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class ActionFilter extends Filter 
{

	
	public String description() 
	{
		return "This class allows able to enable or disable operation depending on the client request.";
	}

	
	public void doFilter(HttpExchange exchange, Chain chain) throws IOException 
	{
		
		
		chain.doFilter(exchange);
	}
}
