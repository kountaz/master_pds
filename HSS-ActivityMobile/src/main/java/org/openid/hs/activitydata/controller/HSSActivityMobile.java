package org.openid.hs.activitydata.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * HSSActivityMobile class that will receives information from the MME Entity to
 * build a BannedUeCallInfo and send to the MME info about consumption sended by
 * the eNodeb Entity
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class HSSActivityMobile implements Runnable {
	private ListenerHSS listenerHss;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private String address;
	private int port;
	private boolean isStarted;
	private Thread thread;

	public HSSActivityMobile(String pAddress, int pPort) {
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init();
			}
			isStarted = false;
			port = pPort;
			address = pAddress;
		} catch (DatagridException e) {
			e.printStackTrace();
		}
	}

	/**
	 * listen method that listen requests from socket client
	 */
	public void listen() throws IOException {
		serverSocket = new ServerSocket(port, 1000000000,
				InetAddress.getByName(address));
		LoggerHelper
		.info("The HSS entity Server listen on port "+port+" ip : "+address);		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		isStarted = true;
		do {
			try {
				/**
				 * Accept the client socket
				 */
				clientSocket = serverSocket.accept();

				// Call the listener to check and treat client requests
				listenerHss = new ListenerHSS(this, clientSocket);
				Thread th = new Thread(listenerHss);
				th.start();
			} catch (IOException e) {
				System.err.println("Accept failed.");
			}

		} while (isStarted);
	}

	public void stop() {
		isStarted = false;
		thread = null;
	}
}
