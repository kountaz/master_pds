package org.openid.hs.scalability;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.openid.hs.bootstrap.WCBootstrap;
import org.openid.hs.core.Service;
import org.openid.hs.core.exception.BuildingException;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.HttpHelper;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.wc.WCType;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.builder.WCBuilder;

/**
 *ScalabilityAPI Class is a singleton that after each 10 secondes :
 *gets the request number of each services started on each worker component 
 *and update the action table according to the result of the analyze
 *execute all actions mentionned in the action's map like starting or stopping
 *one or many worker components
 * @version R3
 * @author Mériem
 */

@Path("/test/scalability")

public class ScalabilityAPI implements Runnable {
/**
 *The port of the webService publisher
 */
	public static int WS_PORT = 5678;
/**
 *The singleton instance
 */
	private static ScalabilityAPI singleton;
/**
 *The Thread that start a Class
 */
	private static Thread scalabilityTask;
/**
 * The get method that return the ScalabilityAPI instance
 */
	public static ScalabilityAPI get() {
		if (singleton == null) {
			singleton = new ScalabilityAPI();
			scalabilityTask = new Thread(singleton);
			scalabilityTask.start();
			try {
				HttpHelper.publishWS(singleton, String.format("http://%s:%d", ResourceHelper.getString("/host.conf", "ip"),WS_PORT));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return singleton;
	}
/**
 * The nodes's Map
 */
	private Map<String, WorkerComponent> nodes;
/**
 * The action's Map
 */
	private Map<String, Integer> actions;
/**
 * The private constructor of the ScalabilityAPI class
 */
	private ScalabilityAPI() {
		nodes = new LinkedHashMap<String, WorkerComponent>();
		actions = new HashMap<String, Integer>();
	}
/**
 * The getActions method that returns action's Map
 */
	public Map<String, Integer> getActions() {
		return actions;
	}
	
	@GET
	@Path("/actions")
/**
 * The showActions method that return actions in string format   
 */
	public String showActions() {
		return getActions().toString();
	}
/**
 * The setAction method that set one new action according
 * to the information inserted into the Path
 * in the actions Map and display the map in the end
 *  in the string format 
 */
 	@GET
	@Path("/actions/{type}/{n}")
	public String setAction(@PathParam("type") String type, @PathParam("n") int n) {
		actions.put(type.toLowerCase(), n);
		return actions.toString();
	}
	
/**
 * The setAction method that set many actions in the
 * actions Map inserted into the path and display 
 * the map in the end in the string format 
 * @param @PathParam("enodeb")int pEnodeb,@PathParam("mme")int pMme,
 * @PathParam("hss")int pHss,@PathParam("exchange")int pExchange,
 * @PathParam("dslam")int pDslam,@PathParam("bras")int pBras)
 */
	@GET
	@Path("/actions/{enodeb}/{mme}/{hss}/{exchange}/{dslam}/{bras}")
	
	public String setActions(
	@PathParam("enodeb")int pEnodeb,
	@PathParam("mme")int pMme,
	@PathParam("hss")int pHss,
	@PathParam("exchange")int pExchange,
	@PathParam("dslam")int pDslam,
	@PathParam("bras")int pBras) {
		setAction(WCType.ENB, pEnodeb);
		setAction(WCType.MME, pMme);
		setAction(WCType.HSS, pHss);
		setAction(WCType.EXCHANGE, pExchange);
		setAction(WCType.DSLAM, pDslam);
		setAction(WCType.BRAS, pBras);
		return actions.toString();
	}
/**
 * The addNode method that inserts new nodes in the nodes Map
 * @param String pName, Map<String, Object> pParams
 */
	public void addNode(String pName, Map<String, Object> pParams) {
		try {
			WCBuilder builder = WCBuilder.getBuilder(pName);
			builder.setParameters(pParams);
			WorkerComponent wc = builder.build();
			new WCBootstrap(wc).listen();
			nodes.put(pName, wc);
		} catch (BuildingException e) {
			e.printStackTrace();
		}
	}
/**
 * The removeNode method that removes nodes from the nodes Map
 * @param String pName 
 */
	public void removeNode(String pName) {
		if (nodes.containsKey(pName)) {
			try {
				nodes.get(pName).stop();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			nodes.remove(pName);
		}
	}
/**
 * The startNode method that start nodes 
 * existing in the nodes Map by mentioning
 * its name
 * @param String pName 
 */
	public void startNode(String pName) {
		if (nodes.containsKey(pName) && !nodes.get(pName).isStarted()) {
			try {
				nodes.get(pName).start();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
/**
 * The stopNode method that stop nodes 
 * existing in the nodes Map by mentioning
 * its name
 * @param String pName 
 */
	public void stopNode(String pName) {
		if (nodes.containsKey(pName) && nodes.get(pName).isStarted()) {
			try {
				nodes.get(pName).stop();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
/**
 * The startAllNodes method that starts all
 * nodes in the nodes Map 
 */
	public void startAllNodes() {
		for (String name : nodes.keySet()) {
			startNode(name);
		}
	}
/**
 * The stopAllNodes method that stops all
 * nodes in the nodes Map 
 */
	public void stopAllNodes() {
		for (String name : nodes.keySet()) {
			stopNode(name);
		}
	}
/**
 * The getNodes method that returns 
 * nodes Collection value
 */
	public Collection<WorkerComponent> getNodes() {
		return nodes.values();
	}
/**
 * The run method that calls analyze method
 * And execute method after each 10 seconds
 */
	@Override
	public void run() {
		while (true) {
			analyze();
			execute();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ignored) { }
		}
	}
/**
 * The analyze method that updates action Map
 * according to the loads retuned by services 
 * that implement LoadManager interface
 */
	private void analyze() {
		for (WorkerComponent wc : nodes.values()) {
			for (String serviceName : wc.getProfile().getServiceSet()) {
				try {
					Service s = wc.getProfile().getService(serviceName);
					if (s instanceof LoadManager) {
						// TODO
						// ((LoadManager) s).getLoad();
					}
				} catch (ProfileException ignored) { }
			}
		}
	}
/**
 * The execute method that applies actions defined 
 * by analyze method
 */
	private void execute() { 

		for (Entry<String, Integer> entry : actions.entrySet()) { /* loop on actions */
			if (entry.getValue() != 0 && WCType.exists(entry.getKey())) { /* whether to start or stop multiple nodes recognized */
				if (entry.getValue() < 0) { /* whether to stop node */
					for (; entry.getValue() < 0; entry.setValue(entry.getValue()+1)) { /* loop on number of nodes to stop */
						for (WorkerComponent wc : nodes.values()) { /* loop on nodes Map */
							if (wc.getType().equals(entry.getKey()) && wc.isStarted()) { /* if the node type existing in the Map correspond to the type of the node to stop and the node in the map is started  */
								try {
									LoggerHelper.info("1 stop by scalability");
									wc.stop();											/* Then stop it */
								} catch (ServiceException e) {
									e.printStackTrace();
								}
								break;
							}
						}
					}
				} else if (entry.getValue() > 0) {  /* whether to start node */
					for (; entry.getValue() > 0; entry.setValue(entry.getValue()-1)) { /* loop on number of nodes to start */
						for (WorkerComponent wc : nodes.values()) { /* loop on nodes Map */
							if (wc.getType().equals(entry.getKey()) && !wc.isStarted()) {  /* if the node type existing in the Map correspond to the type of the node to start and the node in the map is stopped  */
								try {
									LoggerHelper.info("1 startup scalability");
									wc.start();                                            /* Then start it */
								} catch (ServiceException e) {
									e.printStackTrace();
								}
								break;
							}
						}
					}
				}
			}
		}
	}
}
