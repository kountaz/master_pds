package org.openid.hs.simulationactivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 *  The Class ActivitySender thats sends the String
 *  containing the information of call start with toSend() method 
 *  using TCP socket
 * @version R2
 * @author Meriem
 *
 */

public class ActivitySender implements Runnable{
	//private PrintWriter out;
	private ObjectOutputStream oos; 
	private Socket socketClient;
	private String call;
	private int port;
	
	public ActivitySender(String call, int port, String adr) throws IOException{
		this.call = call;
		this.port = port;
		socketClient = new Socket(adr, port);
	}
    /** 
	The method to send simulations of UE calls with TCP socket
    */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//out = new PrintWriter(socketClient.getOutputStream(), true);
			//out.println(call);
			oos = new ObjectOutputStream(socketClient.getOutputStream());
			oos.writeObject(new String(call));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
