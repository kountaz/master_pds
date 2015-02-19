package org.openid.hs.embed.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.openid.hs.core.helper.ResourceHelper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;



public class AdministrationViewHandler implements HttpHandler
{

	
	
	
	public void handle(HttpExchange exchange) throws IOException 
	{
		//Define headers.
		Headers headers = exchange.getResponseHeaders();
	    headers.add("Content-Type", "text/html; charset=utf-8");
	    
	    //If there are many operations requests, launch them.
	    if(exchange.getRequestMethod().equalsIgnoreCase("post"))
	    {
	    	
	    }
		//System.out.println("===>" +  exchange.getRequestURI());
		
		
	    //Define content.
		String responseMsg = "", responseLine = null;
		Properties properties = ResourceHelper.getProperties("/configuration.properties");
		FileReader reader = null;
		BufferedReader buffer = null;
		if(exchange.getRequestURI().toString().equalsIgnoreCase("/supervisor/tabulous.css"))
		{
			reader = new FileReader(properties.getProperty("CSSFilename"));
			buffer = new BufferedReader(reader);
			while((responseLine = buffer.readLine()) != null)
			{
				responseMsg += responseLine + " \n";
			}
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    //os.flush();
		    buffer.close();
		    os.close();
		}
		else if(exchange.getRequestURI().toString().equalsIgnoreCase("/supervisor/tabulous.js"))
		{
			System.out.println("OK 2");
			reader = new FileReader(properties.getProperty("JSFilenameExt1"));
			buffer = new BufferedReader(reader);
			while((responseLine = buffer.readLine()) != null)
			{
				responseMsg += responseLine + " \n";
			}
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    //os.flush();
		    buffer.close();
		    os.close();
		}
		else if(exchange.getRequestURI().toString().equalsIgnoreCase("/supervisor/tabulous.min.js"))
		{
			System.out.println("OK 3");
			reader = new FileReader(properties.getProperty("JSFilenameExt2"));
			buffer = new BufferedReader(reader);
			while((responseLine = buffer.readLine()) != null)
			{
				responseMsg += responseLine + " \n";
			}
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    //os.flush();
		    buffer.close();
		    os.close();
		}
		else if(exchange.getRequestURI().toString().equalsIgnoreCase("/supervisor/jquery-2.1.1.min.js"))
		{
			reader = new FileReader(properties.getProperty("JQueryFilename"));
			buffer = new BufferedReader(reader);
			while((responseLine = buffer.readLine()) != null)
			{
				responseMsg += responseLine + " \n";
			}
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    buffer.close();
		    os.close();
		}
		else
		{
		
			FileReader HTMLFile = new FileReader(properties.getProperty("filename"));
			buffer = new BufferedReader(HTMLFile);
			while((responseLine = buffer.readLine()) != null)
			{
				responseMsg += responseLine + " \n";
			}
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    buffer.close();
		    os.close();
		}
	}

}
