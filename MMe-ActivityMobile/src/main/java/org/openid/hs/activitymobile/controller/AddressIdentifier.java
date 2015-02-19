package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.openid.hs.core.helper.ResourceHelper;

/**
 * AddressIdentifier class allow to get the port and the ip address of the MME
 * Entity
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class AddressIdentifier implements Serializable {

	private String ip;
	private int port;
	private transient Properties p;
	
	public AddressIdentifier() {
		try {
			
			this.ip = InetAddress.getLocalHost().getHostAddress();
			String filePath = "/config.properties";
			 try {
				p = ResourceHelper.getProperties(filePath);;
				String value = p.getProperty("portMME");
				this.port = Integer.parseInt(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
