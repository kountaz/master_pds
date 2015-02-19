package org.openid.hs.enodeb.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openid.hs.client.util.Util;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * Enables to request the next webservice.
 * @see NodeClient.
 * @version R2
 * @author Fanta
 *
 */
public class NodeClient{
	/**
	 * Constructs url,
	 */
	 String restUrlComposite = "";
	 URL restRgstrSvcURL;
	 String target;
	 public NodeClient(String pTarget) {
		 target = pTarget;
	 }
	/**
	 * Constructs the url for http 
	 * connection.
	 */
	public void requestIt(String ...component) {
		restUrlComposite = Util.pathCreator(component);
		String PORT_DEFAULT = target;
		try {	
			restRgstrSvcURL = new URL("http://"+PORT_DEFAULT+restUrlComposite);
			LoggerHelper.info("[Nodeclient Enodeb] Send a request to MME webservices "+restRgstrSvcURL);
			HttpURLConnection cnxToRestSvc = (HttpURLConnection) restRgstrSvcURL.openConnection();
	        cnxToRestSvc.setReadTimeout(50000); // milliseconds 
	        cnxToRestSvc.setConnectTimeout(15000); // milliseconds
	        cnxToRestSvc.addRequestProperty("Accept","text/plain" );
	        cnxToRestSvc.setRequestMethod("POST");
	        cnxToRestSvc.setDoOutput(true);
	        cnxToRestSvc.setDoInput(true);
	        cnxToRestSvc.connect();
	        LoggerHelper.info("Response code = " + cnxToRestSvc.getResponseCode()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
}

