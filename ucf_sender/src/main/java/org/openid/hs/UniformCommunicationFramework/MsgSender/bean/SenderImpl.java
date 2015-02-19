package org.openid.hs.UniformCommunicationFramework.MsgSender.bean;

import java.io.*;
import java.net.*;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;
import org.openid.hs.UniformCommunicationFramework.MsgSender.ISender;
import org.openid.hs.core.helper.LoggerHelper;

public class SenderImpl implements ISender {
	Socket socket;

	private class ThreadMessage implements Runnable {
		private MessageImpl msg;
		public ThreadMessage(MessageImpl msg){
			this.msg = msg;
		}
		@Override
		public void run() {
			try {
				socket = new Socket(msg.getTargetAddress(),
						msg.getPortTarget());
				socket.setSoTimeout(msg.getSOTimeOut());
				LoggerHelper.info("**UCF** TimeOut = "+msg.getSOTimeOut());
				msg.prepareRTMessaging();
				ObjectOutputStream output =	new ObjectOutputStream(socket.getOutputStream());	
			output.writeObject(msg);
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void SendMessage(MessageImpl msg) {
		ThreadMessage tmsg = new ThreadMessage(msg);
		Thread thmsg = new Thread(tmsg);
		thmsg.start();
	}
}
