package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrMortsParMois {
	
private String mois;
private Long nbrMorts;

public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}
public Long getNbrMorts() {
	return nbrMorts;
}
public void setNbrMorts(Long nbrMorts) {
	this.nbrMorts = nbrMorts;
}
public NbrMortsParMois() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrMortsParMois(String mois, Long nbrMorts) {
	super();
	this.mois = mois;
	this.nbrMorts = nbrMorts;
}






}
