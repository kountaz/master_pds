package org.openid.hs.bootstrap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.openid.hs.core.Service;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.WorkerComponent;

/**
 * Bootstrap is used on WorkerComponent to allow remote administration.
 * @version R2
 * @author Fanta, Meryem, Mouhamadou, Steven
 *
 */
public class WCBootstrap implements Service, Runnable {
	/**
	 * Default bootstrap name pattern.
	 */
	public static final String BOOTSTRAP_NAME_PATTERN = "Bootstrap of %s";
	/**
	 * Default socket server backlog.
	 */
	public static final int DEFAULT_SOCKET_SERVER_BACKLOG = 50;
	
	/**
	 * Worker component of this bootstrap.
	 */
	private WorkerComponent wc;
	
	public WCBootstrap(WorkerComponent pWc) {
		wc = pWc;
	}
	/**
	 * Returns the name of the worker component.
	 * @return The name of the worker component.
	 */
	@Override
	public String getName() {
		return String.format(BOOTSTRAP_NAME_PATTERN, wc.getName());
	}
	/**
	 * Starts the worker component.
	 */
	@Override
	public void start() {
		if (!isStarted()) {
			try {
				wc.start();
			} catch (ServiceException e) {
				LoggerHelper.error("Service exception", e);
			}
		} else {
			LoggerHelper.warn("The worker component has already started: the start request was ignored");
		}
	}
	/**
	 * Stops the worker component.
	 */
	@Override
	public void stop() {
		if (isStarted()) {
			try {
				wc.stop();
			} catch (ServiceException e) {
				LoggerHelper.error("Service exception", e);
			}
		} else {
			LoggerHelper.warn("The worker component is stopped: the stop request was ignored");
		}
	}
	/**
	 * Returns the current status of the worker component.
	 * @return The current status of the worker component.
	 */
	public String status() {
		return wc.getStatus().toString();
	}
	/**
	 * Returns true if the worker component is started.
	 * @return True if the worker component is started.
	 */
	@Override
	public boolean isStarted() {
		return wc.isStarted();
	}
	/**
	 * Bootstrap listening.
	 */
	@Override
	public void run() {
		LoggerHelper.info(String.format("Bootstrap of %s is starting...", wc));
		KernelService kernel = wc.getKernel();
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(kernel.getBootstrapPort(), DEFAULT_SOCKET_SERVER_BACKLOG, kernel.getHost());
			LoggerHelper.info("Bootstrap "+ serverSocket.toString());
			do {
				Socket socket = serverSocket.accept();
				WCBootstrapListener listener = new WCBootstrapListener(this, socket);
				//new Thread(listener).start();
				listener.run();
			} while (true);
		} catch (IOException e) {
			LoggerHelper.error(String.format(
					"Could not listen on %s:%s", 
					kernel.getHost(), 
					kernel.getBootstrapPort()
				), e);
		}
	}
	/**
	 * Starts the bootstrap listening.
	 */
	public void listen() {
		Thread threadBootstrap = new Thread(this);
		threadBootstrap.start();
	}
}
