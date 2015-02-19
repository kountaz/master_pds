package org.openid.hs.wc.app;

import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.wc.HiveSupervisor;

public class WorkerComponentEnodeb {
	public static final String NODES_XML_PATH = "/nodes.xml";
	public static void main(String []args) {
		DatagridAPI.nolog();
		HiveSupervisor.run(NODES_XML_PATH);
		System.out.println("-- END");
		System.exit(0);
	}
}
