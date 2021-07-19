package io.oa.accidentincident.entity;

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

@Entity
public class Huissier implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le numero d'huissier")
	private int number;
	@NotNull(message = "s'il vous plais  entrer la date d'huissier")
	@Temporal(TemporalType.DATE) 
	private Date dateHuissier;
	@NotNull(message = "s'il vous plais  entrer le time d'd'huissier")
	@Temporal(TemporalType.TIME)
	 private Date heureHuissier;
	@ManyToOne
	@JoinColumn(name = "securite_id")
	private Securite securite;
	
	@ManyToOne
	@JoinColumn(name = "accident_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Accident accident;
	
	@ManyToOne
	@JoinColumn(name = "sourcedeclarehuissier_id")
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

	public Huissier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Huissier(Long id, @NotNull(message = "s'il vous plais  entrer le numero d'huissier") int number,
			@NotNull(message = "s'il vous plais  entrer la date d'huissier") Date dateHuissier,
			@NotNull(message = "s'il vous plais  entrer le time d'd'huissier") Date heureHuissier, Securite securite,
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

	
	
}
