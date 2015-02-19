package org.openid.hs.core.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Utilities for system.
 * @version R3
 * @author Fanta
 *
 */
public final class PortHelper {
	
	public static boolean portDisponibility(int port) {
		ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }

	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	                /* should not be thrown */
	            }
	        }
	    }

	    return false;
		
	}
	
	
	public static int portSender(int portReceiver){
		String portString = null;
		String suffix = null;
		String recup = null;
		
		portString = Integer.toString(portReceiver);
		suffix = portString.substring(0,1);
	
		if("6".equals(suffix)){
			 recup = "7"+portString.substring(1, portString.length());
			 return Integer.parseInt(recup);

		}
		else{
			recup = "6"+portString.substring(1, portString.length());
			 return Integer.parseInt(recup);
		} 
			
		
		
		
	}
	
}