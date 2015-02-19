package org.openid.hs.core.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Steven, sp00m (http://stackoverflow.com/a/15519745)
 * 
 */
public final class ClassHelper {
	private final static char DOT = '.';
	private final static char SLASH = '/';
	private final static String CLASS_SUFFIX = ".class";
	private final static String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the given '%s' package exists?";

	public final static List<Class<?>> getClassesFromPackage(
			final String pScannedPackage) {
		return getClassesFromPackage(pScannedPackage, true);
	}

	public final static List<Class<?>> getClassesFromPackage(
			final String pScannedPackage, final boolean pSubpackagesScan) {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		final String scannedPath = pScannedPackage.replace(DOT, SLASH);
		final Enumeration<URL> resources;
		try {
			resources = classLoader.getResources(scannedPath);
		} catch (IOException e) {
			throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR,
					scannedPath, pScannedPackage), e);
		}
		final List<Class<?>> classes = new LinkedList<Class<?>>();
		while (resources.hasMoreElements()) {
			final File file = new File(resources.nextElement().getFile());
			classes.addAll(getClassesFromPackage(file, pScannedPackage,
					pSubpackagesScan));
		}
		return classes;
	}

	private final static List<Class<?>> getClassesFromPackage(final File file,
			final String resource, final boolean pSubpackagesScan) {
		final List<Class<?>> classes = new LinkedList<Class<?>>();
		if (file.isDirectory() && pSubpackagesScan) {
			for (File nestedFile : file.listFiles()) {
				classes.addAll(getClassesFromPackage(nestedFile, resource + DOT
						+ nestedFile.getName(), true));
			}
		} else if (resource.endsWith(CLASS_SUFFIX)) {
			final int beginIndex = 0;
			final int endIndex = resource.length() - CLASS_SUFFIX.length();
			final String className = resource.substring(beginIndex, endIndex);
			try {
				classes.add(Class.forName(className));
			} catch (ClassNotFoundException ignore) {

			}
		}
		return classes;
	}
}