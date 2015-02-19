package org.openid.hs.UniformCommunicationFramework.MsgReceiver;

import javax.swing.event.EventListenerList;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;

public interface IReceiver extends Runnable {
	public IMessage receive();
	public void addMessageListener(IReceiverListener listener);
}
