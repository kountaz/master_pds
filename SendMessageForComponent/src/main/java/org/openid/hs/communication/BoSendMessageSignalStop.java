package org.openid.hs.communication;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.feature.bean.ObjectForHSS;
import org.openid.hs.feature.bean.ObjectForSIGNALSTOP;

public class BoSendMessageSignalStop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// définition du port du broker activeMq
		int pBrokerPort = 1230;
		// définition du nom de la file de message de sortie
		String pOutputQueue = "gi.in";
		// définition du nom de la file de message d'entrée
		String pInputQueue = "gi.out";
		// définition du type de message envoyé/reçu
		// String msgType = "COMMUTATOR";
		String msgType = "SIGNALSTOP";
		
		// définition du groupe de message envoyé
		String msgGroup = "name";

		CommunicationClientAPI commClientApi;
		try {
			commClientApi = new CommunicationClientAPI("tcp://localhost:"
					+ pBrokerPort, pOutputQueue, pInputQueue);

			LoggerHelper.info("Client >> I send my messge");

			TextMessage response = commClientApi.createMessage(msgType);
			
			MyFormatter f = new MyFormatter();
			

			ObjectForSIGNALSTOP stop = new ObjectForSIGNALSTOP();
			stop.setSignal("stop");

			
			
			response.setText(f.outputFormatSIGNALSTOP(stop));
			

			
			LoggerHelper
					.info("Client >> Envoi d'une message vers le serveur...");
			commClientApi.sendMessage(response);
			/** FIN simululation côté client **/

			//System.exit(0);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
