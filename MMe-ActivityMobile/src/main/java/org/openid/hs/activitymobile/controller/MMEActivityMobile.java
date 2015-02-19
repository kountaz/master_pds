package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * MMEActivityMobile class that will receives messages from the eNodeB Entity to
 * build a UeCallData and triggers the consumption counter to control the
 * consumption of the call sended by the eNodeb Entity
 * 
 * @version R2
 * @author Mériem
 * 
 */

public class MMEActivityMobile implements Runnable {
	private ListenerMME listenerMme;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private String address;
	private int port;
	private UeDataCallFactory uedatafactory;
	private Datagrid grid;
	private boolean isStarted;
	private Thread thread;

	/**
	 * Constructor that initialize the DatagridAPI
	 * @param port2 
	 * @param string 
	 */
	public MMEActivityMobile(String pAddress, Integer pPort) {
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init();
			}
			isStarted = false;
			address = pAddress;
			port = pPort;
		} catch (DatagridException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to remove the UeDataCall from the grid.
	 */
	public void Remove(String idUe) {
		DataLoader data;
		data = DataLoader.getInstance("UeDataCall");
		grid = data.getData("UeDataCall");
		try {
			grid.remove(idUe);
		} catch (DatagridException e) {

		}

		LoggerHelper.info("MME ENTITY : MMEActivityMobile >>  the Ue number : "
				+ idUe + " is removed from the cache");
	}

	/**
	 * listen method that listen requests from socket client
	 */
	public void listen() throws IOException {
		// serverSocket = new ServerSocket(port);
		serverSocket = new ServerSocket(port, 1000000000,InetAddress.getByName(address));
		LoggerHelper
		.info("The MME entity Server listen on port "+port+" ip : "+address);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		isStarted = true;
		do {
			try {
				clientSocket = serverSocket.accept();
				
				// Call the listener to check and treat client requests
				listenerMme = new ListenerMME(this, clientSocket);
				Thread th = new Thread(listenerMme);
				th.start();
			} catch (IOException e) {
				System.err.println("Accept failed.");
			}

		} while (isStarted);
	}

	public void stop() throws ServiceException {
		try {
			clientSocket.close();
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		isStarted = false;
		thread = null;
	}
}
