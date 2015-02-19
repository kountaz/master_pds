package org.openid.hs.costime.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlJavaTypeAdapter(CustomAdapter.class)
public interface ClientCallbackService extends Remote 
{
	void notifyClient(Date currentDate) throws RemoteException;
	Date getTimeReference() throws RemoteException;
}
