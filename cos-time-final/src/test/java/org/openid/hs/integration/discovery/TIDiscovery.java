package org.openid.hs.integration.discovery;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;

public class TIDiscovery 
{
	public static boolean runIntegrationTest()
	{
		boolean flag = false;
		try
		{
			DatagridAPI.nolog();
			DatagridAPI.init();
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			test.selectDataMaterial("ZONE" , "Paris");
			flag = true;
		}
		catch (Exception ex) 
		{
			LoggerHelper.error("Fatal error!", ex);
		} 
		finally 
		{
			try 
			{
				DatagridAPI.destroy();
			} 
			catch (DatagridException ex) 
			{
				LoggerHelper.error("Fatal error!", ex);
			}
		}
		
		return flag;
	}
	
	
	public static void main(String[] args) 
	{
		boolean isGood = runIntegrationTest();
		if(isGood) 
			LoggerHelper.info("Integration test result: OK");
		else
			LoggerHelper.info("Integration test result: KO");
	}
}
