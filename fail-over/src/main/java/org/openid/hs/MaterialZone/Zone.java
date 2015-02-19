package org.openid.hs.MaterialZone;

import java.net.UnknownHostException;

import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

import com.mongodb.*;

public class Zone {
	public Integer id;
	public String libelle;
	public String type;
	public String MasterAdress;
	public String MasterPort;
	private ZoneHandler zhandler;
	/**
	 * Constructor (Do not Use)
	 * @param id
	 * @param libelle
	 * @param type
	 * @param MasterAdress
	 * @param MasterPort
	 * @throws UnknownHostException
	 * @throws DatagridException
	 */
	public Zone(Integer id, String libelle, String type, String MasterAdress,
			String MasterPort, ZoneHandler z) throws UnknownHostException, DatagridException {
		this.id = id;
		this.libelle = libelle;
		this.type = type;
		this.MasterAdress = MasterAdress;
		this.MasterPort = MasterPort;
		zhandler =z;
	}
	/**
	 * Constructor with the type and zone's name
	 * @param type
	 * @param libelle
	 * @throws UnknownHostException
	 * @throws DatagridException
	 */
	public Zone(String type,String libelle,String IPadrr, String port) throws UnknownHostException, DatagridException
	{
		BasicDBObject doc = new BasicDBObject("libelle", libelle).append("type", type).append("MasterAdress", IPadrr).append("MasterPort", port);
		zhandler =new ZoneHandler();
		System.out.println("first check "+doc);
		Zone z = zhandler.getZone(doc);
		this.id = z.id;
		this.libelle = z.libelle;
		this.type = z.type;
		this.MasterAdress = z.MasterAdress;
		this.MasterPort = z.MasterPort;
	}
	/**
	 * Save the current Zone as Master
	 * @throws DatagridException 
	 */
	public void setMasterAdress() throws DatagridException {
		BasicDBObject doc = new BasicDBObject("id", this.id)
				.append("libelle", libelle).append("type", type)
				.append("MasterAdress", MasterAdress)
				.append("MasterPort", MasterPort);
		this.zhandler.SaveNewMaster(doc);
	}
	/**
	 * Get the Master before on the list
	 * @return
	 * @throws UnknownHostException
	 * @throws DatagridException
	 */
	public Zone getZoneBefore() throws UnknownHostException, DatagridException
	{
		BasicDBObject doc = new BasicDBObject("id", this.id-1)
		.append("libelle", libelle).append("type", type);
		return zhandler.getZone(doc);
	}
	/**
	 * Get the Master behind on the list
	 * @return
	 * @throws UnknownHostException
	 * @throws DatagridException
	 */
	public Zone getZoneNext() throws UnknownHostException, DatagridException
	{
		BasicDBObject doc = new BasicDBObject("id", this.id+1)
		.append("libelle", libelle).append("type", type);
		return zhandler.getZone(doc);
	}
}