package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParMois {
	
private String mois;
private Long nbrAccident;




public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParMois() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParMois(String mois, Long nbrAccident) {
	super();
	this.mois = mois;
	this.nbrAccident = nbrAccident;
}




}
