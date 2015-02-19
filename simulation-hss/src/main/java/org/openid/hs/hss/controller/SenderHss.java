package org.openid.hs.hss.controller;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;


/**
 * Sender packets for hss.
 * @see SenderHss.
 * @version R2
 * @author Fanta
 *
 */
public class SenderHss{
	
	/**
	 * Message to print in output stream.
	 */
	private static DatagramSocket  Socket;
	
	public SenderHss(){	}
	
	
	
	/*
	 * get the sender port information
	 * */
	public static void send(Serializable obj, int receiverPort) {
		 try {
			 	
			 	SocketHelper.createSocketSender(receiverPort, obj);
			 	
	          } catch (Exception e) {
	            LoggerHelper.error("Failure on the process sending in hss.");
	        }
	    }

}
