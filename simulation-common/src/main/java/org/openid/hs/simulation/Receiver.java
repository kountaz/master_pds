package org.openid.hs.simulation;

public interface Receiver extends Runnable {
	public int getPort();
	public void setPort(int pPort);
	public String getTarget();
	public void setTarget(String pPort);
}
