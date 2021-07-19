package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrMortsParAnner {
	
private int annees;
private Long nbrMorts;




public int getAnnees() {
	return annees;
}
public void setAnnees(int annees) {
	this.annees = annees;
}
public Long getNbrMorts() {
	return nbrMorts;
}
public void setNbrMorts(Long nbrMorts) {
	this.nbrMorts = nbrMorts;
}
public NbrMortsParAnner() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrMortsParAnner(int annees, Long nbrMorts) {
	super();
	this.annees = annees;
	this.nbrMorts = nbrMorts;
}





}
