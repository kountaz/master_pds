package org.openid.hs.UniformCommunicationFramework.MsgSender.bean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import java.net.*;
import org.junit.Test;
import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;
import org.openid.hs.UniformCommunicationFramework.MsgSender.ISender;

public class SenderImpl_TU {
	Socket sock = mock(Socket.class);
	@Test
	public void testSendMessage() {
		
		ISender sender = new SenderImpl();
		try {
			MessageImpl msg = new MessageImpl("test",InetAddress.getByName("127.0.0.1"), 7777, null);
			sender.SendMessage(msg);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	assertTrue(true);	
	}

}
