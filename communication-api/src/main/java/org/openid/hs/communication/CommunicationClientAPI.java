package org.openid.hs.communication;

import java.net.URI;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.openid.hs.communication.controller.BrokerConnection;

public class CommunicationClientAPI {
	private URI brokerUrl;
	private BrokerConnection connection;
	private MessageConsumer consumer;
	private MessageProducer producer;
	public CommunicationClientAPI(String pBrokerUrl, String pInputQueue, String pOutputQueue) 
			throws JMSException {
		
		this(URI.create(pBrokerUrl), pInputQueue, pOutputQueue);
	}
	public CommunicationClientAPI(URI pBrokerUrl, String pInputQueue, String pOutputQueue) 
			throws JMSException {
		
		connection = BrokerConnection.get(pBrokerUrl);
		
		consumer = connection.getSession().createConsumer(connection.getSession().createQueue(pInputQueue));
		producer = connection.getSession().createProducer(connection.getSession().createQueue(pOutputQueue));
	}
	public URI getBrokerUrl() {
		return brokerUrl;
	}
	public TextMessage createMessage(String pType) 
			throws JMSException {
		
		TextMessage message = connection.getSession().createTextMessage();
		message.setJMSType(pType);
		
		return message;
	}
	public TextMessage createMessage(String pType, String pContent) 
			throws JMSException {
		
		TextMessage message = createMessage(pType);
		message.setText(pContent);
		
		return message;
	}
	public TextMessage createMessage(String pType, String pTarget, String pContent) 
			throws JMSException {
		
		TextMessage message = createMessage(pType, pContent);
		message.setStringProperty("JMSXGroupID", pTarget);
		
		return message;
	}
	public void sendMessage(TextMessage pMessage) 
			throws JMSException {
		
		producer.send(pMessage);
	}
	public TextMessage receiveMessage() 
			throws JMSException {
		
		TextMessage message = (TextMessage) consumer.receive();
		message.acknowledge();
		return message;
	}
}
