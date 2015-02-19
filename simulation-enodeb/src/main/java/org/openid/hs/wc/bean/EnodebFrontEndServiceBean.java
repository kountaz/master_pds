package org.openid.hs.wc.bean;

import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.enodeb.bean.EnodebWebservice;
import org.openid.hs.wc.FrontEndService;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.WCType;

/**
 * Concrete frontend service.
 * @see FrontEndService
 * @version R2
 * @author Fanta
 *
 */
public class EnodebFrontEndServiceBean extends FrontEndServiceBean {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "frontend.enodeb";
	
	public EnodebFrontEndServiceBean(KernelService pKernel) {
		super(pKernel, SERVICE_NAME);
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init();
			}
			setBean(new EnodebWebservice(DatagridAPI.get().getDatagrid(WCType.ENB)));
		} catch (DatagridException e) {
			e.printStackTrace();
		}
	}
}
