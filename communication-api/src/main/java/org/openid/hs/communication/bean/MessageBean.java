package org.openid.hs.communication.bean;

import org.openid.hs.communication.Message;

/**
 * Concrete message used to send or receive JMS messages.
 * @version R1
 * @author Steven
 * 
 */
public class MessageBean implements Message {
	/**
	 * Type of this message.
	 */
	private String type;
	/**
	 * Group of this message.
	 */
	private String group;
	/**
	 * Expiration of this message.
	 */
	private long expiration;
	/**
	 * Priority of this message.
	 */
	private int priority;
	/**
	 * Content of this message.
	 */
	private Object content;
	
	public MessageBean() {
		this("");
	}
	public MessageBean(String pType) {
		this(pType, "", DEFAULT_EXPIRATION, DEFAULT_PRIORITY);
	}
	public MessageBean(String pType, String pGroup) {
		this(pType, pGroup, DEFAULT_EXPIRATION, DEFAULT_PRIORITY);
	}
	public MessageBean(String pType, Object pContent) {
		this(pType, "", DEFAULT_EXPIRATION, DEFAULT_PRIORITY, pContent);
	}
	public MessageBean(String pType, String pGroup, Object pContent) {
		this(pType, pGroup, DEFAULT_EXPIRATION, DEFAULT_PRIORITY, pContent);
	}
	public MessageBean(String pType, long pExpiration) {
		this(pType, "", pExpiration, DEFAULT_PRIORITY);
	}
	public MessageBean(String pType, String pGroup, long pExpiration) {
		this(pType, pGroup, pExpiration, DEFAULT_PRIORITY);
	}
	public MessageBean(String pType, int pPriority) {
		this(pType, "", DEFAULT_EXPIRATION, pPriority);
	}
	public MessageBean(String pType, String pGroup, int pPriority) {
		this(pType, pGroup, DEFAULT_EXPIRATION, pPriority);
	}
	public MessageBean(String pType, long pExpiration, int pPriority) {
		this(pType, "", pExpiration, pPriority, null);
	}
	public MessageBean(String pType, String pGroup, long pExpiration, int pPriority) {
		this(pType, pGroup, pExpiration, pPriority, null);
	}
	public MessageBean(String pType, long pExpiration, int pPriority, Object pContent) {
		this(pType, "", pExpiration, pPriority, pContent);
	}
	public MessageBean(String pType, String pGroup, long pExpiration, int pPriority, Object pContent) {
		type = pType;
		group = pGroup;
		expiration = pExpiration;
		priority = pPriority;
		content = pContent; 
	}
	/**
	 * Returns the type of this message.
	 * @return The type of this message.
	 */
	public String getType() {
		return type;
	}
	/**
	 * Defines the type of this message.
	 * @param pType The new type for this message.
	 */
	public void setType(String pType) {
		type = pType;
	}
	/**
	 * Returns the group of this message.
	 * @return The group of this message.
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * Defines the group of this message.
	 * @param pGroup The new group for this message.
	 */
	public void setGroup(String pGroup) {
		group = pGroup;
	}
	/**
	 * Returns the expiration delay of this message.
	 * @return The expiration delay of this message.
	 */
	public long getExpiration() {
		return expiration;
	}
	/**
	 * Defines the expiration delay of this message.
	 * @param pExpiration The new expiration delay for this message.
	 */
	public void setExpiration(long pExpiration) {
		expiration = pExpiration;
	}
	/**
	 * Returns the priority value of this message.
	 * @return The priority value of this message.
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * Defines the priority value of this message.
	 * @param pPriority The new priority value for this message.
	 */
	public void setPriority(int pPriority) {
		priority = pPriority;
	}
	/**
	 * Returns the content object of this message.
	 * @return The content object of this message.
	 */
	public Object getContent() {
		return content;
	}
	/**
	 * Defines the content object of this message. 
	 * @param pContent The new content object for this message. 
	 */
	public void setContent(Object pContent) {
		content = pContent;
	}
	@Override
	public String toString() {
		return String.format(
				"Message [ type = %s ; group = %s ; expiration = %d ; priority = %d ; content class = %s ] : %s",
				type, group, expiration, priority, content.getClass().getSimpleName(), content.toString()
			);
	}
}
