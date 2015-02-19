package org.openid.hs.wc.bean;

import org.openid.hs.activitymobile.controller.MMEActivityMobile;
import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.MmeActivityService;

/**
 * 
 * @version R2
 * @author Meryem, Steven
 *
 */
public class MmeActivityServiceBean extends AbstractService implements MmeActivityService {
	private KernelService kernel;
	private MMEActivityMobile mme;
	private Integer port;
	public MmeActivityServiceBean(KernelService pKernel) {
		super(SERVICE_NAME);
		kernel = pKernel;
	}
	@Override
	public void start() throws ServiceException {
		try {
			if (port == 0) {
				throw new ServiceException("A port must be defined");
			}
			mme = new MMEActivityMobile(kernel.getHost().getHostAddress(), port);
			mme.listen();
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		try {
			mme.stop();
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
