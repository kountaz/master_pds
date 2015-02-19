package org.openid.hs.mme.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openid.hs.client.util.Util;
import org.openid.hs.core.helper.LoggerHelper;

/**
 * MME NodeClient.
 * @see NodeClient.
 * @version R2
 * @author Fanta
 *
 */
public class NodeClient{
	/**
	 * Default toString() pattern.
	 */
	 String restUrlComposite = "";
	 URL restRgstrSvcURL; 
	 String target;
	 public NodeClient(String pTarget) {
		 target = pTarget;
	 }
	public void requestIt(String ...component) {
		restUrlComposite = Util.pathCreator(component);
		LoggerHelper.info(restUrlComposite);
		try {
			restRgstrSvcURL = new URL("http://"+target+restUrlComposite);
			LoggerHelper.info("[Nodeclient MME] Send a request to HSS webservices "+ restRgstrSvcURL);
			HttpURLConnection cnxToRestSvc = (HttpURLConnection) restRgstrSvcURL.openConnection();
	        cnxToRestSvc.setReadTimeout(30000 /* milliseconds */);
	        cnxToRestSvc.setConnectTimeout(15000 /* milliseconds */);
	        cnxToRestSvc.addRequestProperty("Accept","text/plain" );
	        cnxToRestSvc.setRequestMethod("POST");
	        cnxToRestSvc.setDoOutput(true);
	        cnxToRestSvc.setDoInput(true);
	        cnxToRestSvc.connect();
	        LoggerHelper.info("Response code = " + cnxToRestSvc.getResponseCode()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	
	}

}
