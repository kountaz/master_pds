package org.openid.hs.wc.scenario;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.scenario.ScenarioOfMME;

public class ScenarioOfMMETest extends TestCase{
	ScenarioOfMME scenario;
	@Before
	public void setUp(){
		 MockitoAnnotations.initMocks(this);
		 try {
			CommunicationAPI commApi = CommunicationAPI.init(1230);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			DatagridAPI DatagridApi = DatagridAPI.init();
		} catch (DatagridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	@Test
	public void testScenarioOfMME(){
		
	}
	@Test
	public void testStart(){
		mock wc = Mockito.mock(mock.class);
		Mockito.when(wc.getId()).thenReturn("001");
		//org.openid.hs.wc.WorkerComponent wc1 = new org.openid.hs.wc.WorkerComponent();
		scenario = new ScenarioOfMME( wc);
		scenario.start();
	}
	public void tearDown(){
		
	}
}
