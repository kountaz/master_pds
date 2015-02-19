package org.openid.hs.faildetector.runner;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.faildetector.bean.FailDetector;
public class FailDetectorRunner {

	public static void main(String[] args) {
		try {
			
			args = new String[2];
			args[0] = "127.0.0.1";
			args[1] = "7001";
			
			System.out.println("test");
			
			DatagridAPI.nolog();
			DatagridAPI.init();
			LoaderMaterialReferentiel test = new LoaderMaterialReferentiel();
			test.dataMaterialDefaultLoaded();
			//test.selectDataMaterial("TYPE" , "HSS","ZONE" , "Paris");
			FailDetector detector = new FailDetector(args[0], args[1], "HSS", "Paris",test);
			detector.start();
			SystemHelper.pause();
			detector.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatagridAPI.destroy();
			} catch (DatagridException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
