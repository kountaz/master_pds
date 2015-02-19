package org.openid.hs.communication.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.network.NetworkConnector;
import org.openid.hs.communication.CommunicationFactory;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.bean.CommunicationFactoryBean;
import org.openid.hs.communication.bean.ConsumerBean;
import org.openid.hs.communication.bean.ProducerBean;
import org.openid.hs.communication.exception.CommunicationException;

/**
 * Communication engine based on an activeMq message broker. 
 * It allows to create producers and consumers of messages.
 * @version R2
 * @author Steven
 * 
 */
public final class CommunicationEngine {
	/**
	 * Pattern of broker name.
	 */
	public static final String BROKER_NAME_PATTERN = "ActiveMQEmbeddedBroker%s";
	/**
	 * Set of ports used by CommunicationEngine instances.
	 */
	private static Set<Integer> engines = new HashSet<Integer>();
	

	/**
	 * Bind address of this communication engine.
	 */
	private URI bindAddress;
	/**
	 * Discovery address of this communication engine.
	 */
	private URI discoveryAddress;
	/**
	 * Broker service of this communication engine.
	 */
	private BrokerService broker;
	/**
	 * Transport connector of the broker.
	 */
	private TransportConnector transportConnector;
	/**
	 * Network connector of the broker.
	 */
	private NetworkConnector networkConnector;
	/**
	 * Connection of this communication engine.
	 */
	private BrokerConnection brokerConnection;
	/**
	 * Producers created by the communication engine.
	 */
	private Map<String, ProducerBean> producers;
	/**
	 * Consumers created by the communication engine.
	 */
	private Map<String, ConsumerBean> consumers;
	/**
	 * Communication factory.
	 */
	private CommunicationFactory factory;
	
