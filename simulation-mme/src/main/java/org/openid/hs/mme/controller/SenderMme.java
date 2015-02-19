package org.openid.hs.mme.controller;

import java.io.Serializable;

import org.openid.hs.core.helper.SocketHelper;

/**
 * Send message to entity layer.
 * 
 * @see SenderMme.
 * @version R2
 * @author Fanta
 * 
 */
public class SenderMme {
	/**
	 * Default toString() pattern.
	 */
	//private static final String DEFAULT_TOSTRING_PATTERN = "An HelloWorld implementation which you says: %s";

	/**
	 * Message to print in output stream.
	 */
	//private static DatagramSocket Socket;

	public SenderMme() {
	}

	public static void send(Serializable obj, int receiverPort) {
		try {
			SocketHelper.createSocketSender(receiverPort, obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
