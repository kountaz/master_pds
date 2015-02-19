package org.openid.hs.wc.helper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.openid.hs.core.exception.InitializationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;

public class WCHelper {
	public static final String SEPARATOR_USED_IN_NAME = ".";
	public static final int MOTIVES_IN_NAME = 4;
	public static final int NETWORK_POS_IN_NAME = 1;
	public static final int TYPE_POS_IN_NAME = 2;
	public static final int AREA_POS_IN_NAME = 3;
	public static final int ID_POS_IN_NAME = 4;
	
	public static final String NODE_NAME_PATTERNS_FILEPATH = "/node_name_patterns.hs";
	private static Set<String> nodeNamePatterns;

	public static void initNodeNamePatterns(String pPatternsFilePath)
			throws IOException {
		nodeNamePatterns = new HashSet<String>();
		for (String type : ResourceHelper.readToArray(pPatternsFilePath)) {
			nodeNamePatterns.add(type);
		}
	}
	private static void verifyInit() throws InitializationException {
		try {
			if (nodeNamePatterns == null) {
				initNodeNamePatterns(NODE_NAME_PATTERNS_FILEPATH);
			}
		} catch (IOException e) {
			throw new InitializationException(
					"Default system data couldn't be loaded", e);
		}
	}

	public static boolean checkName(String pName) {
		try {
			verifyInit();
			getIdFromName(pName);
			for (String pattern : nodeNamePatterns) {
				if (pName.startsWith(pattern)) {
					return true;
				}
			}
			throw new IllegalArgumentException(
					"The given name doesn't start whith any node name pattern : "
							+ nodeNamePatterns);
		} catch (Exception e) {
			LoggerHelper.warn(String.format(
					"WCHelper.checkName(%s) process failed", pName), e);
		}
		return false;
	}

	public static String getNetworkFromName(String pName) {
		return getMotiveFromName(NETWORK_POS_IN_NAME, pName);
	}

	public static String getTypeFromName(String pName) {
		return getMotiveFromName(TYPE_POS_IN_NAME, pName);
	}

	public static String getAreaFromName(String pName) {
		return getMotiveFromName(AREA_POS_IN_NAME, pName);
	}

	public static String getIdFromName(String pName) {
		return getMotiveFromName(ID_POS_IN_NAME, pName);
	}

	private static String getMotiveFromName(int pPos, String pName) {
		StringTokenizer nameTokenizer = new StringTokenizer(pName,
				SEPARATOR_USED_IN_NAME);
		if (nameTokenizer.countTokens() != MOTIVES_IN_NAME) {
			throw new IllegalArgumentException(
					"The given name doesn't match at the next pattern : {network}.{type}.{area}.{id}");
		}
		for (int i = 1; i < pPos; ++i) {
			nameTokenizer.nextToken();
		}
		return nameTokenizer.nextToken();
	}

	private WCHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
