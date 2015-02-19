package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.wc.DatagridService;

/**
 * Concrete datagrid service.
 * @see DatagridService
 * @version R2
 * @author Seybany, Steven
 *
 */
public class DatagridServiceBean extends AbstractService implements DatagridService {
	/**
	 * Database port of the datagrid API.
	 */
	private int databasePort;
	/**
	 * Memcache port of the datagrid API.
	 */
	private int memcachePort;
	/**
	 * The datagrid API.
	 */
	private DatagridAPI datagridApi;
	
	public DatagridServiceBean() {
		super(SERVICE_NAME);

	}
	@Override
	public void setDatabasePort(Integer pDatabasePort) {
		databasePort = pDatabasePort;
	}
	@Override
	public int getDatabasePort() {
		return databasePort;
	}
	@Override
	public void setMemcachePort(Integer pMemcachePort) {
		memcachePort = pMemcachePort;
	}
	@Override
	public int getMemcachePort() {
		return memcachePort;
	}
	@Override
	public void start() throws ServiceException {
		if (databasePort == 0)
			throw new ServiceException("A database port must be defined.");
		if (memcachePort == 0)
			throw new ServiceException("A memcache port must be defined.");
			
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init(databasePort, memcachePort);
			}
			datagridApi = DatagridAPI.get();
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		super.stop();
	}
	@Override
	@Deprecated
	public DatagridAPI getAPI() throws ServiceException {
		if (!isStarted()) {
			throw new ServiceException("Datagrid service must be started");
		}
		
		return datagridApi;
	}
}
