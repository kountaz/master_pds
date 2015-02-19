package org.openid.hs.communication;

import org.openid.hs.communication.exception.CommunicationException;

/**
 * Interface implemented by concrete producers.
 * @version R2
 * @author Steven
 * 
 */
public interface Producer {
	/**
	 * Returns queue name of the producer.
	 * @return Queue name of the producer.
	 */
	public String getOutputQueueName();
	/**
	 * Creates, adapts and sends a JMS message.
	 * @param pMessage A message uses to realize the sending.
	 * @throws CommunicationException
	 */
	public void send(Message pMessage) throws CommunicationException;
}
