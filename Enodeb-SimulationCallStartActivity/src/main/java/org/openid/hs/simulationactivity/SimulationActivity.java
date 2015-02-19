package org.openid.hs.simulationactivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The Class simulationActivity that defines a string containing the information
 * of call start, this is a class that simulates the beginning of a UE call
 * 
 * @version R2
 * @author Meriem
 * 
 */
public class SimulationActivity {
	private ActivitySender sender1;
	private ActivitySender sender2;
	private ActivitySender sender3;
	private String adr;
	private int port;

	public SimulationActivity() {

	}

	/**
	 * The method to send simulations of UE calls
	 * 
	 * @throws IOException
	 */
	public void toSend() throws IOException {
		try {

			adr = "127.0.0.1"; //"192.168.0.44";
			port = 1501;
			String call1 = "208150123345346-1-1-" + System.currentTimeMillis();
			//String call2 = "208150123345347-1-1-"+System.currentTimeMillis();
			/*
			 * String call2 = "208150123345347-1-1-"+System.currentTimeMillis();
			 * String call3 = "208150123345367-1-1-"+System.currentTimeMillis();
			 */
			sender1 = new ActivitySender(call1, port, adr);
			//sender2 = new ActivitySender(call2, port, adr);
			/*
			 * sender2 = new ActivitySender(call2,port,adr); sender3 = new
			 * ActivitySender(call3,port,adr);
			 */
			new Thread(sender1).start();
			//new Thread(sender2).start();
			/*
			 * new Thread(sender2).start(); new Thread(sender3).start();
			 */

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] Args) throws IOException {
		SimulationActivity s = new SimulationActivity();
		s.toSend();
	}
}
