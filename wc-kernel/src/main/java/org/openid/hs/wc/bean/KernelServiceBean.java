package org.openid.hs.wc.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.WorkerComponent;

/**
 * Concrete KernelService.
 * @see KernelService
 * @version R2
 * @author Steven
 *
 */
public class KernelServiceBean extends AbstractService implements KernelService {
	/**
	 * WorkerComponent of the kernel.
	 */
	private WorkerComponent wc;
	/**
	 * Host of the WorkerComponent.
	 */
	private InetAddress host;
	/**
	 * Bootstrap port of the WorkerComponent.
	 */
	private int bootstrapPort;
	
	public KernelServiceBean() {
		super(KernelService.SERVICE_NAME);
	}
	@Override
	public void setWc(WorkerComponent pWc) {
		wc = pWc;
	}
	@Override
	public WorkerComponent getWc() {
		return wc;
	}
	@Override
	public void setHost(String pHost) throws UnknownHostException {
		host = InetAddress.getByName(pHost);
	}
	@Override
	public InetAddress getHost() {
		return host;
	}
	@Override
	public void setBootstrapPort(Integer pPort) {
		bootstrapPort = pPort;
	}
	@Override
	public Integer getBootstrapPort() {
		return bootstrapPort;
	}
	@Override
	public void start() throws ServiceException {
		if (bootstrapPort == 0)
			throw new ServiceException("A bootstrap port must be defined.");
		if (host == null)
			throw new ServiceException("A host address must be defined.");
	}
}
