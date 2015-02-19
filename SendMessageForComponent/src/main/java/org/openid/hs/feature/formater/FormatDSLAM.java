package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForDSLAM;

public class FormatDSLAM {

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

	public ObjectForDSLAM inputFormatDSLAM(String pMessage) throws DocumentException {
		// dslam is the object return and will be contain tne co
		ObjectForDSLAM dslam = new ObjectForDSLAM();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		dslam.setId(Integer.parseInt(doc.selectSingleNode("//root/id").getText()));
		//
		

		dslam.setDate_de_souscription(doc.selectSingleNode("//root/date_de_souscription")
				.getText());
		//
		dslam.setAdresse_ip_flottant(doc.selectSingleNode(
				"//root/adresse_ip_flottant").getText());
		//
		dslam.setAdresse_ip_fixe(doc.selectSingleNode("//root/numero_imsi")
				.getText());
		//
		dslam.setDebit_down_max(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_imei").getText()));
		//
		dslam.setDebit_up_max(Integer.parseInt(doc.selectSingleNode(
				"//root/type_de_contrat").getText()));
		
		return dslam;
	}

	public String outputFormatDSLAM(ObjectForDSLAM dslam) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");

		root.addElement("id").addText(String.valueOf(dslam.getId()));
		root.addElement("date_de_souscription").addText(dslam.getDate_de_souscription());
		root.addElement("adresse_mac").addText(
				String.valueOf(dslam.getAdresse_mac()));
		root.addElement("adresse_ip_flottant").addText(
				dslam.getAdresse_ip_flottant());
		root.addElement("adresse_ip_fixe").addText(
				String.valueOf(dslam.getAdresse_ip_fixe()));
		root.addElement("debit_down_max").addText(
				String.valueOf(dslam.getDebit_down_max()));
		root.addElement("debit_up_max").addText(
				String.valueOf(dslam.getDebit_up_max()));
		
		return doc.asXML();
	}
}
