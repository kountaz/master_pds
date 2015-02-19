package org.openid.hs.simulation;

public interface SimulationWebservice {
	public Receiver getReceiver();
	public int getPortReceiver();
	public void setPortReceiver(int pPortReceiver);
	public String getTarget();
	public void setTarget(String pTarget);
}
