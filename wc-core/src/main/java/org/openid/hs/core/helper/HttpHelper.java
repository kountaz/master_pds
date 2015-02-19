package org.openid.hs.core.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
 * Utilities for http.
 * 
 * @version R2
 * @author Fanta, Steven
 * 
 */
public final class HttpHelper {
	public static final String DEFAULT_LOCALHOST_ADDRESS = "http://localhost";
	private static final int DEFAULT_REQUEST_DELAY = 20;
	private static final int DEFAULT_RESPONSE_DELAY = 10;
	private static Map<String, Server> webServers = new HashMap<String, Server>();
	public static void request(URL pRequest) throws IOException {
		request(pRequest, DEFAULT_REQUEST_DELAY, DEFAULT_RESPONSE_DELAY);
	}
	public static void request(URL pRequest, int pRequestDelay,
			int pResponseDelay) throws IOException {
		System.out.println(pRequest);
		HttpURLConnection httpConnection = (HttpURLConnection) pRequest
				.openConnection();
		httpConnection.setReadTimeout(pRequestDelay * 1000); // milliseconds
		httpConnection.setConnectTimeout(pResponseDelay * 1000); // milliseconds
		httpConnection.addRequestProperty("Accept", "text/plain");
		httpConnection.setRequestMethod("POST");
		httpConnection.setDoOutput(true);

		httpConnection.setDoInput(true);
		httpConnection.connect();
		LoggerHelper.info("Response code = " + httpConnection.getResponseCode());
	}
	public static void publishWS(Object pBean, int pPort) throws IOException {
		publishWS(pBean, DEFAULT_LOCALHOST_ADDRESS+":"+pPort);
	}
	public static void publishWS(Object pBean, String pAddress) throws IOException {
		if (webServers.containsKey(pAddress)) {
			throw new IOException("An object is already published on "+ pAddress);
		}
		
		JAXRSServerFactoryBean srvFcrty = new JAXRSServerFactoryBean();
		srvFcrty.setServiceBean(pBean);
		srvFcrty.setAddress(pAddress);
		Server s = srvFcrty.create();
		
		webServers.put(pAddress, s);
	}
	public static void unpublishWS(int pPort) throws IOException {
		unpublishWS("http://localhost:"+pPort);
	}
	public static void unpublishWS(String pAddress) throws IOException {
		if (!webServers.containsKey(pAddress)) {
			throw new IOException("No object is published on "+ pAddress);
		}
		
		Server s = webServers.get(pAddress);
		s.destroy();
		webServers.remove(pAddress);
	}
	private HttpHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}