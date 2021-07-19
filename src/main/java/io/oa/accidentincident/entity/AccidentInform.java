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
public class AccidentInform implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "accident_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Accident accident;
	@ManyToOne
	@JoinColumn(name = "sourceinform_id")
	private SourceInform sourceinform;
	@NotNull(message = "s'il vous plais  entrer la date d'information")
	@Temporal(TemporalType.DATE) 
	private Date dateInformation;
	@NotNull(message = "s'il vous plais  entrer le time d'information")
	@Temporal(TemporalType.TIME)
	 private Date heureInformation;
	@NotNull(message = "s'il vous plais  entrer la description d'information")
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AccidentInform(Long id, Accident accident, SourceInform sourceinform,
			@NotNull(message = "s'il vous plais  entrer la date d'information") Date dateInformation,
			@NotNull(message = "s'il vous plais  entrer le time d'information") Date heureInformation,
			@NotNull(message = "s'il vous plais  entrer la description d'information") String description) {
		super();
		this.id = id;
		this.accident = accident;
		this.sourceinform = sourceinform;
		this.dateInformation = dateInformation;
		this.heureInformation = heureInformation;
		this.description = description;
	}
	public AccidentInform() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
