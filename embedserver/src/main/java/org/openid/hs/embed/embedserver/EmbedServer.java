package org.openid.hs.embed.embedserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Executors;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.embed.controller.AdministrationViewHandler;
import org.openid.hs.embed.controller.ShutdownHandler;
import org.openid.hs.embed.controller.StartUpHandler;
import org.openid.hs.embed.exceptions.EmbedServerIsAlreadyStarted;
import org.openid.hs.wc.bean.WorkerComponentBean;

import com.sun.net.httpserver.HttpServer;



public class EmbedServer 
{

	private HttpServer server;
	private boolean isStarted;
	private final int PORT = 80;
	
	public EmbedServer() throws IOException
	{
		Map<String, WorkerComponentBean> workers = new Hashtable<String, WorkerComponentBean>();
		try {
			server = HttpServer.create(new InetSocketAddress(ResourceHelper.getString("/server.conf", "host"),ResourceHelper.getInteger("/server.conf", "port")), 0);
			server.createContext("/supervisor", new AdministrationViewHandler());
			server.createContext("/activation", new StartUpHandler(workers));
			server.createContext("/deactivation", new ShutdownHandler(workers));
			server.setExecutor(Executors.newFixedThreadPool(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
		isStarted = false;
	}
	
	
	
	public void prepareStarting() throws DatagridException
	{
		//DatagridAPI.nolog();
		//DatagridAPI.init(1504, 1505);
	}
	
	
	public void prepareShutdown() throws DatagridException
	{
		//DatagridAPI.destroy();
	}
	
	
	public void start() throws EmbedServerIsAlreadyStarted
	{
		if(!isStarted)
		{
			LoggerHelper.info("Starting HTTP server...");
			server.start();
			isStarted = true;
			LoggerHelper.info("HTTP server started...");
		}
		else 
			throw new EmbedServerIsAlreadyStarted("The server is already started");
	}
	
	
	public void stop()
	{
		LoggerHelper.info("Stopping HTTP server...");
		server.stop(1);
		isStarted = false;
	}
}
