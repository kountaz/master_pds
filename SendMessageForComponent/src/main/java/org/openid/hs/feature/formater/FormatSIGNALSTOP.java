package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForSIGNALSTOP;

public class FormatSIGNALSTOP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd-MM-yy");
		Date date = null;
		try {
			date = df.parse("25-12-2010");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);

	}

	public ObjectForSIGNALSTOP inputFormatSIGNALSTOP(String pMessage)
			throws DocumentException {
		// stop is the object return and will be contain tne co
		ObjectForSIGNALSTOP stop = new ObjectForSIGNALSTOP();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		stop.setSignal(doc.selectSingleNode("//root/signal")
				.getText());
		
		

		return stop;
	}

	public String outputFormatSIGNALSTOP(ObjectForSIGNALSTOP stop) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		root.addElement("signal").addText(stop.getSignal());
		

		return doc.asXML();
	}
}
