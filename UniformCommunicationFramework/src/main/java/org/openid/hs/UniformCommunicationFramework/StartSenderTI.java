package org.openid.hs.UniformCommunicationFramework;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.MsgKind;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;
import org.openid.hs.UniformCommunicationFramework.MsgSender.ISender;
import org.openid.hs.UniformCommunicationFramework.MsgSender.bean.SenderImpl;

public class StartSenderTI {
	static ISender sender;
	public static void main(String[] args) throws UnknownHostException {
		sender = new SenderImpl();
		MessageImpl msg = new MessageImpl("this is a test", InetAddress.getByName("127.0.0.1"),7777, MsgKind.Date);
		sender.SendMessage(msg);
	}
}
