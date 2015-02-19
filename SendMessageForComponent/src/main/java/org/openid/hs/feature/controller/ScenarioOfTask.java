package org.openid.hs.feature.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.MyFormatter;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.feature.bean.ObjectForBRAS;
import org.openid.hs.feature.bean.ObjectForCOMMUTATOR;
import org.openid.hs.feature.bean.ObjectForDSLAM;
import org.openid.hs.feature.bean.ObjectForHSS;
import org.openid.hs.feature.bean.ObjectForMME;

public class ScenarioOfTask implements Runnable {
	Properties p;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScenarioOfTask t = new ScenarioOfTask();
		t.run();
		// run();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// définition du port du broker activeMq

		try {
			p = new Properties();
			p.load(new FileInputStream("src/main/resources/config.properties"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			CommunicationAPI commApi = CommunicationAPI.init(Integer.parseInt(p
					.getProperty("pBrokerPort")));
			Communication comm = commApi
					.createCommunication(
							p.getProperty("pOutputQueue"),
							p.getProperty("pInputQueue"),
							"JMSType = 'HSS' or JMSType = 'MME' or JMSType = 'COMMUTATOR' or JMSType = 'BRAS' or JMSType = 'DSLAM' or JMSType = 'SIGNALSTOP'",
							MyFormatter.class);

			LoggerHelper.info("Serveur >> Message is waitting");
			Message responseReceived = comm.receive();
			String type = null;
			type = responseReceived.getType();

			LoggerHelper.info("Serveur >> Firts Message is received");

			if (type.equals("HSS")) {
				do {

					ObjectForHSS object = (ObjectForHSS) responseReceived
							.getContent();
					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					type = responseReceived.getType();
				
					LoggerHelper.info("Message of type " + type
							+ " is received");

				} while (type.equals("HSS"));
				LoggerHelper.info("Reception is finished");
				System.exit(0);

			} else if (type.equals("MME")) {
				do {

					ObjectForMME object = (ObjectForMME) responseReceived
							.getContent();
					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					type = responseReceived.getType();
					// hss= (ObjetCOMMUTATOR) responseReceived.getContent();
					LoggerHelper.info("Message of type " + type
							+ " is received");

				} while (type.equals("MME"));
				LoggerHelper.info("Reception is finished");
				System.exit(0);

			} else if (type.equals("BRAS")) {
				do {

					ObjectForBRAS object = (ObjectForBRAS) responseReceived
							.getContent();
					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					type = responseReceived.getType();
					// hss= (ObjetCOMMUTATOR) responseReceived.getContent();
					LoggerHelper.info("Message of type " + type
							+ " is received");

				} while (type.equals("BRAS"));
				LoggerHelper.info("Reception is finished");
				System.exit(0);

			} else if (type.equals("DSLAM")) {
				do {

					ObjectForDSLAM object = (ObjectForDSLAM) responseReceived
							.getContent();
					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					type = responseReceived.getType();
					// hss= (ObjetCOMMUTATOR) responseReceived.getContent();
					LoggerHelper.info("Message of type " + type
							+ " is received");

				} while (type.equals("DSLAM"));
				LoggerHelper.info("Reception is finished");
				System.exit(0);

			} else if (type.equals("COMMUTATOR")) {

				do {

					ObjectForCOMMUTATOR hss = (ObjectForCOMMUTATOR) responseReceived
							.getContent();
					LoggerHelper.info("Serveur >> Message is waitting");

					responseReceived = comm.receive();

					type = responseReceived.getType();
					// hss= (ObjetCOMMUTATOR) responseReceived.getContent();
					LoggerHelper.info("Message of type " + type
							+ " is received");

				} while (type.equals("COMMUTATOR"));
				LoggerHelper.info("Reception is finished");
				System.exit(0);

			} else {
				System.exit(0);
			}

			

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur");
			e.printStackTrace();
		}
		// runTest(brokerPort, outputQueue, inputQueue, MyFormatter.class);

	}

}
