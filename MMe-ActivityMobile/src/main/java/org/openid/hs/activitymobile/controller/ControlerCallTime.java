package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openid.hs.EnodebMmeCommon.Instruction;
import org.openid.hs.EnodebMmeCommon.MMECallInstruction;
import org.openid.hs.EnodebMmeCommon.bean.MMECallInstructionBean;
import org.openid.hs.MmeHssCommon.ConsumptionInfoMME;
import org.openid.hs.MmeHssCommon.UeBannedInfo;
import org.openid.hs.MmeHssCommon.bean.ConsumptionInfoMMEBean;
import org.openid.hs.MmeHssCommon.bean.UeBannedInfoBean;
import org.openid.hs.activitycontrol.bean.UeDataCall;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;

/**
 * ControlerCallTime class will count and control the consumption of UE Call and
 * set the new call time consumed into the UeCallData object
 * 
 * @version R2
 * @author Mériem
 */
public class ControlerCallTime implements Serializable {

	private int newCallTimeConsumed;
	private Timestamp startCallTime;
	private int diffSeconds;
	private int currentTimeAllowed;
	private boolean finished;
	private boolean started;

	private UeDataCall ued;
	private transient InstructionSender sender;
	private MMECallInstruction instruction;
	private AddressIdentifier address;

	private int timeMilli = 1000;
	private int alertTime = 10;

	private transient MMEActivityMobile mme_activity;
	private transient SimpleDateFormat formatter = new SimpleDateFormat(
			"MM-dd-yyyy HH:mm:ss");

	/**
	 * Constructor that initialize attributes with UeDataCall
	 */
	public ControlerCallTime(UeDataCall ued, Timestamp startCallTime) {
		this.ued = ued;
		this.startCallTime = startCallTime;
		this.started = false;
		this.finished = false;

	}

	/**
	 * getter and setter
	 */
	public int getNewCallTimeConsumed() {
		return newCallTimeConsumed;
	}

	public void setNewCallTimeConsumed(int newCallTimeConsumed) {
		this.newCallTimeConsumed = newCallTimeConsumed;
	}

	public boolean isFinished() {
		return this.finished;
	}

