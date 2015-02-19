package org.openid.hs.enodeb.controller;

import java.io.Serializable;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;

/**
 * Sender packets for enodeb.
 * 
 * @see SenderEnodeb.
 * @version R2
 * @author Fanta
 * 
 */
public class SenderEnodeb {
	//private static DatagramSocket Socket;
	public SenderEnodeb() {
	}
	/*
	 * get the sender port information
	 */
	public static void send(Serializable obj, int receiverPort) {
		try {
			SocketHelper.createSocketSender(receiverPort, obj);
		} catch (Exception e) {
			LoggerHelper.error("Failure on the process sending.");
		}
	}
}
