package org.openid.hs.communication.bean;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.bean.ProducerBean;

public class ProducerBeanTest {
	public static final String TEST_OUTPUT_QUEUENAME = "out";
	public static final String TEST_MESSAGE_TYPE = "AType";
	public static final String TEST_MESSAGE_GROUP = "group";
	public static final long TEST_MESSAGE_EXPIRATION = 9;
	public static final int TEST_MESSAGE_PRIORITY = 1;
	public static final Object TEST_MESSAGE_CONTENT = new Object();
	public static final String TEST_MESSAGE_FORMATTED = "formatted";
	public static class FormatterClass extends CommunicationFormatter {
		public FormatterClass() {
			super();
		}
		public String outputFormatAType(Object pObject) {
			return TEST_MESSAGE_FORMATTED;
		}
	}
	@Test
	public void testConstructor() throws Exception {
		Queue mockQueue = mock(Queue.class);
		Session mockSession = mock(Session.class);
		when(mockSession.createQueue(TEST_OUTPUT_QUEUENAME)).thenReturn((mockQueue));
		
		new ProducerBean(mockSession, TEST_OUTPUT_QUEUENAME, mock(CommunicationFormatter.class));
		
		verify(mockSession, times(1)).createQueue(TEST_OUTPUT_QUEUENAME);
		verify(mockSession, times(1)).createProducer(mockQueue);
	}
	@Test
	public void testSend() throws Exception {
		Message mockMessage = mock(Message.class);
		when(mockMessage.getType()).thenReturn(TEST_MESSAGE_TYPE);
		when(mockMessage.getGroup()).thenReturn(TEST_MESSAGE_GROUP);
		when(mockMessage.getExpiration()).thenReturn(TEST_MESSAGE_EXPIRATION);
		when(mockMessage.getPriority()).thenReturn(TEST_MESSAGE_PRIORITY);
		when(mockMessage.getContent()).thenReturn(TEST_MESSAGE_CONTENT);

		TextMessage mockTextMessage = mock(TextMessage.class);
		
		Queue mockQueue = mock(Queue.class);
		MessageProducer mockProducer = mock(MessageProducer.class);
		
		Session mockSession = mock(Session.class);
		when(mockSession.createQueue(TEST_OUTPUT_QUEUENAME)).thenReturn(mockQueue);
		when(mockSession.createProducer(mockQueue)).thenReturn(mockProducer);
		when(mockSession.createTextMessage()).thenReturn(mockTextMessage);
		
		
		CommunicationFormatter mockFormatter = new FormatterClass();
		
		assertTrue(mockFormatter.canOutputFormat(TEST_MESSAGE_TYPE));
		
		ProducerBean p = new ProducerBean(mockSession, TEST_OUTPUT_QUEUENAME, mockFormatter);
		
		p.send(mockMessage);
		
		verify(mockSession, times(1)).createTextMessage();
		verify(mockProducer, times(1)).send(mockTextMessage);
	}
}
