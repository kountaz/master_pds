package org.openid.hs.activitydata.controller;
import java.text.ParseException;

import org.openid.hs.activitydata.bean.ConsumptionCallInfo;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * BannedUeData class represents the datagrid that contains 
 * all Banned Ue call information that will be sent by the MME Entity
 * @version R2
 * @author Mériem
 * 
 */
public class DataLoader {
	/**
	 * The data instance where we will put UE consumption Data.
	 */
	private static DatagridAPI datagrid;
	private static Datagrid data = null;
	private static DataLoader _instance = null;
	/**
	 * the constructor the creates datagrid API prefilled with ConsumptionCallInfo
	 * objects
	 * 
	 * @throws ParseException
	 */
	private DataLoader(DatagridAPI datagrid, String Name) throws ParseException {
		try {
			
			datagrid = datagrid.get();
			data = datagrid.getDatagrid(Name);
			ConsumptionCallInfo cci1 = new ConsumptionCallInfo("208150123345346", 40, 60);
			ConsumptionCallInfo cci2 = new ConsumptionCallInfo("208150123345347", 45, 60);
			ConsumptionCallInfo cci3 = new ConsumptionCallInfo("20815012334534", 45, 60);
			ConsumptionCallInfo cci4 = new ConsumptionCallInfo("208150123345320", 30, 45);
			
			data.set("208150123345346", cci1);
			data.set("208150123345347", cci2);
			data.set("20815012334534", cci3);
			data.set("208150123345320", cci4);
			
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DataLoader getInstance(DatagridAPI datagrid, String Name) {
		if (_instance == null)
			return create(datagrid, Name);
		else
			return getInstance();
	}

	public static DataLoader getInstance() {
		return _instance;
	}

	private static  DataLoader create(DatagridAPI datagrid, String Name) {
		try {
			_instance = new  DataLoader(datagrid, Name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _instance;
	}
	public Datagrid getData(String Name) {
		return data;
	}
}
