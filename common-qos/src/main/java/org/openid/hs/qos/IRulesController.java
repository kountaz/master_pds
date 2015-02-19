package org.openid.hs.qos;

import java.util.ArrayList;

import org.openid.hs.communication.Communication;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.exception.RulesException;

/**
 * IRulesController provide methods in order to retrieve data from xml configuration file or from message (JMS)
 * @version R2
 * @author Victor
 **/
public interface IRulesController 
{
	public void loadRulesFromFile(String path) throws RulesException;
	public void loadRulesFromCommunication(Communication com) throws RulesException;
	public ArrayList<NodeRules> getRules() throws RulesException;
}
