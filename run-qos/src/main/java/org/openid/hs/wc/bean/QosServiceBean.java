package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.mock.stimulus.dialog.TaskMock;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.measure.controller.TaskMeasure;
import org.openid.hs.qos.rules.controller.RulesController;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.QosService;

/**
 * 
 * @see QosService
 * @version R2
 * @author Victor, Steven
 *
 */
public class QosServiceBean extends AbstractService implements QosService {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "qos";
	
	public static final String DEFAULT_RULES_PATH_FILE = "/rules_call.xml";
	
	private KernelService kernelService;
	
	private String rulesFilePath;
	
	private TaskMock mock_task;
	
	private TaskMeasure measure_task;
	
	public QosServiceBean(KernelService pKernelService) {
		super(SERVICE_NAME);
		kernelService = pKernelService;
	}

	@Override
	public void setRulesFile(String pPath) {
		rulesFilePath = pPath;
	}

	@Override
	public String getRulesFile() {
		return rulesFilePath;
	}
	
	@Override
	public void start() throws ServiceException {
		if (rulesFilePath == null) {
			LoggerHelper.warn("Loading rules per default");
			rulesFilePath = DEFAULT_RULES_PATH_FILE;
		}
		
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init();
			}
			DatagridAPI datagridApi = DatagridAPI.get();
			Datagrid grid_rules = datagridApi.getDatagrid(EnumParameter.G1);
			Datagrid grid_mock = datagridApi.getDatagrid(EnumParameter.S1);
			
			RulesController.init(grid_rules);
			RulesController rc = RulesController.get();
			rc.loadRulesFromFile(rulesFilePath);
			mock_task = new TaskMock(grid_mock);	
			measure_task = new TaskMeasure(grid_mock,rc.getRules(),kernelService.getWc());
			
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	@Override
	public void stop() throws ServiceException {
		measure_task.cancel();
		mock_task.cancel();
		super.stop();
	}
}
