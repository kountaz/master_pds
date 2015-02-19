package org.openid.hs.wc;

import org.openid.hs.core.Profile;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.wc.bean.CommunicationServiceBean;
import org.openid.hs.wc.bean.DatagridServiceBean;
import org.openid.hs.wc.bean.DiscoveryServiceBean;
import org.openid.hs.wc.bean.RulesDeploymentServiceBean;
import org.openid.hs.wc.controller.WCFactory;
import org.openid.hs.wc.controller.WCProfileFactory;

public class WorkerComponentTI {
	public static void main(String []args) {
		//*/
		String host = "localhost";
		int port = 1500;
		try {
			
			DatagridService datagrid = new DatagridServiceBean();
			DiscoveryService discovery = new DiscoveryServiceBean();
			Profile p = WCProfileFactory.createProfile(datagrid, discovery);
			RulesDeploymentService rulesd = new RulesDeploymentServiceBean((KernelService) p.getService(KernelService.SERVICE_NAME));
			CommunicationService communication = new CommunicationServiceBean();
			p.addServiceAfter(communication, KernelService.SERVICE_NAME);
			p.addService(rulesd);
			
			WorkerComponent wc = WCFactory.createWC("lte.hss.paris.006", p);
			wc.setParameter("kernel.host", host);
			wc.setParameter("kernel.bootstrapPort", port);
			wc.setParameter("communication.bindPort", 1530);
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
