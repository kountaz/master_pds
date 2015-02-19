package org.openid.hs.activitydata.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.openid.hs.MmeHssCommon.ConsumptionInfoMME;
import org.openid.hs.MmeHssCommon.UeBannedInfo;
import org.openid.hs.activitydata.bean.ConsumptionCallInfo;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * ListenerHSS class that will read : ConsumptionInfoMME to update the current
 * UE call consumption RequestInfoMME to send to the MME informations about the
 * call consumption of UE UeBannedInfo object to add it in the cache
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class ListenerHSS implements Runnable {
	private ObjectInputStream ois;
	private Socket clientSocket;
	private Socket socket;
	private HSSActivityMobile hssActivityMobile;
	private Datagrid grid;
	private DatagridAPI datagridApi;

	public ListenerHSS(HSSActivityMobile hssActivityMobile, Socket clientSocket) {

		this.clientSocket = clientSocket;
		this.hssActivityMobile = hssActivityMobile;
	}

	/**
	 * Method that search the ConsumptionCallInfo entity in the datagrid with
	 * the idUE return the ConsumptionCallInfo object
	 * 
	 * @return ConsumptionCallInfo
	 */
	public void UpdateCallInfo(String id, int consumption) {
		DataLoader data;
		ConsumptionCallInfo ccifound = null;
		data = DataLoader.getInstance(datagridApi, "ConsumptionCall");
		grid = data.getData("ConsumptionCall");

		for (String key : grid.getKeys()) {
			try {
				ccifound = (ConsumptionCallInfo) grid.get(id);

			} catch (DatagridException e) {

			}
		}
		ccifound.setCallTimeConsumed(consumption);

		LoggerHelper
				.info("HSS ENTITY : Listener >> the Call consumption of the Ue Number: "
						+ ccifound.getIdUE() + " is changed with success");
	}

	/**
	 * Method that put a bannedUeCallInfo into the BannedUeCallData
	 */
	public void putInBannedUeCall(UeBannedInfo bannedCall) {
		BannedUeCallData data;
		ConsumptionCallInfo ccifound = null;
		data = BannedUeCallData.getInstance(datagridApi, "BannedUeCalling");
		grid = data.getData("BannedUeCalling");
		grid.set(bannedCall.getIdUE(), bannedCall);
		LoggerHelper.info("HSS ENTITY : Listener >> the UE number "
				+ bannedCall.getIdUE()
				+ " is registered in the BannedUeCalling data"
				+ "the Ue can't make calls.");
	}

	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(clientSocket.getInputStream());
			Object objet = ois.readObject();
			/**
			 * In the case of CommunicationCallActivity
			 */
			if (objet instanceof UeBannedInfo) {

				UeBannedInfo to = (UeBannedInfo) objet;
				LoggerHelper
						.info("HSS ENTITY : Listener >> Information received : request call barring of the UE number "
								+ to.getIdUE());
				putInBannedUeCall(to);
			}else if(objet instanceof ConsumptionInfoMME){
				ConsumptionInfoMME to = (ConsumptionInfoMME) objet;
				LoggerHelper
				.info("HSS ENTITY : Listener >> Information received : consumption info about the Ue number : "
						+ to.getIdUE());
				UpdateCallInfo(to.getIdUE(), to.getCallTimeConsumed());
			}
			ois.close();
			clientSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
