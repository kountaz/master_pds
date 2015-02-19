package org.openid.hs.qos.rules.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openid.hs.communication.Communication;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.IRulesController;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.exception.RulesException;
import org.openid.hs.qos.rules.dialog.RulesHelper;

/**
 * RulesUtilities make transparent uses of RulesHelper class
 * @version R2
 * @author Victor
 **/
public class RulesController implements IRulesController
{
	private static RulesController singleton;
	public static void init(Datagrid g) {
		if (!isInit()) {
			singleton = new RulesController(g);
		}
	}
	public static boolean isInit() {
		return singleton != null;
	}
	public static RulesController get() {
		return singleton;
	}
	
	
	/**
	 * The grid instance where we will put qos rules.
	 */
	private Datagrid grid;
	
	private Map<String, Integer> actions;
	
	private RulesController(Datagrid g)
	{
		this.grid = g;
	}
	public Datagrid getGrid()
	{
		return grid;
	}
	
	/**
	 * Load data from file
	 * @param path of file
	 * @return void
	 * @throws RulesException 
	 */
	public void loadRulesFromFile(String path) throws RulesException 
	{
		ArrayList<NodeRules> rules = RulesHelper.getDataFromFile(path);
		grid.set("MME", rules);
		LoggerHelper.info("Save all nodes rules in the grid");
	}
	
	/**
	 * Load data from a message
	 * @param Communication in order to receive a message come from SI BO
	 * @return void
	 * @throws RulesException 
	 */
	public void loadRulesFromCommunication(Communication com) throws RulesException  
	{
		Message m;
		try 
		{
			m = com.receive();
			ArrayList<NodeRules> rules = RulesHelper.getDataFromMessage(m);
			this.grid.set(EnumParameter.MME, rules);
			LoggerHelper.info("Save all nodes rules in the grid");
		} 
		catch (CommunicationException | RulesException e) 
		{
			throw new RulesException(e);
		} 
	}
	/**
	 * Return list of qos rules which are contained in the grid
	 * @return ArrayList<NodeRules>
	 * @throws RulesException 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<NodeRules> getRules()
	{
		try 
		{
			return (ArrayList<NodeRules>) grid.get(EnumParameter.MME);
		} 
		catch (DatagridException e) 
		{
			LoggerHelper.info("Failed to retrieved list of rules in grid",e);
		}
		return null;
	}
}