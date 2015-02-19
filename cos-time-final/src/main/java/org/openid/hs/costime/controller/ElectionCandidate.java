package org.openid.hs.costime.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Properties;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.core.TimeReferenceManager;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;

public class ElectionCandidate 
{
	private Properties properties;
	private TimeReferenceManager candidate;
	
	
	
	public ElectionCandidate(TimeReferenceManager manager) throws IOException
	{
		properties = new Properties();
		InputStream in = getClass().getResourceAsStream("/configuration.properties");
		properties.load(in);
		in.close();
		candidate = manager;
	}
	
	public void prepareStartUp() throws IOException, 
								        InterruptedException, 
								        ProcessIsAlreadyStartedException, 
								        PortUnreachableException
	{
		//Configuration
		String addresses[] = properties.getProperty("Election.Collection.Addresses").split(properties.getProperty("Election.Collection.Separator"));
		String ports[] = properties.getProperty("Election.Collection.Ports").split(properties.getProperty("Election.Collection.Separator"));
		//Requete.
		String message = "Election:" + candidate.getIdentifier();
		String responseString = "";
		DatagramChannel channel = null;
		ByteBuffer buffer = null, bufferResponse = null;
		SocketAddress serverAddress = null;
		int indexFail = 0;
		main_loop:
		for(int i = 0; i < addresses.length; i++)
		{
			
			try 
			{
				channel = DatagramChannel.open();
				channel.configureBlocking(false);
				channel.connect(new InetSocketAddress(addresses[i], Integer.parseInt(ports[i])));
				buffer = ByteBuffer.allocate(50);
				bufferResponse = ByteBuffer.allocate(50);
				buffer.clear();
				buffer.put(message.getBytes());
				buffer.flip();
				channel.send(buffer, new InetSocketAddress(addresses[i], Integer.parseInt(ports[i])));
				//Receive response.
				bufferResponse.clear();
				Thread.sleep(10000);
				serverAddress = channel.receive(bufferResponse);
				bufferResponse.flip();
				responseString = new String(bufferResponse.array()).trim();
				if(responseString.equalsIgnoreCase("Election:Preemption"))
				{
					break main_loop;
				}
				else if(responseString.equalsIgnoreCase("") || 
						responseString.equalsIgnoreCase("Election:Ineligible") ||
						serverAddress == null)
				{
					indexFail++;
					if(indexFail == addresses.length)
					{
						LoggerHelper.info("Aquiring the master status and starting...");
						LoggerHelper.info("The service was choosen is : " + candidate.getIdentifier());
						TCoordinator coordinator = new TCoordinator();
						coordinator.begin();
						candidate.start();
						coordinator.commit();
						break main_loop;
					}
				}
				LoggerHelper.info(new String(bufferResponse.array()));
				channel.close();
			} 
			catch (PortUnreachableException ex) 
			{
				LoggerHelper.error("*********************PortUnreachableException**********************");
				LoggerHelper.error("An error occured while the processing: the endpoint is unreachable.");
				LoggerHelper.error("*******************************************************************");
				indexFail++;
				if(indexFail == addresses.length)
				{
					LoggerHelper.info("Aquiring the master status and starting...");
					TCoordinator coordinator = new TCoordinator();
					coordinator.begin();
					candidate.start();
					coordinator.commit();
					break main_loop;
				}
				
				continue;
			} 
			catch (SocketException ex) 
			{
				LoggerHelper.error("*************************SocketException***************************");
				LoggerHelper.error("An error occured while the processing: ", ex);
				LoggerHelper.error("*******************************************************************");
			}
			catch (IOException ex) 
			{
				LoggerHelper.error("**************************IOException******************************");
				LoggerHelper.error("An error occured while the processing: ", ex);
				LoggerHelper.error("*******************************************************************");
			} 
			/*catch (ProcessIsAlreadyStartedException ex)
			{
				LoggerHelper.error("******************ProcessIsAlreadyStartedException*****************");
				LoggerHelper.error("An error occured while the processing: a process is already starting.");
				LoggerHelper.error("*******************************************************************");
			}*/ 		
		}
	}
}
