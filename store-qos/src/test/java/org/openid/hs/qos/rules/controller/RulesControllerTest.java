package org.openid.hs.qos.rules.controller;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openid.hs.communication.Communication;
import org.openid.hs.communication.Message;
import org.openid.hs.communication.controller.MessageFactory;
import org.openid.hs.communication.exception.CommunicationException;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.datagrid.DatagridAPI;
import org.openid.hs.datagrid.exception.DatagridException;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.exception.RulesException;

public class RulesControllerTest
{
	private static final String FILE = "/rules_qos_test.xml";
	private DatagridAPI api;
	private Datagrid mockGrid;
	
	@Before
	public void initGrid() throws DatagridException
	{
		api = DatagridAPI.init();
		mockGrid = Mockito.mock(Datagrid.class);
		RulesController.init(mockGrid);
	}
	
	@Test
	public void loadRulesFromFileTest()throws RulesException, DatagridException 
	{
		RulesController rulesBean = RulesController.get();
		rulesBean.loadRulesFromFile(FILE);
		Mockito.verify(mockGrid,Mockito.times(1)).set(Mockito.matches(EnumParameter.MME),Mockito.anyCollectionOf(NodeRules.class));
	}
	
	@Test
	public void loadRulesFromCommunicationTest() throws RulesException, CommunicationException, FileNotFoundException, DatagridException 
	{
		String input_file = ResourceHelper.readFrom(FILE);
		Message returnMessage = MessageFactory.create("test");
		returnMessage.setContent(input_file);
		Communication mockCommunication = Mockito.mock(Communication.class);
		Mockito.when(mockCommunication.receive()).thenReturn(returnMessage);
		Datagrid mockGrid = Mockito.mock(Datagrid.class);
		RulesController rulesBean = RulesController.get();
		rulesBean.loadRulesFromCommunication(mockCommunication);
		Mockito.verify(mockCommunication,Mockito.times(1)).receive();
		Mockito.verify(mockGrid,Mockito.times(1)).set(Mockito.matches(EnumParameter.MME),Mockito.anyCollectionOf(NodeRules.class));
	}
	
	@Test
	public void getRulesTest()throws RulesException, DatagridException 
	{
		Datagrid mockDataGrid = Mockito.mock(Datagrid.class);
		RulesController rulesBean = RulesController.get();
		rulesBean.loadRulesFromFile(FILE);
		rulesBean.getRules();
		Mockito.verify(mockDataGrid,Mockito.times(1)).get(Mockito.matches(EnumParameter.MME));
	}
	
	@After
	public void tearDown() throws DatagridException
	{
		DatagridAPI.destroy();
	}
}