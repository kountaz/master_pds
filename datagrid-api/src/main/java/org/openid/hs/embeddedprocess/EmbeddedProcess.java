package org.openid.hs.embeddedprocess;

/**
 * Interface of embedded process.
 * @version R3
 * @author Steven, Victor
 *
 */
public interface EmbeddedProcess {
	/**
	 * Returns type of the embedded process.
	 * @return Type of the embedded process.
	 */
	public int getType();
	/**
	 * Returns port of the embedded process.
	 * @return Port of the embedded process.
	 */
	public int getPort();
	/**
	 * Initializes the embedded process.
	 * @param pPort Port of the embedded process.
	 * @throws EmbeddedProcessException
	 */
	public void initProcess() throws EmbeddedProcessException;
	/**
	 * Starts the embedded process.
	 * @throws EmbeddedProcessException
	 */
	public void start() throws EmbeddedProcessException;
	/**
	 * Stops the embedded process.
	 * @throws EmbeddedProcessException
	 */
	public void stop() throws EmbeddedProcessException;
}
