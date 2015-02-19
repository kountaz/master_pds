package org.openid.hs.feature;

import org.openid.hs.wc.WorkerComponent;

public interface ScenarioFactory {
	ScenerioOfComponent createScenario(WorkerComponent wc);
}
