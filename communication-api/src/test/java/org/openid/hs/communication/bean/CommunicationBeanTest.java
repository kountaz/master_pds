package org.openid.hs.communication.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.openid.hs.communication.Communication;
import org.openid.hs.communication.Consumer;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.Producer;
import org.openid.hs.communication.bean.CommunicationBean;

public class CommunicationBeanTest {
	@Test
	public void testSend() throws Exception {
		Producer mockProducer = mock(Producer.class);
		Message message = mock(Message.class);
		
		Communication instance = new CommunicationBean(mockProducer, mock(Consumer.class));
		instance.send(message);
		
		verify(mockProducer, times(1)).send(message);
	}
	
	@Test
	public void testReceive() throws Exception {
		Consumer mockConsumer = mock(Consumer.class);
		
		Message message = mock(Message.class);
		when(mockConsumer.receive()).thenReturn(message);
		
		Communication instance = new CommunicationBean(mock(Producer.class), mockConsumer);
		instance.receive();
		
		verify(mockConsumer, times(1)).receive();
	}
}
