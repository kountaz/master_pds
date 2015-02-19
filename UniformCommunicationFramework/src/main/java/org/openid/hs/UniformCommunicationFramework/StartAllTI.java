package org.openid.hs.UniformCommunicationFramework;

import java.net.UnknownHostException;

public class StartAllTI {

	public static void main(String[] args) throws UnknownHostException {
		int time = (args.length > 0 && args[0] != null) ? Integer.parseInt(args[0]) : 0;
		try {
			StartReceiverTI.main(null);
			Thread.sleep(time);
			StartSenderTI.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
