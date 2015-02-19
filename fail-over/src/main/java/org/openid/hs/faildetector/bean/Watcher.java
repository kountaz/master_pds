package org.openid.hs.faildetector.bean;

import java.util.TimerTask;
/**
 * Use for start every 10 second a check on other component
 * @author openid
 *
 */
class Watcher extends TimerTask {
	private FailDetector FD;
	public Watcher(FailDetector fd)
	{
		FD = fd;
	}
	@Override
	public void run() {
		FD.WatchOverIt(0);
	}

}
