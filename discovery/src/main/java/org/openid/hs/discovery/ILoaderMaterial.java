package org.openid.hs.discovery;

import java.util.Properties;

import org.openid.hs.discovery.exception.InvalidParameters;

import com.mongodb.DBCursor;

/**
 * 
 * @author KOUNTA
 *
 */
public interface ILoaderMaterial {
	
	public void dataMaterialDefaultLoaded();
	public void dataMaterialLoadedFromProperties(String pFilePath);
	public DBCursor selectDataMaterial(String ... pFilter) throws InvalidParameters;
	
}
