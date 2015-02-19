package org.openid.hs.costime.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.bean.RegistrationServiceImpl;

public class TaskScheduler 
{
	
	private static TaskScheduler instance;
	private Timer scheduler;
	private TimerTask task;
	private Properties properties;
	private BOTimeDispatcher boDispatcher;
	
	private TaskScheduler() {}
	
	
	/**
	 * The getInstance method will be instantiate a single instance. 
	 */
	public static TaskScheduler getInstance()
	{
		if(instance == null) instance = new TaskScheduler();
		
		return instance;
	}
	
	
	/**
	 * The initialize method sets the job and the trigger to bind to the scheduler
	 * @param RegistrationServiceImpl class contains the list of client callbacks. 
	 */
	public void initialize(RegistrationServiceImpl service)
	{
		try 
		{
			properties = new Properties();
			InputStream in = getClass().getResourceAsStream("/configuration.properties");
			properties.load(in);
			in.close();
			task = new ClientTimeDispatcher();//new TimeRetriever(service);
			boDispatcher = new BOTimeDispatcher();
			scheduler = new Timer();
		} 
		catch (IOException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		}
	}
	
	
	/**
	 * The launch method start the job associated the trigger.
	 *  
	 */
	public void launch() 
	{
		scheduler.scheduleAtFixedRate(new TimeRetriever(new RegistrationServiceImpl()), 5000, Long.parseLong(properties.getProperty("Job.interval")));
		scheduler.scheduleAtFixedRate(task, 5000, Long.parseLong(properties.getProperty("Job.interval")));
		scheduler.scheduleAtFixedRate(boDispatcher, 5000, Long.parseLong(properties.getProperty("BO.Job.interval")));
	}
	
	
	/**
	 * The shutdown method stops the job of the scheduler.
	 *  
	 */
	public void shutdown() 
	{
		scheduler.cancel();
	}
}
