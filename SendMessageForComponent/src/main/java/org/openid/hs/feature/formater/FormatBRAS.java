package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForBRAS;

public class FormatBRAS {

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

	public ObjectForBRAS inputFormatBRAS(String pMessage) throws DocumentException {
		// bras is the object return and will be contain tne co
		ObjectForBRAS bras = new ObjectForBRAS();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		bras.setId(Integer.parseInt(doc.selectSingleNode("//root/id").getText()));
		//
		

		bras.setDate_de_souscription(doc.selectSingleNode("//root/date_de_souscription")
				.getText());
		//
		bras.setAdresse_ip_flottant(doc.selectSingleNode(
				"//root/adresse_ip_flottant").getText());
		//
		bras.setAdresse_ip_fixe(doc.selectSingleNode("//root/adresse_ip_fixe")
				.getText());
		//
		bras.setDebit_down_max(Integer.parseInt(doc.selectSingleNode(
				"//root/debit_down_max").getText()));
		//
		bras.setDebit_up_max(Integer.parseInt(doc.selectSingleNode(
				"//root/debit_up_max").getText()));
		//
		bras.setLogin_reseau_operateur(doc.selectSingleNode(
				"//root/login_reseau_operateur").getText());
		//
		bras.setMdp_reseau_operateur(doc.selectSingleNode(
				"//root/mdp_reseau_operateur").getText());
		//
		bras.setLogin_public(doc.selectSingleNode("//root/login_public")
				.getText());
		//
	 	bras.setMdp_public(doc.selectSingleNode("//root/mdp_public").getText());

		return bras;
	}

	public String outputFormatBRAS(ObjectForBRAS bras) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");

		root.addElement("id").addText(String.valueOf(bras.getId()));
		root.addElement("date_de_souscription").addText(bras.getDate_de_souscription());
		root.addElement("adresse_mac").addText(
				String.valueOf(bras.getAdresse_mac()));
		root.addElement("adresse_ip_flottant").addText(
				bras.getAdresse_ip_flottant());
		root.addElement("adresse_ip_fixe").addText(
				String.valueOf(bras.getAdresse_ip_fixe()));
		root.addElement("debit_down_max").addText(
				String.valueOf(bras.getDebit_down_max()));
		root.addElement("debit_up_max").addText(
				String.valueOf(bras.getDebit_up_max()));
		root.addElement("login_reseau_operateur").addText(
				String.valueOf(bras.getLogin_reseau_operateur()));
		root.addElement("mdp_reseau_operateur").addText(
				String.valueOf(bras.getMdp_reseau_operateur()));
		root.addElement("login_public").addText(
				String.valueOf(bras.getLogin_public()));
		root.addElement("mdp_public").addText(
				String.valueOf(bras.getMdp_public()));
		return doc.asXML();
	}
}
