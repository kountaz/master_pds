package org.openid.hs.communication.bean;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.CommunicationFactory;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.controller.CommunicationEngine;
import org.openid.hs.communication.exception.CommunicationException;

public final class CommunicationFactoryBean implements CommunicationFactory {
	/**
	 * Default output queue name.
	 */
	public static final String DEFAULT_OUTPUT_QUEUE_NAME = "out";
	/**
	 * Default input queue name.
	 */
	public static final String DEFAULT_INPUT_QUEUE_NAME = "in";
	
	/**
	 * The communication engine of this factory.
	 */
	private CommunicationEngine communicationEngine;
	
	public CommunicationFactoryBean(CommunicationEngine pCommunicationEngine) {
		communicationEngine = pCommunicationEngine;
	}
	@Override
	public Communication createCommunication(CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		return createCommunication("", pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {
		
		try {
			return createCommunication(pCommunicationFormatterClass.newInstance());
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}
	@Override
	public Communication createCommunication(String pMessageSelector, CommunicationFormatter pCommunicationFormatter)
			throws CommunicationException {

		long currentTime = System.currentTimeMillis();
		return createCommunication(DEFAULT_OUTPUT_QUEUE_NAME+currentTime, DEFAULT_INPUT_QUEUE_NAME+currentTime, pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {
		
		try {
			return createCommunication(pMessageSelector, pCommunicationFormatterClass.newInstance());
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		return createCommunication(pOutputQueueName, pInputQueueName, "", pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {

		try {
			return createCommunication(pOutputQueueName, pInputQueueName, pCommunicationFormatterClass.newInstance());
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		Producer producer = communicationEngine.createProducer(pCommunicationFormatter, pOutputQueueName);
		Consumer consumer = communicationEngine.createConsumer(pCommunicationFormatter, pInputQueueName, pMessageSelector);
		
		return new CommunicationBean(producer, consumer);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {
		
		try {
		return createCommunication(pOutputQueueName, pInputQueueName, pMessageSelector, pCommunicationFormatterClass.newInstance());
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}
}
