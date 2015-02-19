package org.openid.hs.replication.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.ILoaderMaterial;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.discovery.exception.InvalidParameters;
import org.openid.hs.replication.IReplication;
import org.openid.hs.replication.exception.ReplicationException;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author KOUNTA
 * @version R2 <a>Class of replication context generic</a>
 */
public class ReplicationManager implements Runnable, Serializable,
		IReplication {

	private static final long serialVersionUID = 1L;
	private final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
	private Lock contextLock = new ReentrantLock();

	private DBCursor cursor;
	private ServerSocket socketReceiver;
	private Socket socketReceiverSocket;
	private ObjectInputStream in;

	private ILoaderMaterial MaterielRefentiel;

	//private CommunicationAPI commApi;
	//private String key;

	private DatagridAPI datagridApi;
	private Datagrid grid;

	private Socket socket;
	private ObjectOutputStream out;

	private String WC_ID;
	private String NETWORK;
	private String TYPE;
	private String ZONE;
	private String PORT_BOOTSTRAP;
	private String ADDRESS_IP;
	
	private Thread thread;
	private boolean isStarted;

	public ReplicationManager(Datagrid pGrid, String WC_ID,
			LoaderMaterialReferentiel MaterielRefentiel)
			throws DatagridException {

		new ThreadPoolExecutor(50, // 10 threads in pool
								50, // 10 max. thread in pool
								0, // No wait for pending tasks
								TimeUnit.MILLISECONDS, queue, // Task are placed here while
												// pending
				new ThreadPoolExecutor.AbortPolicy());
		this.WC_ID = WC_ID;
		this.datagridApi = DatagridAPI.get();
		this.grid = pGrid;

		this.datagridApi.restoreDatagrid(grid);

		this.MaterielRefentiel = MaterielRefentiel;

		isStarted = false;
		
		initWCCoordonnee(MaterielRefentiel);
	}

	public void initWCCoordonnee(ILoaderMaterial ref) {

		try {
			LoggerHelper.info("This is the line concerning my engine");
			cursor = ref.selectDataMaterial("WC_ID", WC_ID);

			for (DBObject dbObject : cursor) {
				NETWORK = dbObject.get("NETWORK").toString();
				TYPE = dbObject.get("TYPE").toString();
				ZONE = dbObject.get("ZONE").toString();
				PORT_BOOTSTRAP = dbObject.get("PORT").toString();
				ADDRESS_IP = dbObject.get("ADDRESS_IP").toString();
			}
			LoggerHelper.info(NETWORK + "|" + TYPE + "|" + ZONE + "|" + WC_ID
					+ "|-|" + ADDRESS_IP + "|" + PORT_BOOTSTRAP);

		} catch (InvalidParameters e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method
	 * 
	 * @param DBObject
	 *            object in order to get all servers references this method will
	 *            be call in updateMyContextToZone() method.
	 * @throws InvalidParameters
	 *             , InvalidParameters
	 * @return void
	 * @throws CommunicationException
	 * @throws DatagridException
	 */
	private void ConTextSender(DBObject dbObject,
			ReplicationObject replicant) {

		String address = null;
		String port = null;
		String WC_ID = null;
		String Type = null;
		String Zone = null;
		/*
		 * try { commApi = CommunicationAPI.init(1530); } catch
		 * (CommunicationException e1) { // TODO Auto-generated catch block
		 * LoggerHelper
		 * .error("Impossible to establish connection of CommunicationAPI"); }
		 */

		address = dbObject.get("ADDRESS_IP").toString();
		port = dbObject.get("PORT").toString();
		WC_ID = dbObject.get("WC_ID").toString();
		Type = dbObject.get("TYPE").toString();
		Zone = dbObject.get("ZONE").toString();

		if (!this.WC_ID.equals(WC_ID)) {

			try {
				socket = new Socket(InetAddress.getByName(address),
						Integer.parseInt(port) + 100);
				out = new ObjectOutputStream(socket.getOutputStream());
				// datagridApi.resetDatagrid(grid);
				out.writeObject(replicant);
				out.flush();
				out.close();
				socket.close();

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				LoggerHelper
						.error("Bad parameters numbers in select from database to get servers for replication",
								e);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				LoggerHelper.error("Bad hostName", e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerHelper.warn("The " + Type + " at " + Zone + " with ID : "
						+ WC_ID + " not reachable for replication", e);
				/**
				 * Send the unavailable servers to BO
				 */
				/*
				 * Communication communicationError =
				 * commApi.createCommunication( "in", "out",
				 * "JMSType = 'ReplicationFailSignal'",
				 * ReplicationFailSignal.class ); Message m =
				 * MessageFactory.create("ReplicationFailSignal");
				 * 
				 * m.setContent(ReplicationFailSignal.builErrorMessage("The "+Type
				 * +" at " +Zone +" with ID : "
				 * +WC_ID+" not reachable for replication"));
				 * communicationError.send(m);
				 */
			}

		} else {
			LoggerHelper.info(this.TYPE + " " + this.WC_ID
					+ " : Ignore my own replication request");
		}
	}

	/**
	 * 
	 * @param ILoaderMaterial
	 *            object in order to get all servers references
	 * @throws InvalidParameters
	 *             , InvalidParameters
	 * @return void
	 * @throws CommunicationException
	 * @throws DatagridException
	 */

	public void updateMyContextToZone(ReplicationObject replicant)
			throws ReplicationException {

		LoggerHelper.info("The server available for replication context");
		try {
			//The following instruction consist to limit replication only in zone. 
			//cursor = MaterielRefentiel.selectDataMaterial("TYPE", this.TYPE,"ZONE", this.ZONE);
			cursor = MaterielRefentiel.selectDataMaterial("TYPE", this.TYPE);

			for (DBObject dbObject : cursor) {
				ConTextSender(dbObject, replicant);
			}
		} catch (InvalidParameters e) {
			throw new ReplicationException(e);
		}
	}

	/**
	 * This method consist to run the replication receiver listener for all
	 * component
	 * 
	 * @param context
	 * @return void
	 * @throws Exception
	 * 
	 */
	public void replicationContextListener() throws Exception {

		try {
			socketReceiver = new ServerSocket(
					Integer.parseInt(PORT_BOOTSTRAP) + 100);

			while (isStarted) {
				LoggerHelper.info(this.TYPE+" "+this.WC_ID+" : Replication system listener in running...");
				socketReceiverSocket = socketReceiver.accept();

				in = new ObjectInputStream(
						socketReceiverSocket.getInputStream());
				try {

					if (contextLock.tryLock(200, TimeUnit.MILLISECONDS)) {
						ReplicationObject replicant = (ReplicationObject) in
								.readObject();
						/**
						 * Remove Object from grid
						 */
						if (replicant.getKey().startsWith("-")) {
							LoggerHelper
									.info(this.TYPE+" "+this.WC_ID+" : "+" Order to delete context with key "
											+ replicant.getKey().substring(1)
											+ " receive from "
											+ replicant.getWC_ID());
							grid.remove(replicant.getKey().substring(1));
							LoggerHelper.info(this.TYPE+" "+this.WC_ID+" : The context with key : "
									+ replicant.getKey().substring(1)
									+ " in the WC number " + this.WC_ID
									+ " is deleted");
						} else {
							grid.set(replicant.getKey(), replicant.getContext());
							LoggerHelper
									.info(this.TYPE+" "+this.WC_ID+" : "+"Replicant context receive from : "
											+ replicant.getWC_ID());
							//LoggerHelper.info("SOLUTIONNNN FOR " + this.WC_ID
							//		+ ":=> " + grid.get("deux"));
						}
					} else {
						LoggerHelper
								.error("Trying to acquire lock for preserving context fails");
						throw new Exception(
								"Trying to acquire lock for preserving context fails");
					}
				} catch (ClassNotFoundException e) {
					LoggerHelper.error("Class not found");
				} catch (InterruptedException e) {
					LoggerHelper.error("Interrupted while updating context.");
				} finally {
					contextLock.unlock();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			replicationContextListener();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerHelper.error("Error to run replication listener", e);
		}
	}

	public ReplicationObject createReplicationObject(String pKey)
			throws ReplicationException {
		String realKey = pKey;
		if (pKey.startsWith("-")) {
			realKey = pKey.substring(1);
		}
		Object value;
		try {
			value = grid.get(realKey);
		} catch (DatagridException e) {
			throw new ReplicationException(e);
		}
		ReplicationObject replicant = new ReplicationObject(pKey, value,
				WC_ID);
		return replicant;
	}

	public void start() {
		isStarted = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		isStarted = false;
		thread = null;
	}
}
