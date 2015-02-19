package org.openid.hs.wc;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SystemHelper;
import org.openid.hs.wc.controller.WCExecutor;

public class HiveSupervisor {
	public static void run(String pXmlPath) {
		WCExecutor executor = null;
		try {
			executor = WCExecutor.createFrom(pXmlPath);
			//executor.startAll();
		} catch (Exception e) {
			LoggerHelper.error("Fatal error!", e);
		} finally {
			if (executor != null) {
				try {
					SystemHelper.pause();
					executor.stopAll();
				} catch (Exception e) {
					LoggerHelper.error("Fatal error!", e);
				}
			}
		}
	}
}
