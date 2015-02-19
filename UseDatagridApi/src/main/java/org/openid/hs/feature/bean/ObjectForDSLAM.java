package org.openid.hs.feature.bean;

import java.io.Serializable;

public class ObjectForDSLAM implements Serializable{

	private Integer id;
	private String date_de_souscription;
	private Integer debit_up_max;
	private Integer debit_down_max;
	private String adresse_mac;
	private String adresse_ip_flottant;
	private String adresse_ip_fixe;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate_de_souscription() {
		return date_de_souscription;
	}

	public void setDate_de_souscription(String date_de_souscription) {
		this.date_de_souscription = date_de_souscription;
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

	public ObjectForDSLAM() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Integer getDebit_down_max() {
		return debit_down_max;
	}

	public void setDebit_down_max(Integer debit_down_max) {
		this.debit_down_max = debit_down_max;
	}

}
