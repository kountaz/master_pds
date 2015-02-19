package org.openid.hs.embeddedprocess;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;
import com.thimbleware.jmemcached.storage.hash.ConcurrentLinkedHashMap;

/**
 * Memcached embedded process.
 * @see AbstractEmbeddedProcess
 * @version R3
 * @author Steven, Victor
 *
 */
public class MemcachedEmbeddedProcess extends AbstractEmbeddedProcess {
	/**
	 * Type of this embedded process.
	 */
	public static final int TYPE = 2;
	/**
	 * All memcached client created.
	 */
	private static Map<Integer, MemcachedClient> clients = new HashMap<Integer, MemcachedClient>();

	/**
	 * Returns memcached client.
	 * @param pPort Port of memcached server.
	 * @return Memcached client.
	 * @throws IOException
	 */
	public static MemcachedClient getClient(int pPort) throws IOException {
		if (!clients.containsKey(pPort)) {
			clients.put(pPort, new MemcachedClient(new InetSocketAddress(
					"localhost", pPort)));
		}
		MemcachedClient client = clients.get(pPort);
		return client;
	}
	/**
	 * Closes memcached clients.
	 * @param pPort Port of client to closed.
	 */
	public static void closeClient(int pPort) {
		if (clients.containsKey(pPort)) {
			MemcachedClient client = clients.get(pPort);
			client.shutdown();
			clients.remove(pPort);
		}
	}
	
	private static Map<Integer, MemCacheDaemon<LocalCacheElement>> daemons = new HashMap<Integer, MemCacheDaemon<LocalCacheElement>>();
	
	/**
	 * Memcached server.
	 */
	private MemCacheDaemon<LocalCacheElement> daemon;

	public MemcachedEmbeddedProcess(int pPort) throws EmbeddedProcessException {
		super(TYPE, pPort);
	}
	@Override
	public void initProcess() throws EmbeddedProcessException {
		if (daemons.containsKey(getPort())) {
			daemon = daemons.get(getPort());
			return;
		}
		try {
			daemon = new MemCacheDaemon<LocalCacheElement>();
			CacheStorage<Key, LocalCacheElement> storage = ConcurrentLinkedHashMap
					.create(ConcurrentLinkedHashMap.EvictionPolicy.FIFO,
							1000000, 67108864);
			daemon.setCache(new CacheImpl(storage));
			//daemon.setBinary(true);
			daemon.setAddr(new InetSocketAddress(getPort()));
			daemon.setIdleTime(1);
			daemon.setVerbose(!AbstractEmbeddedProcess.SILENT_LOGGING);
		} catch (Exception e) {
			throw new EmbeddedProcessException(e);
		}
	}
	@Override
	public void start() throws EmbeddedProcessException {
		daemon.start();
	}
	@Override
	public void stop() throws EmbeddedProcessException {
		closeClient(getPort());
		daemon.stop();
	}
}
