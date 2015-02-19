package org.openid.hs.wc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openid.hs.core.Service;

/**
 * Kernel service of worker component. Its defines most important parameters.
 * @version R2
 * @author Steven
 *
 */
public interface KernelService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "kernel";
	
	/**
	 * Defines the WorkerComponent.
	 * @param pWc
	 */
	public void setWc(WorkerComponent pWc);
	/**
	 * Returns the WorkerComponent.
	 * @return The WorkerComponent.
	 */
	public WorkerComponent getWc();
	/**
	 * Defines the IP of the WorkerComponent. 
	 * @param pHost String representation of an IP address.
	 * @throws UnknownHostException When the host targeted by pHost is unknown.
	 */
	public void setHost(String pHost) throws UnknownHostException;
	/**
	 * Returns IP of the WorkerComponent.
	 * @return IP of the WorkerComponent.
	 */
	public InetAddress getHost();
	/**
	 * Defines bootstrap port of the WorkerComponent.
	 * @param pBootstrapPort Port for the bootstrap.
	 */
	public void setBootstrapPort(Integer pBootstrapPort);
	/**
	 * Returns bootstrap port of the WorkerComponent.
	 * @return Bootstrap port of the WorkerComponent.
	 */
	public Integer getBootstrapPort();
}
