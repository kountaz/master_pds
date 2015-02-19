package org.openid.hs.bootstrap;

/**
 * Enumeration of exists command.
 * @version R2
 * @author Fanta, Meryem, Mouhamadou, Steven
 *
 */
public interface Command {
	/**
	 * Value for start command.
	 */
	public static final int START = 1;
	/**
	 * Value for stop command.
	 */
	public static final int STOP = 0;
	/**
	 * Value for status command.
	 */
	public static final int STATUS = 2;
}
