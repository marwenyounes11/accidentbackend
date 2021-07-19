package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EstimationPrixDegatParDate {
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	private Double estimationPrixDegat;
	public Date getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public Double getEstimationPrixDegat() {
		return estimationPrixDegat;
	}
	public void setEstimationPrixDegat(Double estimationPrixDegat) {
		this.estimationPrixDegat = estimationPrixDegat;
	}
	public EstimationPrixDegatParDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstimationPrixDegatParDate(Date dateAccident, Double estimationPrixDegat) {
		super();
		this.dateAccident = dateAccident;
		this.estimationPrixDegat = estimationPrixDegat;
	}
	
	
	
	
}
