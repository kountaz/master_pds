package org.openid.hs.communication.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.communication.Communication;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.bean.CommunicationBean;
import org.openid.hs.communication.bean.CommunicationFactoryBean;
import org.openid.hs.communication.exception.CommunicationException;

public class CommunicationFactoryTest {
	
	public static final int TEST_BIND_PORT = 9102;
	public static final String TEST_DISCOVERY_ADDRESS = "multicast://224.1.2.3:6255";
	public static final String TEST_OUTPUT_QUEUENAME = "out";
	public static final String TEST_INPUT_QUEUENAME = "in";
	public static final String TEST_MESSAGE_SELECTOR = "";
	
	private static CommunicationFactoryBean instance;
	
	@BeforeClass
	public static void setUp() throws Exception {
		CommunicationEngine engine = new CommunicationEngine(TEST_BIND_PORT, TEST_DISCOVERY_ADDRESS);
		instance = new CommunicationFactoryBean(engine);
	}

	@AfterClass
	public static void tearDown() {
		instance = null;
	}

	@Test
	public void testLightCreation() throws Exception {
		CommunicationFormatter mockFormatter = mock(CommunicationFormatter.class);
		
		Communication c = instance.createCommunication(mockFormatter);
		
		assertTrue(c instanceof CommunicationBean);
	}
	
	@Test
	public void testFullCreation() throws Exception {
		CommunicationFormatter mockFormatter = mock(CommunicationFormatter.class);
		
		String ext = "1";
		Communication c = instance.createCommunication(TEST_OUTPUT_QUEUENAME+ext, TEST_INPUT_QUEUENAME+ext, TEST_MESSAGE_SELECTOR, mockFormatter);
		
		assertTrue(c instanceof CommunicationBean);
	}
	
	@Test(expected=CommunicationException.class)
	public void testCreationProducerOnSameQueue() throws Exception {
		CommunicationFormatter mockFormatter = mock(CommunicationFormatter.class);
		
		String ext = "2";
		instance.createCommunication(TEST_OUTPUT_QUEUENAME+ext, TEST_INPUT_QUEUENAME+ext, mockFormatter);
		instance.createCommunication(TEST_OUTPUT_QUEUENAME+ext, TEST_INPUT_QUEUENAME+ext, mockFormatter);
	}
	
	@Test(expected=CommunicationException.class)
	public void testCreationConsumerOnSameQueue() throws Exception {
		CommunicationFormatter mockFormatter = mock(CommunicationFormatter.class);
		
		String ext = "3";
		instance.createCommunication(TEST_OUTPUT_QUEUENAME+ext, TEST_INPUT_QUEUENAME+ext, mockFormatter);
		instance.createCommunication(TEST_OUTPUT_QUEUENAME+(ext+1), TEST_INPUT_QUEUENAME+ext, mockFormatter);
	}
}
