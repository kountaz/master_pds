package org.openid.hs.wc;

import org.openid.hs.core.Service;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.datagrid.DatagridAPI;

/**
 * Datagrid service which used to recuperate the datagrid API.
 * @see DatagridAPI
 * @version R2
 * @author Seybany, Steven
 *
 */
public interface DatagridService extends Service {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "datagrid";
	
	/**
	 * Defines database port of the datagrid API.
	 * @param pDatabasePort Database port.
	 */
	public void setDatabasePort(Integer pDatabasePort);
	/**
	 * Returns database port of the datagrid API.
	 * @return Database port of the datagrid API.
	 */
	public int getDatabasePort();
	
	/**
	 * Defines memcache port of the datagrid API.
	 * @param pDatabasePort Memcache port of the datagrid API. 
	 */
	public void setMemcachePort(Integer pDatabasePort);
	/**
	 * Returns memcache port of the datagrid API.
	 * @return Memcache port of the datagrid API.
	 */
	public int getMemcachePort();
	
	/**
	 * Returns the datagrid API.
	 * @return The datagrid API.
	 * @throws ServiceException 
	 */
	public DatagridAPI getAPI() throws ServiceException;
}
