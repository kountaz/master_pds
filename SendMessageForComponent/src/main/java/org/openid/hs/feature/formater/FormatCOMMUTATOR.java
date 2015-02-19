package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForCOMMUTATOR;

public class FormatCOMMUTATOR {

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

	public ObjectForCOMMUTATOR inputFormatCOMMUTATOR(String pMessage)
			throws DocumentException {
		// commutator is the object return and will be contain tne co
		ObjectForCOMMUTATOR commutator = new ObjectForCOMMUTATOR();
		//doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		commutator.setId(Integer.parseInt(doc.selectSingleNode("//root/id")
			.getText()));
		//

		commutator.setDate_de_souscription(doc.selectSingleNode(
				"//root/date_de_souscription").getText());
		

		return commutator;
	}

	public String outputFormatCOMMUTATOR(ObjectForCOMMUTATOR commutator) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		root.addElement("id").addText(String.valueOf(commutator.getId()));
		root.addElement("date_de_souscription").addText(
				commutator.getDate_de_souscription());
		root.addElement("numero_fixe").addText(
				String.valueOf(commutator.getNumero_fixe()));

		return doc.asXML();
	}
}
