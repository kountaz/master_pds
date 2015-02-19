package org.openid.hs.communication;

/**
 * Interface message used to configure a concrete shared message. 
 * When sending, a JMS messages is created to be sent.
 * When receiving, a JMS message directs a new instantiation.
 * @version R1
 * @author Steven
 * 
 */
public interface Message {
	/**
	 * Default expiration value.
	 */
	public static final long DEFAULT_EXPIRATION = 0;
	/**
	 * Default priority value.
	 */
	public static final int DEFAULT_PRIORITY = 4;
	
	/**
	 * Returns the type of the concrete message.
	 * @return The type of the concrete message.
	 */
	public String getType();
	/**
	 * Defines the type of the concrete message.
	 * @param pType The new type for the concrete message.
	 */
	public void setType(String pType);
	/**
	 * Returns the group of the concrete message.
	 * @return The group of the concrete message.
	 */
	public String getGroup();
	/**
	 * Defines the group of the concrete message.
	 * @param pGroup The new group for the concrete message.
	 */
	public void setGroup(String pGroup);
	/**
	 * Returns the expiration delay of the concrete message.
	 * @return The expiration delay of the concrete message.
	 */
	public long getExpiration();
	/**
	 * Defines the expiration delay of the concrete message.
	 * @param pExpiration The new expiration delay for the concrete message.
	 */
	public void setExpiration(long pExpiration);
	/**
	 * Returns the priority value of the concrete message.
	 * @return The priority value of the concrete message.
	 */
	public int getPriority();
	/**
	 * Defines the priority value of the concrete message.
	 * @param pPriority The new priority value for the concrete message.
	 */
	public void setPriority(int pPriority);
	/**
	 * Returns the content object of the concrete message.
	 * @return The content object of the concrete message.
	 */
	public Object getContent();
	/**
	 * Defines the content object of the concrete message. 
	 * @param pContent The new content object for the concrete message. 
	 */
	public void setContent(Object pContent);
}
