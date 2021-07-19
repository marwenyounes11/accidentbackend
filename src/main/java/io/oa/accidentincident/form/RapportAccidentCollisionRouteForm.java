package io.oa.accidentincident.form;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.Huissier;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceDeclareHuissier;
import io.oa.accidentincident.entity.SourceInform;


public class RapportAccidentCollisionRouteForm {
	private Set<SourceInform> sourceinforms;
	private Set<Agent> agents;
	private String emplacement;
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	@Temporal(TemporalType.TIME)
	private Date heureAccident;
	private String numTransport ;
	private String nameLigne;
	private String nameDistrict;
	private String subType;
	private String immatriculation ;
	private Set<Degat> degatmateriels;
	private Set<Degat> degatphysiques;
	private Set<Huissier> huissiers;
	private Set<Securite> securites;
	private Set<SourceDeclareHuissier> sourcedeclarehuissiers;
	
	public Set<SourceInform> getSourceinforms() {
		return sourceinforms;
	}
	public void setSourceinforms(Set<SourceInform> sourceinforms) {
		this.sourceinforms = sourceinforms;
	}
	public Set<Agent> getAgents() {
		return agents;
	}
	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
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
	public String getNumTransport() {
		return numTransport;
	}
	public void setNumTransport(String numTransport) {
		this.numTransport = numTransport;
	}
	public String getNameLigne() {
		return nameLigne;
	}
	public void setNameLigne(String nameLigne) {
		this.nameLigne = nameLigne;
	}
	public String getNameDistrict() {
		return nameDistrict;
	}
	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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
	public Set<Huissier> getHuissiers() {
		return huissiers;
	}
	public void setHuissiers(Set<Huissier> huissiers) {
		this.huissiers = huissiers;
	}
	public Set<Securite> getSecurites() {
		return securites;
	}
	public void setSecurites(Set<Securite> securites) {
		this.securites = securites;
	}
	public Set<SourceDeclareHuissier> getSourcedeclarehuissiers() {
		return sourcedeclarehuissiers;
	}
	public void setSourcedeclarehuissiers(Set<SourceDeclareHuissier> sourcedeclarehuissiers) {
		this.sourcedeclarehuissiers = sourcedeclarehuissiers;
	}
	
	public RapportAccidentCollisionRouteForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RapportAccidentCollisionRouteForm(Set<SourceInform> sourceinforms, Set<Agent> agents, String emplacement,
			Date dateAccident, Date heureAccident, String numTransport, String nameLigne, String nameDistrict,
			String subType, String immatriculation, Set<Degat> degatmateriels, Set<Degat> degatphysiques,
			Set<Huissier> huissiers, Set<Securite> securites, Set<SourceDeclareHuissier> sourcedeclarehuissiers) {
		super();
		this.sourceinforms = sourceinforms;
		this.agents = agents;
		this.emplacement = emplacement;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.numTransport = numTransport;
		this.nameLigne = nameLigne;
		this.nameDistrict = nameDistrict;
		this.subType = subType;
		this.immatriculation = immatriculation;
		this.degatmateriels = degatmateriels;
		this.degatphysiques = degatphysiques;
		this.huissiers = huissiers;
		this.securites = securites;
		this.sourcedeclarehuissiers = sourcedeclarehuissiers;
	}
	
	
}
