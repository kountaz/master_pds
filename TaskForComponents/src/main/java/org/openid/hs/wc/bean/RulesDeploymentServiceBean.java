package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.feature.ScenarioFactory;
import org.openid.hs.feature.ScenerioOfComponent;
import org.openid.hs.scenario.ScenarioFactoryImpl;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.RulesDeploymentService;

/**
 * 
 * @version R2
 * @author Mouhamadou, Steven
 *
 */
public class RulesDeploymentServiceBean extends AbstractService implements RulesDeploymentService {
	/**
	 * 
	 */
	private KernelService kernel;
	
	private Thread thread;
	
	public RulesDeploymentServiceBean(KernelService pKernel) {
		super(SERVICE_NAME);
		kernel = pKernel;
	}
	@Override
	public void start() throws ServiceException {
		try {
			ScenarioFactory factory = new ScenarioFactoryImpl();
			ScenerioOfComponent scenario = factory.createScenario(kernel.getWc());
			
			thread = new Thread(scenario);
			thread.start();
			
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		try {
			thread.stop();
			super.stop();
		} catch (Exception e) {
			new ServiceException(e);
		}
	}
}
