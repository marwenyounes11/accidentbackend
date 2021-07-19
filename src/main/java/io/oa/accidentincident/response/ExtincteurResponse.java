package io.oa.accidentincident.response;


import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.Intervention;
import io.oa.accidentincident.entity.MoyenTransport;



public class ExtincteurResponse {
	private Long id;
	private String description;
	private String numberSerie;
	private Date dateAcquisition;
	 private Date heureAcquisition;
	private Date dateMaintenance;
	 private Date heureMaintenance;
	private Date datePeremption;
	 private Date heurePeremption;
	private String subtype;
	private String image;
	private String emplacement;
	private MoyenTransport moyentransport;
	private Depot depot;
	private Departement departement;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNumberSerie() {
		return numberSerie;
	}
	public void setNumberSerie(String numberSerie) {
		this.numberSerie = numberSerie;
	}
	public Date getDateAcquisition() {
		return dateAcquisition;
	}
	public void setDateAcquisition(Date dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}
	public Date getHeureAcquisition() {
		return heureAcquisition;
	}
	public void setHeureAcquisition(Date heureAcquisition) {
		this.heureAcquisition = heureAcquisition;
	}
	public Date getDateMaintenance() {
		return dateMaintenance;
	}
	public void setDateMaintenance(Date dateMaintenance) {
		this.dateMaintenance = dateMaintenance;
	}
	public Date getHeureMaintenance() {
		return heureMaintenance;
	}
	public void setHeureMaintenance(Date heureMaintenance) {
		this.heureMaintenance = heureMaintenance;
	}
	public Date getDatePeremption() {
		return datePeremption;
	}
	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}
	public Date getHeurePeremption() {
		return heurePeremption;
	}
	public void setHeurePeremption(Date heurePeremption) {
		this.heurePeremption = heurePeremption;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public MoyenTransport getMoyentransport() {
		return moyentransport;
	}
	public void setMoyentransport(MoyenTransport moyentransport) {
		this.moyentransport = moyentransport;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public ExtincteurResponse(Long id, String description, String numberSerie, Date dateAcquisition,
			Date heureAcquisition, Date dateMaintenance, Date heureMaintenance, Date datePeremption,
			Date heurePeremption, String subtype, String image, String emplacement, MoyenTransport moyentransport,
			Depot depot, Departement departement) {
		super();
		this.id = id;
		this.description = description;
		this.numberSerie = numberSerie;
		this.dateAcquisition = dateAcquisition;
		this.heureAcquisition = heureAcquisition;
		this.dateMaintenance = dateMaintenance;
		this.heureMaintenance = heureMaintenance;
		this.datePeremption = datePeremption;
		this.heurePeremption = heurePeremption;
		this.subtype = subtype;
		this.image = image;
		this.emplacement = emplacement;
		this.moyentransport = moyentransport;
		this.depot = depot;
		this.departement = departement;
	}
	public ExtincteurResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
