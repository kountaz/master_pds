package org.openid.hs.replication.helper;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.replication.bean.ReplicationFactory;
import org.openid.hs.replication.bean.ReplicationManager;
import org.openid.hs.replication.bean.ReplicationObject;

public class TestReplication {

	public static void runMasterTest(String id) {
		try {
			DatagridAPI datagridApi = DatagridAPI.get();
			
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			test.selectDataMaterial("ZONE" , "Paris");
			
			
			Datagrid grid = datagridApi.loadDatagrid("Context");
			grid.set("un", "ONE");
			grid.set("deux", "TWO");
			grid.set("trois", "TREE");
			
			ReplicationManager API = ReplicationFactory.createReplicationManager(grid, id, test);
	
			API.start();
		
			ReplicationObject replicant = API.createReplicationObject("deux");
			API.updateMyContextToZone(replicant);
		
			datagridApi.persistDatagrid(grid);
		} 	catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
	}
	
	public static void runSlaveTest(String id) {
		try {
			
		/**
		 * Discovery
		 */ 
				DatagridAPI datagridApi = DatagridAPI.get();				
				LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
				test.dataMaterialDefaultLoaded();
				test.selectDataMaterial("ZONE" , "Paris");
				Datagrid grid = datagridApi.loadDatagrid("Context");
				
				ReplicationManager API = ReplicationFactory.createReplicationManager(grid, id, test);
				API.start();
		} 	catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		}
	}
	
	public static void main(final String[] args) throws DatagridException {

		DatagridAPI.nolog();
		DatagridAPI.init(1550, 1551);
		
		String id ="004";
		String id2 ="005";
		String id3 ="006";
		runSlaveTest(id);
		runSlaveTest(id2);
		runMasterTest(id3);
		
		SystemHelper.pause();
		DatagridAPI.destroy();
		System.exit(0);
	}
}
