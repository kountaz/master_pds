package org.openid.hs.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Utitlies for logger.
 * @version R2
 * @author Steven
 *
 */
public class LoggerHelper {
	private static Logger LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	public static Logger getLogger() {
		return LOGGER;
	}
	public static void debug(String arg0) {
		LOGGER.debug(arg0);
	}
	public static void debug(String arg0, Object arg1) {
		LOGGER.debug(arg0, arg1);
	}
	public static void debug(String arg0, Object[] arg1) {
		LOGGER.debug(arg0, arg1);	
	}
	public static void debug(String arg0, Throwable arg1) {
		LOGGER.debug(arg0, arg1);
	}
	public static void debug(Marker arg0, String arg1) {
		LOGGER.debug(arg0, arg1);
	}
	public static void debug(String arg0, Object arg1, Object arg2) {
		LOGGER.debug(arg0, arg1, arg2);
	}
	public static void debug(Marker arg0, String arg1, Object arg2) {
		LOGGER.debug(arg0, arg1, arg2);
	}
	public static void debug(Marker arg0, String arg1, Object[] arg2) {
		LOGGER.debug(arg0, arg1, arg2);
	}
	public static void debug(Marker arg0, String arg1, Throwable arg2) {
		LOGGER.debug(arg0, arg1, arg2);
	}
	public static void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		LOGGER.debug(arg0, arg1, arg2, arg3);
	}
	public static void error(String arg0) {
		LOGGER.error(arg0);
	}
	public static void error(String arg0, Object arg1) {
		LOGGER.error(arg0, arg1);
	}
	public static void error(String arg0, Object[] arg1) {
		LOGGER.error(arg0, arg1);	
	}
	public static void error(String arg0, Throwable arg1) {
		LOGGER.error(arg0, arg1);
	}
	public static void error(Marker arg0, String arg1) {
		LOGGER.error(arg0, arg1);
	}
	public static void error(String arg0, Object arg1, Object arg2) {
		LOGGER.error(arg0, arg1, arg2);
	}
	public static void error(Marker arg0, String arg1, Object arg2) {
		LOGGER.error(arg0, arg1, arg2);
	}
	public static void error(Marker arg0, String arg1, Object[] arg2) {
		LOGGER.error(arg0, arg1, arg2);
	}
	public static void error(Marker arg0, String arg1, Throwable arg2) {
		LOGGER.error(arg0, arg1, arg2);
	}
	public static void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		LOGGER.error(arg0, arg1, arg2, arg3);
	}
	public static String getName() {
		return LOGGER.getName();
	}
	public static void info(String arg0) {
		LOGGER.info(arg0);
	}
	public static void info(String arg0, Object arg1) {
		LOGGER.info(arg0, arg1);
	}
	public static void info(String arg0, Object[] arg1) {
		LOGGER.info(arg0, arg1);	
	}
	public static void info(String arg0, Throwable arg1) {
		LOGGER.info(arg0, arg1);
	}
	public static void info(Marker arg0, String arg1) {
		LOGGER.info(arg0, arg1);
	}
	public static void info(String arg0, Object arg1, Object arg2) {
		LOGGER.info(arg0, arg1, arg2);
	}
	public static void info(Marker arg0, String arg1, Object arg2) {
		LOGGER.info(arg0, arg1, arg2);
	}
	public static void info(Marker arg0, String arg1, Object[] arg2) {
		LOGGER.info(arg0, arg1, arg2);
	}
	public static void info(Marker arg0, String arg1, Throwable arg2) {
		LOGGER.info(arg0, arg1, arg2);
	}
	public static void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		LOGGER.info(arg0, arg1, arg2, arg3);
	}
	public static boolean isDebugEnabled() {
		return LOGGER.isDebugEnabled();
	}
	public static boolean isDebugEnabled(Marker arg0) {
		return LOGGER.isDebugEnabled(arg0);
	}
	public static boolean isErrorEnabled() {
		return LOGGER.isErrorEnabled();
	}
	public static boolean isErrorEnabled(Marker arg0) {
		return LOGGER.isErrorEnabled(arg0);
	}
	public static boolean isInfoEnabled() {
		return LOGGER.isInfoEnabled();
	}
	public static boolean isInfoEnabled(Marker arg0) {
		return LOGGER.isInfoEnabled(arg0);
	}
	public static boolean isTraceEnabled() {
		return LOGGER.isTraceEnabled();
	}
	public static boolean isTraceEnabled(Marker arg0) {
		return LOGGER.isTraceEnabled(arg0);
	}
	public static boolean isWarnEnabled() {
		return LOGGER.isWarnEnabled();
	}
	public static boolean isWarnEnabled(Marker arg0) {
		return LOGGER.isWarnEnabled(arg0);
	}
	public static void trace(String arg0) {
		LOGGER.trace(arg0);
	}
	public static void trace(String arg0, Object arg1) {
		LOGGER.trace(arg0, arg1);
	}
	public static void trace(String arg0, Object[] arg1) {
		LOGGER.trace(arg0, arg1);	
	}
	public static void trace(String arg0, Throwable arg1) {
		LOGGER.trace(arg0, arg1);
	}
	public static void trace(Marker arg0, String arg1) {
		LOGGER.trace(arg0, arg1);
	}
	public static void trace(String arg0, Object arg1, Object arg2) {
		LOGGER.trace(arg0, arg1, arg2);
	}
	public static void trace(Marker arg0, String arg1, Object arg2) {
		LOGGER.trace(arg0, arg1, arg2);
	}
	public static void trace(Marker arg0, String arg1, Object[] arg2) {
		LOGGER.trace(arg0, arg1, arg2);
	}
	public static void trace(Marker arg0, String arg1, Throwable arg2) {
		LOGGER.trace(arg0, arg1, arg2);
	}
	public static void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		LOGGER.trace(arg0, arg1, arg2, arg3);
	}
	public static void warn(String arg0) {
		LOGGER.warn(arg0);
	}
	public static void warn(String arg0, Object arg1) {
		LOGGER.warn(arg0, arg1);
	}
	public static void warn(String arg0, Object[] arg1) {
		LOGGER.warn(arg0, arg1);	
	}
	public static void warn(String arg0, Throwable arg1) {
		LOGGER.warn(arg0, arg1);
	}
	public static void warn(Marker arg0, String arg1) {
		LOGGER.warn(arg0, arg1);
	}
	public static void warn(String arg0, Object arg1, Object arg2) {
		LOGGER.warn(arg0, arg1, arg2);
	}
	public static void warn(Marker arg0, String arg1, Object arg2) {
		LOGGER.warn(arg0, arg1, arg2);
	}
	public static void warn(Marker arg0, String arg1, Object[] arg2) {
		LOGGER.warn(arg0, arg1, arg2);
	}
	public static void warn(Marker arg0, String arg1, Throwable arg2) {
		LOGGER.warn(arg0, arg1, arg2);
	}
	public static void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		LOGGER.warn(arg0, arg1, arg2, arg3);
	}
	private LoggerHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}