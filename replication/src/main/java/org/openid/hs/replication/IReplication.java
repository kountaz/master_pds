package org.openid.hs.replication;

import org.openid.hs.discovery.ILoaderMaterial;
import org.openid.hs.replication.bean.ReplicationObject;
import org.openid.hs.replication.exception.ReplicationException;

public interface IReplication {

	public void initWCCoordonnee(ILoaderMaterial ref);
	public void replicationContextListener() throws Exception;
	public void updateMyContextToZone(ReplicationObject replicant) throws ReplicationException;
	
}
