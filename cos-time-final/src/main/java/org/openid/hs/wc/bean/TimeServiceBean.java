package org.openid.hs.wc.bean;

import java.io.IOException;
import java.util.Properties;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.costime.controller.ElectionCandidate;
import org.openid.hs.costime.controller.ElectionTask;
import org.openid.hs.costime.core.TimeReferenceManager;
import org.openid.hs.costime.exceptions.ProcessIsAlreadyStartedException;
import org.openid.hs.wc.TimeService;

public class TimeServiceBean extends AbstractService implements TimeService {

	private Properties properties;
	private TimeReferenceManager manager;
	private ElectionCandidate client;
	private Thread tElection;
	public TimeServiceBean() {
		super(SERVICE_NAME);
		try {
			properties = ResourceHelper.getProperties("/configuration.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void start() throws ServiceException {
		try {
			manager = new TimeReferenceManager();
			//Listening...
			tElection = new Thread(new ElectionTask(manager, Integer.parseInt(properties.getProperty("Election.currentNode.Port"))));
			tElection.start();
			//Requesting...
			client = new ElectionCandidate(manager);
			client.prepareStartUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.start();
	}
	@Override
	public void stop() throws ServiceException {
		tElection.stop();
		manager.stop();
		super.stop();
	}
}
