package io.oa.accidentincident.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Materiel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String description;
	private String numberSerie;
	@Temporal(TemporalType.DATE) 
	private Date dateAcquisition;
	@Temporal(TemporalType.TIME)
	 private Date heureAcquisition;
	@Temporal(TemporalType.DATE) 
	private Date dateMaintenance;
	@Temporal(TemporalType.TIME)
	 private Date heureMaintenance;
	@Temporal(TemporalType.DATE) 
	private Date datePeremption;
	@Temporal(TemporalType.TIME)
	 private Date heurePeremption;
	private String subtype;
	private String image;
	private String emplacement;
	private String length;
	private String diametre;
	private int nombrebouche;
	private String coleurpoteaux;
	
	@ManyToOne
	@JoinColumn(name = "moyentransport_id",nullable=true)
	private MoyenTransport moyentransport;
	@ManyToOne
	@JoinColumn(name = "depot_id",nullable=true)
	private Depot depot;
	@ManyToOne
	@JoinColumn(name = "departement_id",nullable=true)
	private Departement departement;
	@OneToMany(mappedBy="materiel",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Intervention> interventions ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getDiametre() {
		return diametre;
	}
	public void setDiametre(String diametre) {
		this.diametre = diametre;
	}
	public int getNombrebouche() {
		return nombrebouche;
	}
	public void setNombrebouche(int nombrebouche) {
		this.nombrebouche = nombrebouche;
	}
	public String getColeurpoteaux() {
		return coleurpoteaux;
	}
	public void setColeurpoteaux(String coleurpoteaux) {
		this.coleurpoteaux = coleurpoteaux;
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
	public Collection<Intervention> getInterventions() {
		return interventions;
	}
	public void setInterventions(Collection<Intervention> interventions) {
		this.interventions = interventions;
	}
	public Materiel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Materiel(Long id, String type, String description, String numberSerie, Date dateAcquisition,
			Date heureAcquisition, Date dateMaintenance, Date heureMaintenance, Date datePeremption,
			Date heurePeremption, String subtype, String image, String emplacement, String length, String diametre,
			int nombrebouche, String coleurpoteaux, MoyenTransport moyentransport, Depot depot, Departement departement,
			Collection<Intervention> interventions) {
		super();
		this.id = id;
		this.type = type;
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
		this.length = length;
		this.diametre = diametre;
		this.nombrebouche = nombrebouche;
		this.coleurpoteaux = coleurpoteaux;
		this.moyentransport = moyentransport;
		this.depot = depot;
		this.departement = departement;
		this.interventions = interventions;
	}
	
	
}
