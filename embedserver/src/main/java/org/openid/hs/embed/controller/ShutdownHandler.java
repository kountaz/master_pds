package org.openid.hs.embed.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import org.openid.hs.bootstrap.WCConsoleAPI;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.embed.logging.HTMLFormatter;
import org.openid.hs.wc.bean.WorkerComponentBean;
import org.slf4j.Logger;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ShutdownHandler implements HttpHandler 
{

	private Map<String, WorkerComponentBean> workerComponents;
	
	
	public ShutdownHandler(Map<String, WorkerComponentBean> workers)
	{
		this.workerComponents = workers; 
	}
	
	
	public void handle(HttpExchange exchange) throws IOException 
	{
		Headers headers = exchange.getResponseHeaders();
		String method = exchange.getRequestMethod();
		headers.add("Content-Type", "application/json");
		headers.add("Access-Control-Allow-Origin", "*");
		Logger logger = LoggerHelper.getLogger();
		HTMLFormatter formatter = new HTMLFormatter();
		if(method.equalsIgnoreCase("POST"))
		{
			LoggerHelper.info("Processing of the client request...");
			InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),"utf-8");
	        BufferedReader br = new BufferedReader(isr);
	        String query = br.readLine();
	        query = query.replaceAll("%3A", ":");
	        System.out.println(query);
	        int separator = query.indexOf("=");
	        String key = query.substring(0, separator);
	        String addr = query.substring(separator+1);
	        WCConsoleAPI.stop(addr);
	        String results = "{";
	        results += "\"" + key + "\"" + ": \"ok\"";
			results += "}";
			String responseMsg = results;
			exchange.sendResponseHeaders(200, responseMsg.length());
		    OutputStream os = exchange.getResponseBody();
		    os.write(responseMsg.getBytes(), 0, responseMsg.length());
		    os.close();
	        LoggerHelper.info("json : " + results);
		}
	}
}
