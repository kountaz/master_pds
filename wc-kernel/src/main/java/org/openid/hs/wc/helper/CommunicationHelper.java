package org.openid.hs.wc.helper;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.bean.CommunicationBean;
import org.openid.hs.communication.bean.ConsumerBean;
import org.openid.hs.communication.bean.ProducerBean;
import org.openid.hs.communication.controller.BrokerConnection;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.wc.controller.SuperFormatter;

/**
 * Utilities for communications.
 * @version R2
 * @author Steven
 *
 */
public class CommunicationHelper {
	public static final String LOCAL_ADDRESS_PATTERN = "tcp://localhost:%d";
	
	public static Communication createCommunication(int pBrokerPort,
			String pQueueName)
			throws CommunicationException {
		
		return createCommunication(String.format(LOCAL_ADDRESS_PATTERN, pBrokerPort), pQueueName);
	}
	/**
	 * Creates new communication.
	 * @param pBrokerUrl Broker which hosts the communications
	 * @param pQueueName Queue name.
	 * @param pMessageSelector Message selector.
	 * @param pFormatterClass Communication formatter.
	 * @return New communication.
	 * @throws CommunicationException
	 */
	public static Communication createCommunication(String pBrokerUrl,
			String pQueueName)
			throws CommunicationException {
		
		return createCommunication(pBrokerUrl, pQueueName, pQueueName);
	}
	
	public static Communication createCommunication(int pBrokerPort,
			String pInputQueueName, String pOutputQueueName)
			throws CommunicationException {
		
		return createCommunication(String.format(LOCAL_ADDRESS_PATTERN, pBrokerPort), pInputQueueName, pOutputQueueName);
	}
	/**
	 * Creates new communication.
	 * @param pBrokerUrl Broker which hosts the communications
	 * @param pInputQueueName Input queue name.
	 * @param pOutputQueueName Output queue name.
	 * @return New communication.
	 * @throws CommunicationException
	 */
	public static Communication createCommunication(String pBrokerUrl,
			String pInputQueueName, String pOutputQueueName)
			throws CommunicationException {
		
		return createCommunication(pBrokerUrl, pInputQueueName, pOutputQueueName, "");
	}
	
	public static Communication createCommunication(int pBrokerPort,
			String pInputQueueName, String pOutputQueueName,
			String pMessageSelector)
			throws CommunicationException {
		
		return createCommunication(String.format(LOCAL_ADDRESS_PATTERN, pBrokerPort), pInputQueueName, pOutputQueueName, "", null);
	}
	/**
	 * Creates new communication.
	 * @param pBrokerUrl Broker which hosts the communications
	 * @param pInputQueueName Input queue name.
	 * @param pOutputQueueName Output queue name.
	 * @param pMessageSelector Message selector.
	 * @return New communication.
	 * @throws CommunicationException
	 */
	public static Communication createCommunication(String pBrokerUrl,
			String pInputQueueName, String pOutputQueueName,
			String pMessageSelector)
			throws CommunicationException {
		
		return createCommunication(pBrokerUrl, pInputQueueName, pOutputQueueName, "", null);
	}
	
	public static Communication createCommunication(int pBrokerPort,
			String pInputQueueName, String pOutputQueueName,
			Class<? extends SuperFormatter> pFormatterClass)
			throws CommunicationException {
		
		return createCommunication(String.format(LOCAL_ADDRESS_PATTERN, pBrokerPort), pInputQueueName, pOutputQueueName, "", pFormatterClass);
	}
	/**
	 * Creates new communication.
	 * @param pBrokerUrl Broker which hosts the communications
	 * @param pInputQueueName Input queue name.
	 * @param pOutputQueueName Output queue name.
	 * @param pFormatterClass Communication formatter.
	 * @return New communication.
	 * @throws CommunicationException
	 */
	public static Communication createCommunication(String pBrokerUrl,
			String pInputQueueName, String pOutputQueueName,
			Class<? extends SuperFormatter> pFormatterClass)
			throws CommunicationException {
		
		return createCommunication(pBrokerUrl, pInputQueueName, pOutputQueueName, "", pFormatterClass);
	}
	
	public static Communication createCommunication(int pBrokerPort,
			String pInputQueueName, String pOutputQueueName,
			String pMessageSelector,
			Class<? extends SuperFormatter> pFormatterClass)
			throws CommunicationException {
		
		return createCommunication(String.format(LOCAL_ADDRESS_PATTERN, pBrokerPort), pInputQueueName, pOutputQueueName, pMessageSelector, pFormatterClass);
	}
	/**
	 * Creates new communication.
	 * @param pBrokerUrl Broker which hosts the communications
	 * @param pInputQueueName Input queue name.
	 * @param pOutputQueueName Output queue name.
	 * @param pMessageSelector Message selector.
	 * @param pFormatterClass Communication formatter.
	 * @return New communication.
	 * @throws CommunicationException
	 */
	public static Communication createCommunication(String pBrokerUrl,
			String pInputQueueName, String pOutputQueueName,
			String pMessageSelector,
			Class<? extends SuperFormatter> pFormatterClass)
			throws CommunicationException {
		
		try {
			BrokerConnection connection = BrokerConnection.get(pBrokerUrl);
			CommunicationFormatter formatter = null;
			if (pFormatterClass != null) {
				formatter = pFormatterClass.newInstance();
			}
			Producer producer = new ProducerBean(connection.getSession(),
					pOutputQueueName, formatter);
			Consumer consumer = new ConsumerBean(connection.getSession(),
					pInputQueueName, pMessageSelector, formatter);
			return new CommunicationBean(producer, consumer);
		} catch (Exception e) {
			throw new CommunicationException(e);
		}
	}
	private CommunicationHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
