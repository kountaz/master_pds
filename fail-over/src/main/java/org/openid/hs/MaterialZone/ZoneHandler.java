package org.openid.hs.MaterialZone;

import java.net.UnknownHostException;

import org.openid.hs.datagrid.*;
import org.openid.hs.datagrid.exception.DatagridException;
import org.slf4j.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ZoneHandler {
	private final static String MONGO_TABLE_NAME = "MASTERTABLE";
	private final static String MONGO_DOMAINE_NAME = "HIVE_SUPERVISOR";
	private DatagridAPI datagridApi;
	private MongoClient mongo;
	private DBCollection table;
	private DB databaseMaster;
	private Datagrid grid;
	Logger logger = LoggerFactory.getLogger(ZoneHandler.class);
	/**
	 * Constructor
	 * @param pDatagridApi
	 * @throws UnknownHostException
	 * @throws DatagridException 
	 */
	public ZoneHandler() throws UnknownHostException, DatagridException
	{
		datagridApi = DatagridAPI.get();
		this.initMongoDBForMaterial();
	}
	
	/**
	 * @return void
	 * This method consist to established connection to MongoDB
	 * using  
	 * @throws UnknownHostException 
	 * @throws DatagridException 
	 */
	private void initMongoDBForMaterial() throws UnknownHostException, DatagridException {
		
		mongo = datagridApi.getMongoClient();
		grid = datagridApi.loadDatagrid("MasterGrid");
		//logger.info("Connection to MongoDB for Material is established");
		/*databaseMaster = mongo.getDB(MONGO_DOMAINE_NAME);
		table = databaseMaster.getCollection(MONGO_TABLE_NAME);
		table.drop();*/
	}
	/**
	 * Save on the MongoDB Base a new Master
	 * @param dbo
	 * @throws DatagridException 
	 */
	public void SaveNewMaster(BasicDBObject dbo)
	{
		try {
			grid.remove(dbo.get("id").toString());
		} catch (DatagridException e) {
			logger.info("Nothing for this Zone, it's a new Entry");
		}catch(NullPointerException e){
			logger.info("Nothing for this Zone, it's a new Entry");
		}
		if(dbo.get("id") == null)
			dbo.append("id",grid.getKeys().size());
		grid.set(dbo.get("id").toString(), dbo);
	}
	/**
	 * Return the zone of the DBOject pass
	 * @param dbo
	 * @return the zone of the DBOject pass
	 * @throws UnknownHostException
	 * @throws DatagridException
	 */
	public Zone getZone(BasicDBObject dbo) throws UnknownHostException, DatagridException
	{
		System.out.println(dbo);
		for (String str : grid.getKeys()) {
			DBObject obj = (DBObject)grid.get(dbo.get("str").toString());
			if(obj.get("libelle").toString()== dbo.getString("libelle")&& obj.get("type").toString()== dbo.getString("TYPE").toString())
			{
				return new Zone(Integer.parseInt(obj.get("id").toString()), obj.get("libelle").toString(), obj.get("type").toString(), obj.get("MasterAdress").toString(), obj.get("MasterPort").toString(),this);
			}
		}
		dbo.append("id", null);
		this.SaveNewMaster(dbo);
		System.out.println(dbo);
		Zone z = new Zone(Integer.parseInt(dbo.get("id").toString()), dbo.get("libelle").toString(), dbo.get("type").toString(), dbo.get("MasterAdress").toString(), dbo.get("MasterPort").toString(),this);
		return z;
	}
}