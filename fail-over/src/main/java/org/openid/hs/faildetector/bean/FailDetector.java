package org.openid.hs.faildetector.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import org.openid.hs.MaterialZone.Zone;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.ILoaderMaterial;
import org.openid.hs.discovery.exception.InvalidParameters;
import org.openid.hs.faildetector.IFailDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class FailDetector implements IFailDetector {

	Logger logger = LoggerFactory.getLogger(FailDetector.class);
	public boolean isMaster = false;
	public String IPAdress = null;
	public String Port = null;
	public String type = null;
	public String zone = null;
	public String id = null;
	private ILoaderMaterial refMateriel;
	private Timer t;
	private Zone zhand;
	private Thread thread;
	private boolean isStarted;
	/**
	 * Create a new FailDetector Object
	 * 
	 * @param ip
	 *            the component IP adress
	 * @param port
	 *            the component port
	 * @param type
	 *            the component type(kind)
	 * @param zone
	 *            the component zone
	 * @param refm
	 *            pass a referential material for get the list of other
	 *            component whith same kind, same zone
	 * @throws DatagridException 
	 * @throws UnknownHostException 
	 */
	public FailDetector(String ip, String port, String type, String zone,
			ILoaderMaterial refm) throws UnknownHostException, DatagridException {
		refMateriel = refm;
		this.IPAdress = ip;
		this.Port = port;
		this.type = type;
		this.zone = zone;
		this.zhand = new Zone(this.type, this.zone,this.IPAdress,this.Port);
		this.isStarted = false;
		logger.info("\r\n\r\n");
		logger.info("Composant " + this.IPAdress + ":" + this.Port
				+ " de type " + this.type + " et de zone " + this.zone
				+ " est initialisé");
		logger.info("\r\n\r\n");
	}

	@Override
	public void run() {
		try {
			ServerSocket socket;
			while (isStarted) {
				socket = new ServerSocket(Integer.parseInt(this.Port));
				Socket client = socket.accept();
				String Mastermsg = new BufferedReader(new InputStreamReader(
						client.getInputStream())).readLine();
				if (Mastermsg != null) {
					this.isMaster = false;
					logger.info("\r\n\r\n");
					logger.info("Master msg: " + Mastermsg);
					logger.info("\r\n\r\n");
				}
				// }
				client.close();
				socket.close();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		isStarted = true;
		thread = new Thread(this);
		thread.start();
		t = new Timer();
		t.schedule(new Watcher(this), 0, 1 * 10000);
	}
	
	public void stop() {
		isStarted = false;
		thread = null;
		t.cancel();
	}

	@SuppressWarnings("static-access")
	@Override
	public List<DBObject> WhichWatch() {
		try {
			List<DBObject> refbefore = new ArrayList<DBObject>();
			List<DBObject> rep = new ArrayList<DBObject>();
			DBCursor curs = refMateriel.selectDataMaterial("TYPE", this.type,
					"ZONE", this.zone);
			if (curs != null) {
				if (curs.size() > 0) {
					for (DBObject dbObject : curs) {
						// logger.info(dbObject.toString());
						refbefore.add(dbObject);
						String util = dbObject.get("ADDRESS_IP").toString()
								+ ":" + dbObject.get("PORT").toString();
						if ((this.IPAdress + ":" + this.Port).compareTo(util) > 0) {
							rep.add(dbObject);
						}
					}

					if (this.isMaster == true && curs.size() > 0)
						rep.add(refbefore.get(refbefore.size() - 1));
					if (rep != null)
						Collections.reverse(rep);
				}
			}
			return rep;
		} catch (InvalidParameters e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "static-access", "resource" })
	@Override
	public void WatchOverIt(int target_rank) {
		List<DBObject> whichWatch = this.WhichWatch();
		if (whichWatch.size() > 0) {
			Socket socket = new Socket();
			if (target_rank == whichWatch.size()) {
				this.masterDeclaration();
			} else if (target_rank < whichWatch.size()) {
				this.isMaster = false;
				String addrutil = whichWatch.get(target_rank).get("ADDRESS_IP")
						.toString();
				String portutil = whichWatch.get(target_rank).get("PORT")
						.toString();
				try {
					logger.info("\r\n\r\n");
					logger.info("Try to connect to " + addrutil + ":"
							+ portutil);
					// logger.info("\r\n\r\n");
					socket = new Socket(InetAddress.getByName(addrutil),
							Integer.parseInt(portutil));
					socket.setSoTimeout(15000);
					logger.info(addrutil + ":" + portutil + " is alive");
					socket.close();
					socket = null;

				} catch (ConnectException e) {
					logger.info("This Addresse is fail " + addrutil + ":"
							+ portutil);
					WatchOverIt(target_rank + 1);
				} catch (SocketTimeoutException e) {
					logger.info("This Addresse is fail " + addrutil + ":"
							+ portutil);
					WatchOverIt(target_rank + 1);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void masterDeclaration() {
		if (this.isMaster != true) {
			this.isMaster = true;
			logger.info("\r\n\r\n");
			logger.info("Me, I Declare " + this.IPAdress + ":" + this.Port
					+ " I'm the master");
			logger.info("\r\n\r\n");
			List<DBObject> refbefore = null;
			DBCursor curs;
			try {
				zhand.setMasterAdress();
			} catch (DatagridException e1) {
			}
			try {
				curs = refMateriel.selectDataMaterial("TYPE", this.type,
						"ZONE", this.zone);
				if (curs.size() > 0) {
					for (DBObject dbObject : curs) {
						String addrutil = dbObject.get("ADDRESS_IP").toString();
						String portutil = dbObject.get("PORT").toString();
						this.callMaster(addrutil, portutil);
					}
				}
			} catch (InvalidParameters e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("resource")
	public void callMaster(String addrutil, String portutil) {
		try {
			Socket socket = new Socket();
			if ((addrutil + ":" + portutil).compareTo(this.IPAdress + ":"
					+ this.Port) != 0) {
				socket = new Socket(InetAddress.getByName(addrutil),
						Integer.parseInt(portutil));
				socket.getOutputStream()
						.write(("Hey It's " + this.IPAdress + ":" + this.Port + " I'm the master")
								.getBytes());
			}
			socket.close();
		} catch (ConnectException e) {
			logger.info("Impossible déclarer l'éléction en temps que Master à "
					+ addrutil + ":" + portutil);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
