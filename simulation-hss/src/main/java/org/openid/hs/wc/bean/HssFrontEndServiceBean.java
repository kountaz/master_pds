package org.openid.hs.wc.bean;

import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.hss.bean.HssWebservice;
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
public class HssFrontEndServiceBean extends FrontEndServiceBean {
	/**
	 * Default service name.
	 */
	public static final String SERVICE_NAME = "frontend.hss";
	
	public HssFrontEndServiceBean(KernelService pKernel) {
		super(pKernel, SERVICE_NAME);
		try {
			if (!DatagridAPI.isInit()) {
				DatagridAPI.init();
			}
			setBean(new HssWebservice(DatagridAPI.get().getDatagrid(WCType.ENB)));
		} catch (DatagridException e) {
			e.printStackTrace();
		}
	}
}
