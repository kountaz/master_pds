package org.openid.hs.embed.embedserver;

import java.io.IOException;
import java.util.Scanner;

import org.openid.hs.core.helper.HttpHelper;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;

public class EmbedServerApp {
	private static EmbedServer2 ws;

	public static void main(String[] args) {
		boolean flag = false;
		try {
			String HOST = ResourceHelper.getString("/server.conf", "host");
			int WEBSERVICE_PORT = ResourceHelper.getInteger("/server.conf", "webservice.port");
			ws = new EmbedServer2();
			while (!flag) {
				int choice = 0;
				Scanner sc = new Scanner(System.in);
				LoggerHelper.info("1 - Démarrer le serveur:");
				LoggerHelper.info("2 - Arrêter le serveur:");
				LoggerHelper.info("3 - Sortir de l'application:");
				LoggerHelper.info("Veuillez saisir votre choix:");
				choice = sc.nextInt();
				if (choice == 1) {
					LoggerHelper.info("---------------------------Starting---------------------------------");
					HttpHelper.publishWS(ws, "http://"+HOST+":"+WEBSERVICE_PORT);
					LoggerHelper.info("--------------------------------------------------------------------");
				} else if (choice == 2) {
					HttpHelper.unpublishWS(WEBSERVICE_PORT);
				} else if (choice == 3) {
					flag = true;
					sc.close();
				}
			}
		} catch (IOException ex) {
			LoggerHelper.error("***************************IOException*****************************");
			LoggerHelper.error("An error occured while the processing: the stream is impossible.", ex);
			LoggerHelper.error("*******************************************************************");
		} finally {
			System.exit(0);
		}

	}

}
