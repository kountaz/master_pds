package org.openid.hs.communication.bean;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.communication.exception.FormatObjectException;

/**
 * Concrete communication interface.
 * @version R2
 * @author Steven
 * 
 */
public class CommunicationBean implements Communication {

	/**
	 * Producer of this communication API.
	 */
	private Producer producer;
	/**
	 * Consumer of this communication API.
	 */
	private Consumer consumer;
	
	public CommunicationBean(Producer pProducer, Consumer pConsumer) {
		producer = pProducer;
		consumer = pConsumer;
	}
	/**
	 * Creates, adapts and sends a JMS message.
	 * @param pMessage A message uses to realize the sending.
	 * @throws ProducerException
	 * @throws FormatObjectException When the content object of the message can't be adapted.
	 */
	@Override
	public void send(Message pMessage) throws CommunicationException {
		producer.send(pMessage);
	}
	/**
	 * Returns a message created from a received JMS message.
	 * @return A message created from a received JMS message.
	 * @throws ConsumerException
	 * @throws FormatObjectException When the JMS message received can't be adapted.
	 */
	@Override
	public Message receive() throws CommunicationException {
		return consumer.receive();
	}
	/**
	 * Returns output queue name.
	 * @return Output queue name.
	 */
	@Override
	public String getOutputQueueName() {
		return producer.getOutputQueueName();
	}
	/**
	 * Returns input queue name.
	 * @return input queue name.
	 */
	@Override
	public String getInputQueueName() {
		return consumer.getInputQueueName();
	}
}
