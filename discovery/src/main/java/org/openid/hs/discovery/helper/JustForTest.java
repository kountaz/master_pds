package org.openid.hs.discovery.helper;



import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.discovery.bean.LoaderMaterialReferentielV2;
import org.openid.hs.discovery.exception.InvalidParameters;


public class JustForTest {

	public static void main(String[] args) throws InvalidParameters {
		// TODO Auto-generated method stub
		

		try {
			/**
			 * INITIALISATION OF DATAGRIDAPI CONNECTOR
			 */
			DatagridAPI.nolog();
			DatagridAPI.init();
			
			/**
			 * Testing the discovery component
			 */
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			test.selectDataMaterial("ZONE" , "Paris");
			
			//LoaderMaterialReferentielV2 test = new LoaderMaterialReferentielV2(datagridApi);
			//test.dataMaterialDefaultLoaded();
			//test.selectDataMaterial("ZONE" , "paris");
			//test.resetTable();
			//DatagridAPI.destroy();
			
			/**
			 * Testing the FailOver Component
			 */
			//FailDetector detector = new FailDetector("127.0.0.1", args[0], "HSS", "Paris",test );
			//detector.run();
			//detector.WatchOverIt(0);
			
		} catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		} finally {
			try {
				DatagridAPI.destroy();
			} catch (DatagridException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.exit(0);
	}
}
