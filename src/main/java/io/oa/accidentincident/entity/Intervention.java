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


@Entity
public class Intervention implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "materiel_id")
	private Materiel materiel;
	@ManyToOne
	@JoinColumn(name = "agentintervention_id")
	private AgentIntervention agentintervention;
	@NotNull(message = "s'il vous plais  entrer la date d'itervention")
	@Temporal(TemporalType.DATE) 
	private Date dateIntervention;
	@NotNull(message = "s'il vous plais  entrer le time d'itervention")
	@Temporal(TemporalType.TIME)
	 private Date heureIntervention;
	@NotNull(message = "s'il vous plais  entrer l'action d'intervention")
	private String action;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Materiel getMateriel() {
		return materiel;
	}
	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}
	public AgentIntervention getAgentintervention() {
		return agentintervention;
	}
	public void setAgentintervention(AgentIntervention agentintervention) {
		this.agentintervention = agentintervention;
	}
	public Date getDateIntervention() {
		return dateIntervention;
	}
	public void setDateIntervention(Date dateIntervention) {
		this.dateIntervention = dateIntervention;
	}
	public Date getHeureIntervention() {
		return heureIntervention;
	}
	public void setHeureIntervention(Date heureIntervention) {
		this.heureIntervention = heureIntervention;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Intervention() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Intervention(Long id, Materiel materiel, AgentIntervention agentintervention,
			@NotNull(message = "s'il vous plais  entrer la date d'itervention") Date dateIntervention,
			@NotNull(message = "s'il vous plais  entrer le time d'itervention") Date heureIntervention,
			@NotNull(message = "s'il vous plais  entrer l'action d'intervention") String action) {
		super();
		this.id = id;
		this.materiel = materiel;
		this.agentintervention = agentintervention;
		this.dateIntervention = dateIntervention;
		this.heureIntervention = heureIntervention;
		this.action = action;
	}
	
	
	
	
}
