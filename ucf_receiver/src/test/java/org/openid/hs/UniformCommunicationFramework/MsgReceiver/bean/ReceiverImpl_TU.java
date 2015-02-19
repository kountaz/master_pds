package org.openid.hs.UniformCommunicationFramework.MsgReceiver.bean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.net.*;

import org.junit.Test;
import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;

public class ReceiverImpl_TU {
	Socket sock = mock(Socket.class);
	ServerSocket srck = mock(ServerSocket.class);
	@Test
	public void testReceive() {
		assertTrue(true);
	}

}
