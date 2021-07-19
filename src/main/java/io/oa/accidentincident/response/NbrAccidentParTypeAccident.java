package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParTypeAccident {
	

private Long nbrAccident;
private String type;

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
public NbrAccidentParTypeAccident() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParTypeAccident(Long nbrAccident, String type) {
	super();
	this.nbrAccident = nbrAccident;
	this.type = type;
}




}
