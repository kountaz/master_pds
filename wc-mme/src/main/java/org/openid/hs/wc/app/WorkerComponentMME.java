package org.openid.hs.wc.app;

import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.wc.HiveSupervisor;

public class WorkerComponentMME {
	public static final String NODES_XML_PATH = "/nodes.xml";
	public static void main(String []args) {
		DatagridAPI.nolog();
		HiveSupervisor.run(NODES_XML_PATH);
		try {
			DatagridAPI.destroy();
		} catch (DatagridException ignored) { }
		System.out.println("-- END");
		System.exit(0);
	}
}
