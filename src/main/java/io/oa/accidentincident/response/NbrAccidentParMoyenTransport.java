package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParMoyenTransport {
	
private String type;
private Long nbrAccident;
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
public NbrAccidentParMoyenTransport() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParMoyenTransport(String type, Long nbrAccident) {
	super();
	this.type = type;
	this.nbrAccident = nbrAccident;
}





}
