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
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceDeclareHuissier;

public class HuissierResponse implements Serializable{

	private Long id;
	private int number;
	@Temporal(TemporalType.DATE) 
	private Date dateHuissier;
	@Temporal(TemporalType.TIME)
	 private Date heureHuissier;
	private Securite securite;
	private Accident accident;
	private SourceDeclareHuissier sourcedeclarehuissier;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getDateHuissier() {
		return dateHuissier;
	}
	public void setDateHuissier(Date dateHuissier) {
		this.dateHuissier = dateHuissier;
	}
	public Date getHeureHuissier() {
		return heureHuissier;
	}
	public void setHeureHuissier(Date heureHuissier) {
		this.heureHuissier = heureHuissier;
	}
	public Securite getSecurite() {
		return securite;
	}
	public void setSecurite(Securite securite) {
		this.securite = securite;
	}
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	public SourceDeclareHuissier getSourcedeclarehuissier() {
		return sourcedeclarehuissier;
	}
	public void setSourcedeclarehuissier(SourceDeclareHuissier sourcedeclarehuissier) {
		this.sourcedeclarehuissier = sourcedeclarehuissier;
	}
	public HuissierResponse(Long id, int number, Date dateHuissier, Date heureHuissier, Securite securite,
			Accident accident, SourceDeclareHuissier sourcedeclarehuissier) {
		super();
		this.id = id;
		this.number = number;
		this.dateHuissier = dateHuissier;
		this.heureHuissier = heureHuissier;
		this.securite = securite;
		this.accident = accident;
		this.sourcedeclarehuissier = sourcedeclarehuissier;
	}
	public HuissierResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
