package org.openid.hs.enodebactivitymobile.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;

public class SenderObject {
	private int port;
	private String ipMME;
	private ObjectOutputStream oos;
	private CommunicationCallActivity cca;
	private Socket socket;

	public SenderObject() {

	}

	public void sendToMME(CommunicationCallActivity o) {
		String filePath = "/config.properties";
		Properties p;
		try {
			p = ResourceHelper.getProperties(filePath);
			String keyip = "ipMME";
			ipMME = p.getProperty(keyip);
			String keyport = "portMME";
			port = Integer.parseInt(p.getProperty(keyport));
			socket = new Socket(ipMME, port);
			LoggerHelper.info("TEST" + socket.getInetAddress().toString() + socket.getPort());
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(o);
			LoggerHelper
					.info("Enodeb Entity: SenderObject>> the Communication Call Activity of the UE number "
							+ o.getIdUE() + " sent to MME entity");
			oos.close();
			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
