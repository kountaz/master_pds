package org.openid.hs.core.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Utilities for system.
 * @version R2
 * @author Seybany, Steven
 *
 */
public final class SocketHelper {
	private static DatagramSocket  socket;
	
	public static void send(Object obj, String target) {
		int doubledot = target.indexOf(":");
		send(obj, target.substring(0, doubledot), Integer.parseInt(target.substring(doubledot+1)));
	}
	public static void send(Object obj, String targetHost, int targetPort) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(targetHost);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			os.writeObject(obj);
			byte[] data = outputStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(data, data.length,
					IPAddress, targetPort);
			socket.send(sendPacket);
			os.close();
			socket.close();
			System.out.println(String.format(">>>>> CP0: SocketHelper.send(obj, %s, %d)", targetHost, targetPort));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * */
	public static void createSocketSender(int DEFAULT_PORT, Serializable DEFAULT_OBJECT) {
		try{
			int 	DEFAULT_PORT_SENDER = PortHelper.portSender(DEFAULT_PORT);
			socket = new DatagramSocket();
	        
			 InetAddress IPAddress = InetAddress.getByName("localhost");
	        
	         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	         
	         ObjectOutputStream os = new ObjectOutputStream(outputStream);
	        
	         os.writeObject((Object)DEFAULT_OBJECT);
	         byte[] data = outputStream.toByteArray();
	         DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, DEFAULT_PORT_SENDER);
	         socket.send(sendPacket);
	         os.close();
	      	}catch(Exception e){}      
	}
	
	/*
	 * 
	 * */
	public static Object createSocketReceiver (DatagramSocket socket, byte[] incomingData) throws IOException  {
		
		DatagramPacket incomingPacket = new DatagramPacket(incomingData, 1024);
		ByteArrayInputStream in = null ;
		ObjectInputStream is = null;
		try {
			socket.receive(incomingPacket);
		byte[] data = incomingPacket.getData();
        in = new ByteArrayInputStream(data);
        is = new ObjectInputStream(in);
         return (Object)is.readObject();
       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{}
		
	}
	
}