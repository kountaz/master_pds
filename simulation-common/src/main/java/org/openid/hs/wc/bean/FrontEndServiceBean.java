package org.openid.hs.wc.bean;

import java.io.IOException;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.HttpHelper;
import org.openid.hs.simulation.SimulationWebservice;
import org.openid.hs.wc.FrontEndService;
import org.openid.hs.wc.KernelService;

/**
 * Concrete frontend service.
 * @see FrontEndService
 * @version R3
 * @author Fanta, Steven
 *
 */
public class FrontEndServiceBean extends AbstractService implements FrontEndService {
	private KernelService kernel;
	private int port;
	private int portReceiver;
	private String target;
	private SimulationWebservice bean;
	private Thread receiverThread;

	public FrontEndServiceBean(KernelService pKernel, String pName) {
		super(pName);
		kernel = pKernel;
	}
	@Override
	public void setPort(Integer pPort) {
		port = pPort;
	}
	@Override
	public int getPort() {
		return port;
	}
	@Override
	public void setPortReceiver(Integer pPort) {
		portReceiver = pPort;
	}
	@Override
	public int getPortReceiver() {
		return portReceiver;
	}
	@Override
	public void setTarget(String pPort) {
		target = pPort;
	}
	@Override
	public String getTarget() {
		return target;
	}
	public void setBean(SimulationWebservice pBean) {
		bean = pBean;
	}
	public SimulationWebservice getBean() {
		return bean;
	}
	@Override
	public void start() throws ServiceException {
		if (port == 0)
			throw new ServiceException("A port must be defined.");
		if (bean == null)
			throw new ServiceException("A bean must be defined.");
		
		try {
			String url = String.format("http://%s:%d", kernel.getHost().getHostAddress(), port);
			//simulationApi = SimulationAPI.createAPI(portReceiver);
			bean.setPortReceiver(portReceiver);
			bean.setTarget(target);
			HttpHelper.publishWS(bean, url);
			receiverThread = new Thread(bean.getReceiver());
			receiverThread.start();
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		try {
			HttpHelper.unpublishWS(port);
			receiverThread.stop();
			super.stop();
		} catch (IOException e) {
			new ServiceException(e);
		}
	}
}