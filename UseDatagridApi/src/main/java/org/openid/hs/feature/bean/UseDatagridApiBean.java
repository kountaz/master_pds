package org.openid.hs.feature.bean;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.feature.UseDatagridApi;

public class UseDatagridApiBean implements UseDatagridApi {
	static Datagrid grid;

	public UseDatagridApiBean() {
		// TODO Auto-generated constructor stub
	}

	public static Datagrid getMyDatagrid(){
		return grid;
	}
	public static DatagridAPI getDatagridApi() throws DatagridException {
		return DatagridAPI.get();
	}

	public static UseDatagridApiBean initMyDatagrid() {

		try {
			grid = DatagridAPI.get().loadDatagrid("Rules");
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new UseDatagridApiBean();
	}

	@Override
	public void saveMyData(ObjectForHSS object) {
		// TODO Auto-generated method stub

		grid.set(String.valueOf(object.getId()), object);
		LoggerHelper.info("UsedatagridApibean>> object of id "
				+ String.valueOf(object.getId()) + " is saved");
		try {

			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMyData(ObjectForHSS object) {
		// TODO Auto-generated method stub

		try {
			grid.remove(String.valueOf(object.getId()));
			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObjectForHSS getMyData(String key, ObjectForHSS object) {
		// TODO Auto-generated method stub
		try {
			object = (ObjectForHSS) grid.get(key);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void saveMyData(ObjectForMME object) {
		// TODO Auto-generated method stub
		grid.set(String.valueOf(object.getId()), object);
		LoggerHelper.info("UsedatagridApibean>> object of id "
				+ String.valueOf(object.getId()) + " is saved");
		try {

			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeMyData(ObjectForMME object) {
		// TODO Auto-generated method stub

		try {
			grid.remove(String.valueOf(object.getId()));
			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObjectForMME getMyData(String key, ObjectForMME object) {
		// TODO Auto-generated method stub
		try {
			object = (ObjectForMME) grid.get(key);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void saveMyData(ObjectForBRAS object) {
		// TODO Auto-generated method stub
		grid.set(String.valueOf(object.getId()), object);
		LoggerHelper.info("UsedatagridApibean>> object of id "
				+ String.valueOf(object.getId()) + " is saved");
		try {

			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeMyData(ObjectForBRAS object) {
		// TODO Auto-generated method stub

		try {
			grid.remove(String.valueOf(object.getId()));
			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObjectForBRAS getMyData(String key, ObjectForBRAS object) {
		// TODO Auto-generated method stub
		try {
			object = (ObjectForBRAS) grid.get(key);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void saveMyData(ObjectForDSLAM object) {
		// TODO Auto-generated method stub
		grid.set(String.valueOf(object.getId()), object);
		LoggerHelper.info("UsedatagridApibean>> object of id "
				+ String.valueOf(object.getId()) + " is saved");
		try {

			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeMyData(ObjectForDSLAM object) {
		// TODO Auto-generated method stub

		try {
			grid.remove(String.valueOf(object.getId()));
			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObjectForDSLAM getMyData(String key, ObjectForDSLAM object) {
		// TODO Auto-generated method stub
		try {
			object = (ObjectForDSLAM) grid.get(key);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void saveMyData(ObjectForCOMMUTATOR object) {
		// TODO Auto-generated method stub
		grid.set(String.valueOf(object.getId()), object);
		LoggerHelper.info("UsedatagridApibean>> object of id "
				+ String.valueOf(object.getId()) + " is saved");
		try {

			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeMyData(ObjectForCOMMUTATOR object) {
		// TODO Auto-generated method stub

		try {
			grid.remove(String.valueOf(object.getId()));
			DatagridAPI.get().persistDatagrid(grid);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObjectForCOMMUTATOR getMyData(String key, ObjectForCOMMUTATOR object) {
		// TODO Auto-generated method stub
		try {
			object = (ObjectForCOMMUTATOR) grid.get(key);
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			DatagridAPI.destroy();
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
