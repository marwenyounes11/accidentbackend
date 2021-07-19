package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParDate {
	
@Temporal(TemporalType.DATE) 
private Date dateAccident;
private Long nbrAccident;

public Date getDateAccident() {
	return dateAccident;
}
public void setDateAccident(Date dateAccident) {
	this.dateAccident = dateAccident;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParDate() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParDate(Date dateAccident, Long nbrAccident) {
	super();
	this.dateAccident = dateAccident;
	this.nbrAccident = nbrAccident;
}



}
