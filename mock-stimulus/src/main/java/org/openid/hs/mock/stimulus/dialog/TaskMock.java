package org.openid.hs.mock.stimulus.dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Timer;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.mock.stimulus.controller.StimulusController;
import org.openid.hs.qos.bean.Stimulus;

public class TaskMock 
{
	/**
	 * Timer for created all tasks measure
	 */
	private Timer t;
	/**
	 * Datagrid who contains stimulus data
	 */
	private Datagrid grid;
	/**
	 * ArrayList<Stimulus> who contains the list of stimulus object
	 */
	private ArrayList<Stimulus> lstStimulus;
	/**
	 * Path for test set properties file
	 */
	private static final String pathFile = "/call.properties";
	/**
	 * Value of split value who will be use for split data come from properties file
	 */
	private static final String split_value = ";";

	/**
	 * Constructor for TaskMock
	 * @param A grid who represents the grid who contains stimulus data, list of qos rules
	 * @return void
	 */
	public TaskMock(Datagrid g)
	{
		lstStimulus  = new ArrayList<Stimulus>();
		grid = g;
		loadMockCall();
		t = new Timer();
		t.schedule(new StimulusController(grid,lstStimulus), 0, 20*1000);
	}

	/**
	 * Load test set which are contains in properties file
	 * @return void
	 */
	public void loadMockCall()
	{
		try 
		{
			Properties prop = ResourceHelper.getProperties(pathFile);
			Iterator<?> it = prop.keySet().iterator();
			String[] tabSplit;
			while(it.hasNext())
			{
				String key = (String) it.next();
				String valeur = null;
				if	(!key.equals("pattern"))	
				{
					valeur = prop.getProperty(key);
					tabSplit = valeur.split(split_value);
					Stimulus mock_call = new Stimulus(Integer.parseInt(tabSplit[0]), Integer.parseInt(tabSplit[1]), Integer.parseInt(tabSplit[2]), Integer.parseInt(tabSplit[3]));
					lstStimulus.add(mock_call);
				}
			}
		} 
		catch (IOException e) 
		{
			LoggerHelper.error(String.format("Qos - file %s was not found", pathFile), e);
		}	
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