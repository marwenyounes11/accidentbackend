package io.oa.accidentincident.form;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.DegatVictime;
import io.oa.accidentincident.entity.Victime;


public class IncidentJournalierTravailForm {
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	@Temporal(TemporalType.TIME)
	private Date heureAccident;
	private String subType;
	private String emplacement;
	private Set<Degat> degatmateriels;
	private Set<Degat> degatphysiques;
	private Set<Victime> victimes;
	
	
	public Date getDateAccident() {
		return dateAccident;
	}


	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}


	public Date getHeureAccident() {
		return heureAccident;
	}


	public void setHeureAccident(Date heureAccident) {
		this.heureAccident = heureAccident;
	}


	public String getSubType() {
		return subType;
	}


	public void setSubType(String subType) {
		this.subType = subType;
	}


	public String getEmplacement() {
		return emplacement;
	}


	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}


	public Set<Degat> getDegatmateriels() {
		return degatmateriels;
	}


	public void setDegatmateriels(Set<Degat> degatmateriels) {
		this.degatmateriels = degatmateriels;
	}


	public Set<Degat> getDegatphysiques() {
		return degatphysiques;
	}


	public void setDegatphysiques(Set<Degat> degatphysiques) {
		this.degatphysiques = degatphysiques;
	}


	public Set<Victime> getVictimes() {
		return victimes;
	}


	public void setVictimes(Set<Victime> victimes) {
		this.victimes = victimes;
	}


	public IncidentJournalierTravailForm() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IncidentJournalierTravailForm(Date dateAccident, Date heureAccident, String subType, String emplacement,
			Set<Degat> degatmateriels, Set<Degat> degatphysiques, Set<Victime> victimes) {
		super();
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.subType = subType;
		this.emplacement = emplacement;
		this.degatmateriels = degatmateriels;
		this.degatphysiques = degatphysiques;
		this.victimes = victimes;
	}
	
	
	
}
