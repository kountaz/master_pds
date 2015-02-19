package org.openid.hs.core.helper;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

/**
 * Utilities for string.
 * @version R2
 * @author Steven
 *
 */
public final class StringHelper {
	public static final int DEFAULT_RANDOM_STRING_LENGTH = 16;
	/**
	 * Uppers the first letter of a string.
	 * @param pString String to upper the first letter.
	 * @return String with first letter upper.
	 */
	public static String upperCaseFirstLetter(String pString) {
		char []stringChars = pString.toCharArray();
		stringChars[0] = Character.toUpperCase(stringChars[0]);
		return new String(stringChars);
	}
	/**
	 * Creates random string.
	 * @return Random string.
	 */
	public static String randomString() {
		return Long.toHexString(DEFAULT_RANDOM_STRING_LENGTH);
	}
	/**
	 * Creates random string.
	 * @param pLength Random string length.
	 * @return Random string.
	 */
	public static String randomString(int pLength) {
		Random random = new Random(System.currentTimeMillis());
		StringBuffer str = new StringBuffer();
		do {
			long randomLong = random.nextLong();
			str.append(Long.toHexString(randomLong));
		} while (str.length() < pLength);
		return str.substring(0, pLength);
	}
	public static byte[] toBytes(char[] pChars) {
		CharBuffer charBuffer = CharBuffer.wrap(pChars);
		ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
		byte[] bytes = Arrays.copyOfRange(
				byteBuffer.array(),
				byteBuffer.position(), 
				byteBuffer.limit()
			);
		Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
		Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
		return bytes;
	}
	public static byte[] toBytes(String pValue) {
		return toBytes(pValue.toCharArray());
	}
	private StringHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}