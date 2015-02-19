package org.openid.hs.communication;

import org.openid.hs.communication.exception.CommunicationException;

/**
 * Interface implemented by concrete consumers.
 * @version R2
 * @author Steven
 * 
 */
public interface Consumer {
	/**
	 * Returns queue name of the consumer.
	 * @return Queue name of the consumer.
	 */
	public String getInputQueueName();
	/**
	 * Returns a message created from a received JMS message.
	 * @return A message created from a received JMS message.
	 * @throws CommunicationException
	 */
	public Message receive() throws CommunicationException;
}
