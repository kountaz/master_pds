package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForNOTIFY;

public class FormatNOTIFY {
	
	

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

	public ObjectForNOTIFY inputFormatNOTIFY(String pMessage)
			throws DocumentException {
		// notify is the object return and will be contain tne co
		ObjectForNOTIFY notify = new ObjectForNOTIFY();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		notify.setId(Integer.parseInt(doc.selectSingleNode("//root/id")
				.getText()));
		notify.setNotify(doc.selectSingleNode("//root/notify")
				.getText());
		

		return notify;
	}

	public String outputFormatNOTIFY(ObjectForNOTIFY notify) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		root.addElement("id").addText(String.valueOf(notify.getId()));
		root.addElement("notify").addText(notify.getNotify());
		

		return doc.asXML();
	}
}
