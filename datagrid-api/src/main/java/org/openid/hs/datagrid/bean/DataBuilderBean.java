package org.openid.hs.datagrid.bean;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import net.spy.memcached.internal.OperationFuture;

import org.openid.hs.datagrid.Data;
import org.openid.hs.datagrid.DataBuilder;
import org.openid.hs.datagrid.DataState;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.controller.DataSerializer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

/**
 * Concrete data builder.
 * @See DataBuilder
 * @version R3
 * @author Steven, Victor
 *
 */
public class DataBuilderBean implements DataBuilder, Runnable {
	/**
	 * Datagrid of this builder.
	 */
	private Datagrid grid;
	/**
	 * Key of the data built.
	 */
	private String key;
	/**
	 * Last cache key.
	 */
	private String cacheKey;
	/**
	 * Last persist key.
	 */
	private String persistKey;
	/**
	 * Expiration delay of this data built.
	 */
	private int expiration;
	/**
	 * Expiration task.
	 */
	private Thread expirationTask;
	/**
	 * Cache flag.
	 */
	private boolean cached;
	/**
	 * Persist flag
	 */
	private boolean persisted;
	
	public DataBuilderBean(String pKey, Datagrid pGrid) {
		this(pKey, DEFAULT_EXPIRATION, pGrid);
	}
	public DataBuilderBean(String pKey, int pExpiration, Datagrid pGrid) {
		super();
		grid = pGrid;
		key = pKey;
		cacheKey = new String(key);
		persistKey = new String(key);
		expiration = pExpiration;
		cached = false;
		persisted = false;
	}
	@Override
	public void setKey(String pKey) {
		key = pKey;
		if (!key.equals(cacheKey)) {
			cached = false;
		}
		if (!key.equals(persistKey)) {
			persisted = false;
		}
	}
	@Override
	public void setExpiration(int pExpiration) {
		expiration = pExpiration;
	}
	@Override
	public boolean isCached() {
		return cached;
	}
	@Override
	public boolean isPersisted() {
		return persisted;
	}
	@Override
	public byte[] getState() {
		return DataState.create(cached, persisted);
	}
	@Override
	public String state() {
		return DataState.toString(getState());
	}
	@Override
	public String buildStoreKey() {
		return grid.buildStoreKey(key);
	}
	@Override
	public Data build(Object pValue) {
		return new DataBean(key, pValue, expiration);
	}
	@Override
	public void set(Object pValue) {
		Data data = build(pValue);
		grid.getEngine().getMemcached().set(buildStoreKey(), expiration, data);
		System.out.println("INFO: >> Set "+ key +" / "+ expiration);
		cancelExpirationTask();
		if (!key.equals(cacheKey)) {
			unset(cacheKey);
			cacheKey = new String(key);
		}
		cached = true;
		expirationTask = new Thread(this);
		expirationTask.start();
	}
	@Override
	public Data getData() {
		if (!cached) {
			return null;
		}
		try {
			return (Data) grid.getEngine().getMemcached().get(buildStoreKey());
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public Object get() {
		Data data = getData();
		return data != null ? data.getValue() : null;
	}
	@Override
	public void unset() {
		unset(key);
	}
	private void unset(String pKey) {
		OperationFuture<Boolean> operation = grid.getEngine().getMemcached().delete(grid.buildStoreKey(pKey));
		cancelExpirationTask();
		try {
			if (operation.get()) {
				System.out.println("INFO: >> Unset "+ pKey);
			}
		} catch (InterruptedException | ExecutionException ignored) { }
		cached = false;
	}
	@Override
	public void run() {
		if (expiration > 0) {
			try {
				for (int i = 0; i < expiration; ++i) {
					Thread.sleep(1000);
				}
				cached = false;
				// expirationTimer.stop();
				System.out.println("INFO. >> Expire " + cacheKey);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("__ expiration task aborded "+ cacheKey);
			}
		}
	}
	private void cancelExpirationTask() {
		if (expirationTask != null && expirationTask.isAlive()) {
			expirationTask.interrupt();
			System.out.println("__ kill previous expiration task "+ cacheKey);
		}
	}
	@Override
	public void persist() {
		if (!key.equals(persistKey)) {
			unpersist(persistKey);
			persistKey = new String(key);
		}
		if (!isCached()) {
			System.out.println("ERR: data can't be persisted because it's expired");
			return;
		}
		BasicDBObject query = new BasicDBObject("key", buildStoreKey());
		grid.getStorage().remove(query);
		try {
			query.append("value", DataSerializer.toString(getData()));
			grid.getStorage().insert(query);
			System.out.println("INFO: >> Persist "+ key);

			persisted = true;
		} catch (IOException e) {
			System.out.println("ERR: data can't be persisted because the serialization of the value to persist has failed");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	@Override
	public void unpersist() {
		unpersist(key);
	}
	private void unpersist(String pKey) {
		BasicDBObject query = new BasicDBObject("key", grid.buildStoreKey(pKey));
		WriteResult operation = grid.getStorage().remove(query);
		if (operation.getN() > 0) {
			System.out.println("INFO: >> Unpersist "+ pKey);
		}
		persisted = false;
	}
	@Override
	public void restore() {
		BasicDBObject query = new BasicDBObject("key", grid.buildStoreKey(persistKey));
		DBCursor result = grid.getStorage().find(query);
		if (!result.hasNext()) {
			System.out.println("ERR: data can't be restored because it's not found in database");
			return;
		}
		Data data;
		try {
			data = (Data) DataSerializer.fromString(result.next().get("value").toString());
			key = data.getKey();
			expiration = data.getExpiration();
			set(data.getValue());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("ERR: data can't be restored because the unserialization of the persited value has failed");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	@Override
	public void remove() {
		unset();
		unpersist();
	}
	@Override
	public void synchronize() {
		if (isCached()) {
			persist();
		} else if (isPersisted()) {
			restore();
		}
	}
	@Override
	public String toString() {
		return String.format("%s: %s (%ds), %s >> %s", super.toString(), key, expiration, state(), getData());
	}
}
