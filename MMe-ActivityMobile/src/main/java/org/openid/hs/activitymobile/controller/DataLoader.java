package org.openid.hs.activitymobile.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openid.hs.activitycontrol.bean.UeDataCall;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;

/**
 * DataLoader class that creat a DataGrid this class is also used to feed the
 * datagrid for testing the program works
 * 
 * @version R2
 * @author Mériem
 * 
 */
public class DataLoader {
	private static DatagridAPI datagridapi;
	/**
	 * The data instance where we will put UE Call Data.
	 */
	private static Datagrid data = null;
	private static DataLoader _instance = null;
	/**
	 * the constructor the creates datagrid API prefilled with UEdataCall
	 * objects to test the operation of the control program
	 * 
	 * @throws ParseException
	 */
	private DataLoader(String Name) throws ParseException {
		try {
			String filePath = "/config.properties";
			Properties p = ResourceHelper.getProperties(filePath);
			String ip = "ipEnodeb";
			String valueip = p.getProperty(ip);
			
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			data = DatagridAPI.get().getDatagrid(Name);
			UeDataCall ue1 = new UeDataCall(valueip, 1501,
					"208150123345346", 1, 60, 40,stamp);
			UeDataCall ue2 = new UeDataCall(valueip, 1501,
					"208150123345347", 1, 60, 45,stamp);
			UeDataCall ue3 = new UeDataCall(valueip, 1501,
					"208150123345348", 1, 60, 45,stamp);
			data.set("208150123345346", ue1);
			data.set("208150123345347", ue2);
			data.set("208150123345348", ue2);
			
		} catch (DatagridException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DataLoader getInstance(String Name) {
		if (_instance == null)
			return create(Name);
		else
			return getInstance();
		
	}

	public static DataLoader getInstance() {
		return _instance;
	}

	private static  DataLoader create(String Name) {
		try {
			_instance = new DataLoader(Name);
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
