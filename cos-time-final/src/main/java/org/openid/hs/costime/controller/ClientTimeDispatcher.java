package org.openid.hs.costime.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.TimerTask;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.costime.ntp.NTPConnector;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.discovery.exception.InvalidParameters;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


/**
 * @author: OPENID - Patrick
 * @version: 1.0
 * TimeRetriever is a job will be associate to a trigger.
 */

public class ClientTimeDispatcher extends TimerTask
{
	
	/**
	 * The NTPConnector member gets time reference to NTP server.
	 */
	private NTPConnector connector;
	/**
	 * The Date member represents time reference.
	 */
	private Date timeReference;
	/**
	 * The DBCursor member contains the material referential.
	 */
	private DBCursor cursor;
	
	
	/**
	 * The TimeDispatcher constructor make NTPConnector instance and get the material referential.
	 */
	public ClientTimeDispatcher()
	{
		connector = new NTPConnector();
	}
	
	
	
	/**
	 * The run method dispatches the time reference to the client components.
	 */
	public void run() 
	{
		LoggerHelper.info("-----------------------TimeDispatcher---------------------------------");
		timeReference = connector.getTime();
		LoggerHelper.info("Sending time reference to the client components...");
		try 
		{
			
			/*if(!DatagridAPI.isInit())
			{
				DatagridAPI.nolog();
				DatagridAPI.init(1503, 1502);
			}*/
			
			LoaderMaterialReferentiel referential = new LoaderMaterialReferentiel();
			referential.dataMaterialDefaultLoaded();
			cursor = referential.selectDataMaterial();
		} 
		catch (UnknownHostException ex) 
		{
			LoggerHelper.error("An error is occured while the runtime: ", ex);
		} 
		catch (DatagridException ex) 
		{
			LoggerHelper.error("An error is occured while the runtime: ", ex);
			
		} 
		catch (InvalidParameters ex) 
		{
			LoggerHelper.error("An error is occured while the runtime: ", ex);
		}
		
		if(cursor.count() > 0)
		{
			String currentIPAddress = null, currentPort = null;
			DatagramChannel channel = null;
			ByteBuffer buffer = null;
			DBObject db_object = null;
			ByteArrayOutputStream baos = null;
			ObjectOutputStream oos = null;
			
			while(cursor.hasNext()) 
			{
				try 
				{
					db_object = cursor.next();
					currentIPAddress = db_object.get("ADDRESS_IP").toString();
					currentIPAddress = db_object.get("PORT").toString();
					channel = DatagramChannel.open();
					channel.configureBlocking(false);
					channel.connect(new InetSocketAddress(currentIPAddress, Integer.parseInt(currentPort)));
					buffer = ByteBuffer.allocate(255);
					buffer.clear();
					baos = new ByteArrayOutputStream(255);
					oos = new ObjectOutputStream(baos); 
					oos.writeObject(timeReference);
					buffer.put(baos.toByteArray());
					buffer.flip();
					channel.send(buffer, new InetSocketAddress(currentIPAddress, Integer.parseInt(currentPort)));
					channel.close();
				} 
				catch (NumberFormatException ex) 
				{
					LoggerHelper.error("An error is occured while the runtime: ", ex);				
				} 
				catch (IOException ex) 
				{
					LoggerHelper.error("An error is occured while the runtime: ", ex);
					
				}
				
			}
		}
		else
			LoggerHelper.info("There is not client for this notification.");
		LoggerHelper.info("End of the off time...");
		LoggerHelper.info("-------------------------------END------------------------------------");
	}
}
