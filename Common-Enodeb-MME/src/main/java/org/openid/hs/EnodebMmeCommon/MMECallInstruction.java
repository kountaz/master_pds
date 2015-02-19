package org.openid.hs.EnodebMmeCommon;

import java.io.Serializable;

/**
 * MMECallInstruction interface that will be used by the Enodeb Entity to
 * execute instruction sent by MME Entity
 * 
 * @see MMECallInstructionBean
 * @version R2
 * @author Mériem
 */
public interface MMECallInstruction  extends Serializable{
	public String getIp();

	public int getPort();

	public int getIdInstruction();

	public String getIdUe();
}
