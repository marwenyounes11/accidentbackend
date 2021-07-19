package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentVictimeParAnner {
	
private int annees;
private Long nbrAccident;
private Long nbrBlesser;
private Long nbrMorts;
private Double estimationPrixDegat;
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
public Long getNbrBlesser() {
	return nbrBlesser;
}
public void setNbrBlesser(Long nbrBlesser) {
	this.nbrBlesser = nbrBlesser;
}
public Long getNbrMorts() {
	return nbrMorts;
}
public void setNbrMorts(Long nbrMorts) {
	this.nbrMorts = nbrMorts;
}
public Double getEstimationPrixDegat() {
	return estimationPrixDegat;
}
public void setEstimationPrixDegat(Double estimationPrixDegat) {
	this.estimationPrixDegat = estimationPrixDegat;
}
public NbrAccidentVictimeParAnner(int annees, Long nbrAccident, Long nbrBlesser, Long nbrMorts,
		Double estimationPrixDegat) {
	super();
	this.annees = annees;
	this.nbrAccident = nbrAccident;
	this.nbrBlesser = nbrBlesser;
	this.nbrMorts = nbrMorts;
	this.estimationPrixDegat = estimationPrixDegat;
}
public NbrAccidentVictimeParAnner() {
	super();
	// TODO Auto-generated constructor stub
}



}
