package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EstimationPrixDegatParAnner {
	
private int annees;
private Double estimationPrixDegat;

public int getAnnees() {
	return annees;
}
public void setAnnees(int annees) {
	this.annees = annees;
}
public Double getEstimationPrixDegat() {
	return estimationPrixDegat;
}
public void setEstimationPrixDegat(Double estimationPrixDegat) {
	this.estimationPrixDegat = estimationPrixDegat;
}
public EstimationPrixDegatParAnner(int annees, Double estimationPrixDegat) {
	super();
	this.annees = annees;
	this.estimationPrixDegat = estimationPrixDegat;
}
public EstimationPrixDegatParAnner() {
	super();
	// TODO Auto-generated constructor stub
}



}
