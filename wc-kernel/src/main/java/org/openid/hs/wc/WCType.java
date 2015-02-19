package org.openid.hs.wc;

public class WCType {
	public static String ENB = "enodeb";
	public static String MME = "mme";
	public static String HSS = "hss";
	public static String EXCHANGE = "exchange";
	public static String DSLAM = "dslam";
	public static String BRAS = "bras";
	public static String []ALL = {ENB, MME, HSS, EXCHANGE, DSLAM, BRAS};
	public static boolean exists(String pType) {
		for (String type : ALL) {
			if (type.equals(pType)) {
				return true;
			}
		}
		return false;
	}
	private WCType() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
