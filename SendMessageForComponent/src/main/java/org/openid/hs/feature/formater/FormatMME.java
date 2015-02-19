package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForMME;

public class FormatMME {

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

	public ObjectForMME inputFormatMME(String pMessage) throws DocumentException {
		// mme is the object return and will be contain tne co
		ObjectForMME mme = new ObjectForMME();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		mme.setId(Integer.parseInt(doc.selectSingleNode("//root/id").getText()));
		//
		

		mme.setDate_de_souscription(doc.selectSingleNode("//root/date_de_souscription").getText());
		//
		mme.setType_evenement(doc.selectSingleNode("//root/type_evenement")
				.getText());
		//
		mme.setNumero_imsi(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_imsi").getText()));
		//
		mme.setNumero_imei(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_imei").getText()));
		//
		mme.setNumero_publique(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_publique").getText()));
		
		return mme;
	}
	

	public String outputFormatMME(ObjectForMME mme) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		root.addElement("id").addText(String.valueOf(mme.getId()));
		root.addElement("date_de_souscription").addText(
				String.valueOf(mme.getDate_de_souscription()));
		root.addElement("type_evenement").addText(mme.getType_evenement());
		root.addElement("numero_imsi").addText(
				String.valueOf(mme.getNumero_imsi()));
		root.addElement("numero_imei").addText(
				String.valueOf(mme.getNumero_imei()));
		root.addElement("numero_publique").addText(
				String.valueOf(mme.getNumero_publique()));
		return doc.asXML();
	}
}
