package org.openid.hs.costime.notification;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.communication.CommunicationClientAPI;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;


/**
 * @author: OPENID - Patrick
 * @version: 1.0
 * TimeReferenceTransmitter has the duty to transmit the timeReference 
 * to the Back Office using the message queue. 
 */

public class TimeReferenceTransmitter 
{
	/**
	 * CommunicationClientAPI supplies the sending feature of time reference.
	 */
	private CommunicationClientAPI communicator;
	/**
	 * Properties supplies the parameters in the properties file.
	 */
	private Properties properties;
	
	/**
	 * Constructor sets communicator member to write in the queue.
	 * 
	 */
	public TimeReferenceTransmitter()
	{
		try 
		{
			properties = ResourceHelper.getProperties("/configuration.properties");
			communicator = new CommunicationClientAPI(properties.getProperty("CommunicationAPI.Address")
									                    + ":" + properties.getProperty("CommunicationAPI.Port"), 
														  properties.getProperty("CommunicationAPI.InputQueuename"), 
														  properties.getProperty("CommunicationAPI.OutputQueuename"));
		} 
		catch (IOException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		catch (JMSException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		} 
		/*catch (CommunicationException ex) 
		{
			LoggerHelper.error("An error occured while the processing: ", ex);
		}*/
		
	}
	
	
	/**
	 * The writeMessage method writes the time reference in milliseconds.
	 * @param Date which is going to send to the Back Office.
	 */
	public void writeMessage(Date timeReference)
	{
		int nbFails = 0;
		//int pBrokerPort = Integer.parseInt(properties.getProperty("CommunicationAPI.Port"));
		boolean isTerminated = false;
		while(!isTerminated)
		{
			/*try 
			{
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement("message");
				root.addText("" + timeReference.getTime());
				TextMessage message = communicator.createMessage(doc.asXML());
				communicator.sendMessage(message);
				isTerminated = true;
			}*/
			try
			{
				ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(properties.getProperty("CommunicationAPI.Address") + ":" + properties.getProperty("CommunicationAPI.Port"));
		        Connection connection = connectionFactory.createConnection();
		        connection.start();
		        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Topic topic = session.createTopic(properties.getProperty("CommunicationAPI.OutputQueuename"));
		        MessageProducer producer = session.createProducer(topic);
		        // We will send a small text message saying 'Hello'
		        
		        Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement("message");
				root.addText("" + timeReference.getTime());
		        TextMessage message = session.createTextMessage();
		        message.setText(doc.asXML());
		        // Here we are sending the message!
		        producer.send(message);
		        System.out.println("Sent message '" + message.getText() + "'");
		        connection.close();
		        isTerminated = true;
			}
			catch (JMSException ex) 
			{
				LoggerHelper.error("An error occured while the processing: %s", ex);
				nbFails++;
				if(nbFails == 3) isTerminated = true;
 			} 
		}
	}
}