	public void setFinished(boolean stat) {
		this.finished = stat;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public Timestamp getStartCallTime() {
		return this.startCallTime;
	}

	public void setStartCallTime(Timestamp startCallTime) {
		this.startCallTime = startCallTime;
	}

	public InstructionSender getSender() {
		return sender;
	}

	public void setSender(InstructionSender sender) {
		this.sender = sender;
	}

	/**
	 * Start method that launches counter
	 */
	public void start() {
		setFinished(false);
		setStarted(true);
		refreshCurrentConsumption();
		timeMilli = 1000;
		lanchCounter(currentTimeAllowed);
	}

	/**
	 * stop method that stop counting consumption
	 */
	public void stop() {

		setFinished(true);
	}

	/**
	 * method to return difference time in seconds between two times
	 * 
	 * @return integer
	 */
	public int getTimeDiff(Date startTime, Date currentTime) {
		if (startTime.compareTo(currentTime) < 0) {
			java.util.Date tmp = startTime;
			startTime = currentTime;
			currentTime = tmp;
		}
		long a = (startTime.getTime() / 1000) - (currentTime.getTime() / 1000);
		diffSeconds = Integer.parseInt(Long.toString(a));
		return diffSeconds;
	}

	/**
	 * method to return Date Object with String
	 * 
	 * @return Date
	 */
	public Date stringToDate(String sDate) throws ParseException {
		return formatter.parse(sDate);
	}

	/**
	 * method to return current date
	 * 
	 * @return Date
	 */
	public Date getCurrentTime() throws ParseException {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return date;
	}

	/**
	 * method to return current time consumed Call by the UE
	 * 
	 * @return Date
	 */
	public int getCurrentTimeConsumed() {
		Date dateStartCallTime = new Date(this.startCallTime.getTime());
		Date currentTime = null;
		int currentTimeConsumed = 0;
		try {
			currentTime = this.getCurrentTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentTimeConsumed = this.getTimeDiff(dateStartCallTime, currentTime);
		return currentTimeConsumed;
	}

	/**
	 * method to refresh NewCallTimeConsumed with the sum of the current Time
	 * Consumed and the Old time consumed in seconds and change the time allowed
	 * to be consumed by the current one
	 */
	public void refreshCurrentConsumption() {
		int newTimeConsumed = this.ued.getCallTimeConsumed();
		this.getCurrentTimeConsumed();
		this.setNewCallTimeConsumed(newTimeConsumed);
		this.currentTimeAllowed = this.ued.getCallTimeAllowed()
				- this.getNewCallTimeConsumed();
	}

	/**
	 * method to send Alert instruction with creating an instruction and calling
	 * the Instruction Sender
	 */
	public void sendAlertInstruction() {
		LoggerHelper
				.info("MME ENTITY : ControlerCallTimer >> Instruction alert will be sent to eNodeB on the UE number : "
						+ ued.getIdUE());
		/**
		 * Instantiate the AddressIdentifier to get the port and the address of
		 * the MME Entity
		 */
		address = new AddressIdentifier();
		/**
		 * create an alert instruction with the Instruction interface
		 */
		instruction = new MMECallInstructionBean(address.getIp(),
				address.getPort(), Instruction.ALERT, ued.getIdUE());
		/**
		 * send the instruction with the InstructionSender Object
		 */
		sender = new InstructionSender();
		sender.toSendInstruction(instruction, ued.getIp(), ued.getPort());

	}

	/**
	 * method to send Stop instruction with creating an instruction and calling
	 * the Instruction Sender
	 */
	public void sendStopInstruction() {
		LoggerHelper
				.info("MME ENTITY : ControlerCallTimer >> Stop Instruction will be sent to eNodeB on the UE number : "
						+ ued.getIdUE());
		/**
		 * Instantiate the AddressIdentifier to get the port and the address of
		 * the MME Entity
		 */
		address = new AddressIdentifier();
		/**
		 * create an stop instruction with the Instruction interface
		 */
		instruction = new MMECallInstructionBean(address.getIp(),
				address.getPort(), Instruction.STOP, ued.getIdUE());
		/**
		 * send the instruction with the InstructionSender Object
		 */
		sender = new InstructionSender();
		sender.toSendInstruction(instruction, ued.getIp(), ued.getPort());

	}

	/**
	 * method to send consumption info to the HSS
	 */
	public void sendConsumptionInfo() {

		String filePath = "/config.properties";
		Properties p;
		
		try {
			p = ResourceHelper.getProperties(filePath);
			String ipHSS = "ipHSS";
			String ip = p.getProperty(ipHSS);
			int port = Integer.parseInt(p.getProperty("portHSS"));
			sender = new InstructionSender();
			ConsumptionInfoMME ccm = new ConsumptionInfoMMEBean(this.ued.getIdUE(),this.ued.getCallTimeConsumed()) ;
			sender.toSendConsumptionInfo(ccm, ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * method to send Banned Ue info to the HSS
	 */
	public void sendUeBannedInfo() {
		String filePath = "/config.properties";
		Properties p;
		try {
			p = ResourceHelper.getProperties(filePath);
			String ipHSS = "ipHSS";
			String ip = p.getProperty(ipHSS);
			int port = Integer.parseInt(p.getProperty("portHSS"));
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			UeBannedInfo cim = new UeBannedInfoBean(this.ued.getIdUE(),
					timeStamp);
			sender = new InstructionSender();
			sender.toSendBannedInfo(cim, ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method to remove the UeDataCall from the datagrid
	 * 
	 */
	public void destroy(String id) {
		//mme_activity.Remove(id);
	}
	/**
	 * method to start counting the call consumption and according to the
	 * current consumption send instructions or update the time consumed of the
	 * UE
	 */
	public void lanchCounter(int currentTimeAllowed) {
		
		LoggerHelper
				.info("MME ENTITY : ueDataCall : Controller >>  the counter consumption is lanched for the Ue number: "
						+ ued.getIdUE());
		LoggerHelper.info("remaining time to consume :  " + currentTimeAllowed);
		int j = 0;
		int i = currentTimeAllowed;
		for (i = currentTimeAllowed; i >= 0; i--) {

			j = j + 1;
			try {
				Thread.sleep(timeMilli);
			} catch (InterruptedException e) {
				// System.out.print("erreur");
			}
			
			/**
			 * if the counter reaches the alert time value send alert
			 * instruction to Enodeb
			 */
			if (i == alertTime) {
				LoggerHelper
						.info("MME ENTITY : ControlerCallTimer >> the Call of UE number : "
								+ ued.getIdUE()
								+ " will be cut of in "
								+ alertTime + " seconds");
				sendAlertInstruction();
			}
			/**
			 * If the call is finished before that reaches the current time
			 * allowed the consumption counter will be stopped and the time
			 * consumed will be updated else if the call is not finished and the
			 * counter has completed an instruction will be created and sent to
			 * Enodeb
			 */
			if (finished) {
				timeMilli = 0;
				i = 0;
				this.ued.setCallTimeConsumed(j + newCallTimeConsumed);

				LoggerHelper
						.info(" MME ENTITY : ControlerCallTimer >> time consumed : "
								+ j
								+ " and time already consumed : "
								+ newCallTimeConsumed
								+ " and the total of the time consumed : "
								+ this.ued.getCallTimeConsumed());
				
				sendConsumptionInfo();
			} else {
				if (i == 0) {
					LoggerHelper
							.info("MME ENTITY : ControlerCallTimer >> The Call of UE number "
									+ ued.getIdUE()
									+ " reaches the remaining time allowed : "
									+ currentTimeAllowed + " seconds");
					setStarted(false);
					destroy(this.ued.getIdUE());
				Thread t1 = new Thread(new Runnable() {
						public void run() {
							sendStopInstruction();
						}
					});
					t1.start();
					Thread t2 = new Thread(new Runnable() {
						public void run() {
							sendUeBannedInfo();
						}
					});
					t2.start();

				}
			}
			LoggerHelper.info(Integer.toString(i)+" seconds");
		}
	}
}
