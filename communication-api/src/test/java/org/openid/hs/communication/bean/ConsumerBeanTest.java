package org.openid.hs.communication.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.junit.Test;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.bean.ConsumerBean;

public class ConsumerBeanTest extends TestCase {
	public static final String TEST_INPUT_QUEUENAME = "in";
	public static final String TEST_MESSAGE_SELECTOR = "";
	public static final String TEST_MESSAGE_TYPE = "AType";
	public static final String TEST_MESSAGE_GROUP = "group";
	public static final long TEST_MESSAGE_EXPIRATION = 9;
	public static final int TEST_MESSAGE_PRIORITY = 1;
	public static final String TEST_MESSAGE_FORMATTED = "formatted";
	public static class FormatterClass extends CommunicationFormatter {
		public FormatterClass() {
			super();
		}
		public String inputFormatAType(Object pObject) {
			return TEST_MESSAGE_FORMATTED;
		}
	}
	@Test
	public void testConstructor() throws Exception {
		Queue mockQueue = mock(Queue.class);
		Session mockSession = mock(Session.class);
		when(mockSession.createQueue(TEST_INPUT_QUEUENAME)).thenReturn((mockQueue));
		
		new ConsumerBean(mockSession, TEST_INPUT_QUEUENAME, TEST_MESSAGE_SELECTOR, mock(CommunicationFormatter.class));
		
		verify(mockSession, times(1)).createQueue(TEST_INPUT_QUEUENAME);
		verify(mockSession, times(1)).createConsumer(mockQueue, TEST_MESSAGE_SELECTOR);
	}
	@Test
	public void testReceive() throws Exception {
		TextMessage mockTextMessage = mock(TextMessage.class);
		when(mockTextMessage.getJMSType()).thenReturn(TEST_MESSAGE_TYPE);
		when(mockTextMessage.getStringProperty("JMSXGroupID")).thenReturn(TEST_MESSAGE_GROUP);
		when(mockTextMessage.getJMSExpiration()).thenReturn(TEST_MESSAGE_EXPIRATION);
		when(mockTextMessage.getJMSPriority()).thenReturn(TEST_MESSAGE_PRIORITY);
		
		Queue mockQueue = mock(Queue.class);
		MessageConsumer mockConsumer = mock(MessageConsumer.class);
		when(mockConsumer.receive()).thenReturn(mockTextMessage);
		
		Session mockSession = mock(Session.class);
		when(mockSession.createQueue(TEST_INPUT_QUEUENAME)).thenReturn(mockQueue);
		when(mockSession.createConsumer(mockQueue, TEST_MESSAGE_SELECTOR)).thenReturn(mockConsumer);
		when(mockSession.createTextMessage()).thenReturn(mockTextMessage);
		
		CommunicationFormatter mockFormatter = new FormatterClass();
		
		assertTrue(mockFormatter.canInputFormat(TEST_MESSAGE_TYPE));
		
		ConsumerBean c = new ConsumerBean(mockSession, TEST_INPUT_QUEUENAME, TEST_MESSAGE_SELECTOR, mockFormatter);
		Message m = c.receive();
		
		verify(mockConsumer, times(1)).receive();
		
		assertTrue(
				m.getType().equals(TEST_MESSAGE_TYPE) && m.getGroup().equals(TEST_MESSAGE_GROUP)
				&& m.getExpiration() == TEST_MESSAGE_EXPIRATION && m.getPriority() == TEST_MESSAGE_PRIORITY
				&& m.getContent().equals(TEST_MESSAGE_FORMATTED)
			);
	}
}
