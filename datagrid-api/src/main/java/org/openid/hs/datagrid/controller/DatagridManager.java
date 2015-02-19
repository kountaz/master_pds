package org.openid.hs.datagrid.controller;

import org.openid.hs.datagrid.DataBuilder;
import org.openid.hs.datagrid.bean.DatagridBean;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.DBCollection;

/**
 * Datagrid manager.
 * @see DatagridBean
 * @version R3
 * @author Steven, Victor
 *
 */
public final class DatagridManager extends DatagridBean {
	public DatagridManager(String pName, DataEngine pEngine) {
		super(pName, pEngine);
	}
	public DatagridManager(DBCollection pStorage, DataEngine pEngine) {
		super(pStorage, pEngine);
	}
	/**
	 * Returns true if data in grid are cached.
	 * @return True if data in grid are cached.
	 */
	public boolean isCached() {
		for (DataBuilder builder : data.values()) {
			if (!builder.isCached()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns true if data in grid are persisted.
	 * @return True if data in grid are persisted.
	 */
	public boolean isPersisted() {
		for (DataBuilder builder : data.values()) {
			if (!builder.isPersisted()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Persists data in grid.
	 */
	public void persist() {
		DataBuilder builder;
		for (String key : data.keySet()) {
			builder = data.get(key);
			builder.persist();
		}
		for (String key : removedData.keySet()) {
			builder = removedData.get(key);
			builder.remove();
		}
		removedData.clear();
	}
	/**
	 * Restores data in grid.
	 */
	public void restore() {
		DataBuilder builder;
		for (String key : removedData.keySet()) {
			builder = removedData.get(key);
			data.put(key, builder);
		}
		removedData.clear();
		for (String key : data.keySet()) {
			builder = data.get(key);
			builder.restore();
		}
	}
	/**
	 * Resets data in grid.
	 */
	public void reset() {
		String []keySet = new String[data.keySet().size()];
		keySet = data.keySet().toArray(keySet);
		for (String key : keySet) {
			try {
				remove(key);
			} catch (DatagridException ignored) { }
		}
	}
	/**
	 * Synchronizes data in grid.
	 */
	public void synchronize() {
		for (DataBuilder builder : data.values()) {
			builder.synchronize();
		}
	}
}
