package org.openid.hs.feature.bean;

import java.io.Serializable;


public class ObjectForCOMMUTATOR implements  Serializable{

	private Integer id=0;
	private String type_evenement="a";
	private String date_de_souscription="00-00-00";
	private Integer numero_fixe=0;
	
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

	public Integer getNumero_fixe() {
		return numero_fixe;
	}

	public void setNumero_fixe(Integer numero_fixe) {
		this.numero_fixe = numero_fixe;
	}

	public ObjectForCOMMUTATOR() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getType_evenement() {
		return type_evenement;
	}

	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}

}
