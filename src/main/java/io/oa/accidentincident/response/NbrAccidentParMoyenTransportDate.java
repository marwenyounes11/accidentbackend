package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParMoyenTransportDate {
	
@Temporal(TemporalType.DATE) 
private Date dateAccident;
private String type;
private Long nbrAccident;

public Date getDateAccident() {
	return dateAccident;
}
public void setDateAccident(Date dateAccident) {
	this.dateAccident = dateAccident;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParMoyenTransportDate() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParMoyenTransportDate(Date dateAccident, String type, Long nbrAccident) {
	super();
	this.dateAccident = dateAccident;
	this.type = type;
	this.nbrAccident = nbrAccident;
}




}
