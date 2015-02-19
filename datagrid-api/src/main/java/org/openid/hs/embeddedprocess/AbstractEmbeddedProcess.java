package org.openid.hs.embeddedprocess;

/**
 * Abstract implementation of embedded process interface.
 * @see EmbeddedProcess
 * @version R3
 * @author Steven, Victor
 *
 */
public abstract class AbstractEmbeddedProcess implements EmbeddedProcess {
	/**
	 * Parameter which used to activate silent logging.
	 */
	public static boolean SILENT_LOGGING = false;

	/**
	 * Type of the embedded process.
	 */
	private int type;
	/**
	 * Port of the embedded process.
	 */
	private int port;
	
	public AbstractEmbeddedProcess(int pType, int pPort) throws EmbeddedProcessException {
		type = pType;
		port = pPort;
		initProcess();
	}
	@Override
	public int getType() {
		return type;
	}
	@Override
	public int getPort() {
		return port;
	}
}
