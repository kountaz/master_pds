package org.openid.hs.discovery.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Properties;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.DatagridAPI;
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

public class LoaderMaterialReferentielV2 implements ILoaderMaterial {

	private final static String SPLIT_CARACTER_POINT_VIRGULE = ";" ;
	private final static String SPLIT_CARACTER_2POINTS = ":" ;
	private String [] split;
	private static String [] split_ID;
	private static String [] split_IP_PORT;
	
	private final static String MONGO_TABLE_NAME = "MATERIAL";
	
	private final static String MONGO_DOMAINE_NAME = "HIVE_SUPERVISOR";
	
	private final static String DEFAULT_PROPERTIES_FILE_NAME = "/MaterialReferentiel.properties";
	
	private DatagridAPI datagridApi;
	
	
	
	private MongoClient mongo;
	
	private DBCollection table;
	
	private BasicDBObject dataUnity;
	
	private DB databaseMaterial;
	
	/**
	 * Constructor
	 * @throws UnknownHostException 
	 */
	public LoaderMaterialReferentielV2(DatagridAPI pDatagridApi) throws UnknownHostException 	{
		datagridApi = pDatagridApi;
		dataUnity = new BasicDBObject();
		initMongoDBForMaterial();
	}
	
	
	/**
	 * @return void
	 * This method consist to established connection to MongoDB
	 * using  
	 * @throws UnknownHostException 
	 */
	private void initMongoDBForMaterial() throws UnknownHostException {
		
		mongo = datagridApi.getMongoClient();
		LoggerHelper.info("Connection to MongoDB for Material is established");
		databaseMaterial = mongo.getDB(MONGO_DOMAINE_NAME);
		table = databaseMaterial.getCollection(MONGO_TABLE_NAME);
		table.drop();
	}
	
	@Override
	public void dataMaterialLoadedFromProperties(String pFilePath) {
		dataMaterialLoaded(pFilePath);
	}
	
	/**
	 * This method consist to load data material from file properties in resources directory "MaterialReferentiel.properties"
	 */
	//@Override
	private void dataMaterialLoaded(String pFilePath) {
		
		Properties properties = new Properties();
		try {
			properties.load(Object.class.getResourceAsStream(pFilePath));
		} catch (IOException e) {
			LoggerHelper.error(String.format("File %s was not found", pFilePath), e);
		}
		LoggerHelper.info("Properties file already loaded, number line is "+properties.size());
		
		try {
			String contentFileProperties = ResourceHelper.readFrom(pFilePath);
			String [] line = contentFileProperties.split("\n");
			
			for	(String nextLine : line )	{
				
				if	(!nextLine.startsWith("#"))	{
					//System.out.println(nextLine);
					nextLine.trim();
					split = nextLine.split(SPLIT_CARACTER_POINT_VIRGULE);
					//System.out.println(split[0].trim()+"|"+split[1].trim()+"|");
					
					for	(int j=0 ; j<split.length ; j++)	{
						
						split_ID = split[0].trim().split("-");
						split_IP_PORT = split[1].trim().split(SPLIT_CARACTER_2POINTS);
						
						dataUnity.put("WC_ID", split_ID[3].trim());
						dataUnity.put("NETWORK", split_ID[0].trim());
						dataUnity.put("TYPE", split_ID[1].trim());
						dataUnity.put("ZONE", split_ID[2].trim());
						dataUnity.put("ADDRESS_IP", split_IP_PORT[0].trim());
						dataUnity.put("PORT", split_IP_PORT[1].trim());
						
						//System.out.println(split_ID.length);
						LoggerHelper.info(split_ID[0]+"|"+split_ID[1]+"|"+split_ID[2]+"|"+split_ID[3].trim()+"|-|"+split_IP_PORT[0].trim()+"|"+split_IP_PORT[1].trim());
						this.table.insert(dataUnity);
						this.dataUnity.clear();
					}
				}
			}
			LoggerHelper.info("\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LoggerHelper.error("Fatal error to loader referentiel material");
		}
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
		LoggerHelper.info("\n");
		return cursor;
	}

	@Override
	public void dataMaterialDefaultLoaded() {
		dataMaterialLoadedFromProperties(DEFAULT_PROPERTIES_FILE_NAME);
	}
	
	public void resetTable()	{
		this.table.drop();
	}
}
