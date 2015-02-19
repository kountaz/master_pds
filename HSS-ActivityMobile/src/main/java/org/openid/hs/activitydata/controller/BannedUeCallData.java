package org.openid.hs.activitydata.controller;
import java.text.ParseException;

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
public class BannedUeCallData {
	/**
	 * The data instance where we will put UE Call Data.
	 */
	private static DatagridAPI datagrid;
	private static Datagrid data = null;
	private static BannedUeCallData _instance = null;
	/**
	 * the constructor the creates datagrid API prefilled with UEdataCall
	 * objects to test the operation of the control program
	 * 
	 * @throws ParseException
	 */
	private BannedUeCallData(DatagridAPI datagrid, String Name) throws ParseException {
		try {
			datagrid = datagrid.get();
			data = datagrid.getDatagrid(Name);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static BannedUeCallData getInstance(DatagridAPI datagrid, String Name) {
		if (_instance == null)
			return create(datagrid, Name);
		else
			return getInstance();
	}

	public static BannedUeCallData getInstance() {
		return _instance;
	}

	private static  BannedUeCallData create(DatagridAPI datagrid, String Name) {
		try {
			_instance = new  BannedUeCallData(datagrid, Name);
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
