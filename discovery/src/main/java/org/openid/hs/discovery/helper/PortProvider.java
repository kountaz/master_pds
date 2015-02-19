package org.openid.hs.discovery.helper;

import java.io.IOException;
import java.net.Socket;

import org.openid.hs.core.helper.LoggerHelper;

public class PortProvider {
	 
	 public static boolean available(int port) {

		    try (Socket ignored = new Socket("localhost", port)) {
		    	LoggerHelper.error("The port specified is not available");
		    	ignored.close();
		        return false;
		        
		    } catch (IOException ignored) {
		        return true;
		    }
		}
}
