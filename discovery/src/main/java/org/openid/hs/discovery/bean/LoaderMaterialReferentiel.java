package org.openid.hs.discovery.bean;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Properties;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.ILoaderMaterial;
import org.openid.hs.discovery.exception.InvalidParameters;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * 
 * @author KOUNTA
 *
 */

public class LoaderMaterialReferentiel implements ILoaderMaterial {

	private Iterator<?> it;
	
	private final static String SPLIT_CARACTER = ";" ;
	
	private final static String MONGO_TABLE_NAME = "MATERIAL";
	
	private final static String MONGO_DOMAINE_NAME = "HIVE_SUPERVISOR";
	
	private final static String DEFAULT_PROPERTIES_FILE_NAME = "/nodes_registry.properties";
	
	private String [] split;
	
	private MongoClient mongo;
	
	private DBCollection table;
	
	private BasicDBObject dataUnity;
	
	private DB databaseMaterial;
	
	/**
	 * Constructor
	 * @throws UnknownHostException 
	 * @throws DatagridException 
	 */
	public LoaderMaterialReferentiel() throws UnknownHostException, DatagridException 	{
		
		
		dataUnity = new BasicDBObject();
		initMongoDBForMaterial();
	}
	
	
	/**
	 * @return void
	 * This method consist to established connection to MongoDB
	 * using  
	 * @throws UnknownHostException 
	 * @throws DatagridException 
	 */
	private void initMongoDBForMaterial() throws UnknownHostException, DatagridException {
		
		mongo = DatagridAPI.get().getMongoClient();
		LoggerHelper.info("Connection to MongoDB for Material is established");
		databaseMaterial = mongo.getDB(MONGO_DOMAINE_NAME);
		table = databaseMaterial.getCollection(MONGO_TABLE_NAME);
		table.drop();
	}
	
	@Override
	public void dataMaterialLoadedFromProperties(String pFilePath) {
		try {
			dataMaterialLoaded(ResourceHelper.getProperties(pFilePath));
		} catch (IOException e) {
			LoggerHelper.error(String.format("File %s was not found", pFilePath), e);
		}
	}
	
	/**
	 * This method consist to load data material from file properties in resources directory "MaterialReferentiel.properties"
	 */
	//@Override
	public void dataMaterialLoaded(Properties propertie) {
		LoggerHelper.info("Properties file already loaded, number line is "+(propertie.size()-1));
		it = propertie.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String valeur = null;
				if	(!key.equals("pattern"))	{
					valeur = propertie.getProperty(key);
					split = valeur.split(SPLIT_CARACTER);
					
					dataUnity.put("WC_ID", split[0]);
					dataUnity.put("NETWORK", split[1]);
					dataUnity.put("TYPE", split[2]);
					dataUnity.put("ZONE", split[3]);
					dataUnity.put("ADDRESS_IP", split[4]);
					dataUnity.put("PORT", split[5]);
					
					LoggerHelper.info(split[0]+"-"+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4]+"-"+split[5]);
					
					table.insert(dataUnity);
					dataUnity.clear();

				}
		}
		LoggerHelper.info("\n");
	}

	/**
	 * This method gives solution to get Information from MongoDB 
	 * Those informations are selected from Material database following 
	 * the information provided in parameters.
	 * @param String...pFilter all filter parameters
	 * @return DBCursor Object containing all material reference selected in request
	 * @throws InvalidParameters 
	 * 
	 */
	@Override
	public DBCursor selectDataMaterial(String ... pFilter) throws InvalidParameters {
		
		
			if	(pFilter.length % 2 != 0) 
				throw new InvalidParameters("Invalid query parameters in selectDataMaterial method");
			/*
		String request = "Select * from MATERIAL WHERE ";
		int index =1;
		
		for	(String pQuery : pFilter)	{
			
			if	(index % 2 != 0)	{
				request = request+ pQuery + " = ";
				index++;
			}
			else	{
					if	(index == pFilter.length)	
						request = request+ pQuery;
					else	{
						request = request+ pQuery + " AND ";
						index++;
					}
			}
		}
		
		LoggerHelper.info(request);
		*/
		BasicDBObject searchQuery = new BasicDBObject();
		for	(int i=0 ; i<pFilter.length-1 ; i=i+2)	{
			
			searchQuery.put(pFilter[i], pFilter[i+1]);
		}
		DBCursor cursor = table.find(searchQuery).sort(new BasicDBObject("ADDRESS_IP", 1).append("PORT", 1));

		while (cursor.hasNext()) {
			LoggerHelper.info(cursor.next().toString());
		}
		return cursor;
	}

	@Override
	public void dataMaterialDefaultLoaded() {
		dataMaterialLoadedFromProperties(DEFAULT_PROPERTIES_FILE_NAME);
	}
}
