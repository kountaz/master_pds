package org.openid.hs.rt.support.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.rt.support.RealTimeMessagingSupport;
import org.openid.hs.rt.support.exception.PriorityException;
/**
 * 
 * @author KOUNTA
 * @version R3
 *
 */
public class SupportMessaging implements RealTimeMessagingSupport , Serializable	{

	private static final long serialVersionUID = 4879201981810456411L;
	private long sendingMoment;
	private long consumingMoment;
	private long pendingTime;
	protected long referenceFromProperties;
	private boolean messageReliability;
	private String pFilePath;
	private String pFILEPATH_DEFAULT = "/param.properties";
	private String keyReferenceTime;
	private Iterator<?> it;
	private Properties propertie;
	private DateFormat TimeFormatter;
	
	
	public SupportMessaging()	{
		TimeFormatter			= 	new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	}
	@Override
	public void prepareRTMessaging(String pFilePath , String keyReferenceTime)	{	
		this.pFilePath			=	pFilePath;
		this.keyReferenceTime 	= 	keyReferenceTime;
		LoaderProperties();
		initSendingTime();
	}
	@Override
	public void prepareRTMessaging(String keyReferenceTime)	{	
		this.pFilePath			=	pFILEPATH_DEFAULT;
		this.keyReferenceTime 	= 	keyReferenceTime;
		LoaderProperties();
		initSendingTime();
	}
	public void prepareRTMessaging()	{	
		initSendingTime();
		LoggerHelper.info("++ RTM ++ Message send at : "+this.displayTime(this.getSendingTime()));
		
	}
	
	/**
	 * The priority message range is [1-3]
	 * 1 correspond to lower priority
	 * 
	 */
	@Override
	public void messagePriority(int priority)	{
		
			try {
				if	(priority<1 || priority>3)	
					throw new PriorityException("The message priority range is from 1 to 3");
				setPriority(priority);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private static void setPriority(int priority)	{
		
		if	(Math.round(priority) == 1)	{
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			LoggerHelper.info("++ RTM ++ : Treatment message priority is lower");
		}
		else if (priority==2)	{
			Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
			LoggerHelper.info("++ RTM ++ : Treatment message priority is normal");
		}
		else {
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			LoggerHelper.info("++ RTM ++ : Treatment message priority is higher");
		}
	}
	private void LoaderProperties()	{
		try {
			this.propertie = ResourceHelper.getProperties(pFilePath);
			LoggerHelper.info("++ RTM ++ : Properties "+pFilePath+" already loaded, number line is "+(propertie.size()));
			it = propertie.keySet().iterator();
			
			while (it.hasNext()) {
				String key = (String) it.next();
				String valeur = null;
					if	(key.equals(keyReferenceTime))	{					
						valeur = propertie.getProperty(key);
						referenceFromProperties = Long.parseLong(valeur);
					}
			}
			
		} catch (IOException e) {
			LoggerHelper.error(String.format("++ RTM ++ : File %s was not found", pFilePath), e);
		}
	}
	@Override
	public long getSendingTime() {
		// TODO Auto-generated method stub
		return sendingMoment;
	}
	@Override
	public long getConsumingTime() {
		// TODO Auto-generated method stub
		if	(consumingMoment!=-1)
			return consumingMoment;
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public void validateMessage() {
		// TODO Auto-generated method stub
		consumingMoment = currentTime();
		pendingTime();
	}
	
	@Override
	public boolean isReliableMessage() {
		messageReliability = pendingTime > referenceFromProperties ? false : true;
		return messageReliability;
	}
	
	@Override
	public String displayTime(long time) {
		// TODO Auto-generated method stub
		return TimeFormatter.format(new Date(time));
	}
	
	private void initSendingTime() {
		// TODO Auto-generated method stub
		sendingMoment = currentTime();
	}
	private void pendingTime() {
		pendingTime = consumingMoment - sendingMoment;
	}
	@Override
	public long getPendingTime() {
		return pendingTime;
	}
	
	private long currentTime()	{
		return System.currentTimeMillis();
	}

	
}
