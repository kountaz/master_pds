package org.openid.hs.embed.integration;

import org.openid.hs.core.bean.ProfileBean;
import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.wc.bean.DiscoveryServiceBean;
import org.openid.hs.wc.bean.KernelServiceBean;
import org.openid.hs.wc.bean.WorkerComponentBean;

public class TIWorkerComponentBean
{
	
	public static boolean runIntegrationTest() throws ProfileException, ServiceException, DatagridException
	{
		DatagridAPI.nolog();
		DatagridAPI.init();
		ProfileBean profile = new ProfileBean();
		KernelServiceBean kernel = new KernelServiceBean();
		kernel.start();
		DiscoveryServiceBean discovery = new DiscoveryServiceBean();
		discovery.start();
		profile.addService(kernel);
		profile.addService(discovery);
		WorkerComponentBean worker = new WorkerComponentBean("LTE.HSS.Paris.001", profile);
		DatagridAPI.destroy();
		
		
		return worker.isStarted();
	}
	
	public static void main(String args[])
	{
		try 
		{
			if(runIntegrationTest())
			{
				LoggerHelper.info("It s a success statement.");
			}
			else
			{
				LoggerHelper.info("It s a failed statement.");
			}
		} 
		catch (ProfileException ex) 
		{
			LoggerHelper.error("An error is occured while the life of process", ex);
		} 
		catch (ServiceException ex) 
		{
			LoggerHelper.error("An error is occured while the life of process", ex);
		} 
		catch (DatagridException ex) 
		{
			LoggerHelper.error("An error is occured while the life of process", ex);
		}
	}
}
