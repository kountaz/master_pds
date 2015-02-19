package org.openid.hs.enodeb.controller;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.simulation.CallBeginBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;

/**
 * Concrete HelloWorld.
 * 
 * @see HelloWorld.
 * @version R2
 * @author Steven
 * 
 */
public class ReceiverEnodeb implements Receiver {

	private int port;

	// private int DEFAULT_PORT_SENDER;

	private String target;

	private NodeClient nc;

	DatagramSocket socket = null;

	/*
	 * Constructor
	 */
	public ReceiverEnodeb() {
		//while (PortHelper.portDisponibility(portReceiver)==false){
		//	portReceiver++;
		//}
	}
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
		try {
			socket = new DatagramSocket(this.port);
			LoggerHelper.info("Simulation enodeb listening on port "+port+"...");
			byte[] incomingData = new byte[1024];
			nc = new NodeClient(target);
			while (true) {
				try {
					Object is = SocketHelper.createSocketReceiver(socket,incomingData);
					Map<String, Object> mylist = new HashMap<String, Object>();
					mylist.put("l", is);

					if ((mylist.get("l").getClass().getSimpleName())
							.contains("CallBeginBean")) {
						CallBeginBean c = (CallBeginBean) (mylist.get("l"));
						nc.requestIt("/connexions/connexion/start",
								c.getPhonenumber1(), c.getPhonenumber2());
						LoggerHelper.info("Send a bean to mme webservice....");
					}

					if ((mylist.get("l").getClass().getSimpleName())
							.contains("CallEndBean")) {
						CallEndBean cc = (CallEndBean) (mylist.get("l"));
						nc.requestIt("/connexions/connexion/stop",
								cc.getNumberphone1(), cc.getNumberphone2(),
								Integer.toString(cc.getEmittedByte()),
								cc.getEntityResponseE());
						LoggerHelper.info("Send a bean to mme webservice....");
					}

					mylist.remove("l");
				} catch (Exception e) {
					LoggerHelper.error("Failure on the receive of a bean.");
				}
			}
		} catch (IOException e) {
			LoggerHelper.error(e.getMessage(), e);
		}
	}
}
