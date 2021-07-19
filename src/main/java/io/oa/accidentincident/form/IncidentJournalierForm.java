package io.oa.accidentincident.form;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.DegatVictime;


public class IncidentJournalierForm {
	private String nameDistrict;
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	@Temporal(TemporalType.TIME)
	private Date heureAccident;
	private String subType;
	private String nameLigne;
	private String immatriculation ;
	 private String numTransport ;
	private String type ;
	private String emplacement;
	private String name;
	private String lastName;
	private Set<Degat> degatmateriels;
	private Set<Degat> degatphysiques;
	public String getNameDistrict() {
		return nameDistrict;
	}
	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}
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
	public String getNameLigne() {
		return nameLigne;
	}
	public void setNameLigne(String nameLigne) {
		this.nameLigne = nameLigne;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getNumTransport() {
		return numTransport;
	}
	public void setNumTransport(String numTransport) {
		this.numTransport = numTransport;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	
	
	
	public IncidentJournalierForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IncidentJournalierForm(String nameDistrict, Date dateAccident, Date heureAccident, String subType,
			String nameLigne, String immatriculation, String numTransport, String type, String emplacement, String name,
			String lastName, Set<Degat> degatmateriels, Set<Degat> degatphysiques) {
		super();
		this.nameDistrict = nameDistrict;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.subType = subType;
		this.nameLigne = nameLigne;
		this.immatriculation = immatriculation;
		this.numTransport = numTransport;
		this.type = type;
		this.emplacement = emplacement;
		this.name = name;
		this.lastName = lastName;
		this.degatmateriels = degatmateriels;
		this.degatphysiques = degatphysiques;
	}
	
	
}
