package org.openid.hs.bootstrap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.openid.hs.core.helper.LoggerHelper;

/**
 * API used to send requests for start a worker component, or stop 
 * a worker component or get the status of a worker component. 
 * @version R2
 * @author Fanta, Meryem, Mouhamadou, Steven
 *
 */
public class WCConsoleAPI {
	public static void start(String pAddr) {
		int doubledot = pAddr.indexOf(":");
		if (doubledot > -1) {
			start(pAddr.substring(0, doubledot), Integer.parseInt(pAddr.substring(doubledot+1)));
		}
	}
	public static void stop(String pAddr) {
		int doubledot = pAddr.indexOf(":");
		if (doubledot > -1) {
			stop(pAddr.substring(0, doubledot), Integer.parseInt(pAddr.substring(doubledot+1)));
		}
	}
	/**
	 * Start a remote worker component.
	 * @param pHost IP of the remote worker component.
	 * @param pPort Port of the remote worker component bootstrap.
	 */
	public static void start(String pHost, int pPort) {
		try {
			Socket socket = new Socket(pHost, pPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(Command.START);
			socket.close();
		} catch (Exception e) {
			LoggerHelper.error("Error!", e);
		}
	}
	/**
	 * Stop a remote worker component.
	 * @param pHost IP of the remote worker component.
	 * @param pPort Port of the remote worker component bootstrap.
	 */
	public static void stop(String pHost, int pPort) {
		try {
			Socket socket = new Socket(pHost, pPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(Command.STOP);
			socket.close();
		} catch (Exception e) {
			LoggerHelper.error("Error!", e);
		}
	}
	/**
	 * Returns the current status of a remote worker component.
	 * @param pHost IP of the remote worker component.
	 * @param pPort Port of the remote worker component bootstrap.
	 */
	public static String status(String pHost, int pPort) {
		String status;
		try {
			Socket socket = new Socket(pHost, pPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(Command.STATUS);
			status = in.readLine();
			socket.close();
		} catch (Exception e) {
			LoggerHelper.error("Error!", e);
			status = "unknown";
		}
		return status;
	}
	private WCConsoleAPI() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
