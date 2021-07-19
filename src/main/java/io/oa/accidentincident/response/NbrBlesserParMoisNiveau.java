package io.oa.accidentincident.response;


public class NbrBlesserParMoisNiveau {
	
private String mois;
private Long nbrBlesser;
private String niveauBlessure ;



public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}



public Long getNbrBlesser() {
	return nbrBlesser;
}
public void setNbrBlesser(Long nbrBlesser) {
	this.nbrBlesser = nbrBlesser;
}
public String getNiveauBlessure() {
	return niveauBlessure;
}
public void setNiveauBlessure(String niveauBlessure) {
	this.niveauBlessure = niveauBlessure;
}
public NbrBlesserParMoisNiveau() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrBlesserParMoisNiveau(String mois, Long nbrBlesser, String niveauBlessure) {
	super();
	this.mois = mois;
	this.nbrBlesser = nbrBlesser;
	this.niveauBlessure = niveauBlessure;
}



}
