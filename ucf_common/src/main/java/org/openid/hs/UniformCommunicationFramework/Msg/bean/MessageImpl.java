package org.openid.hs.UniformCommunicationFramework.Msg.bean;

import java.io.*;
import java.net.*;

import org.openid.hs.UniformCommunicationFramework.Msg.IMessage;
import org.openid.hs.UniformCommunicationFramework.Msg.MsgKind;
import org.openid.hs.UniformCommunicationFramework.Msg.TimeOutHelper;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.rt.support.bean.SupportMessaging;

public class MessageImpl extends SupportMessaging implements IMessage, Serializable {

	private Serializable content;
	private transient InetAddress targetAddr;
	private transient int porttarget;
	private MsgKind msgkind;
	private int SOTimeOut;
	public MessageImpl(Serializable content,InetAddress  Addr,int port,MsgKind msgkind)
	{
		super();
		this.content = content;
		this.targetAddr = Addr;
		this.msgkind = msgkind;
		this.porttarget = port;
		this.referenceFromProperties = this.SOTimeOut = new TimeOutHelper().timeOut(msgkind);
		LoggerHelper.info("**UCF** Message = "+this.toString());
	}
	@Override
	public Serializable getContent() {
		return content;
	}
	@Override
	public MsgKind getMsgKind() {
		return msgkind;
	}
	@Override
	public InetAddress  getTargetAddress() {
		return this.targetAddr;
	}
	@Override
	public int getPortTarget() {
		return this.porttarget;
	}
	@Override
	public int getSOTimeOut() {
		return this.SOTimeOut;
	}
	@Override
	public String toString()
	{
		return "content:"+this.getContent()+" || content type:"+this.getContent().getClass().getSimpleName()+" || targetAddr:"+this.targetAddr+" || msgkind:"+this.msgkind+" || porttarget:"+this.porttarget+" || SOTimeOut:"+this.SOTimeOut;
	}

}
