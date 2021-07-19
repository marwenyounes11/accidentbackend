package io.oa.accidentincident.response;


public class EstimationPrixDegatParSousTypeAccident {
	
	private String subType;
	private Double estimationPrixDegat;
	
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	public Double getEstimationPrixDegat() {
		return estimationPrixDegat;
	}
	public void setEstimationPrixDegat(Double estimationPrixDegat) {
		this.estimationPrixDegat = estimationPrixDegat;
	}
	public EstimationPrixDegatParSousTypeAccident() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstimationPrixDegatParSousTypeAccident(String subType, Double estimationPrixDegat) {
		super();
		this.subType = subType;
		this.estimationPrixDegat = estimationPrixDegat;
	}
	
	
}
