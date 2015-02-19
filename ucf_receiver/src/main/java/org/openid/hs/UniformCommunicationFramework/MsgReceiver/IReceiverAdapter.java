package org.openid.hs.UniformCommunicationFramework.MsgReceiver;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;

public abstract class IReceiverAdapter implements IReceiverListener {

	@Override
	public void MessageReceiver(IMessage msg) {}

}
