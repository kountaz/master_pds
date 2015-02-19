package org.openid.hs.core.helper;

import java.util.Scanner;

/**
 * Utilities for system.
 * @version R2
 * @author Seybany, Steven
 *
 */
public final class SystemHelper {
	public static void pause() {
		System.out.println("Press Any Key To Continue...");
		Scanner scanner = new java.util.Scanner(System.in);
		scanner.nextLine();
		scanner.close();
	}
	private SystemHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}