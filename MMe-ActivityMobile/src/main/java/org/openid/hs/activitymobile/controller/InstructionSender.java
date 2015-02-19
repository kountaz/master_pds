package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import org.openid.hs.EnodebMmeCommon.MMECallInstruction;
import org.openid.hs.MmeHssCommon.ConsumptionInfoMME;
import org.openid.hs.MmeHssCommon.UeBannedInfo;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * InstructionSender Class that will send the Instruction to send to the enodeb,
 * the instruction can be an alert or an shut Off demand
 * 
 * @see ControlerCallTime
 * @version R2
 * @author Mériem
 */
public class InstructionSender {
	private Socket socket;
	private ObjectOutputStream oos;

	public InstructionSender() {

	}

	/**
	 * The send instruction method
	 */
	public void toSendInstruction(MMECallInstruction instruction, String ip,
			int port) {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(instruction);
			oos.close();
			socket.close();
			LoggerHelper
					.info("MME ENTITY : InstructionSender >> instruction is sent successfully to the eNodeb :"
							+ ip + " and port : " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The send consumption info method
	 */
	public void toSendConsumptionInfo(ConsumptionInfoMME info, String ip,
			int port) {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(info);
			oos.close();
			socket.close();
			LoggerHelper
					.info("MME ENTITY : InstructionSender >> The consumption information of the UE  number : "
							+ info.getIdUE()
							+ " is sent successfully to the HSS :"
							+ ip
							+ " and port : " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The send banned UE Call info method
	 */
	public void toSendBannedInfo(UeBannedInfo info, String ip, int port) {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(info);
			oos.close();
			socket.close();
			LoggerHelper
					.info("MME ENTITY : InstructionSender >> instruction is sent successfully to the eNodeb :"
							+ ip + " and port : " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
