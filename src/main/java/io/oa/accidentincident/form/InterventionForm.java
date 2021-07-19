package io.oa.accidentincident.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class InterventionForm {
	private Long id;	
	private Long idmateriel;
	private Long idagentintervention;
	private String dateIntervention;
	private String heureIntervention;
	private String action;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdmateriel() {
		return idmateriel;
	}
	public void setIdmateriel(Long idmateriel) {
		this.idmateriel = idmateriel;
	}
	public Long getIdagentintervention() {
		return idagentintervention;
	}
	public void setIdagentintervention(Long idagentintervention) {
		this.idagentintervention = idagentintervention;
	}
	public String getDateIntervention() {
		return dateIntervention;
	}
	public void setDateIntervention(String dateIntervention) {
		this.dateIntervention = dateIntervention;
	}
	public String getHeureIntervention() {
		return heureIntervention;
	}
	public void setHeureIntervention(String heureIntervention) {
		this.heureIntervention = heureIntervention;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public InterventionForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterventionForm(Long id, Long idmateriel, Long idagentintervention, String dateIntervention,
			String heureIntervention, String action) {
		super();
		this.id = id;
		this.idmateriel = idmateriel;
		this.idagentintervention = idagentintervention;
		this.dateIntervention = dateIntervention;
		this.heureIntervention = heureIntervention;
		this.action = action;
	}

	
	
	
	
	
}
