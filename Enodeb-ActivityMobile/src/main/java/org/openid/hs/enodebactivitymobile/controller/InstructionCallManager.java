package org.openid.hs.enodebactivitymobile.controller;

import org.openid.hs.EnodebMmeCommon.Instruction;
import org.openid.hs.EnodebMmeCommon.MMECallInstruction;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * InstructionManager class that will execute instruction received from the MME
 * Entity
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class InstructionCallManager {
	MMECallInstruction instruction;

	/**
	 * Constructor
	 */
	public InstructionCallManager(MMECallInstruction instruction) {
		this.instruction = instruction;
	}

	/**
	 * Method to send alert instruction
	 */
	public void toAlert() {
		LoggerHelper
				.info("Enodeb Entity: InstructionCallManager >> : the Ue number "
						+ this.instruction.getIdUe() + " was alerted");
	}

	/**
	 * Method to send Stop instruction
	 */
	public void toStop() {
		LoggerHelper
				.info("Enodeb Entity: InstructionCallManager >> : the Ue number "
						+ this.instruction.getIdUe() + " was stoped");
	}

	/**
	 * Method to define the type of instruction and send it
	 */
	public void execute() {

		int id = this.instruction.getIdInstruction();
		switch (id) {
		case Instruction.ALERT:
			toAlert();
			break;
		case Instruction.STOP:
			toStop();
			break;
		default:
			LoggerHelper
					.error("Enodeb Entity: InstructionCallManager >> : Instruction unknown");

		}
	}
}
