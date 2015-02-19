package org.openid.hs.feature;

import org.openid.hs.feature.bean.ObjectForBRAS;
import org.openid.hs.feature.bean.ObjectForCOMMUTATOR;
import org.openid.hs.feature.bean.ObjectForDSLAM;
import org.openid.hs.feature.bean.ObjectForHSS;
import org.openid.hs.feature.bean.ObjectForMME;

public interface UseDatagridApi {

	public void destroy();
	public void saveMyData(ObjectForHSS object);
	public void removeMyData(ObjectForHSS object);
	public ObjectForHSS getMyData(String key, ObjectForHSS object);
	
	public void saveMyData(ObjectForMME object);
	public void removeMyData(ObjectForMME object);
	public ObjectForMME getMyData(String key, ObjectForMME object);
	
	public void saveMyData(ObjectForBRAS object);
	public void removeMyData(ObjectForBRAS object);
	public ObjectForBRAS getMyData(String key, ObjectForBRAS object);
	
	public void saveMyData(ObjectForDSLAM object);
	public void removeMyData(ObjectForDSLAM object);
	public ObjectForDSLAM getMyData(String key, ObjectForDSLAM object);
	
	public void saveMyData(ObjectForCOMMUTATOR object);
	public void removeMyData(ObjectForCOMMUTATOR object);
	public ObjectForCOMMUTATOR getMyData(String key, ObjectForCOMMUTATOR object);
	
	
}
