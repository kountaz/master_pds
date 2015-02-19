package org.openid.hs.scenario;

import java.io.IOException;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.MyFormatter;
import org.openid.hs.communication.controller.MessageFactory;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.feature.ScenerioOfComponent;
import org.openid.hs.feature.UseReplicationApi;
import org.openid.hs.feature.bean.ObjectForCOMMUTATOR;
import org.openid.hs.feature.bean.ObjectForHSS;
import org.openid.hs.feature.bean.ObjectForNOTIFY;
import org.openid.hs.feature.bean.UseDatagridApiBean;
import org.openid.hs.feature.bean.UseReplicatioApiBean;
import org.openid.hs.wc.WorkerComponent;

public class ScenarioOfCOMMUTATOR implements ScenerioOfComponent {
	String id;
	UseDatagridApiBean usedatagridapi;
	Datagrid grid;
	String type_evenement = null;

	CommunicationAPI commApi;
	Communication comm;
	Message responseReceived;
	Message sendNotify;
	MyFormatter f;
	ObjectForNOTIFY notify;
	UseReplicationApi usereplicationapi;

	public ScenarioOfCOMMUTATOR(WorkerComponent wc) {
		try {
			usedatagridapi = UseDatagridApiBean.initMyDatagrid();
			usereplicationapi = new UseReplicatioApiBean();
			usereplicationapi.listening(wc.getId());

			f = new MyFormatter();
			notify = new ObjectForNOTIFY();
			commApi = CommunicationAPI.get();
			comm = commApi
					.createCommunication(
							ResourceHelper.getString("/config.properties",
									"pOutputQueue"),
							ResourceHelper.getString("/config.properties",
									"pInputQueue"),
							"JMSType = 'COMMUTATOR' or JMSType = 'SIGNALSTOP'",
							MyFormatter.class);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		start();

	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
			LoggerHelper.info("Serveur >> Message is waitting");
			responseReceived = comm.receive();
			do {

				if (responseReceived.getType().equals("COMMUTATOR")) {
					ObjectForCOMMUTATOR object = (ObjectForCOMMUTATOR) responseReceived
							.getContent();

					putMyDataInDatagrid(object);
					repiqueMyObject(object);

					sendMessageAtBO();

					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					LoggerHelper.info("Message of type " + responseReceived.getType()
							+ " is received");
				}
			} while (responseReceived.getType().equals("COMMUTATOR"));

			LoggerHelper.info("Reception is finished");
			

		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessageAtBO() throws CommunicationException {
		sendNotify = MessageFactory.create("NOTIFY", notify);
		comm.send(sendNotify);
	}

	public void repiqueMyObject(ObjectForCOMMUTATOR object) {
		usereplicationapi.replication(String.valueOf(object.getId()));
	}

	public void putMyDataInDatagrid(ObjectForCOMMUTATOR object) {

		type_evenement = object.getType_evenement();

		if (type_evenement.equals("a") || type_evenement.equals("m")) {

			usedatagridapi.saveMyData(object);

			System.out
					.println(usedatagridapi.getMyData(
							String.valueOf(object.getId()), new ObjectForCOMMUTATOR())
							.getId());

		} else if (type_evenement.equals("s")) {
			usedatagridapi.removeMyData(object);
		}

		else {

		}
	}

}