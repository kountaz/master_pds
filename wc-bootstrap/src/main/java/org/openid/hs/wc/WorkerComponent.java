package org.openid.hs.wc;

import org.openid.hs.core.Service;
import org.openid.hs.core.ServiceManager;
import org.openid.hs.core.exception.ServiceException;

/**
 * Interface of a WorkerComponent object.
 * @version R2
 * @author Steven
 *
 */
public interface WorkerComponent extends Service, ServiceManager {
	/**
	 * Returns network of the WorkerComponent.
	 * @return Network of the WorkerComponent.
	 */
	public String getNetwork();
	/**
	 * Returns type of the WorkerComponent.
	 * @return type of the WorkerComponent.
	 */
	public String getType();
	/**
	 * Returns area of the WorkerComponent.
	 * @return area of the WorkerComponent.
	 */
	public String getArea();
	/**
	 * Returns id of the WorkerComponent.
	 * @return id of the WorkerComponent.
	 */
	public String getId();
	/**
	 * Returns id of the WorkerComponent.
	 * @return id of the WorkerComponent.
	 */
	public Integer getNumericId();
	/**
	 * Returns Status object of the WorkerComponent.
	 * @return Status object of the WorkerComponent.
	 */
	public WCStatus getStatus();
	/**
	 * Returns the kernel of the WorkerComponent.
	 * @return The kernel of the WorkerComponent.
	 */
	public KernelService getKernel();
	/**
	 * Defines parameter of a service. The valid path parameter 
	 * syntax is "service name[dot]parameter name".
	 * @param pParameterPath Path of the parameter to define.
	 * @param pValue Value of the parameter to define.
	 * @throws ServiceException When parameter is not found.
	 */
	public void setParameter(String pParameterPath, Object pValue) 
			throws ServiceException;
	/**
	 * Returns parameter of a service. The valid path parameter 
	 * syntax is "service name[dot]parameter name".
	 * @param pParameterPath Path of the parameter.
	 * @throws ServiceException When parameter is not found.
	 */
	public Object getParameter(String pParameterPath) 
			throws ServiceException;
}
