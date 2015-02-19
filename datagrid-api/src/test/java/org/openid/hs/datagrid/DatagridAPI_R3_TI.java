package org.openid.hs.datagrid;

import org.openid.hs.datagrid.DataBuilder;
import org.openid.hs.datagrid.DataState;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.bean.DataBuilderBean;
import org.openid.hs.datagrid.bean.DatagridBean;
import org.openid.hs.datagrid.controller.DataEngine;
import org.openid.hs.embeddedprocess.EmbeddedProcessException;
import org.openid.hs.embeddedprocess.EmbeddedProcessHelper;
import org.openid.hs.embeddedprocess.MemcachedEmbeddedProcess;
import org.openid.hs.embeddedprocess.MongoEmbeddedProcess;

public class DatagridAPI_R3_TI {
	public static void main(String []args) {
		int pMongodbPort = 1501;
		String pDatabase = "hs_datagrids";
		int pMemcachedPort = 1502;
		try {
			/*
			EmbeddedProcessHelper.silentLogging();
			
			EmbeddedProcessHelper.startServer(MongoEmbeddedProcess.TYPE, pMongodbPort);
			EmbeddedProcessHelper.startServer(MemcachedEmbeddedProcess.TYPE, pMemcachedPort);
			
			DataEngine engine = new DataEngine(pMongodbPort, pDatabase, pMemcachedPort);
			
			Datagrid grid = new DatagridBean("test", engine);
			
			String testKey = "k1";
			Integer testValue = 1;
			int testExpiration = 2;
			
			// set(String pKey, Object pValue, int expiration)
			DataBuilder builder = new DataBuilderBean(testKey, grid);
			builder.setExpiration(testExpiration);
			
			builder.persist();
			
			System.out.println(builder.state());
			System.out.println("xxx "+ builder.get());
			
			builder.set(testValue);
			
			System.out.println(builder.state());
			System.out.println("xxx "+ builder.get());
			
			builder.persist();
			
			Thread.sleep(6000);
			
			System.out.println(builder.state());
			System.out.println("xxx "+ builder.get());
			
			builder.persist();
			builder.restore();
			
			System.out.println(builder.state());
			System.out.println("xxx "+ builder.get());
			
			builder.setKey("k2");
			System.out.println(builder.state());
			builder.set(testValue);
			System.out.println(builder.state());
			builder.set(2);
			System.out.println(builder.state());

			builder.persist();

			System.out.println(builder.state());
			
			builder.setKey("k3");
			builder.set(3);
			
			System.out.println("xxx "+ builder.get());
			builder.restore();
			System.out.println("xxx "+ builder.get());
			
			Thread.sleep(8000);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				EmbeddedProcessHelper.stopAllServers();
			} catch (EmbeddedProcessException e) {
				e.printStackTrace();
			}
			System.out.println("-- END");
			System.exit(0);
		}
	}
}
