package org.openid.hs.feature.controller;

import java.io.IOException;

import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.Profile;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.feature.ScenarioFactory;
import org.openid.hs.feature.ScenerioOfComponent;
import org.openid.hs.scenario.ScenarioFactoryImpl;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.controller.WCFactory;

public class Task implements Runnable {

	ScenarioFactory factory;
	ScenerioOfComponent scenario;
	WorkerComponent wc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task t = new Task();
		try {
			CommunicationAPI commApi = CommunicationAPI.init(ResourceHelper.getInteger("/config.properties","pBrokerPort"));
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread th = new Thread(t);
		th.start();
		SystemHelper.pause();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			 wc = WCFactory.createWC("lte.hss.paris.12",null);
			//System.out.println(wc.getProfile().getServiceSet());
			
			factory = new ScenarioFactoryImpl();
			scenario = factory.createScenario(wc);
			
			Thread th = new Thread(scenario);
			th.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
