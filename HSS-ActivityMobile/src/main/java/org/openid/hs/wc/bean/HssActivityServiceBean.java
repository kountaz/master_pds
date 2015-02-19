package org.openid.hs.wc.bean;

import org.openid.hs.activitydata.controller.HSSActivityMobile;
import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.wc.HssActivityService;
import org.openid.hs.wc.KernelService;

/**
 * 
 * @version R2
 * @author Meryem, Steven
 *
 */
public class HssActivityServiceBean extends AbstractService implements HssActivityService {
	private HSSActivityMobile hss;
	private KernelService kernel;
	private Integer port;
	public HssActivityServiceBean(KernelService pKernel) {
		super(SERVICE_NAME);
		kernel = pKernel;
	}
	@Override
	public void start() throws ServiceException {
		try {
			if (port == 0) {
				throw new ServiceException("A port must be defined");
			}
			hss = new HSSActivityMobile(kernel.getHost().getHostAddress(), port);
			hss.listen();
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		try {
			hss.stop();
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
