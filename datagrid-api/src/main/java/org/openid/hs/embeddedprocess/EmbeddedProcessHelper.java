package org.openid.hs.embeddedprocess;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utilities for embedded process.
 * @version R3
 * @author Steven, Victor
 *
 */
public class EmbeddedProcessHelper {
	/**
	 * Range minimum authorized port.
	 */
	public static final int MIN_PORT_NUMBER = 1025;
	/**
	 * Range maximum authorized port.
	 */
	public static final int MAX_PORT_NUMBER = 65535;
	
	/**
	 * All embedded process created.
	 */
	private static Map<Integer, EmbeddedProcess> servers = new LinkedHashMap<Integer, EmbeddedProcess>();

	/**
	 * Starts a new embedded process. 
	 * @param pType Tyoe of the new embedded process.
	 * @param pPort Port of the new embedded process.
	 * @throws EmbeddedProcessException
	 */
	public static void startServer(int pType, int pPort)
			throws EmbeddedProcessException {
		if (servers.containsKey(pPort)) {
			throw new EmbeddedProcessException(String.format(
					"Port %d is already used by an embedded process", pPort));
		}
		if (!checkPortAvailability(pPort)) {
			throw new EmbeddedProcessException(
					String.format(
							"Port %d is already used (checking of port availability has failed).",
							pPort));
		}
		try {
			EmbeddedProcess process = EmbeddedProcessFactory.createProcess(
					pType, pPort);
			process.start();
			servers.put(pPort, process);
		} catch (Exception e) {
			e.printStackTrace();
			new EmbeddedProcessException(e);
		}
	}
	/**
	 * Stops an ambedded process.
	 * @param pPort Port of process which must be stopped.
	 * @throws EmbeddedProcessException
	 */
	public static void stopServer(int pPort) throws EmbeddedProcessException {
		if (!servers.containsKey(pPort)) {
			throw new EmbeddedProcessException(
					"No embedded process exists on the port " + pPort);
		}
		EmbeddedProcess process = servers.get(pPort);
		process.stop();
		servers.remove(pPort);
	}
	/**
	 * Stops all embedded process.
	 * @throws EmbeddedProcessException
	 */
	public static void stopAllServers() throws EmbeddedProcessException {
		Integer[] ports = new Integer[servers.size()];
		servers.keySet().toArray(ports);
		for (int p : ports) {
			stopServer(p);
		}
	}
	/**
	 * Activates silent logging.
	 */
	public static void silentLogging() {
		silentLogging(true);
	}
	/**
	 * Defines silent logging value.
	 * @param pValue Silent logging value.
	 */
	public static void silentLogging(boolean pValue) {
		AbstractEmbeddedProcess.SILENT_LOGGING = pValue;
	}

	/**
	 * Checks to see if a specific port is available.
	 * @param port the port to check for availability
	 * @author http://bit.ly/1m6cZNh
	 */
	public static boolean checkPortAvailability(int port) {
		if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}
	
	private EmbeddedProcessHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