	public CommunicationEngine(int pBindPort, String pDiscoveryAddress) 
			throws CommunicationException {
		
		if (engines.contains(pBindPort)) {
			throw new CommunicationException(String.format("A broker on the port %s has already been created.", pBindPort));
		}
		
		try {
			setupBroker(URI.create("tcp://0.0.0.0:"+ pBindPort), URI.create(pDiscoveryAddress));
		} catch (Exception e) {
			throw new CommunicationException("The broker service setup has failed.", e);
		}
		
		producers = new HashMap<String, ProducerBean>();
		consumers = new HashMap<String, ConsumerBean>();
		
		factory = new CommunicationFactoryBean(this);
		
		engines.add(pBindPort);
	}
	/**
	 * Returns the bind address of this communication engine.
	 * @return The bind address of this communication engine.
	 */
	public URI getBindAddress() {
		return bindAddress;
	}
	/**
	 * Returns the discovery address of this communication engine.
	 * @return The discovery address of this communication engine.
	 */
	public URI getDiscoveryAddress() {
		return discoveryAddress;
	}
	/**
	 * Returns the broker service of this communication engine.
	 * @return The broker service of this communication engine.
	 */
	public BrokerService getBrokerService() {
		return broker;
	}
	/**
	 * Returns communication factory of this communication engine.
	 * @return Communication factory of this communication engine.
	 */
	public CommunicationFactory getCommunicationFactory() {
		return factory;
	}
	/**
	 * Indicates if a producer exists on the given queue name.
	 * @param pQueueName A queue name. 
	 * @return True if a producer exists on the given queue name.
	 */
	public boolean producerExists(String pQueueName) {
		return producers.containsKey(pQueueName);
	}
	/**
	 * Indicates if a consumer exists on the given queue name.
	 * @param pQueueName A queue name.
	 * @return True if a consumer exists on the given queue name.
	 */
	public boolean consumerExists(String pQueueName) {
		return consumers.containsKey(pQueueName);
	}
	/**
	 * Creates a producer on the given queue name.
	 * @param pCommunicationFormatter A communication formatter used by the new producer.
	 * @param pQueueName A queue name.
	 * @return New producer on the given queue name and using the given communication adapter.
	 * @throws CommunicationException When a producer already exists on the given queue name or a JMSException was caught.
	 */
	public Producer createProducer(CommunicationFormatter pCommunicationFormatter, String pQueueName) 
			throws CommunicationException {
		
		//if (producerExists(pQueueName)) {
		//	throw new CommunicationException(String.format("The producer on the queue %s already exsists. Uses getProducer(%s) to get it.", pQueueName, pQueueName));
		//}
		
		ProducerBean producer = new ProducerBean(brokerConnection.getSession(), pQueueName, pCommunicationFormatter);
		producers.put(pQueueName, producer);
		return producer;
	}
	/**
	 * Creates a consumer on the given queue name.
	 * @param pCommunicationFormatter A communication formatter used by the new consumer.
	 * @param pQueueName A queue name.
	 * @param pMessageSelector A message selector.
	 * @return New consumer on the given queue name and using the given communication adapter.
	 * @throws CommunicationException When a consumer already exists on the given queue name or a JMSException was caught.
	 */
	public Consumer createConsumer(CommunicationFormatter pCommunicationFormatter, String pQueueName, String pMessageSelector) 
			throws CommunicationException {
		
		//if (consumerExists(pQueueName)) {
		//	throw new CommunicationException(String.format("The consumer on the queue %s already exsists. Uses getConsumer(%s) to get it.", pQueueName, pQueueName));
		//}
		
		ConsumerBean consumer = new ConsumerBean(brokerConnection.getSession(), pQueueName, pMessageSelector, pCommunicationFormatter);
		consumers.put(pQueueName, consumer);
		return consumer;
	}
	/**
	 * Returns the producer on the given queue name.
	 * @param pQueueName A queue name.
	 * @return The producer on the given queue name.
	 * @throws CommunicationException When the producer on the given queue name doesn't exists.
	 */
	public Producer getProducer(String pQueueName) 
			throws CommunicationException {
		
		if (!producerExists(pQueueName))
			throw new CommunicationException(String.format("The producer on the queue %s doesn't exists. Uses the createProducer method to create it., pQueueName"));
		
		return producers.get(pQueueName);
	}
	/**
	 * Returns the consumer on the given queue name.
	 * @param pQueueName A queue name.
	 * @return The consumer on the given queue name.
	 * @throws CommunicationException When the consumer on the given queue name doesn't exists.
	 */
	public Consumer getConsumer(String pQueueName) 
			throws CommunicationException {
		
		if (!consumerExists(pQueueName))
			throw new CommunicationException(String.format("The consumer on the queue %s doesn't exists. Uses the createConsumer method to create it.", pQueueName));
		
		return consumers.get(pQueueName);
	}
	/**
	 * Setup the broker service.
	 * @param pBindAddress A bind address URI for the broker service.
	 * @param pDiscoveryAddress A discovery address URI for the broker service.
	 * @throws StartFailureException When an exception is caught while the start of the broker service.
	 * @throws Exception
	 */
	private void setupBroker(URI pBindAddress, URI pDiscoveryAddress) 
			throws Exception {
		
		bindAddress = pBindAddress;
		discoveryAddress = pDiscoveryAddress;
		
		broker = new BrokerService();
		broker.setBrokerName(String.format(BROKER_NAME_PATTERN, bindAddress.getPort()));
		broker.setPersistent(true);
		broker.setUseJmx(false);
		broker.setAdvisorySupport(false);
		
		transportConnector = broker.addConnector(bindAddress);
		// stop discovery for ActiveMQ
		//transportConnector.setDiscoveryUri(discoveryAddress);
		//
		networkConnector = broker.addNetworkConnector(discoveryAddress);
		networkConnector.setDynamicOnly(true);
		networkConnector.setNetworkTTL(3); // nb de brokers que peut traverser un message pour atteindre un consommateur
		networkConnector.setDuplex(false);
		networkConnector.setPrefetchSize(1); // nb de messages à récupérer par un broker
		networkConnector.setDecreaseNetworkConsumerPriority(false); // pour traiter les consommateurs des autres brokers avec la même priorité
		//*/
		broker.start();
		broker.waitUntilStarted();
		
		brokerConnection = BrokerConnection.get(bindAddress);
	}
}
