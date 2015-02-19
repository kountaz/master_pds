package org.openid.hs.UniformCommunicationFramework.Msg.bean;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;

public class MsgImpl_TU {

	@Test
	public void testMessageImpl() {
		String test = "test";
		try {
			IMessage imsg = new MessageImpl(test, InetAddress.getByName("127.0.0.1"), 7777, null);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertSame("test", test);
	}

}
