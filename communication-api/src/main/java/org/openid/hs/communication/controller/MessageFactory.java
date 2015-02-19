package org.openid.hs.communication.controller;

import org.openid.hs.communication.Message;
import org.openid.hs.communication.bean.MessageBean;

/**
 * Messages factory.
 * @version R2
 * @author Steven
 * 
 */
public class MessageFactory {
	public static Message create() {
		return new MessageBean();
	}
	public static Message create(String pType) {
		return new MessageBean(pType);
	}
	public static Message create(String pType, String pGroup) {
		return new MessageBean(pType, pGroup);
	}
	public static Message create(String pType, Object pContent) {
		return new MessageBean(pType, pContent);
	}
	public static Message create(String pType, String pGroup, Object pContent) {
		return new MessageBean(pType, pGroup, pContent);
	}
	public static Message create(String pType, long pExpiration) {
		return new MessageBean(pType, pExpiration);
	}
	public static Message create(String pType, String pGroup, long pExpiration) {
		return new MessageBean(pType, pGroup, pExpiration);
	}
	public Message create(String pType, int pPriority) {
		return new MessageBean(pType, pPriority);
	}
	public Message create(String pType, String pGroup, int pPriority) {
		return new MessageBean(pType, pGroup, pPriority);
	}
	public Message create(String pType, long pExpiration, int pPriority) {
		return new MessageBean(pType, pExpiration, pPriority);
	}
	public Message create(String pType, String pGroup, long pExpiration, int pPriority) {
		return new MessageBean(pType, pGroup, pExpiration, pPriority);
	}
	public Message create(String pType, long pExpiration, int pPriority, Object pContent) {
		return new MessageBean(pType, pExpiration, pPriority, pContent);
	}
	public Message create(String pType, String pGroup, long pExpiration, int pPriority, Object pContent) {
		return new MessageBean(pType, pGroup, pExpiration, pPriority, pContent);
	}
	private MessageFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
