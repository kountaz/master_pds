package org.openid.hs.qos.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Rules bean
 * @version R2
 * @author Victor
 **/
public class Rules implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String unit;
	private ArrayList<ThresholdRules> lstThresholdRules;
	
	public Rules(String rName, String rUnit)
	{
		this.name = rName;
		this.unit = rUnit;
		lstThresholdRules = new ArrayList<ThresholdRules>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getUnit() 
	{
		return unit;
	}
	
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
	
	public ArrayList<ThresholdRules> getList()
	{
		return this.lstThresholdRules;
	}
}