package org.openid.hs.qos.rules.dialog;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.communication.Message;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.bean.Rules;
import org.openid.hs.qos.bean.ServicesRules;
import org.openid.hs.qos.bean.ThresholdRules;
import org.openid.hs.qos.exception.RulesException;

/**
 * RulesUtilities provide some methods in order to parse and extract data from xml configuration file
 * @version R2
 * @author Victor
 **/
public class RulesHelper
{
	private RulesHelper() 
	{ 

	}
	/**
	 * Retrieve data from xml file and create our beans
	 * @param xml path of file or content of the message we receive from SI BO
	 * @throws RulesException 
	 */
	public static ArrayList<NodeRules> getDataFromXml(String xml) throws RulesException
	{
		ArrayList<NodeRules> lstNodeRules = new ArrayList<NodeRules>();
		try 
		{
			Document document = DocumentHelper.parseText(xml);
			Element qos_rules = document.getRootElement();
			ArrayList<ServicesRules> lstServicesRules  = new ArrayList<ServicesRules>();
			for(Iterator i1 = qos_rules.elementIterator("nodes"); i1.hasNext();)
			{
				Element element_nodes = (Element) i1.next();
				for(Iterator i2 = element_nodes.elementIterator("type_node"); i2.hasNext();)
				{
					Element element_type_node = (Element) i2.next();
					NodeRules node_rules = new NodeRules();
					node_rules.setName(element_type_node.attributeValue("name"));			
					for(Iterator i3 = element_type_node.elementIterator("service"); i3.hasNext();)
					{
						Element element_service = (Element) i3.next();
						ServicesRules services_rules = new ServicesRules(element_service.attributeValue("name"));				
						for(Iterator i4 = element_service.elementIterator("rules"); i4.hasNext();)
						{
							Element element_rules = (Element) i4.next();
							Rules rules = new Rules(element_rules.attributeValue("name"), element_rules.attributeValue("unit"));
							ThresholdRules tr_low = new ThresholdRules(EnumParameter.LOW, Integer.parseInt(element_rules.elementText(EnumParameter.LOW)));
							rules.getList().add(tr_low);
							ThresholdRules tr_medium = new ThresholdRules(EnumParameter.MEDIUM, Integer.parseInt(element_rules.elementText(EnumParameter.MEDIUM)));
							rules.getList().add(tr_medium);
							ThresholdRules tr_warning = new ThresholdRules(EnumParameter.WARNING, Integer.parseInt(element_rules.elementText(EnumParameter.WARNING)));
							rules.getList().add(tr_warning);
							ThresholdRules tr_critical = new ThresholdRules(EnumParameter.CRITICAL, Integer.parseInt(element_rules.elementText(EnumParameter.CRITICAL)));
							rules.getList().add(tr_critical);
							services_rules.getLstRules().add(rules);
						}
						lstServicesRules.add(services_rules);
					}
					node_rules.setLstServicesRules(lstServicesRules);
					lstNodeRules.add(node_rules);
				}
			}
			LoggerHelper.info("Load XML configuration file for MME");
			return lstNodeRules;
		}
		catch (DocumentException e) 
		{
			throw new RulesException(e);
		} 
	}
	/**
	 * Load data from an XML file which its path is given in parameter.
	 * @param path : this is the xml file path
	 * @throws RulesException 
	 */
	public static ArrayList<NodeRules> getDataFromFile(String path) throws RulesException
	{
		if(path == null)
			throw new RulesException("File path can not be null");

		try 
		{
			String xml = ResourceHelper.readFrom(path);
			return getDataFromXml(xml);
		} 
		catch (FileNotFoundException e) 
		{
			throw new RulesException(e);
		}
	}

	/**
	 * Load data from a message
	 * @param Message come frome SI BO
	 * @throws RulesException 
	 */
	public static ArrayList<NodeRules> getDataFromMessage(Message msg) throws RulesException
	{
		return getDataFromXml(msg.getContent().toString());
	}
}