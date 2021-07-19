package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NbrAccidentParSousTypeAccident {
	

private Long nbrAccident;
private String subType;

public String getSubType() {
	return subType;
}
public void setSubType(String subType) {
	this.subType = subType;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParSousTypeAccident() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParSousTypeAccident(Long nbrAccident, String subType) {
	super();
	this.nbrAccident = nbrAccident;
	this.subType = subType;
}

}
