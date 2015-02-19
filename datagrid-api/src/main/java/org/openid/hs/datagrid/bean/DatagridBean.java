package org.openid.hs.datagrid.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openid.hs.datagrid.DataBuilder;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.DBCollection;

/**
 * Concrete datagrid.
 * @see Datagrid
 * @version R3
 * @author Steven, Victor
 *
 */
public class DatagridBean implements Datagrid {
	/**
	 * Data engine of this datagrid.
	 */
	private DataEngine engine;
	/**
	 * Database collection of this datagrid.
	 */
	private DBCollection storage;
	/**
	 * Name of this datagrid.
	 */
	private String name;
	
	protected Map<String, DataBuilder> data;
	protected Map<String, DataBuilder> removedData;
	
	public DatagridBean(String pName, DataEngine pEngine) {
		this(pEngine.getDatabase().getCollection(pName), pEngine);
	}
	public DatagridBean(DBCollection pStorage, DataEngine pEngine) {
		engine = pEngine;
		storage = pStorage;
		name = pStorage.getName();
		data = new HashMap<String, DataBuilder>();
		removedData = new HashMap<String, DataBuilder>();
	}
	@Override
	public DataEngine getEngine() {
		return engine;
	}
	@Override
	public DBCollection getStorage() {
		return storage;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String buildStoreKey(String pKey) {
		return String.format("%s.%s.%s", engine.getDatabase().getName(), getName(), pKey);
	}
	@Override
	public Set<String> getKeys() {
		return data.keySet();
	}
	@Override
	public Object get(String pKey) throws DatagridException {
		if (!data.containsKey(pKey)) {
			throw new DatagridException("No entry for the key "+ pKey);
		}
		return data.get(pKey).get();
	}
	@Override
	public void set(String pKey, Object pValue) {
		set(pKey, pValue, DataBuilder.DEFAULT_EXPIRATION);
	}
	@Override
	public void set(String pKey, Object pValue, int pExpiration) {
		DataBuilder builder = data.get(pKey);
		if (builder == null && removedData.containsKey(pKey)) {
			builder = removedData.get(pKey);
			removedData.remove(pKey);
			data.put(pKey, builder);
		}
		if (builder == null) {
			builder = new DataBuilderBean(pKey, this);
			data.put(pKey, builder);
		}
		builder.setExpiration(pExpiration);
		builder.set(pValue);
	}
	@Override
	public Object remove(String pKey) throws DatagridException {
		if (!data.containsKey(pKey)) {
			throw new DatagridException("No entry for the key "+ pKey);
		}
		DataBuilder builder = data.get(pKey);
		Object value = builder.get();
		builder.unset();
		data.remove(pKey);
		removedData.put(pKey, builder);
		return value;
	}
}
