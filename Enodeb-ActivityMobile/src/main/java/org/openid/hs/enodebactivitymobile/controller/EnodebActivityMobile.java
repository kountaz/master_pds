package org.openid.hs.enodebactivitymobile.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * EnodebActivityMobile class a class that will receives messages from the
 * eNodeB simulation to build a communicationcallactivity and send it to MME, it
 * also receives the messages from the MME to apply instructions.
 * 
 * @version R2
 * @author Mériem
 * 
 */

public class EnodebActivityMobile implements Runnable {

	private ListenerEnodeb listenerEnodeb;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private String address;
	private int port;
	private CallActivityFactory activityfactory;
	private InstructionCallManager instruction;
	private SenderObject so;
	private Thread thread;
	private boolean isStarted;
	
	public EnodebActivityMobile(String pAddress, int pPort) {
		isStarted = false;
		port = pPort;
		address = pAddress;
	}

	/**
	 * listen method that listen requests from socket client
	 */
	public void listen() throws IOException {

		serverSocket = new ServerSocket(port,1000000000,InetAddress.getByName(address));
		LoggerHelper
		.info("The Enodeb entity Server listen on port "+port+" ip : "+address);

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		isStarted = true;
		do {
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
			}
			
			try {
				/**
				 * Accept the client socket
				 */
				clientSocket = serverSocket.accept();
				
				// Call the listener to check and treat client requests
				listenerEnodeb = new ListenerEnodeb(this, clientSocket);
				Thread th = new Thread(listenerEnodeb);
				th.start();
				
			} catch (IOException e) {
				LoggerHelper.error("EnodebActivityMobile >> Accept socket failed!", e);
			}

		} while (isStarted);
	}

	public void stop() {
		isStarted = false;
		thread = null;
	}

	/**
	 * CreateCallActivity is the method that creat a eventCall object and
	 * CommuncationCallActivity object with the string received from the enodeb
	 * simulation
	 */
	public void createAndSendCallActivity(String callActivity) {
		/**
		 * Defines the ip Address and the port of the Enodeb entity Return the
		 * CallActivity object with the CallActivityFactory class
		 */
		activityfactory = new CallActivityFactory(address, port, callActivity);
		CommunicationCallActivity cca = activityfactory
				.getCommunicationCallActivity();

		LoggerHelper
				.info("Enodeb Entity: EnodebActivityMobile>> The CommunicationCallActivity is created "
						+ "and will be sent to the MME Entity");
		LoggerHelper
				.info("Enodeb Entity: EnodebActivityMobile>> The CommunicationCallActivity created >> "
						+"ip : "
						+cca.getIp()
						+" port : "
						+cca.getPort()
						+ " Ue number : "
						+ cca.getIdUE()
						+ "Id status : "
						+ cca.getEvenement().getIdEvent()
						+ " Start time of the event: "
						+ cca.getEvenement().getTimeEvent());
		so = new SenderObject();
		so.sendToMME(cca);
	}

}
