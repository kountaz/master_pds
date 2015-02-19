package org.openid.hs.feature.bean;

import java.io.Serializable;


public class ObjectForBRAS implements  Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id=0;
	private String date_de_souscription="00-00-00";
	private String type_evenement="a";
	private Integer debit_down_max=0;
	private Integer debit_up_max=0;
	private String adresse_mac="7E:FB:56:A2:AF:89";
	private String adresse_ip_flottant="127.0.0.1";
	private String adresse_ip_fixe="127.0.0.1";
	private String login_reseau_operateur="0000";
	private String mdp_reseau_operateur="0000";
	private String login_public="0000";
	private String mdp_public="0000";
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDebit_down_max() {
		return debit_down_max;
	}

	public void setDebit_down_max(Integer debit_down_max) {
		this.debit_down_max = debit_down_max;
	}

	public Integer getDebit_up_max() {
		return debit_up_max;
	}

	public void setDebit_up_max(Integer debit_up_max) {
		this.debit_up_max = debit_up_max;
	}

	public String getAdresse_mac() {
		return adresse_mac;
	}

	public void setAdresse_mac(String adresse_mac) {
		this.adresse_mac = adresse_mac;
	}

	public String getAdresse_ip_flottant() {
		return adresse_ip_flottant;
	}

	public void setAdresse_ip_flottant(String adresse_ip_flottant) {
		this.adresse_ip_flottant = adresse_ip_flottant;
	}

	public String getAdresse_ip_fixe() {
		return adresse_ip_fixe;
	}

	public void setAdresse_ip_fixe(String adresse_ip_fixe) {
		this.adresse_ip_fixe = adresse_ip_fixe;
	}

	public String getLogin_reseau_operateur() {
		return login_reseau_operateur;
	}

	public void setLogin_reseau_operateur(String login_reseau_operateur) {
		this.login_reseau_operateur = login_reseau_operateur;
	}

	public String getMdp_reseau_operateur() {
		return mdp_reseau_operateur;
	}

	public void setMdp_reseau_operateur(String mdp_reseau_operateur) {
		this.mdp_reseau_operateur = mdp_reseau_operateur;
	}

	public String getLogin_public() {
		return login_public;
	}

	public void setLogin_public(String login_public) {
		this.login_public = login_public;
	}

	public String getMdp_public() {
		return mdp_public;
	}

	public void setMdp_public(String mdp_public) {
		this.mdp_public = mdp_public;
	}

	public ObjectForBRAS() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//  TODO Auto-generated method stub

	}

	public String getDate_de_souscription() {
		return date_de_souscription;
	}

	public void setDate_de_souscription(String date_de_souscription) {
		this.date_de_souscription = date_de_souscription;
	}

	public String getType_evenement() {
		return type_evenement;
	}

	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}

}
