package org.openid.hs.rt.support;
/**
 * 
 * @author KOUNTA
 * 
 * Interface of message support RT
 * 
 */
public interface RealTimeMessagingSupport {

	public long getSendingTime();
	public long getPendingTime();
	public long getConsumingTime();
	public void validateMessage();
	public String displayTime(long time);
	public boolean isReliableMessage();
	public void prepareRTMessaging(String pFilePath , String keyReferenceTime);
	public void prepareRTMessaging(String keyReferenceTime);
	public void messagePriority(int priority);
}
