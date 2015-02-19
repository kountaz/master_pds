package org.openid.hs.qos.measure.dialog;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.communication.Communication;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.controller.MessageFactory;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.qos.CommunicationParameter;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.bean.Stimulus;
import org.openid.hs.wc.helper.CommunicationHelper;


public class MeasureProducer 
{
	private MeasureProducer()
	{
		
	}
	
	public static void sendMessage(String message)
	{
		try 
		{
			Communication communication_bo = CommunicationHelper.createCommunication(CommunicationParameter.BROKER_TIS, CommunicationParameter.QUEUE_TIS);
			Message trafic_message = MessageFactory.create();
			trafic_message.setContent(message);
			communication_bo.send(trafic_message);
			LoggerHelper.info("Qos - send qos data to SI BO");
		} 
		catch (CommunicationException e) 
		{
			LoggerHelper.info("Qos - failed to get a communication instance",e);
		}		
	}
	
	public static void generateMessage(Stimulus stimulus, String id_node)
	{
		Document doc = DocumentHelper.createDocument();
		Element type_message = doc.addElement("MessageQOS");
		Element	event_type = type_message.addElement("eventType");
		event_type.setText("CHARGEIN");
		Element node_type = type_message.addElement("nodeType");
		node_type.setText(EnumParameter.MME);
		Element mac_adress = type_message.addElement("macAdress");
		mac_adress.setText(id_node);
		Element bit_rate = type_message.addElement("bitRate");
		bit_rate.setText(String.valueOf(stimulus.getBit_rate()));
		Element delay = type_message.addElement("delay");
		delay.setText(String.valueOf(stimulus.getDelay()));
		Element packet_loss_rate = type_message.addElement("packetLossRate");
		packet_loss_rate.setText(String.valueOf(stimulus.getPacket_loss_rate()));
		Element jitter = type_message.addElement("jitter");
		jitter.setText(String.valueOf(stimulus.getJitter()));
		String xml =  doc.asXML();
		LoggerHelper.info(xml);
		sendMessage(xml);
	}
}	