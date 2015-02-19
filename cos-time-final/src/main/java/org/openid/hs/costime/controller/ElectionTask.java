package org.openid.hs.costime.controller;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.UUID;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.core.TimeReferenceManager;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;

public class ElectionTask implements Runnable 
{
	private volatile boolean isStarted;
	private UUID uuid_process;
	private DatagramSocket server;
	private final int BUFFER_SIZE = 50;
	private int currentPort;
	private TCoordinator coordinator;
	private TimeReferenceManager master;
	
	
	
	public ElectionTask(TimeReferenceManager candidate, int port) throws IOException, ProcessIsAlreadyStartedException
	{
		isStarted = true;
		currentPort = port;
		coordinator = new TCoordinator();
		master = candidate;
		uuid_process = master.getIdentifier();
	}
	
	
	public void run() 
	{
		try 
		{
			int result = -2;
			String values[] = new String[2], electionMessage = null;
			DatagramChannel channel = DatagramChannel.open();
			server = channel.socket();
			server.bind(new InetSocketAddress(currentPort));
			ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE),
					   bufferResponse = ByteBuffer.allocate(BUFFER_SIZE);
			SocketAddress client = null;
			while(isStarted)
			{
				//Receiving request.
				LoggerHelper.info(".........................Election................................");
				client = channel.receive(buffer);
				buffer.flip();
				//Processing of request.
				values = new String(buffer.array()).trim().split(":");
				if(values[0].equalsIgnoreCase("Election") && (values[1] != null))
				{
					coordinator.begin();
					if(!master.isMaster())
					{
						result = uuid_process.compareTo(UUID.fromString(values[1]));
						bufferResponse.clear();
						if(result == -1 || result == 0)
						{
							LoggerHelper.info("The identifier is less than Local identifier.");
							electionMessage = "Election:Ineligible";
							bufferResponse.put(electionMessage.getBytes());
						}
						else if(result == 1)
						{
							LoggerHelper.info("The identifier is greater than Local identifier.");
							LoggerHelper.info("The choosen identifier is : " + master.getIdentifier());
							electionMessage = "Election:Preemption";
							bufferResponse.put(electionMessage.getBytes());
							master.start();
						}
						bufferResponse.flip();
						channel.send(bufferResponse, client);
					}
					else
					{
						master.stop();
					}
					coordinator.commit();
				}
				buffer.clear();
				LoggerHelper.info(".........................EndElection................................");
			}
			channel.close();
		} 
		catch (SocketException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		catch (IOException ex)
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		
	}
	
	
	public void start()
	{
		this.isStarted = true;
	}
	
	
	public void stop()
	{
		this.isStarted = false;
	}
}
