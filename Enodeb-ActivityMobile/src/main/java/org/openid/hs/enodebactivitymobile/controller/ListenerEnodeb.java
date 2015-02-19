package org.openid.hs.enodebactivitymobile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;
import org.openid.hs.EnodebMmeCommon.Instruction;
import org.openid.hs.EnodebMmeCommon.MMECallInstruction;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;

/**
 * ListenerEnodeb class a class that will read Ue Call message creats the
 * CommunicationCallActivity object and send it to MME entity
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class ListenerEnodeb implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	private Socket clientSocket;
	private int port;
	private Properties propertie;
	private EnodebActivityMobile enodebActivityMobile;
	private CommunicationCallActivity cca;
	private ObjectInputStream ois;
	private String instructionType;
	private InstructionCallManager manager;
	/**
	 * Constructor thats initialize EnodebActivityMobile object and Socket
	 * object
	 */
	public ListenerEnodeb(EnodebActivityMobile enodebActivityMobile,
			Socket clientSocket) {
		this.enodebActivityMobile = enodebActivityMobile;
		this.clientSocket = clientSocket;
	}

	/**
	 * manage method that identifies and handles the Object received
	 * 
	 */
	public void manage(Object object) {
		if (object instanceof String) {
			/**
			 * In the case of String object
			 */
			String s = (String) object;
			enodebActivityMobile.createAndSendCallActivity(s);
		} else if (object instanceof MMECallInstruction) {
			/**
			 * In the case of MMECallInstruction object
			 */
			MMECallInstruction to = (MMECallInstruction) object;
			int id = to.getIdInstruction();
			switch (id) {
			case Instruction.ALERT:
				LoggerHelper
						.info("Enodeb Entity: Listener >> Information received : Alert Instruction of UE number: "
								+ to.getIdUe());
				
				break;
			case Instruction.STOP:
				LoggerHelper
				.info("Enodeb Entity: Listener >> Information received : Stop Instruction of UE number: "
						+ to.getIdUe());
				break;
			default:
				LoggerHelper.error("Enodeb Entity: Listener >> Information received : Instruction unknown");
				// throw new Exception();
			}
			
			manager = new InstructionCallManager(to);
			manager.execute();
		}
	}

	/**
	 * The run method that read message from Enodeb simulation, create a
	 * CommunicationCallActivity with the enodebActivityMobile object and send
	 * it to the MME entity
	 */
	@Override
	public void run() {
		try {

			ois = new ObjectInputStream(clientSocket.getInputStream());
			Object objet;
			try {
				objet = ois.readObject();
				manage(objet);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ois.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
