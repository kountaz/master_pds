package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.faildetector.bean.FailDetector;
import org.openid.hs.wc.FailDetectorService;
import org.openid.hs.wc.KernelService;

/**
 * 
 * @version R2
 * @author Yannis, Steven
 *
 */
public class FailDetectorServiceBean extends AbstractService implements FailDetectorService {
	private KernelService kernel;
	private int port;
	private FailDetector detector;
	private LoaderMaterialReferentiel referential;
	public FailDetectorServiceBean(KernelService pKernel) {
		super(SERVICE_NAME);
		kernel = pKernel;
	}
	@Override
	public void start() throws ServiceException {
		if (port == 0) {
			throw new ServiceException("A port must be defined");
		}
		
		try {
			if (referential == null) {
				referential = new LoaderMaterialReferentiel();
				referential.dataMaterialDefaultLoaded();
			}
			detector = new FailDetector(kernel.getHost().getHostAddress(), 
					getPort().toString(), kernel.getWc().getType().toUpperCase(), 
					kernel.getWc().getArea(), referential);
			detector.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void stop() throws ServiceException {
		detector.stop();
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
