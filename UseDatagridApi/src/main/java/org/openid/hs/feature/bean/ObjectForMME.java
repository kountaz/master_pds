package org.openid.hs.feature.bean;

import java.io.Serializable;

public class ObjectForMME implements  Serializable{
	private Integer id;
	private String date_de_souscription;
	private String type_evenement;
	private Integer numero_publique=0;
	private Integer numero_imsi;
	private Integer numero_imei;
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

	public String getType_evenement() {
		return type_evenement;
	}

	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}

	public Integer getNumero_imsi() {
		return numero_imsi;
	}

	public void setNumero_imsi(Integer numero_imsi) {
		this.numero_imsi = numero_imsi;
	}

	public Integer getNumero_imei() {
		return numero_imei;
	}

	public void setNumero_imei(Integer numero_imei) {
		this.numero_imei = numero_imei;
	}

	public ObjectForMME() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Integer getNumero_publique() {
		return numero_publique;
	}

	public void setNumero_publique(Integer numero_publique) {
		this.numero_publique = numero_publique;
	}

}
