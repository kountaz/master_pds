package org.openid.hs.qos.bean;

import java.io.Serializable;

/**
 * ThresholdRules bean
 * @version R2
 * @author Victor
 **/
public class ThresholdRules implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String name;
	private int value;
	
	public ThresholdRules()
	{
		
	}
	
	public ThresholdRules(String tName, int tValue)
	{
		this.name = tName;
		this.value = tValue;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
	}
}