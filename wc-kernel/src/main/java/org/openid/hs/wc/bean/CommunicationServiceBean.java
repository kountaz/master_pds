package org.openid.hs.wc.bean;

import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.wc.CommunicationService;

/**
 * Concrete communication service.
 * @see CommunicationService
 * @version R2
 * @author Steven
 *
 */
public class CommunicationServiceBean extends AbstractService implements CommunicationService {	
	/**
	 * Bind port for the communication engine of the service.
	 */
	private int bindPort;
	/**
	 * Discovery address for the communication engine of the service.
	 */
	private String discoveryAddress;
	
	public CommunicationServiceBean() {
		super(SERVICE_NAME);
		discoveryAddress = CommunicationAPI.DEFAULT_DISCOVERY_ADDRESS;
	}
	@Override
	public void setBindPort(Integer pBindPort) {
		bindPort = pBindPort;
	}
	@Override
	public int getBindPort() {
		return bindPort;
	}
	@Override
	public void setDiscoveryAddress(String pDiscoveryAddress) {
		discoveryAddress = pDiscoveryAddress;
	}
	@Override
	public String getDiscoveryAddress() {
		return discoveryAddress;
	}
	@Override
	public void start() throws ServiceException {
		if (bindPort == 0)
			throw new ServiceException("A bind port must be defined.");
		if (discoveryAddress == null)
			throw new ServiceException("A discovery address must be defined.");
		
		try {
			if (!CommunicationAPI.isInit()) {
				CommunicationAPI.init(bindPort, discoveryAddress);
			}
			super.start();
		} catch (CommunicationException e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		//try {
			//CommunicationAPI.destroy();
			super.stop();
		//} catch (CommunicationException e) {
			//new ServiceException(e);
		//}
	}
}
