package io.oa.accidentincident.response;


public class NbrBlesserExterne {

private Long nbrBlesser;
private String corpBlesser;
public Long getNbrBlesser() {
	return nbrBlesser;
}
public void setNbrBlesser(Long nbrBlesser) {
	this.nbrBlesser = nbrBlesser;
}
public String getCorpBlesser() {
	return corpBlesser;
}
public void setCorpBlesser(String corpBlesser) {
	this.corpBlesser = corpBlesser;
}
public NbrBlesserExterne(Long nbrBlesser, String corpBlesser) {
	super();
	this.nbrBlesser = nbrBlesser;
	this.corpBlesser = corpBlesser;
}
public NbrBlesserExterne() {
	super();
	// TODO Auto-generated constructor stub
}


}
