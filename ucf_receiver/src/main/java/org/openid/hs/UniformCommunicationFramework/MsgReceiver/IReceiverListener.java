package org.openid.hs.UniformCommunicationFramework.MsgReceiver;

import java.util.EventListener;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;

public interface IReceiverListener extends EventListener {
	void MessageReceiver(IMessage msg);
}
