package org.openid.hs.UniformCommunicationFramework;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.MsgReceiver.IReceiver;
import org.openid.hs.UniformCommunicationFramework.MsgReceiver.IReceiverAdapter;
import org.openid.hs.UniformCommunicationFramework.MsgReceiver.IReceiverListener;
import org.openid.hs.UniformCommunicationFramework.MsgReceiver.bean.ReceiverImpl;
import org.openid.hs.core.helper.LoggerHelper;

public class StartReceiverTI extends IReceiverAdapter {
	static IReceiver receiver;

	public static void main(String[] args) {
		init();
	}
	public static void init()
	{
		LoggerHelper.info("**UCF** init receiver ");
		receiver = new ReceiverImpl("127.0.0.1", 7777,30);
		receiver.addMessageListener(new StartReceiverTI());
	}
	@Override
	public void MessageReceiver(IMessage msg) {
		LoggerHelper.info("**UCF** return object "+msg.getContent());
	}
}
