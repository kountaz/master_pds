package org.openid.hs.mme.controller;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.simulation.CallBeginBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;



/**
 * Receive object for mme.
 * @see ReceiverMME.
 * @version R2
 * @author Fanta
 *
 */
public class ReceiverMme implements Receiver {

	
	/**
	 * Message to print in output stream.
	 */
	private int port;
	
	//private CallConnexion ceb;
	
	private String target;
	
	private NodeClient nc;
	
	DatagramSocket socket = null;
	
	//private int  DEFAULT_PORT_SENDER;
	
	/*
	 *Constructor
	 * */
	public ReceiverMme(){
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
	@Override
	public void run() {
		try{
			socket = new DatagramSocket(port);
			LoggerHelper.info("Simulation mme listening on port "+port+"...");
            byte[] incomingData = new byte[1024];
            nc = new NodeClient(target);
            while (true) {
                try {
					Object is = SocketHelper.createSocketReceiver(socket, incomingData);

					Map<String,Object> mylist = new HashMap<String,Object>();  
					mylist.put("l", is);
					
			    	if((mylist.get("l").getClass().getSimpleName()).contains("CallBeginBean"))
			    	{
			    		CallBeginBean c = (CallBeginBean)(mylist.get("l"));
			    		c.setEntityResponse("ok");
			    		nc.requestIt("/autorizations/call/begin",c.getPhonenumber1(),c.getEntityResponse());
						
					}
			    	if((mylist.get("l").getClass().getSimpleName()).contains("CallEndBean"))
			    	{
			    		CallEndBean cc = (CallEndBean)(mylist.get("l"));
			    		cc.setEntityResponseM("ok");
			    		nc.requestIt("/autorizations/call/end",cc.getNumberphone1(),cc.getNumberphone2(),Integer.toString(cc.getEmittedByte()),cc.getEntityResponseM());
						
					}
			    	mylist.remove("l");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
				
				
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

