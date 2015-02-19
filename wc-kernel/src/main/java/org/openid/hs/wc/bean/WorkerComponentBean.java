package org.openid.hs.wc.bean;

import java.util.HashSet;
import java.util.Set;

import org.openid.hs.core.AbstractServiceManager;
import org.openid.hs.core.Profile;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.WCStatus;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.controller.WCStatusFactory;
import org.openid.hs.wc.helper.WCHelper;

/**
 * Concrete WorkerComponent.
 * @see WorkerComponent
 * @version R2
 * @author Steven
 *
 */
public class WorkerComponentBean extends AbstractServiceManager implements WorkerComponent {
	/**
	 * Name of this worker component.
	 */
	private String name;
	/**
	 * Network of this worker component.
	 */
	private String network;
	/**
	 * Type of this worker component.
	 */
	private String type;
	/**
	 * Zone of this worker component.
	 */
	private String area;
	/**
	 * ID of this worker component.
	 */
	private String id;
	/**
	 * Current status of this worker component.
	 */
	private WCStatus status;
	/**
	 * Kernel of this worker component.
	 */
	private KernelService kernel;
	/**
	 * Indicates if the worker component is running.
	 */
	private boolean started;
	
	public WorkerComponentBean(String pName, Profile pProfile) 
			throws ProfileException {
		
		super(pProfile);
		
		name = pName;
		network = WCHelper.getNetworkFromName(name);
		type = WCHelper.getTypeFromName(name);
		area = WCHelper.getAreaFromName(name);
		id = WCHelper.getIdFromName(name);
		status = WCStatusFactory.createInitStatus(this);
		kernel = (KernelService) getProfile().getService(KernelService.SERVICE_NAME);
		kernel.setWc(this);
		started = false;
	}
	/**
	 * Startup services in the profile of this worker component.
	 * @exception ServiceException When a startup of a service failed.
	 */
	@Override
	public void start() throws ServiceException {
		if (isStarted()) {
			throw new ServiceException("The worker component is already started.");
		}
		LoggerHelper.info(String.format("The worker component '%s' is starting...", getName()));
		Set<String> failedServices = new HashSet<String>();
		for (String s : getProfile().getServiceSet()) {
			LoggerHelper.info(String.format("The service '%s' is starting...", s));
			try {
				startService(s);
			} catch (Exception e) {
				failedServices.add(s);
				LoggerHelper.error(String.format("The startup of the '%s' "
						+ "service has failed", s), e);
			}
			LoggerHelper.info(String.format("The service '%s' was successfully started!", s));
		}
		started = true;
		status = WCStatusFactory.createStartStatus(this);
		if (failedServices.size() == 0) {
			LoggerHelper.info(String.format("The worker component '%s' was successfully started!", getName()));
		} else {
			if (failedServices.size() == getProfile().getServiceSet().size()) {
				throw new ServiceException(String.format(
						"The startup of the worker component '%s' has failed: "
						+ "none service could start", 
						getName()
					));
			} else {
				LoggerHelper.warn(String.format("The worker component '%s' "
						+ "partially started. %d service%s couldn't started: %s", 
						getName(), failedServices.size(), 
						(failedServices.size() != 1 ? "s":""), 
						failedServices.toString()));
			}
		}
	}
	/**
	 * Shutdown services in the profile of this worker component.
	 * @exception ServiceException When a shutdown of a service failed.
	 */
	@Override
	public void stop() throws ServiceException {
		if (!isStarted()) {
			throw new ServiceException("The worker component is already stopped.");
		}
		LoggerHelper.info(String.format("The worker component '%s' will be stopped...", getName()));
		Object[]services = getProfile().getServiceSet().toArray();
		for (int i = services.length-1; i>=0; --i) {
			String s = String.valueOf(services[i]);
			try {
				LoggerHelper.info(String.format("The service '%s' will be stopped.", s));
				stopService(s);
				LoggerHelper.info(String.format("The service '%s' is now stopped.", s));
			} catch (Exception e) {
				LoggerHelper.error(String.format("Error append during stop "
						+ "process of the serive '%s'.", s), e);
			}
		}
		started = false;
		status = WCStatusFactory.createStopStatus(this);
		LoggerHelper.info(String.format("The worker component %s was stopped.", getName()));
	}
	/**
	 * Returns true if the worker component is started.
	 * @return True if the worker component is started.
	 */
	@Override
	public boolean isStarted() {
		return (started) ? true : false;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getNetwork() {
		return network;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getArea() {
		return area;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public Integer getNumericId() {
		return Integer.valueOf(id);
	}
	@Override
	public WCStatus getStatus() {
		return status;
	}
	@Override
	public KernelService getKernel() {
		return kernel;
	}
	@Override
	public void setParameter(String pParameterPath, Object pValue) 
			throws ServiceException {
		
		int dotPosition = pParameterPath.lastIndexOf(".");
		if (dotPosition == -1)
			throw new ServiceException(String.format(
					"Parameter path syntax of '%s' is invalid: the valid syntax is 'service name[dot]parameter name'",
					pParameterPath
				));
		
		String serviceName = pParameterPath.substring(0, dotPosition);
		String parameterName = pParameterPath.substring(dotPosition+1);
		
		setParameter(serviceName, parameterName, pValue);
	}
	@Override
	public Object getParameter(String pParameterPath) 
			throws ServiceException {
		
		int dotPosition = pParameterPath.lastIndexOf(".");
		if (dotPosition == -1)
			throw new ServiceException(String.format(
					"Parameter path syntax of '%s' is invalid: the valid syntax is 'service name[dot]parameter name'",
					pParameterPath
				));
		
		String serviceName = pParameterPath.substring(0, dotPosition);
		String parameterName = pParameterPath.substring(dotPosition+1);
		
		return getParameter(serviceName, parameterName);
	}
	@Override
	public String toString() {
		return String.format("WC %s (%s)", name, network);
	}
}
