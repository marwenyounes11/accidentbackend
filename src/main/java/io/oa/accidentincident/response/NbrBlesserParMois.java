package io.oa.accidentincident.response;


public class NbrBlesserParMois {
	
private String mois;
private Long nbrBlessureMineur;
private Long nbrBlessureDanger;

public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}


public Long getNbrBlessureMineur() {
	return nbrBlessureMineur;
}
public void setNbrBlessureMineur(Long nbrBlessureMineur) {
	this.nbrBlessureMineur = nbrBlessureMineur;
}


public Long getNbrBlessureDanger() {
	return nbrBlessureDanger;
}
public void setNbrBlessureDanger(Long nbrBlessureDanger) {
	this.nbrBlessureDanger = nbrBlessureDanger;
}
public NbrBlesserParMois() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrBlesserParMois(String mois, Long nbrBlessureMineur, Long nbrBlessureDanger) {
	super();
	this.mois = mois;
	this.nbrBlessureMineur = nbrBlessureMineur;
	this.nbrBlessureDanger = nbrBlessureDanger;
}











}
