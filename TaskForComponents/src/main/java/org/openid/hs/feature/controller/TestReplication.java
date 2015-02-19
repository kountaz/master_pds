package org.openid.hs.feature.controller;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.feature.bean.UseDatagridApiBean;
import org.openid.hs.replication.bean.ReplicationFactory;
import org.openid.hs.replication.bean.ReplicationManager;
import org.openid.hs.replication.bean.ReplicationObject;

public class TestReplication {

	public static void runMasterTest(String id) {
		try {
			
			
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			//test.selectDataMaterial("ZONE" , "Paris");
			
			
			
			
			ReplicationManager API = ReplicationFactory.createReplicationManager(UseDatagridApiBean.getMyDatagrid(), id, test);
	
			API.start();
		
			ReplicationObject replicant = API.createReplicationObject("deux");
			API.updateMyContextToZone(replicant);
			System.out.println("Dans 6 => context = "+UseDatagridApiBean.getMyDatagrid().get("deux"));
		
			//UseDatagridApiBean.getMyDatagrid().persistDatagrid(UseDatagridApiBean.getMyDatagrid());
		} 	catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
	}
	
	public static void runSlaveTest(String id) {
		try {
			
		/**
		 * Discovery
		 */
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			//test.selectDataMaterial("ZONE" , "Paris");
			
			
			
			
			ReplicationManager API = ReplicationFactory.createReplicationManager(UseDatagridApiBean.getMyDatagrid(), id, test);
	
			API.start();
		
		//	ReplicationObject replicant = API.createReplicationObject("deux");
			//API.updateMyContextToZone(replicant);
			//System.out.println("Dans 6 => context = "+UseDatagridApiBean.getMyDatagrid().get("deux"));
		} 	catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
	}
	
	public static void main(final String[] args) throws DatagridException {

		DatagridAPI.nolog();
		DatagridAPI.init(1550, 1551);
		
		String id2 ="002";
		String id3 ="003";
	
		runSlaveTest(id2);
		runSlaveTest(id3);
		
		
		SystemHelper.pause();
		DatagridAPI.destroy();
		System.exit(0);
	}

}
