package org.openid.hs.qos.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * NodeRules is the root node for list of rules
 * @version R2
 * @author Victor
 **/
public class NodeRules implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<ServicesRules> lstServiceRules;
	
	public NodeRules()
	{
		
	}
	
	public NodeRules(String nName)
	{
		this.name = nName;
		this.lstServiceRules = new ArrayList<ServicesRules>();
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public ArrayList<ServicesRules> getLstServiceRules() 
	{
		return this.lstServiceRules;
	}
	
	public void setLstServicesRules(ArrayList<ServicesRules> lstSR) 
	{
		this.lstServiceRules = lstSR;
	}
}