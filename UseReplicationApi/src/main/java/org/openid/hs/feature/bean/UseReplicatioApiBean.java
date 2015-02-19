package org.openid.hs.feature.bean;


import java.net.UnknownHostException;

import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.feature.UseReplicationApi;
import org.openid.hs.replication.bean.ReplicationFactory;
import org.openid.hs.replication.bean.ReplicationManager;
import org.openid.hs.replication.bean.ReplicationObject;
import org.openid.hs.replication.exception.ReplicationException;

public class UseReplicatioApiBean implements UseReplicationApi{
	
	ReplicationManager API ;
	public UseReplicatioApiBean() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listening(String id) {
		// TODO Auto-generated method stub
		
		try {
			LoaderMaterialReferentiel test;
			test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			API = ReplicationFactory.createReplicationManager(UseDatagridApiBean.getMyDatagrid(), id, test);
			
			API.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void replication(String key) {
		// TODO Auto-generated method stub
		
		try {
			ReplicationObject replicant;
			replicant = API.createReplicationObject(key);
			API.updateMyContextToZone(replicant);
		} catch (ReplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
