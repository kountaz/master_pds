package org.openid.hs.communication.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.bean.ConsumerBean;
import org.openid.hs.communication.bean.ProducerBean;
import org.openid.hs.communication.controller.CommunicationEngine;

public class CommunicationEngineTest {
	
	public static final int TEST_BIND_PORT = 9101;
	public static final String TEST_DISCOVERY_ADDRESS = "multicast://224.1.2.3:6255";
	public static final String TEST_OUTPUT_QUEUENAME = "out";
	public static final String TEST_INPUT_QUEUENAME = "in";
	public static final String TEST_MESSAGE_SELECTOR = "";
	
	private static CommunicationEngine instance;
	
	@BeforeClass
	public static void setUp() throws Exception {
		instance = new CommunicationEngine(TEST_BIND_PORT, TEST_DISCOVERY_ADDRESS);
	}

	@AfterClass
	public static void tearDown() {
		instance = null;
	}

	@Test
	public void testInitialization() {
		assertTrue(
				instance.getBindAddress().getPort() == TEST_BIND_PORT
				&& instance.getDiscoveryAddress().toString().equals(TEST_DISCOVERY_ADDRESS)
				&& instance.getBrokerService().isStarted()
			);
	}
	
	@Test
	public void testConsumerCreation() throws Exception {		
		Consumer c = instance.createConsumer(mock(CommunicationFormatter.class), TEST_INPUT_QUEUENAME, TEST_MESSAGE_SELECTOR);
		assertTrue(
				c instanceof ConsumerBean 
				&& instance.consumerExists(TEST_INPUT_QUEUENAME) 
				&& instance.getConsumer(TEST_INPUT_QUEUENAME) == c
			);
	}
	
	@Test
	public void testProducerCreation() throws Exception {
		Producer p = instance.createProducer(mock(CommunicationFormatter.class), TEST_OUTPUT_QUEUENAME);
		assertTrue(
				p instanceof ProducerBean 
				&& instance.producerExists(TEST_OUTPUT_QUEUENAME) 
				&& instance.getProducer(TEST_OUTPUT_QUEUENAME) == p
			);
	}
}
