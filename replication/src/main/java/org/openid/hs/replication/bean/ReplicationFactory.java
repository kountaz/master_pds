package org.openid.hs.replication.bean;

import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;

public class ReplicationFactory {
	/**
	 * Indicates if the ReplicationContext API is initialized.
	 * 
	 * @return True if the unique ReplicationContext API is initialized.
	 * @throws DatagridException 
	 */
	public static ReplicationManager createReplicationManager(
			Datagrid pGrid, String WC_ID,
			LoaderMaterialReferentiel MaterielRefentiel) throws DatagridException {
		ReplicationManager instance;
		
		instance = new ReplicationManager(pGrid, WC_ID, MaterielRefentiel);
		
		return instance;
	}
	
	private ReplicationFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
