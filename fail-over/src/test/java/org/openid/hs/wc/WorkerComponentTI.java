package org.openid.hs.wc;

import org.openid.hs.core.Profile;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.wc.bean.DatagridServiceBean;
import org.openid.hs.wc.bean.DiscoveryServiceBean;
import org.openid.hs.wc.bean.FailDetectorServiceBean;
import org.openid.hs.wc.bean.KernelServiceBean;
import org.openid.hs.wc.controller.WCFactory;
import org.openid.hs.wc.controller.WCProfileFactory;

public class WorkerComponentTI {
	public static void main(String []args) {
		//*/
		String host = "localhost";
		int port = 1500;
		try {
			KernelService kernel = new KernelServiceBean();
			DatagridService datagrid = new DatagridServiceBean();
			DiscoveryService discovery = new DiscoveryServiceBean();
			FailDetectorService faildetector = new FailDetectorServiceBean(kernel);
			Profile p = WCProfileFactory.createProfile(kernel, datagrid, 
					discovery, faildetector);
			
			WorkerComponent wc = WCFactory.createWC("lte.mme.paris.006", p);
			wc.setParameter("kernel.host", host);
			wc.setParameter("kernel.bootstrapPort", port);
			wc.setParameter("faildetector.port", 7001);
			wc.setParameter("datagrid.databasePort", 1532);
			wc.setParameter("datagrid.memcachePort", 1533);
			//wc.setParameter("discovery.referential", "erfgezfez");
			
			wc.start();
			SystemHelper.pause();
			wc.stop();
			
		} catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
		
		System.exit(0);
	}
}
