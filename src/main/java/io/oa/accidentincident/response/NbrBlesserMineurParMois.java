package io.oa.accidentincident.response;


public class NbrBlesserMineurParMois {
	
private String mois;
private Long nbrBlessureMineur;


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
public NbrBlesserMineurParMois() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrBlesserMineurParMois(String mois, Long nbrBlessureMineur) {
	super();
	this.mois = mois;
	this.nbrBlessureMineur = nbrBlessureMineur;
}










}
