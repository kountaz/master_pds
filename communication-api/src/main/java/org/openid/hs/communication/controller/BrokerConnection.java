package org.openid.hs.communication.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Connection to a broker.
 * @version R2
 * @author Steven
 * 
 */
public class BrokerConnection {
	/**
	 * All created connections.
	 */
	private static Map<URI, BrokerConnection> instances = new HashMap<URI, BrokerConnection>();
	/**
	 * Returns a connection to a broker.
	 * @param pBrokerUrl Address of the broker which will be joined.
	 * @return A connection to a broker.
	 * @throws JMSException
	 * @throws URISyntaxException
	 */
	public static BrokerConnection get(String pBrokerUrl) throws JMSException, URISyntaxException {
		return get(new URI(pBrokerUrl));
	}
	/**
	 * Returns a connection to a broker.
	 * @param pBrokerUrl Address of the broker which will be joined.
	 * @return A connection to a broker.
	 * @throws JMSException
	 */
	public static BrokerConnection get(URI pBrokerUrl) throws JMSException {
		BrokerConnection connection;
		if (instances.containsKey(pBrokerUrl)) {
			connection = instances.get(pBrokerUrl);
		} else {
			connection = new BrokerConnection(pBrokerUrl);
		}
		return connection;
	}
	/**
	 * Closes all created connections.
	 * @throws JMSException
	 */
	public static void closeAllConnections() throws JMSException {
		BrokerConnection []list = new BrokerConnection[instances.size()];
		instances.values().toArray(list);
		for (BrokerConnection c : list) {
			c.close();
			instances.remove(c);
		}
	}
	
	/**
	 * Connection to a broker
	 */
	private Connection connection;
	/**
	 * Session on the broker
	 */
	private Session session;
	
	private BrokerConnection(String pBrokerUrl) throws JMSException, URISyntaxException {
		this(new URI(pBrokerUrl));
	}
	private BrokerConnection(URI pBrokerUrl) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(pBrokerUrl);
		
		connection = connectionFactory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		
		instances.put(pBrokerUrl, this);
	}
	/**
	 * Returns connection to the broker.
	 * @return Connection to the broker.
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * Returns session on the broker.
	 * @return Session on the broker.
	 */
	public Session getSession() {
		return session;
	}
	/**
	 * Close this broker connection.
	 * @throws JMSException
	 */
	public void close() throws JMSException {
		session.close();
		connection.close();
	}
}
