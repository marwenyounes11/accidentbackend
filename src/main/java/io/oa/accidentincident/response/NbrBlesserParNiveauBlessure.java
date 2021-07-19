package io.oa.accidentincident.response;


public class NbrBlesserParNiveauBlessure {	
private String niveauBlessure ;
private Long nbrBlesser;
public String getNiveauBlessure() {
	return niveauBlessure;
}
public void setNiveauBlessure(String niveauBlessure) {
	this.niveauBlessure = niveauBlessure;
}
public Long getNbrBlesser() {
	return nbrBlesser;
}
public void setNbrBlesser(Long nbrBlesser) {
	this.nbrBlesser = nbrBlesser;
}
public NbrBlesserParNiveauBlessure(String niveauBlessure, Long nbrBlesser) {
	super();
	this.niveauBlessure = niveauBlessure;
	this.nbrBlesser = nbrBlesser;
}
public NbrBlesserParNiveauBlessure() {
	super();
	// TODO Auto-generated constructor stub
}




}
