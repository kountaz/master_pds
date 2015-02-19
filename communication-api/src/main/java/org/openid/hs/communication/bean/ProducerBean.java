package org.openid.hs.communication.bean;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.exception.CommunicationException;

/**
 * Concrete producer.
 * @version R2
 * @author Steven
 * 
 */
public final class ProducerBean implements Producer {
	
	/**
	 * The session of this producer.
	 */
	private Session session;
	/**
	 * The queue name of this producer.
	 */
	private String queueName;
	/**
	 * The concrete message producer of this producer.
	 */
	private MessageProducer producer;
	/**
	 * Communication formatter used by this producer.
	 */
	private CommunicationFormatter communicationFormatter;
	
	public ProducerBean(Session pSession, String pQueueName) 
			throws CommunicationException {
		
		this(pSession, pQueueName, null);
	}
	public ProducerBean(Session pSession, String pQueueName, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		try {
			session = pSession;
			queueName = pQueueName;
			producer = session.createProducer(session.createQueue(queueName));
			communicationFormatter = pCommunicationFormatter;
		} catch (JMSException e) {
			throw new CommunicationException("Producer creation has failed due to a JMSException.", e);
		}
	}
	/**
	 * Returns queue name of the producer.
	 * @return Queue name of the producer.
	 */
	@Override
	public String getOutputQueueName() {
		return queueName;
	}
	/**
	 * Creates, adapts and sends a JMS message.
	 * @param pMessage A message uses to realize the sending.
	 * @throws CommunicationException  
	 */
	@Override
	public void send(Message pMessage) 
			throws CommunicationException {
		
		try {
			TextMessage message = prepareMessage(pMessage);
			producer.send(message);
		} catch (Exception e) {
			throw new CommunicationException("Sending has failed due to an axception.", e);
		}
	}
	/**
	 * Creates and returns a JMS message.
	 * @param pMessage A message uses to create the JMS message.
	 * @return A new JMS message configured. 
	 * @throws CommunicationException
	 */
	private TextMessage prepareMessage(Message pMessage)
			throws CommunicationException {
		
		try {
			TextMessage message = session.createTextMessage();
			message.setJMSType(pMessage.getType());
			message.setStringProperty("JMSXGroupID", pMessage.getGroup());
			message.setJMSExpiration(pMessage.getExpiration());
			message.setJMSPriority(pMessage.getPriority());
			String text;
			if (communicationFormatter != null) {
				text = (String) communicationFormatter.outputFormat(pMessage.getType(), pMessage.getContent());
			} else {
				text = pMessage.getContent().toString();
			}
			message.setText(text);
			
			return message;
		} catch (Exception e) {
			throw new CommunicationException("The message preparation has failed before its sending.", e);
		}
	}
}
