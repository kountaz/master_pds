package org.openid.hs.qos.measure.controller;

import java.util.ArrayList;
import java.util.TimerTask;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.IMeasureController;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.bean.Stimulus;
import org.openid.hs.qos.measure.dialog.MeasureHelper;
import org.openid.hs.qos.measure.dialog.MeasureProducer;
import org.openid.hs.wc.WorkerComponent;

public 	class MeasureController extends TimerTask implements IMeasureController 
{
	/**
	 * cpt is counter which represents the number of tasks will be launched in the demo
	 */
	private int cpt = 4;
	/**
	 * Datagrid will contains stimulus data
	 */
	private Datagrid grid;
	/**
	 * ArrayList<NodeRules> will contains the list of qos rules
	 */
	private ArrayList<NodeRules> lstRules;
	/**
	 * Store a reference of the worker component
	 */
	private WorkerComponent wc_instance;
	
	public MeasureController(Datagrid oneGrid, ArrayList<NodeRules> list, WorkerComponent wc)
	{
		this.wc_instance = wc;
		this.lstRules = new ArrayList<NodeRules>();
		this.lstRules = list;
		this.grid = oneGrid;
	}
	
	public void setGrid(Datagrid g)
	{
		this.grid = g;
	}
	
	public Datagrid getGrid()
	{
		return this.grid;
	}
	
	/**
	 * Retrieve stimulus data wich are in the stimulus grid
	 * @return void
	 */
	@Override
	public void doCalcul() 
	{
		try 
		{
			Stimulus one_stimulus = (Stimulus) grid.get(EnumParameter.G1);
			grid.remove(EnumParameter.G1);
			LoggerHelper.info(one_stimulus.toString());
			MeasureProducer.generateMessage(one_stimulus,wc_instance.getId());
			MeasureHelper.measureQos(lstRules, one_stimulus);
		} 
		catch (DatagridException e) 
		{
			LoggerHelper.error("Qos - retrieve Stimulus object from grid failed : ",e);
		}
	}

	@Override
	public void run() 
	{
		if(cpt > 0)
		{
			doCalcul();
			cpt--;
		}
		else
		{
			LoggerHelper.info("QoS - stop mock simulation");
			cancel();
		}
	}
}