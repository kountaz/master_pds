package org.openid.hs.costime.notification;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.openid.hs.costime.client.ClientCallbackService;

public class ClientProxy implements InvocationHandler 
{

	private ClientCallbackService client;
	
	public ClientProxy(ClientCallbackService client)
	{
		this.client = client;
	}
	
	
	public Object invoke(Object object, Method method, Object[] args)
			throws Throwable 
	{
		return method.invoke(client, args[0]);
	}
}
