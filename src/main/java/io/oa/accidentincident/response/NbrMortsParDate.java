package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrMortsParDate {
	
@Temporal(TemporalType.DATE) 
private Date dateAccident;
private Long nbrMorts;

public Date getDateAccident() {
	return dateAccident;
}
public void setDateAccident(Date dateAccident) {
	this.dateAccident = dateAccident;
}

public Long getNbrMorts() {
	return nbrMorts;
}
public void setNbrMorts(Long nbrMorts) {
	this.nbrMorts = nbrMorts;
}
public NbrMortsParDate() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrMortsParDate(Date dateAccident, Long nbrMorts) {
	super();
	this.dateAccident = dateAccident;
	this.nbrMorts = nbrMorts;
}




}
