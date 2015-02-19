package org.openid.hs.datagrid;

/**
 * Interface of data builder.
 * @version R3
 * @author Steven, Victor
 *
 */
public interface DataBuilder {
	/**
	 * Default expiration delay.
	 */
	public static final int DEFAULT_EXPIRATION = 0;
	/**
	 * Defines key of the data built.
	 * @param pKey Key Key name.
	 */
	public void setKey(String pKey);
	/**
	 * Defines expiration delay of the data built.
	 * @param pExpiration Expiration delay in seconds.
	 */
	public void setExpiration(int pExpiration);
	/**
	 * Returns true if the data built is cached.
	 * @return True if the data built is cached or false.
	 */
	public boolean isCached();
	/**
	 * Returns true if the data built is persisted.
	 * @return True if the data built is persisted or false.
	 */
	public boolean isPersisted();
	/**
	 * Returns state of the data built.
	 * @return State code (0|1:expired or cached, 0|1: unpersisted or persisted).
	 */
	public byte[] getState();
	/**
	 * Returns state string of the data built.
	 * @return State string.
	 */
	public String state();
	/**
	 * Creates and returns storage key of data built.
	 * @return Storage key.
	 */
	public String buildStoreKey();
	/**
	 * Creates and returns the data of this builder.
	 * @param pValue Value of data to be built.
	 * @return New data built by this builder.
	 */
	public Data build(Object pValue);
	/**
	 * Sets data built to the cache.
	 * @param pValue Value of data to be built.
	 */
	public void set(Object pValue);
	/**
	 * Returns data in cache if data isn't expired.
	 * @return Data in cache or null.
	 */
	public Data getData();
	/**
	 * Returns data value in cache if data isn't expired.
	 * @return Data value in cache or null.
	 */
	public Object get();
	/**
	 * Remove data from the cache if data built is cached.
	 */
	public void unset();
	/**
	 * Persists data in database if data built is cached.
	 */
	public void persist();
	/**
	 * Remove data from the database if data built is persisted.
	 */
	public void unpersist();
	/**
	 * Sets persisted data to the cache if data built is persisted.
	 */
	public void restore();
	/**
	 * Remove data built (unset and persist opérations).
	 */
	public void remove();
	/**
	 * Synchronize data built : restore if data is expired and persisted or 
	 * persist if data is cached.
	 */
	public void synchronize();
}
