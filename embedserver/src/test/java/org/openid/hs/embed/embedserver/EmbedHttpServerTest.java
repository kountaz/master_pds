package org.openid.hs.embed.embedserver;

import java.io.IOException;

import org.junit.Test;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.embed.exceptions.EmbedServerIsAlreadyStarted;



public class EmbedHttpServerTest 
{
	@Test(expected = EmbedServerIsAlreadyStarted.class)
	public void startTest() throws EmbedServerIsAlreadyStarted, IOException, DatagridException
	{
		EmbedServer server = new EmbedServer();
		server.start();
		server.start();
	}
}
