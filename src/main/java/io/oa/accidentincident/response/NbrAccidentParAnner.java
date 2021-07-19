package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParAnner {
	
private int annees;
private Long nbrAccident;



public int getAnnees() {
	return annees;
}
public void setAnnees(int annees) {
	this.annees = annees;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParAnner() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParAnner(int annees, Long nbrAccident) {
	super();
	this.annees = annees;
	this.nbrAccident = nbrAccident;
}



}
