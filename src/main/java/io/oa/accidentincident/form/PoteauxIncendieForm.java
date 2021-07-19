package io.oa.accidentincident.form;


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



public class PoteauxIncendieForm {
	private Long id;
	private String description;
	private String numberSerie;
	private String dateAcquisition;
	 private String heureAcquisition;
	private String dateMaintenance;
	 private String heureMaintenance;
	private String datePeremption;
	 private String heurePeremption;
	private String image;
	private String emplacement;
	private String coleurpoteaux;
	private Long iddepartement;
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
	public String getDateAcquisition() {
		return dateAcquisition;
	}
	public void setDateAcquisition(String dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}
	public String getHeureAcquisition() {
		return heureAcquisition;
	}
	public void setHeureAcquisition(String heureAcquisition) {
		this.heureAcquisition = heureAcquisition;
	}
	public String getDateMaintenance() {
		return dateMaintenance;
	}
	public void setDateMaintenance(String dateMaintenance) {
		this.dateMaintenance = dateMaintenance;
	}
	public String getHeureMaintenance() {
		return heureMaintenance;
	}
	public void setHeureMaintenance(String heureMaintenance) {
		this.heureMaintenance = heureMaintenance;
	}
	public String getDatePeremption() {
		return datePeremption;
	}
	public void setDatePeremption(String datePeremption) {
		this.datePeremption = datePeremption;
	}
	public String getHeurePeremption() {
		return heurePeremption;
	}
	public void setHeurePeremption(String heurePeremption) {
		this.heurePeremption = heurePeremption;
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
	public String getColeurpoteaux() {
		return coleurpoteaux;
	}
	public void setColeurpoteaux(String coleurpoteaux) {
		this.coleurpoteaux = coleurpoteaux;
	}
	public Long getIddepartement() {
		return iddepartement;
	}
	public void setIddepartement(Long iddepartement) {
		this.iddepartement = iddepartement;
	}
	public PoteauxIncendieForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoteauxIncendieForm(Long id, String description, String numberSerie, String dateAcquisition,
			String heureAcquisition, String dateMaintenance, String heureMaintenance, String datePeremption,
			String heurePeremption, String image, String emplacement, String coleurpoteaux, Long iddepartement) {
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
		this.image = image;
		this.emplacement = emplacement;
		this.coleurpoteaux = coleurpoteaux;
		this.iddepartement = iddepartement;
	}
	
	
	
	
}
