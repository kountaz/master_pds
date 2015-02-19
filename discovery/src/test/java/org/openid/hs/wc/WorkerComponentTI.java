package org.openid.hs.wc;

import org.openid.hs.bootstrap.WCBootstrap;
import org.openid.hs.bootstrap.WCConsoleAPI;
import org.openid.hs.core.AbstractService;
import org.openid.hs.core.Profile;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.wc.bean.CommunicationServiceBean;
import org.openid.hs.wc.bean.DatagridServiceBean;
import org.openid.hs.wc.bean.DiscoveryServiceBean;
import org.openid.hs.wc.controller.WCFactory;
import org.openid.hs.wc.controller.WCProfileFactory;

public class WorkerComponentTI {
	public static class ServiceA extends AbstractService {
		private String x;
		public ServiceA() {
			super("Service A");
		}
		public void setX(String v) {
			x = v;
		}
		public String getX() {
			return x;
		}
	}
	public static class ServiceB extends AbstractService {
		private ServiceA serviceA;
		public ServiceB(ServiceA pServiceA) {
			super("Service B");
			serviceA = pServiceA;
		}
		public ServiceA getServiceA() {
			return serviceA;
		}
	}
	
	public static void main(String []args) {
		//*/
		String host = "localhost";
		int port = 1500;
		try {
			ServiceA a = new ServiceA(); // on créer un service A
			ServiceB b = new ServiceB(a); // puis un service B
			
			DatagridService datagrid = new DatagridServiceBean();
			DiscoveryService discovery = new DiscoveryServiceBean();
			Profile p = WCProfileFactory.createProfile(a, b, datagrid, discovery); 	// on créer un profile avec les services A, B
																// le profile retourné contient les services [Kernel, A, B] 
			CommunicationService communication = new CommunicationServiceBean();
			p.addServiceAfter(communication, KernelService.SERVICE_NAME); 	// on insère le service après le KernelService
																			// le profile contient les services [Kernel, Communication, A, B]
			
			// on créer un worker component à partir du profil
			WorkerComponent wc = WCFactory.createWC("lte.mme.paris.012", p);
			// on paramètre le worker component
			wc.setParameter("kernel.host", host);
			wc.setParameter("kernel.bootstrapPort", port);
			wc.setParameter("communication.bindPort", 1530);
			wc.setParameter("datagrid.databasePort", 1532);
			wc.setParameter("datagrid.memcachePort", 1533);
			//wc.setParameter("discovery.referential", "erfgezfez");
			
			// on créer un bootstrap sur ce worker component
			WCBootstrap bootstrap = new WCBootstrap(wc);
			// on lance le bootstrap
			bootstrap.listen();
			
			//LOGGER.info(WCConsoleAPI.status(host, port));
			WCConsoleAPI.start(host, port); // on démarre le worker component via la console d'admi
			//LOGGER.info(WCConsoleAPI.status(host, port));
			LoggerHelper.info(WCConsoleAPI.status(host, port)); // on récupère son statut
			WCConsoleAPI.stop(host, port); // on stop le WC
			LoggerHelper.info(WCConsoleAPI.status(host, port)); // on récupère son statut
			
			WCConsoleAPI.stop(host, port); // on stop le WC
			
		} catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
		
		System.exit(0);
	}
}
