package org.openid.hs.EnodebMmeCommon.bean;

import org.openid.hs.EnodebMmeCommon.MMECallInstruction;

/**
 * MMECallInstructionBean class implements MMECallInstruction interface that
 * will be used by the Enodeb Entity to execute instruction sent by MME Entity
 * 
 * @see MMECallInstruction
 * @version R2
 * @author Mériem
 */
public class MMECallInstructionBean implements MMECallInstruction{

	/**
	 * MME address ip
	 */
	private String ip;
	/**
	 * MME port
	 */
	private int port;
	/**
	 * the id of instruction 
	 */
	private int idInstruction;
	/**
	 * the id of the ue concerned 
	 */
	private String idUe;

	public MMECallInstructionBean(String ip, int port, int idInstruction,
			String idUe) {
		super();
		this.ip = ip;
		this.port = port;
		this.idInstruction = idInstruction;
		this.idUe = idUe;
	}
	/**
	 * getters and setters
	 */
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getIdInstruction() {
		return idInstruction;
	}

	public void setIdInstruction(int idInstruction) {
		this.idInstruction = idInstruction;
	}

	public String getIdUe() {
		return idUe;
	}

	public void setIdUe(String idUe) {
		this.idUe = idUe;
	}

}
