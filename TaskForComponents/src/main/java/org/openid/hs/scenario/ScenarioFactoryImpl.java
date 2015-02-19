package org.openid.hs.scenario;

import org.openid.hs.feature.ScenarioFactory;
import org.openid.hs.feature.ScenerioOfComponent;
import org.openid.hs.wc.WorkerComponent;


public class ScenarioFactoryImpl implements ScenarioFactory {


	@Override
	public ScenerioOfComponent createScenario(WorkerComponent wc) {
		String type = wc.getType().toUpperCase();
		if(type.equals("MME"))
			return new ScenarioOfMME(wc);
		else if(type.equals("HSS"))
			return new ScenarioOfHSS(wc);
		else if(type.equals("BRAS"))
			return new ScenarioOfBRAS(wc);
		else if(type.equals("DSLAM"))
			return new ScenarioOfDSLAM(wc);
		else 
		return new ScenarioOfCOMMUTATOR(wc);
	}

}
