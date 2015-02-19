package org.openid.hs.qos.measure.controller;

import java.util.ArrayList;
import java.util.Timer;

import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.wc.WorkerComponent;

/**
 * TaskMeasure
 * @version R2
 * @author Victor
 **/
public class TaskMeasure 
{
	/**
	 * Timer for created all tasks measure
	 */
	private Timer t;
	/**
	 * Datagrid will contains stimulus data
	 */
	private Datagrid gridTraffic;
	/**
	 * ArrayList<NodeRules> will contains the list of qos rules
	 */
	private ArrayList<NodeRules> lstNodeRules;
	/**
	 * Store a reference of the worker component
	 */
	private WorkerComponent wc_instance;
	/**
	 * 
	 * Constructor for Task Measure
	 * @param A grid who represents the grid who contains stimulus data, list of qos rules
	 * @return void
	 */
	public TaskMeasure(Datagrid grid, ArrayList<NodeRules> lstRules, WorkerComponent wc)
	{
		gridTraffic = grid;
		lstNodeRules = lstRules;
		wc_instance = wc;
		t = new Timer();
		t.schedule(new MeasureController(gridTraffic,lstNodeRules, wc_instance), 0, 30*1000);
	}
	
	/**
	 * This method provid a mecanism in order to stop all task measure before stopping the component
	 * @return void
	 */
	public void cancel()
	{
		t.cancel();
	}
}