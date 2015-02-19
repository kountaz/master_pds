package org.openid.hs.bootstrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.openid.hs.core.helper.LoggerHelper;

/**
 * Listener of a bootstrap to wait for remote request sent by administrator.
 * @version R2
 * @author Fanta, Meryem, Mouhamadou, Steven
 *
 */
public class WCBootstrapListener implements Runnable {
	/**
	 * Bootstrap of the listener.
	 */
	private WCBootstrap bootstrap;
	/**
	 * Socket of the listener.
	 */
	private Socket socket;
	
	public WCBootstrapListener(WCBootstrap pBootstrap, Socket pSocket) {
		bootstrap = pBootstrap;
		socket = pSocket;
	}
	/**
	 * Waits for request sent in the socket.
	 */
	@Override
	public void run() {
		LoggerHelper.info("Bootstrap accepted "+ socket);
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			int request = Integer.valueOf(in.readLine().trim());
			switch (request) {
				case Command.START:
					LoggerHelper.info("Boostrap received start request from "+ socket);
					bootstrap.start();
					break;
				case Command.STOP:
					LoggerHelper.info("Boostrap received stop request from "+ socket);
					bootstrap.stop();
					break;
				case Command.STATUS:
					LoggerHelper.info("Boostrap received status request from "+ socket);
					out.println(bootstrap.status());
					break;
			}
			
			socket.close();
			LoggerHelper.info("Bootstrap closed "+ socket);
		} catch (IOException e) {
			LoggerHelper.error("IOException", e);
		}
	}
}
