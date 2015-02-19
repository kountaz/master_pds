package org.openid.hs.replication.helper;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.wc.controller.SuperFormatter;

public class ReplicationFailSignal extends SuperFormatter {


	public static String builErrorMessage(String pMessage)	{
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("Fail");
		root.addElement("Replication").addText(String.valueOf(pMessage));
		root.addText(pMessage.toString());
		return doc.asXML();
	}
}
