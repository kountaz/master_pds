package org.openid.hs.qos.bean;

import java.io.Serializable;

/**
 * Stimulus is a mock who represents a part of the simulation framework
 * @version R2
 * @author Victor
 **/
public class Stimulus implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int bit_rate;
	private int delay;
	private int packet_loss_rate;
	private int jitter;
	
	public Stimulus(int bit_rate, int delay,int packet_loss_rate, int jitter) 
	{
		super();
		this.bit_rate = bit_rate;
		this.delay = delay;
		this.packet_loss_rate = packet_loss_rate;
		this.jitter = jitter;
	}
	
	public int getBit_rate() 
	{
		return bit_rate;
	}

	public void setBit_rate(int bit_rate) 
	{
		this.bit_rate = bit_rate;
	}

	public int getDelay() 
	{
		return delay;
	}

	public void setDelay(int delay) 
	{
		this.delay = delay;
	}

	public int getPacket_loss_rate() 
	{
		return packet_loss_rate;
	}

	public void setPacket_loss_rate(int packet_loss_rate) 
	{
		this.packet_loss_rate = packet_loss_rate;
	}

	public int getJitter() 
	{
		return jitter;
	}

	public void setJitter(int jitter) 
	{
		this.jitter = jitter;
	}
	
	@Override
	public String toString() {
		return "Stimulus [bit_rate=" + bit_rate + ", delay=" + delay
				+ ", packet_loss_rate=" + packet_loss_rate + ", jitter="
				+ jitter + "]";
	}
}