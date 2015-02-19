package org.openid.hs.wc.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.core.exception.BuildingException;
import org.openid.hs.core.exception.InitializationException;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.scalability.ScalabilityAPI;

public class WCExecutor {
	private static WCExecutor singleton;
	public static WCExecutor get() {
		if (singleton == null) {
			singleton = new WCExecutor(ScalabilityAPI.get());
		}
		return singleton;
	}
	public static WCExecutor createFrom(String pXmlPath) throws InitializationException {
		try {
			String xml = ResourceHelper.readFrom(pXmlPath);
			WCExecutor executor = get();
			executor.parseNodes(xml);
			return executor;
		} catch (FileNotFoundException e) {
			throw new InitializationException(e);
		}
	}
	private ScalabilityAPI scalability;
	private WCExecutor(ScalabilityAPI pScalability) {
		scalability = pScalability;
	}
	public void startAll() {
		scalability.startAllNodes();
	}
	public void stopAll() {
		scalability.stopAllNodes();
	}
	private void parseNodes(String pXml) throws InitializationException {
		try {
			Document doc = DocumentHelper.parseText(pXml);
			Element root = doc.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				parseNode(element);
			}
		} catch (Exception e) {
			throw new InitializationException(e);
		}
	}
	private void parseNode(Element pNode) throws BuildingException {
		String name = pNode.attributeValue("name");
		Map<String, Object> params = new HashMap<String, Object>();
		for (Object o : pNode.elements()) {
			Element param = (Element) o;
			String key = param.attributeValue("name").trim();
			String value = param.getText().trim();
			params.put(key, value);
		}
		scalability.addNode(name, params);
	}
}
