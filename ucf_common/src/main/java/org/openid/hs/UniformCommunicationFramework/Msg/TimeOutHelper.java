package org.openid.hs.UniformCommunicationFramework.Msg;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;

public class TimeOutHelper {
	
	private int timeOut;
	private String pFILEPATH_DEFAULT = "/param.properties";
	private Iterator<?> it;
	private Properties propertie;

	public int timeOut(MsgKind msg)	{
		
		LoaderProperties(msg);
		return timeOut;
	}
	
	private void LoaderProperties(MsgKind msg)	{
		try {
			this.propertie = ResourceHelper.getProperties(pFILEPATH_DEFAULT);
			it = propertie.keySet().iterator();
			
			while (it.hasNext()) {
				String key = (String) it.next();
				String valeur = null;
				
				if	(msg.Date.toString().equals(key))	{
					valeur = propertie.getProperty(key);
					timeOut = Integer.parseInt(valeur);
				}
				else if	(msg.Other.toString().equals(key))	{
					valeur = propertie.getProperty(key);
					timeOut = Integer.parseInt(valeur);
				}
			}
			
		} catch (IOException e) {
			LoggerHelper.error(String.format("File %s was not found", pFILEPATH_DEFAULT), e);
		}
	}
}
