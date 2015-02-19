package org.openid.hs.mock.stimulus.controller;

import java.util.ArrayList;
import java.util.TimerTask;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.bean.Stimulus;

public class StimulusController extends TimerTask
{
	private int cpt = 5;
	private Datagrid myGrid;
	private ArrayList<Stimulus> list;

	public StimulusController(Datagrid grid,ArrayList<Stimulus> lstStimulus)
	{
		list = new ArrayList<Stimulus>();
		list = lstStimulus;
		myGrid = grid;
	}

	/**
	 * Launch tasks with a regular period
	 * @return void
	 */
	@Override
	public void run() 
	{
		if(cpt > 0)
		{
			createRandom();
			cpt--;
		}
		else
		{
			LoggerHelper.info("Qos - stop measure");
			cancel();
		}		
	}
	/**
	 * Choose a random value which are contained in the list of stimulus
	 * @return void
	 */
	public void createRandom()
	{
		int indiceAuHasard = (int) (Math.random() * (list.size() - 1));
		myGrid.set(EnumParameter.G1, list.get(indiceAuHasard));
		LoggerHelper.info("Qos - stimulus has been successfully added");
	}
}