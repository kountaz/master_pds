package org.openid.hs.feature.bean;

import java.io.Serializable;

public class ObjectForHSS implements Serializable {


	private Integer id= 0;
	private String date_de_souscription = "00-00-00";
	private String type_evenement ="a";
	
	private Integer numero_imsi =0;
	private Integer numero_imei= 0;
	private Integer numero_publique= 0;
	private String  type_de_contrat= "n";
	private String type_de_forfait="b";
	
	private Integer duree_appel_autorisee= 0;
	private Integer nombre_sms_autorise= 0;
	private Integer nombre_mms_autorise=0;
	
	private Integer consommation_internet_autorisee=0;
	private Integer debit_autorise= 0;
	private Integer surf_sur_internet= 0;
	private Integer regarder_une_video_sur_son_equipement= 0;
	private Integer emails_recus_envoyes_avec_piece_jointe= 0;
	private Integer telecharger_une_application= 0;
	private String periodicite="m";
	
	private Integer temps_appel_consomme= 0;
	private Integer nombre_sms_consomme= 0;
	private  Integer nombre_mms_consomme= 0;
	private Integer consommation_internet_consomme= 0;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the date_de_souscription
	 */
	public String getDate_de_souscription() {
		return date_de_souscription;
	}
	/**
	 * @param date_de_souscription the date_de_souscription to set
	 */
	public void setDate_de_souscription(String date_de_souscription) {
		this.date_de_souscription = date_de_souscription;
	}
	/**
	 * @return the type_evenement
	 */
	public String getType_evenement() {
		return type_evenement;
	}
	/**
	 * @param type_evenement the type_evenement to set
	 */
	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}
	/**
	 * @return the numero_imsi
	 */
	public Integer getNumero_imsi() {
		return numero_imsi;
	}
	/**
	 * @param numero_imsi the numero_imsi to set
	 */
	public void setNumero_imsi(Integer numero_imsi) {
		this.numero_imsi = numero_imsi;
	}
	/**
	 * @return the numero_imei
	 */
	public Integer getNumero_imei() {
		return numero_imei;
	}
	/**
	 * @param numero_imei the numero_imei to set
	 */
	public void setNumero_imei(Integer numero_imei) {
		this.numero_imei = numero_imei;
	}
	/**
	 * @return the numero_publique
	 */
	public Integer getNumero_publique() {
		return numero_publique;
	}
	/**
	 * @param numero_publique the numero_publique to set
	 */
	public void setNumero_publique(Integer numero_publique) {
		this.numero_publique = numero_publique;
	}
	/**
	 * @return the type_de_contrat
	 */
	public String getType_de_contrat() {
		return type_de_contrat;
	}
	/**
	 * @param type_de_contrat the type_de_contrat to set
	 */
	public void setType_de_contrat(String type_de_contrat) {
		this.type_de_contrat = type_de_contrat;
	}
	/**
	 * @return the type_de_forfait
	 */
	public String getType_de_forfait() {
		return type_de_forfait;
	}
	/**
	 * @param type_de_forfait the type_de_forfait to set
	 */
	public void setType_de_forfait(String type_de_forfait) {
		this.type_de_forfait = type_de_forfait;
	}
	/**
	 * @return the duree_appel_autorisee
	 */
	public Integer getDuree_appel_autorisee() {
		return duree_appel_autorisee;
	}
	/**
	 * @param duree_appel_autorisee the duree_appel_autorisee to set
	 */
	public void setDuree_appel_autorisee(Integer duree_appel_autorisee) {
		this.duree_appel_autorisee = duree_appel_autorisee;
	}
	/**
	 * @return the nombre_sms_autorisee
	 */
	
	
	/**
	 * @return the consommation_internet_autorisee
	 */
	public Integer getConsommation_internet_autorisee() {
		return consommation_internet_autorisee;
	}
	/**
	 * @param consommation_internet_autorisee the consommation_internet_autorisee to set
	 */
	public void setConsommation_internet_autorisee(
			Integer consommation_internet_autorisee) {
		this.consommation_internet_autorisee = consommation_internet_autorisee;
	}
	/**
	 * @return the debit_autorise
	 */
	public Integer getDebit_autorise() {
		return debit_autorise;
	}
	/**
	 * @param debit_autorise the debit_autorise to set
	 */
	public void setDebit_autorise(Integer debit_autorise) {
		this.debit_autorise = debit_autorise;
	}
	/**
	 * @return the surf_sur_internet
	 */
	public Integer getSurf_sur_internet() {
		return surf_sur_internet;
	}
	/**
	 * @param surf_sur_internet the surf_sur_internet to set
	 */
	public void setSurf_sur_internet(Integer surf_sur_internet) {
		this.surf_sur_internet = surf_sur_internet;
	}
	/**
	 * @return the regarder_une_video_sur_son_equipement
	 */
	public Integer getRegarder_une_video_sur_son_equipement() {
		return regarder_une_video_sur_son_equipement;
	}
	/**
	 * @param regarder_une_video_sur_son_equipement the regarder_une_video_sur_son_equipement to set
	 */
	public void setRegarder_une_video_sur_son_equipement(
			Integer regarder_une_video_sur_son_equipement) {
		this.regarder_une_video_sur_son_equipement = regarder_une_video_sur_son_equipement;
	}
	/**
	 * @return the emails_recus_envoyes_avec_piece_jointe
	 */
	public Integer getEmails_recus_envoyes_avec_piece_jointe() {
		return emails_recus_envoyes_avec_piece_jointe;
	}
	/**
	 * @param emails_recus_envoyes_avec_piece_jointe the emails_recus_envoyes_avec_piece_jointe to set
	 */
	public void setEmails_recus_envoyes_avec_piece_jointe(
			Integer emails_recus_envoyes_avec_piece_jointe) {
		this.emails_recus_envoyes_avec_piece_jointe = emails_recus_envoyes_avec_piece_jointe;
	}
	/**
	 * @return the telecharger_une_application
	 */
	public Integer getTelecharger_une_application() {
		return telecharger_une_application;
	}
	/**
	 * @param telecharger_une_application the telecharger_une_application to set
	 */
	public void setTelecharger_une_application(Integer telecharger_une_application) {
		this.telecharger_une_application = telecharger_une_application;
	}
	public Integer getNombre_sms_autorise() {
		return nombre_sms_autorise;
	}
	public void setNombre_sms_autorise(Integer nombre_sms_autorise) {
		this.nombre_sms_autorise = nombre_sms_autorise;
	}
	public Integer getNombre_mms_autorise() {
		return nombre_mms_autorise;
	}
	public void setNombre_mms_autorise(Integer nombre_mms_autorise) {
		this.nombre_mms_autorise = nombre_mms_autorise;
	}
	public Integer getTemps_appel_consomme() {
		return temps_appel_consomme;
	}
	public void setTemps_appel_consomme(Integer temps_appel_consomme) {
		this.temps_appel_consomme = temps_appel_consomme;
	}
	public Integer getNombre_sms_consomme() {
		return nombre_sms_consomme;
	}
	public void setNombre_sms_consomme(Integer nombre_sms_consomme) {
		this.nombre_sms_consomme = nombre_sms_consomme;
	}
	public Integer getNombre_mms_consomme() {
		return nombre_mms_consomme;
	}
	public void setNombre_mms_consomme(Integer nombre_mms_consomme) {
		this.nombre_mms_consomme = nombre_mms_consomme;
	}
	public Integer getConsommation_internet_consomme() {
		return consommation_internet_consomme;
	}
	public void setConsommation_internet_consomme(
			Integer consommation_internet_consomme) {
		this.consommation_internet_consomme = consommation_internet_consomme;
	}
	public String getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	
}
