package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrBlesserParAnner {
	
private int annees;
private Long nbrBlesser;



public int getAnnees() {
	return annees;
}
public void setAnnees(int annees) {
	this.annees = annees;
}
public Long getNbrBlesser() {
	return nbrBlesser;
}
public void setNbrBlesser(Long nbrBlesser) {
	this.nbrBlesser = nbrBlesser;
}
public NbrBlesserParAnner() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrBlesserParAnner(int annees, Long nbrBlesser) {
	super();
	this.annees = annees;
	this.nbrBlesser = nbrBlesser;
}






}
