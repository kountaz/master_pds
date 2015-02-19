package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Properties;

import org.openid.hs.EnodebMmeCommon.CommunicationCallActivity;
import org.openid.hs.EnodebMmeCommon.Status;
import org.openid.hs.MmeHssCommon.RequestInfoMME;
import org.openid.hs.activitycontrol.bean.UeDataCall;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * ListenerMME class that will read CommunicationCallActivity object, checks if
 * the UE id and the activity id exist in the cache if they exist in the cache
 * create the UeDataCall object and call the consumption counter else send
 * demand to HSS entity to get informations about the Ue Activity to create the
 * UeDataCall to controls over
 * 
 * @version R2
 * @author Mériem
 * 
 */

public class ListenerMME implements Runnable {

	private ObjectInputStream ois;
	private Socket clientSocket;
	private Socket socket;
	private String ipHSS;
	private int port;
	private Properties propertie;
	private MMEActivityMobile mmeActivity;
	private ObjectOutputStream oos;
	private UeDataCall cca = null;
	private DatagridAPI datagridApi;
	private Datagrid grid;
	private int startCall = 0;
	private int endCall = 0;
	private ControlerCallTime cct;

	/**
	 * Constructor thats initialize EnodebActivityMobile object and Socket
	 * object
	 */
	public ListenerMME(MMEActivityMobile mmeActivity, Socket clientSocket) {
		this.mmeActivity = mmeActivity;
		this.clientSocket = clientSocket;
	}

	/**
	 * Method that search the UeDataCall entity in the datagrid with the idUE
	 * return the UeDataCall object
	 * 
	 * @return UeDataCall
	 */
	public UeDataCall toFind(String id) {
		DataLoader data;
		UeDataCall ccafound = null;
		data = DataLoader.getInstance("UeDataCall");
		grid = data.getData("UeDataCall");

		for (String key : grid.getKeys()) {
			try {
				ccafound = (UeDataCall) grid.get(id);
			} catch (DatagridException e) {

			}
		}
		return ccafound;
	}

	/**
	 * Method that search the UeDataCall entity in the datagrid with the idUE
	 * return true if the UeDataCall found else return false
	 * 
	 * @return boolean
	 */
	public boolean isExistingCall(String id) {
		DataLoader data;
		data = DataLoader.getInstance("UeDataCall");
		grid = data.getData("UeDataCall");

		for (String key : grid.getKeys()) {
			try {
				this.cca = (UeDataCall) grid.get(id);
			} catch (DatagridException e) {

			}
		}
		if (cca != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that return UeDataCall
	 * 
	 * @return UeDataCall
	 */
	public UeDataCall getUeDataCall() {
		return this.cca;
	}

	/**
	 * Method that send a request to HSS Entity to get consumption information
	 * call about a UE
	 * 
	 * @parameter RequestInfoMME
	 */
	public void toRequestInfo(RequestInfoMME request) {
		String filePath = "/config.properties";
		Properties p;
		try {
			p = ResourceHelper.getProperties(filePath);
			String keyip = "ipHSS";
			ipHSS = p.getProperty(keyip);
			String keyport = "portHSS";
			port = Integer.parseInt(p.getProperty(keyport));

			try {
				socket = new Socket(ipHSS, port);

				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(request);
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * The run method that reads CommunicationCallActivity object from eNodeb
	 * entity, create a UeDataCall and start controlling the Call consumption
	 * else send information about UE to HSS to get enough information of
	 * consumption to start control it.
	 */
	@Override
	public void run() {

		try {

			ois = new ObjectInputStream(clientSocket.getInputStream());
			Object objet = ois.readObject();
			/**
			 * In the case of CommunicationCallActivity
			 */
			if (objet instanceof CommunicationCallActivity) {

				CommunicationCallActivity to = (CommunicationCallActivity) objet;
				String id = to.getIdUE();
				int statusCall = to.getEvenement().getIdEvent();
				switch (statusCall) {
				case Status.START:
					LoggerHelper
							.info("MME ENTITY : Listener >> Information received : Call Start of UE number: "
									+ id);
					/**
					 * Verify if the Ue call activity exist in the cache of the
					 * MME Else a request info will be sent to the HSS entity
					 */
					if (isExistingCall(id)) {
						LoggerHelper
								.info("MME ENTITY : Listener >> UE number: "
										+ id
										+ " already exists in the MME cache");
						/**
						 * get a counterCallTime of the UeDataCall and set the
						 * new value of the start call time
						 */
						this.cct = getUeDataCall().getControler();
						cct.setNewCallTimeConsumed(60);
						//cct.setStartCallTime(to.getEvenement().getTimeEvent());
						cct.start();
					} else {
						LoggerHelper
								.info("MME ENTITY : Listener >> UE number: "
										+ id
										+ " does not exist in the MME cache");
					}
					break;
				case Status.END:
					LoggerHelper
							.info("MME ENTITY : Listener >> Information received : Call End of UE number: "
									+ id);
					UeDataCall ueToStopControl = toFind(id);
					if (ueToStopControl != null) {
						this.cct = ueToStopControl.getControler();
						if (cct.isStarted()) {
							LoggerHelper
							.info("MME ENTITY : Listener >> call UE number: "
									+ id+" is started the controler will be stopped");
							this.cct.stop();
							cct.setStarted(false);
						}else{
							LoggerHelper
							.info("MME ENTITY : Listener >> no call started for UE number: "
									+ id);
						}
					}
					break;
				default:
					LoggerHelper
							.error("MME ENTITY : Listener >> status unknown");
					// throw new Exception();
				}

			}
			ois.close();
			clientSocket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
