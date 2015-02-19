package org.openid.hs.wc.builder;

import org.openid.hs.core.Profile;
import org.openid.hs.core.exception.BuildingException;
import org.openid.hs.wc.CommunicationService;
import org.openid.hs.wc.DatagridService;
import org.openid.hs.wc.DiscoveryService;
import org.openid.hs.wc.FailDetectorService;
import org.openid.hs.wc.FrontEndService;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.MmeActivityService;
import org.openid.hs.wc.QosService;
import org.openid.hs.wc.RulesDeploymentService;
import org.openid.hs.wc.TimeService;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.bean.CommunicationServiceBean;
import org.openid.hs.wc.bean.DatagridServiceBean;
import org.openid.hs.wc.bean.DiscoveryServiceBean;
import org.openid.hs.wc.bean.FailDetectorServiceBean;
import org.openid.hs.wc.bean.KernelServiceBean;
import org.openid.hs.wc.bean.MmeActivityServiceBean;
import org.openid.hs.wc.bean.MmeFrontEndServiceBean;
import org.openid.hs.wc.bean.QosServiceBean;
import org.openid.hs.wc.bean.RulesDeploymentServiceBean;
import org.openid.hs.wc.bean.TimeServiceBean;
import org.openid.hs.wc.controller.WCFactory;
import org.openid.hs.wc.controller.WCProfileFactory;

public final class BrasBuilder extends WCBuilder {
	protected BrasBuilder() {
		super();
	}

	public WorkerComponent build() throws BuildingException {
		KernelService kernel = new KernelServiceBean();
		DatagridService datagrid = new DatagridServiceBean();
		CommunicationService communication = new CommunicationServiceBean();
		DiscoveryService discovery = new DiscoveryServiceBean();
		FailDetectorService failDetector = new FailDetectorServiceBean(kernel);
		RulesDeploymentService rulesDeploy = new RulesDeploymentServiceBean(kernel);
		TimeService time = new TimeServiceBean();
		try {
			Profile p = WCProfileFactory.createProfile(kernel, datagrid,
					communication, discovery, failDetector, rulesDeploy, time);

			wc = WCFactory.createWC(getName(), p);
		} catch (Exception e) {
			throw new BuildingException(e);
		}

		applyParameters();

		return wc;
	}
}
