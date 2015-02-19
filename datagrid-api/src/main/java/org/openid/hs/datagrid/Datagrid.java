package org.openid.hs.datagrid;

import java.util.Set;

import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.DBCollection;

/**
 * Interface of datagrid.
 * @version R3
 * @author Steven, Victor
 *
 */
public interface Datagrid {
	/**
	 * Returns data engine of the datagrid.
	 * @return Data engine.
	 */
	public DataEngine getEngine();
	/**
	 * Returns database collection of the datagrid. 
	 * @return Database collection.
	 */
	public DBCollection getStorage();
	/**
	 * Returns name of the datagrid.
	 * @return Name string.
	 */
	public String getName();
	/**
	 * Creates and returns storage key corresponding to the given key.
	 * @param pKey Key string.
	 * @return Storage key.
	 */
	public String buildStoreKey(String pKey);
	/**
	 * Returns the key set of the datagrid.
	 * @return The key set of the datagrid.
	 */
	public Set<String> getKeys();
	/**
	 * Returns a value of the datagrid.
	 * @param pKey Data key to retrieve.
	 * @return A value of the datagrid corresponding to the given key.
	 * @throws DatagridException
	 */
	public Object get(String pKey) throws DatagridException;
	/**
	 * Defines a value in the datagrid.
	 * @param pKey Data key.
	 * @param pValue Data value.
	 */
	public void set(String pKey, Object pValue);
	/**
	 * Defines a value in the datagrid.
	 * @param pKey Data key.
	 * @param pValue Data value.
	 * @param pExpiration Expiration duration.
	 */
	public void set(String pKey, Object pValue, int pExpiration);
	/**
	 * Removes a value of the datagrid.
	 * @param pKey Data key to remove.
	 * @return A value of the datagrid corresponding to the given key. 
	 * @throws DatagridException
	 */
	public Object remove(String pKey) throws DatagridException;
}
