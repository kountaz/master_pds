package org.openid.hs.core.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Utilities for resources.
 * @version R2
 * @author Steven
 *
 */
public class ResourceHelper {
	public static final String DEFAULT_CHARSET = "UTF-8";
	private static Map<String, Properties> resources = new HashMap<String, Properties>();
	public static Properties getProperties(String pFilePath) throws IOException {
		if (!resources.containsKey(pFilePath)) {
			Properties properties = new Properties();
			properties.load(Object.class.getResourceAsStream(pFilePath));
			resources.put(pFilePath, properties);
		}
		return resources.get(pFilePath);
	}
	public static Object get(String pFilePath, String pKey) throws IOException {
		return getProperties(pFilePath).get(pKey);
	}
	public static String getString(String pFilePath, String pKey) throws IOException {
		return String.valueOf(get(pFilePath, pKey));
	}
	public static int getInteger(String pFilePath, String pKey) throws IOException {
		return Integer.valueOf(getString(pFilePath, pKey));
	}
	public static short getShort(String pFilePath, String pKey) throws IOException {
		return Short.parseShort(getString(pFilePath, pKey));
	}
	public static long getLong(String pFilePath, String pKey) throws IOException {
		return Long.parseLong(getString(pFilePath, pKey));
	}
	public static float getFloat(String pFilePath, String pKey) throws IOException {
		return Float.parseFloat(getString(pFilePath, pKey));
	}
	public static double getDouble(String pFilePath, String pKey) throws IOException {
		return Double.parseDouble(getString(pFilePath, pKey));
	}
	public static char[] getChars(String pFilePath, String pKey) throws IOException {
		String value = getString(pFilePath, pKey);
		return value.toCharArray();
	}
	public static char getChar(String pFilePath, String pKey) throws IOException {
		return getString(pFilePath, pKey).charAt(0);
	}
	public static byte[] getBytes(String pFilePath, String pKey) throws IOException {
		return StringHelper.toBytes(getString(pFilePath, pKey));
	}
	public static byte getByte(String pFilePath, String pKey) throws IOException {
		byte []bytes = getBytes(pFilePath, pKey);
		return bytes[0];
	}
	public static boolean getBoolean(String pFilePath, String pKey) throws IOException {
		String value = getString(pFilePath, pKey);
		if (value.equals("1")) {
			return true;
		}
		if (value.equals("true")) {
			return true;
		}
		if (value.toLowerCase().equals("t")) {
			return true;
		}
		return Boolean.parseBoolean(value);
	}
	public static String readFrom(String pFilePath) throws FileNotFoundException {
		Scanner scanner = createScanner(pFilePath);
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}
	public static String[] readToArray(String pTypesFilePath) throws IOException {
		List<String> buffer = new ArrayList<String>();
		Scanner scanner = ResourceHelper.createScanner(pTypesFilePath);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			if (line.length() > 0) {
				buffer.add(line);
			}
		}
		String []text = new String[buffer.size()];
		buffer.toArray(text);
		buffer.clear();
		scanner.close();
		return text;
	}
	public static Scanner createScanner(String pFilePath) {
		return new Scanner(Object.class.getResourceAsStream(pFilePath));
	}
	private ResourceHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
