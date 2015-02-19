package org.openid.hs.communication;

import org.openid.hs.communication.controller.BrokerConnection;
import org.openid.hs.communication.controller.CommunicationEngine;
import org.openid.hs.communication.exception.CommunicationException;

/**
 * API which used to host an embedded broker and create communications.
 * @version R2
 * @author Steven
 *
 */
public class CommunicationAPI implements CommunicationFactory {
	/**
	 * Default bind port.
	 */
	public static final int DEFAULT_BIND_PORT = 1503;
	/**
	 * Default discovery address.
	 */
	public static final String DEFAULT_DISCOVERY_ADDRESS = "multicast://default";
	/**
	 * The unique instance of CommunicationAPI.
	 */
	private static CommunicationAPI instance;
	
	/**
	 * Initializes the communication API.
	 * @return The unique communication API.
	 * @throws CommunicationException When the communication API is already initialized.
	 */
	public static CommunicationAPI init() 
			throws CommunicationException {
		
		return init(DEFAULT_BIND_PORT);
	}
	/**
	 * Initializes the communication API.
	 * @param pBindPort A bind port.
	 * @return The unique communication API.
	 * @throws CommunicationException When the communication API is already initialized.
	 */
	public static CommunicationAPI init(int pBindPort) 
			throws CommunicationException {
		
		return init(pBindPort, DEFAULT_DISCOVERY_ADDRESS);
	}
	/**
	 * Initializes the communication API.
	 * @param pBindPort A bind port.
	 * @param pDiscoveryAddress A discovery address.
	 * @return The unique communication API.
	 * @throws CommunicationException When the communication API is already initialized.
	 */
	public static CommunicationAPI init(int pBindPort, String pDiscoveryAddress)
		throws CommunicationException {
		
		if (isInit())
			throw new CommunicationException("The communication API is already initialized.");
		
		instance = new CommunicationAPI(pBindPort, pDiscoveryAddress);
		
		return instance;
	}
	/**
	 * Destroys the communication API.
	 * @throws CommunicationException When the communication API isn't initialized.
	 */
	public static void destroy() 
			throws CommunicationException {
		
		if (!isInit())
			throw new CommunicationException("The communication API isn't initialized yet.");
		
		try {
			BrokerConnection.closeAllConnections();
			instance.engine.getBrokerService().stop();
			instance.engine.getBrokerService().waitUntilStopped();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		instance = null;
	}
	/**
	 * Indicates if the communication API is initialized.
	 * @return True if the unique communication API is initialized.
	 */
	public static boolean isInit() {
		return instance != null;
	}
	/**
	 * Returns the unique communication API.
	 * @return The unique communication API.
	 * @throws CommunicationException When the communication API isn't initialized.
	 */
	public static CommunicationAPI get() 
			throws CommunicationException {
		
		if (!isInit())
			throw new CommunicationException("The communication API isn't initialized yet.");
		
		return instance;
	}
	
	
	/**
	 * The communication engine of the API.
	 */
	private CommunicationEngine engine;
	
	private CommunicationAPI(int pBindPort, String pDiscoveryAddress) 
			throws CommunicationException {
		
		engine = new CommunicationEngine(pBindPort, pDiscoveryAddress);
	}
	public CommunicationEngine getEngine() {
		return engine;
	}
	@Override
	public Communication createCommunication(CommunicationFormatter pCommunicationFormatter)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pCommunicationFormatterClass);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		return engine.getCommunicationFactory().createCommunication(pOutputQueueName, pInputQueueName, pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pOutputQueueName, pInputQueueName, pCommunicationFormatterClass);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException {
		
		return engine.getCommunicationFactory().createCommunication(pOutputQueueName, pInputQueueName, pMessageSelector, pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pOutputQueueName, pInputQueueName, pMessageSelector, pCommunicationFormatterClass);
	}
	@Override
	public Communication createCommunication(String pMessageSelector, CommunicationFormatter pCommunicationFormatter)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pMessageSelector, pCommunicationFormatter);
	}
	@Override
	public Communication createCommunication(String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass)
			throws CommunicationException {

		return engine.getCommunicationFactory().createCommunication(pMessageSelector, pCommunicationFormatterClass);
	}
}
