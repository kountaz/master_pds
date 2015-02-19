package org.openid.hs.wc.scenario;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openid.hs.communication.CommunicationAPI;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.scenario.ScenarioFactoryImpl;
import org.openid.hs.scenario.ScenarioOfBRAS;

public class ScenarioFactoryImplTest {
	ScenarioFactoryImpl scenario;
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
	public void testScenarioOfHSS(){
		
	}
	@Test
	public void testCreateScenario(){
		mock wc = Mockito.mock(mock.class);
		Mockito.when(wc.getId()).thenReturn("001");
		Mockito.when(wc.getType()).thenReturn("mme");
		//org.openid.hs.wc.WorkerComponent wc1 = new org.openid.hs.wc.WorkerComponent();
		scenario = new ScenarioFactoryImpl();
		scenario.createScenario(wc);
	}
	public void tearDown(){
		
	}
}
