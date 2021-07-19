package io.oa.accidentincident.response;


public class NbrBlesserDangerParMois {
	
private String mois;
private Long nbrBlessureDanger;


public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}

public Long getNbrBlessureDanger() {
	return nbrBlessureDanger;
}
public void setNbrBlessureDanger(Long nbrBlessureDanger) {
	this.nbrBlessureDanger = nbrBlessureDanger;
}

public NbrBlesserDangerParMois() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrBlesserDangerParMois(String mois, Long nbrBlessureDanger) {
	super();
	this.mois = mois;
	this.nbrBlessureDanger = nbrBlessureDanger;
}









}
