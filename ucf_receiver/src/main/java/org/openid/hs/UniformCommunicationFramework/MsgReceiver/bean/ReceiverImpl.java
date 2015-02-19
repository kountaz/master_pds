package org.openid.hs.UniformCommunicationFramework.MsgReceiver.bean;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;
import org.openid.hs.UniformCommunicationFramework.MsgReceiver.*;
import org.openid.hs.core.helper.LoggerHelper;

import java.util.concurrent.*;
import java.io.*;
import java.net.*;

import javax.swing.event.*;

public class ReceiverImpl implements IReceiver {
	ServerSocket srvr;
	Socket skt;
	MessageImpl object;
	private final EventListenerList listeners = new EventListenerList();
	Thread thmsg;
	ThreadReception trec;
	ExecutorService executor;
	public ReceiverImpl(String addr, int port,int nbth) {
		try {
			executor = Executors.newFixedThreadPool(nbth);
			//trec = new ThreadReception(this);
			LoggerHelper.info("**UCF** Listener Inialisée");
			srvr = new ServerSocket(port);
			//thmsg = new Thread(trec);
			for (int i = 0; i < nbth; i++) {
				thmsg = new Thread(trec);
				trec = new ThreadReception(this,i);
				executor.execute(thmsg);
			}
			//thmsg.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private class ThreadReception implements Runnable 
	{
		ReceiverImpl trct;
		int ind;
		public ThreadReception(ReceiverImpl rimpl,int ind)
		{
			this.ind = ind;
			this.trct = rimpl;
		}
		@Override
		public void run() {
			while(true){
			trct.run();}
		}
	}
	public IMessage receive() {
		LoggerHelper.info("**UCF** we receive " + object.getContent());
		return object;
	}
	protected void fireMessageReceive(MessageImpl msg) {
		LoggerHelper.info("**UCF** fireMessageReceive");
		for(IReceiverListener listener : getMessageListeners()) {
            listener.MessageReceiver(msg);
        }
	}
	public void addMessageListener(IReceiverListener listener) {
	        listeners.add(IReceiverListener.class, listener);
	    }
	public IReceiverListener[] getMessageListeners() {
		return listeners.getListeners(IReceiverListener.class);
	}
	@Override
	public void run() {
		try {
			while (true) {
				skt = srvr.accept();
				ObjectInputStream objectStream = new ObjectInputStream(
						skt.getInputStream());
				object = (MessageImpl) objectStream.readObject();
				object.validateMessage();

				LoggerHelper.info("**UCF** Message receive time : "+object.displayTime(object.getConsumingTime()));
				LoggerHelper.info("**UCF** Message status : "+object.isReliableMessage());
				
				LoggerHelper.info("**UCF** Message entrance");
				if(object.isReliableMessage())
					this.fireMessageReceive(object);
				else
					LoggerHelper.warn("The message rejected");
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				skt.close();
				srvr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
