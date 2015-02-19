package org.openid.hs.communication;

/**
 * Interface implemented by concrete communication classes. 
 * @version R2
 */
public interface Communication extends Producer, Consumer {
	/**
	 * Returns input queue name.
	 * @return input queue name.
	 */
	public String getInputQueueName();
	/**
	 * Returns output queue name.
	 * @return Output queue name.
	 */
	public String getOutputQueueName();
}
