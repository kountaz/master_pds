package org.openid.hs.embeddedprocess;

/**
 * Embedded process factory.
 * @version R3
 * @author Steven, Victor
 *
 */
public class EmbeddedProcessFactory {
	/**
	 * Creates embedded process.
	 * @param pType Type of the new process.
	 * @param pPort Port of the new process.
	 * @return A new embedded process.
	 * @throws EmbeddedProcessException
	 */
	public static EmbeddedProcess createProcess(int pType, int pPort) throws EmbeddedProcessException {
		EmbeddedProcess process;
		switch (pType) {
		case MongoEmbeddedProcess.TYPE:
			process = new MongoEmbeddedProcess(pPort);
			break;
		case MemcachedEmbeddedProcess.TYPE:
			process = new MemcachedEmbeddedProcess(pPort);
			break;
		default:
			throw new IllegalArgumentException("You must use an existing value of EmbeddedProcess");
		}
		return process;
	}
	private EmbeddedProcessFactory() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
