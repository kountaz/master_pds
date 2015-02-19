package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.enodebactivitymobile.controller.EnodebActivityMobile;
import org.openid.hs.wc.EnodebActivityService;
import org.openid.hs.wc.KernelService;

/**
 * 
 * @version R2
 * @author Meryem, Steven
 *
 */
public class EnodebActivityServiceBean extends AbstractService implements EnodebActivityService {
	private KernelService kernel;
	private EnodebActivityMobile aem;
	private int port;
	public EnodebActivityServiceBean(KernelService pKernel) {
		super(SERVICE_NAME);
		kernel = pKernel;
	}
	@Override
	public void start() throws ServiceException {
		try {
			if (port == 0) {
				throw new ServiceException("A port must be defined");
			}
			aem = new EnodebActivityMobile(kernel.getHost().getHostAddress(), port);
			aem.listen();
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		try {
			aem.stop();
			super.stop();
		} catch (Exception e) {
			new ServiceException(e);
		}
	}
	@Override
	public void setPort(Integer pPort) {
		port = pPort;
	}
	@Override
	public Integer getPort() {
		return port;
	}
}
