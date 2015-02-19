package org.openid.hs.core.helper;

import java.io.*;
import java.util.Properties;


public class PortLoaderHelper {
	
	
	
	/*
	 * Returns the port receiver for
	 * framework and  the port 
	 * sender for APIsimulation
	 * */
	public static int pReceiverSenderFromProperties(){
		Properties prop = new Properties();
		InputStream input = null;
		input=PortLoaderHelper.class.getResourceAsStream(System.getProperty("user.home")+"\toto.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			LoggerHelper.error("Fail to load the file in pReceiverSenderFromProperties");
		}
		return Integer.parseInt(prop.getProperty("portreceiver"));
	}
	
	/*
	* Returns the port sender for
	 * framework and  the port 
	 * receiver for APIsimulation
	 * */
	public static int pSenderReceiverFromProperties(){
		
		Properties prop = new Properties();
		InputStream input = null;
		input=PortLoaderHelper.class.getResourceAsStream(System.getProperty("user.home")+"\toto.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			LoggerHelper.error("Fail to load the file in pReceiverSenderFromProperties");
		}
		return Integer.parseInt(prop.getProperty("portsender"));
	}
	
	
	
	public static int loadPortToProperties(){

		return 0;
	}
	
	/*
	 * 
	 * To modify the file wich contains
	 * the ports receiver and sender
	 * informations.
	 * */
	public static void modifyProperties (String nReceiver, String nSender){
		
		try{
			FileInputStream in = new FileInputStream(System.getProperty("user.home")+"\toto.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
	
			FileOutputStream out = new FileOutputStream(System.getProperty("user.home")+"\toto.properties");
			props.setProperty(nReceiver, "america");
			props.setProperty(nSender, "america");
			props.store(out, null);
			out.close();
	}catch(Exception e){
		LoggerHelper.info("Fail to the modify of the toto properties");
	}
	}
}
