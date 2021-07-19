package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EstimationPrixDegatParTypeAccident {
	
	private String type;
	private Double estimationPrixDegat;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getEstimationPrixDegat() {
		return estimationPrixDegat;
	}
	public void setEstimationPrixDegat(Double estimationPrixDegat) {
		this.estimationPrixDegat = estimationPrixDegat;
	}
	public EstimationPrixDegatParTypeAccident() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstimationPrixDegatParTypeAccident(String type, Double estimationPrixDegat) {
		super();
		this.type = type;
		this.estimationPrixDegat = estimationPrixDegat;
	}
	
	
	
	
	
}
