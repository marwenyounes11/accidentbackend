package io.oa.accidentincident.response;

public class NbrAccidentParDistrict {
private String nameDistrict;
private Long nbrAccident;
public String getNameDistrict() {
	return nameDistrict;
}
public void setNameDistrict(String nameDistrict) {
	this.nameDistrict = nameDistrict;
}
public Long getNbrAccident() {
	return nbrAccident;
}
public void setNbrAccident(Long nbrAccident) {
	this.nbrAccident = nbrAccident;
}
public NbrAccidentParDistrict() {
	super();
	// TODO Auto-generated constructor stub
}
public NbrAccidentParDistrict(String nameDistrict, Long nbrAccident) {
	super();
	this.nameDistrict = nameDistrict;
	this.nbrAccident = nbrAccident;
}



}
