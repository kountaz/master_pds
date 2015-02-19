package org.openid.hs.hss.controller;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.simulation.CallAuthorizationBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;




/**
 * Receiver packets for Hss.
 * @see ReceiverHss.
 * @version R2
 * @author Fanta
 *
 */
public class ReceiverHss implements Receiver {
		
	DatagramSocket socket = null;
	
	private int port;
	private String target;
	//private int  DEFAULT_PORT_SENDER;
	
	/*
	 * Constructor
	 * */
	public ReceiverHss(){
		//while (PortHelper.portDisponibility(DEFAULT_PORT_RECEIVER)==false){
			//DEFAULT_PORT_RECEIVER++;
		//}
	}
	/*
	 * Getters
	 * */
	@Override
	public int getPort() {
		return port;
	}
	@Override
	public void setPort(int pPort) {
		port = pPort;
	}
	@Override
	public String getTarget() {
		return target;
	}
	@Override
	public void setTarget(String pTarget) {
		target = pTarget;
	}
	
	//public int getDEFAULT_PORT_SENDER(){
		//return this.DEFAULT_PORT_SENDER;
	//}
	
	@Override
	public void run() {
		try{
			socket = new DatagramSocket(port);
			LoggerHelper.info("Simulation hss listening on port "+port+"...");
            byte[] incomingData = new byte[1024];
            while (true) {
                try {
                			Object is = SocketHelper.createSocketReceiver(socket, incomingData);
                			LoggerHelper.info("Simulation hss listening on port "+this.port+"...");
					        Map<String,Object> mylist = new HashMap<String,Object>();  
					    	mylist.put("l", is);
							String control = mylist.get("l").getClass().getSimpleName();
							if(control.contains("CallAuthorizationBean"))
							{
					    		
					    		CallAuthorizationBean c = (CallAuthorizationBean)(mylist.get("l"));
					    		LoggerHelper.info("receiver hss- object callAuthorization is received.");
					    		LoggerHelper.info("receiver hss- scenario call in begin is over.");
							}

							if(control.contains("CallEndBean"))
							{	
					    		CallEndBean cc = (CallEndBean)(mylist.get("l"));
					    		LoggerHelper.info("receiver hss- object callAuthorization is received.");
					    		LoggerHelper.info("receiver hss- scenario call in begin is over.");
							}
					    	
					    	mylist.remove("l");	
							
				} catch (Exception e) {
					e.printStackTrace();
				}
      	                
            }      
			
	}    catch (IOException ex)
	{}
	}
}
