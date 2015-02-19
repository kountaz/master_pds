package org.openid.hs.communication.bean;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.exception.CommunicationException;

/**
 * Concrete consumer.
 * 
 * @version R2
 * @author Steven
 * 
 */
public final class ConsumerBean implements Consumer {
	/**
	 * The session of this consumer.
	 */
	private Session session;
	/**
	 * The queue name of this consumer.
	 */
	private String queueName;
	/**
	 * The concrete message consumer of this consumer.
	 */
	private MessageConsumer consumer;
	/**
	 * Communication formatter used by this producer.
	 */
	private CommunicationFormatter communicationFormatter;

	public ConsumerBean(Session pSession, String pQueueName,
			String pMessageSelector) throws CommunicationException {

		this(pSession, pQueueName, pMessageSelector, null);
	}

	public ConsumerBean(Session pSession, String pQueueName,
			String pMessageSelector,
			CommunicationFormatter pCommunicationFormatter)
			throws CommunicationException {

		try {
			session = pSession;
			queueName = pQueueName;
			consumer = session.createConsumer(session.createQueue(queueName),
					pMessageSelector);
			communicationFormatter = pCommunicationFormatter;
		} catch (JMSException e) {
			throw new CommunicationException(
					"Consumer creation has failed due to a JMSException.", e);
		}
	}

	/**
	 * Returns queue name of the consumer.
	 * 
	 * @return Queue name of the consumer.
	 */
	@Override
	public String getInputQueueName() {
		return queueName;
	}

	/**
	 * Returns a message created from a received JMS message.
	 * 
	 * @return A message created from a received JMS message.
	 * @throws CommunicationException
	 */
	@Override
	public Message receive() throws CommunicationException {

		Message wrapper = null;
		try {
			TextMessage message = (TextMessage) consumer.receive();

			wrapper = new MessageBean(message.getJMSType(),
					message.getStringProperty("JMSXGroupID"),
					message.getJMSExpiration(), message.getJMSPriority());

			if (communicationFormatter != null) {
				wrapper.setContent(communicationFormatter.inputFormat(
						message.getJMSType(), message.getText()));
			} else {
				wrapper.setContent(message.getText());
			}

			// XXX ACK
			message.acknowledge();
		} catch (Exception e) {
			throw new CommunicationException(
					"Receiving has failed due to an exception.", e);
		}

		return wrapper;
	}
}
