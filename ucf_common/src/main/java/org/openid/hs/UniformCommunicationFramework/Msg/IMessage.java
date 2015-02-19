package org.openid.hs.UniformCommunicationFramework.Msg;

import java.io.Serializable;
import java.net.*;

public interface IMessage extends Serializable {
	public Serializable getContent();
	public InetAddress getTargetAddress();
	public int getPortTarget();
	public MsgKind getMsgKind();
	public int getSOTimeOut();
}
