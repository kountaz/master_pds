package org.openid.hs.feature.formater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openid.hs.feature.bean.ObjectForHSS;

public class FormatHSS {

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

	public ObjectForHSS inputFormatHSS(String pMessage) throws DocumentException {
		// hss is the object return and will be contain tne co
		ObjectForHSS hss = new ObjectForHSS();
		// doc contains the message
		Document doc = DocumentHelper.parseText(pMessage);
		//
		// Element item = (Element) doc.selectSingleNode("//root/id");

		hss.setId(Integer.parseInt(doc.selectSingleNode("//root/id").getText()));
		//
		
		hss.setDate_de_souscription(doc.selectSingleNode("//root/date_de_souscription")
				.getText());
		//
		hss.setType_evenement(doc.selectSingleNode("//root/type_evenement")
				.getText());
		//
		hss.setNumero_imsi(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_imsi").getText()));
		//
		hss.setNumero_imei(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_imei").getText()));
		//
		hss.setNumero_publique(Integer.parseInt(doc.selectSingleNode(
				"//root/numero_publique").getText()));
		//
		hss.setType_de_contrat(doc.selectSingleNode("//root/type_de_contrat")
				.getText());
		//
		hss.setType_de_forfait(doc.selectSingleNode("//root/type_de_forfait")
				.getText());
		//
		hss.setDuree_appel_autorisee(Integer.parseInt(doc.selectSingleNode(
				"//root/duree_appel_autorisee").getText()));
		//
		hss.setNombre_sms_autorise(Integer.parseInt(doc.selectSingleNode(
				"//root/nombre_sms_autorise").getText()));
		//
		hss.setNombre_mms_autorise(Integer.parseInt(doc.selectSingleNode(
				"//root/nombre_mms_autorise").getText()));
		//
		hss.setConsommation_internet_autorisee(Integer.parseInt(doc
				.selectSingleNode("//root/consommation_internet_autorisee")
				.getText()));
		//
		hss.setDebit_autorise(Integer.parseInt(doc.selectSingleNode(
				"//root/debit_autorise").getText()));
		return hss;
	}

	public String outputFormatHSS(ObjectForHSS hss) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		root.addElement("id").addText(String.valueOf(hss.getId()));
		root.addElement("date_de_souscription").addText(
				hss.getDate_de_souscription());
		root.addElement("type_evenement").addText(hss.getType_evenement());
		root.addElement("numero_imsi").addText(
				String.valueOf(hss.getNumero_imsi()));
		root.addElement("numero_imei").addText(
				String.valueOf(hss.getNumero_imei()));
		root.addElement("numero_publique").addText(
				String.valueOf(hss.getNumero_publique()));
		root.addElement("type_de_contrat").addText(hss.getType_de_contrat());
		root.addElement("type_de_forfait").addText(hss.getType_de_forfait());
		root.addElement("duree_appel_autorisee").addText(
				String.valueOf(hss.getDuree_appel_autorisee()));
		root.addElement("nombre_sms_autorise").addText(
				String.valueOf(hss.getNombre_sms_autorise()));
		root.addElement("nombre_mms_autorise").addText(
				String.valueOf(hss.getNombre_mms_autorise()));
		root.addElement("consommation_internet_autorisee").addText(
				String.valueOf(hss.getConsommation_internet_autorisee()));
		root.addElement("debit_autorise").addText(
				String.valueOf(hss.getDebit_autorise()));
		root.addElement("surf_sur_internet").addText(
				String.valueOf(hss.getSurf_sur_internet()));
		root.addElement("regarder_une_viddeo_sur_son_equipement").addText(
				String.valueOf(hss.getRegarder_une_video_sur_son_equipement()));
		root.addElement("emails_recus_envoyes_avec_piece_jointe")
				.addText(
						String.valueOf(hss
								.getEmails_recus_envoyes_avec_piece_jointe()));
		root.addElement("telecharger_une_application").addText(
				String.valueOf(hss.getTelecharger_une_application()));

		// Element root1 = doc.addElement("id");
		return doc.asXML();
	}
}
