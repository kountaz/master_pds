package org.openid.hs.datagrid;

import java.util.HashMap;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.datagrid.controller.DatagridManager;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.embeddedprocess.EmbeddedProcessException;
import org.openid.hs.embeddedprocess.EmbeddedProcessHelper;
import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;

import com.mongodb.MongoClient;

/**
 * Datagrid API which used to manage datagrid.
 * @version R3
 * @author Steven, Victor
 *
 */
public class DatagridAPI {
	/**
	 * Default mongodb port.
	 */
	public static final int DEFAULT_MONGODB_PORT = 1500;
	/**
	 * Default memcached port.
	 */
	public static final int DEFAULT_MEMCACHED_PORT = 1501;
	/**
	 * Database which stores datagrids.
	 */
	public static final String DATABASE_NAME = "hs_datagrids";
	/**
	 * Ports of embedded process.
	 */
	private static int []processPorts;
	/**
	 * The unique instance of DatagridAPI.
	 */
	private static DatagridAPI instance;
	
	/**
	 * Indicates if the datagrid API is initialized.
	 * @return True if the unique datagrid API is initialized.
	 */
	public static boolean isInit() {
		return instance != null;
	}
	/**
	 * Initializes the datagrid API.
	 * @return The unique datagrid API.
	 * @throws DatagridException When the datagrid API is already initialized.
	 */
	public static DatagridAPI init() throws DatagridException {
		return init(DEFAULT_MONGODB_PORT, DEFAULT_MEMCACHED_PORT);
	}
	/**
	 * Initializes the datagrid API.
	 * @param pMongodbPort
	 * @param pMemcachedPort
	 * @return The unique datagrid API.
	 * @throws DatagridException When the datagrid API is already initialized.
	 */
	public static DatagridAPI init(int pMongodbPort, int pMemcachedPort) 
			throws DatagridException {
		
		if (isInit()) {
			throw new DatagridException("The datagrid API is already initialized.");
		}
		if (pMongodbPort == pMemcachedPort) {
			throw new DatagridException("Mongodb and Memcached servers can't be on the same port.");
		}
		
		try {
			EmbeddedProcessHelper.startServer(MongoEmbeddedProcess.TYPE, pMongodbPort);
			EmbeddedProcessHelper.startServer(MemcachedEmbeddedProcess.TYPE, pMemcachedPort);
			
			processPorts = new int[] {pMongodbPort, pMemcachedPort};
			DataEngine engine = new DataEngine(pMongodbPort, DATABASE_NAME, pMemcachedPort);
			instance = new DatagridAPI(engine);
			
			return instance;
		} catch (Exception e) {
			throw new DatagridException(e);
		}
	}
	/**
	 * Destroys the datagrid API.
	 * @throws DatagridException When the datagrid API isn't initialized.
	 */
	public static void destroy() 
			throws DatagridException {
		
		if (!isInit()) {
			throw new DatagridException("The datagrid API isn't initialized yet.");
		}
		
		try {
			for (int port : processPorts) {
				EmbeddedProcessHelper.stopServer(port);
			}
		} catch (EmbeddedProcessException e) {
			e.printStackTrace();
			throw new DatagridException(e);
		}
		
		instance = null;
	}
	/**
	 * Returns the unique datagrid API.
	 * @return The unique datagrid API.
	 * @throws DatagridException When the datagrid API isn't initialized.
	 */
	public static DatagridAPI get() 
			throws DatagridException {
		
		if (!isInit()) {
			throw new DatagridException("The datagrid API isn't initialized yet.");
		}
		
		return instance;
	}
	/**
	 * Active the silent logging of embedded process.
	 */
	public static void nolog() {
		EmbeddedProcessHelper.silentLogging();
	}
	
	/**
	 * Data engine of the datagrid API.
	 */
	private DataEngine engine;
	/**
	 * Datagrid manager.
	 */
	private Map<String, DatagridManager> managers;
	
	private DatagridAPI(DataEngine pEngine) {
		engine = pEngine;
		managers = new HashMap<String, DatagridManager>();
	}
	/**
	 * Returns a mongo client.
	 * @return A mongo client.
	 */
	public MongoClient getMongoClient() {
		return engine.getMongodb();
	}
	/**
	 * Returns a memcached client.
	 * @return A memcached client.
	 */
	public MemcachedClient getMemcachedClient() {
		return engine.getMemcached();
	}
	/**
	 * Returns datagrid.
	 * @param pGridName Grid name.
	 * @return Datagrid.
	 */
	public Datagrid getDatagrid(String pGridName) {
		if (managers.containsKey(pGridName)) {
			return managers.get(pGridName);
		}
		return createDatagrid(pGridName);
	}
	/**
	 * Returns true is the given datagrid is empty.
	 * @param pGrid Datagrid to test.
	 * @return True is the given datagrid is empty.
	 */
	public boolean isEmpty(Datagrid pGrid) {
		DatagridManager manager = managers.get(pGrid.getName());
		return manager.getKeys().size() == 0;
	}
	/**
	 * Returns true if the given datagrid is cached.
	 * @param pGrid Datagrid to test.
	 * @return True if the given datagrid is cached.
	 */
	public boolean isCached(Datagrid pGrid) {
		DatagridManager manager = managers.get(pGrid.getName());
		return manager.isCached();
	}
	/**
	 * Returns true if the given datagrid is persisted.
	 * @param pGrid Datagrid to test.
	 * @return True if the given datagrid is persisted.
	 */
	public boolean isPersisted(Datagrid pGrid) {
		DatagridManager manager = managers.get(pGrid.getName());
		return manager.isPersisted();
	}
	/**
	 * Persists the given datagrid.
	 * @param pGrid Datagrid to persist.
	 */
	public void persistDatagrid(Datagrid pGrid) throws DatagridException {
		DatagridManager manager = managers.get(pGrid.getName());
		manager.persist();
	}
	/**
	 * Restores the given datagrid.
	 * @param pGrid Datagrid to restore.
	 */
	public void restoreDatagrid(Datagrid pGrid) throws DatagridException {
		DatagridManager manager = managers.get(pGrid.getName());
		manager.restore();
	}
	/**
	 * Resets the given datagrid.
	 * @param pGrid Datagrid to reset.
	 */
	public void resetDatagrid(Datagrid pGrid) throws DatagridException {
		DatagridManager manager = managers.get(pGrid.getName());
		manager.reset();
	}
	/**
	 * Load datagrid (a restoration is made.
	 * @param pGridName Grid name.
	 * @return Datagrid.
	 */
	public Datagrid loadDatagrid(String pGridName) throws DatagridException {
		DatagridManager manager = managers.get(pGridName);
		if (manager == null) {
			manager = createDatagrid(pGridName);
		}
		manager.restore();
		return manager;
	}
	/**
	 * Synchronizes the given datagrid.
	 * @param pGrid Datagrid to synchronize.
	 */
	public void synchronizeDatagrid(Datagrid pGrid) {
		DatagridManager manager = managers.get(pGrid.getName());
		manager.synchronize();
	}
	/**
	 * Creates and returns new datagrid.
	 * @param pGridName Grid name.
	 * @return New datagrid.
	 */
	private DatagridManager createDatagrid(String pGridName) {
		DatagridManager manager = new DatagridManager(pGridName, engine);
		managers.put(pGridName, manager);
		return manager;
	}
}
