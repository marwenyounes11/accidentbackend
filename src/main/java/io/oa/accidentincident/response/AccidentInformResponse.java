package io.oa.accidentincident.response;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.SourceInform;



public class AccidentInformResponse {
	private Long id;
	private Accident accident;
	private SourceInform sourceinform;
	@Temporal(TemporalType.DATE) 
	private Date dateInformation;
	@Temporal(TemporalType.TIME)
	 private Date heureInformation;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	public SourceInform getSourceinform() {
		return sourceinform;
	}
	public void setSourceinform(SourceInform sourceinform) {
		this.sourceinform = sourceinform;
	}
	public Date getDateInformation() {
		return dateInformation;
	}
	public void setDateInformation(Date dateInformation) {
		this.dateInformation = dateInformation;
	}
	public Date getHeureInformation() {
		return heureInformation;
	}
	public void setHeureInformation(Date heureInformation) {
		this.heureInformation = heureInformation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AccidentInformResponse(Long id, Accident accident, SourceInform sourceinform, Date dateInformation,
			Date heureInformation, String description) {
		super();
		this.id = id;
		this.accident = accident;
		this.sourceinform = sourceinform;
		this.dateInformation = dateInformation;
		this.heureInformation = heureInformation;
		this.description = description;
	}
	public AccidentInformResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
