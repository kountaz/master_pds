package org.openid.hs.communication;

import org.openid.hs.communication.exception.CommunicationException;

/**
 * Interface of communication factory.
 * @version R2
 * @author Steven
 *
 */
public interface CommunicationFactory {
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pCommunicationFormatter A communication formatter.
	 * @return A new concrete communication interface on default queue names and without message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pCommunicationFormatterClass A class of communication formatter.
	 * @return A new concrete communication interface on default queue names and without message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(Class<? extends CommunicationFormatter> pCommunicationFormatterClass) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pMessageSelector A message selector to filter messages received.
	 * @param pCommunicationFormatter A communication formatter.
	 * @return A new concrete communication interface on default queue names and with message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pMessageSelector, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pMessageSelector A message selector to filter messages received.
	 * @param pCommunicationFormatterClass A class of communication formatter.
	 * @return A new concrete communication interface on default queue names and with message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pOutputQueueName An output queue name. 
	 * @param pInputQueueName An input queue name. 
	 * @param pCommunicationFormatter A communication formatter.
	 * @return A new concrete communication interface without message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pOutputQueueName An output queue name. 
	 * @param pInputQueueName An input queue name. 
	 * @param pCommunicationFormatterClass A class of communication formatter.
	 * @return A new concrete communication interface without message selector.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, Class<? extends CommunicationFormatter> pCommunicationFormatterClass) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pOutputQueueName An output queue name. 
	 * @param pInputQueueName An input queue name.
	 * @param pMessageSelector A message selector to filter messages received.
	 * @param pCommunicationFormatter A communication formatter.
	 * @return A new concrete communication interface.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, CommunicationFormatter pCommunicationFormatter) 
			throws CommunicationException;
	/**
	 * Creates and returns a concrete communication interface.
	 * @param pOutputQueueName An output queue name. 
	 * @param pInputQueueName An input queue name.
	 * @param pMessageSelector A message selector to filter messages received.
	 * @param pCommunicationFormatterClass A class of communication formatter.
	 * @return A new concrete communication interface.
	 * @throws CommunicationException
	 */
	public Communication createCommunication(String pOutputQueueName, String pInputQueueName, String pMessageSelector, Class<? extends CommunicationFormatter> pCommunicationFormatterClass) 
			throws CommunicationException;
}
