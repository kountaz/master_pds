package org.openid.hs.qos.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ServiceRules bean
 * @version R2
 * @author Victor
 **/
public class ServicesRules implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Rules> lstRules;
	
	public ServicesRules()
	{
		
	}
	
	public ServicesRules(String sName)
	{
		this.name = sName;
		this.lstRules = new ArrayList<Rules>();
	}

	public ArrayList<Rules> getLstRules()
	{
		return this.lstRules;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}