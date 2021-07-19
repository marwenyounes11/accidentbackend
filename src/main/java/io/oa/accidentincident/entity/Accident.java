package io.oa.accidentincident.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class Accident implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@NotNull(message = "s'il vous plais  entrer la date d'accident")
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	@NotNull(message = "s'il vous plais  entrer le time d'accident")
	@Temporal(TemporalType.TIME)
	 private Date heureAccident;
	@NotNull(message = "s'il vous plais  entrer la description")
	private String description;
	//@NotNull(message = "s'il vous plais  entrer l'image")
	private String image;
	private String subType;
	
	@OneToMany(mappedBy = "accident",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)

     Set<Degat> degats;	  
	@ManyToOne
	@JoinColumn(name = "lieux_id")
	private LieuxAccident lieux;
	@OneToMany(mappedBy = "accident",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)

     Set<Huissier> huissiers;
	@OneToMany(mappedBy = "accident",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	
     Set<AccidentInform> accidentinforms;
	@OneToMany(mappedBy = "accident",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	
     Set<AccidentTransport> accidenttransports;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public Set<Degat> getDegats() {
		return degats;
	}
	public void setDegats(Set<Degat> degats) {
		this.degats = degats;
	}
	public LieuxAccident getLieux() {
		return lieux;
	}
	public void setLieux(LieuxAccident lieux) {
		this.lieux = lieux;
	}
	public Set<Huissier> getHuissiers() {
		return huissiers;
	}
	public void setHuissiers(Set<Huissier> huissiers) {
		this.huissiers = huissiers;
	}
	public Set<AccidentInform> getAccidentinforms() {
		return accidentinforms;
	}
	public void setAccidentinforms(Set<AccidentInform> accidentinforms) {
		this.accidentinforms = accidentinforms;
	}
	public Set<AccidentTransport> getAccidenttransports() {
		return accidenttransports;
	}
	public void setAccidenttransports(Set<AccidentTransport> accidenttransports) {
		this.accidenttransports = accidenttransports;
	}
	public Accident() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accident(Long id, String type,
			@NotNull(message = "s'il vous plais  entrer la date d'accident") Date dateAccident,
			@NotNull(message = "s'il vous plais  entrer le time d'accident") Date heureAccident,
			@NotNull(message = "s'il vous plais  entrer la description") String description, String image,
			String subType, Set<Degat> degats, LieuxAccident lieux, Set<Huissier> huissiers,
			Set<AccidentInform> accidentinforms, Set<AccidentTransport> accidenttransports) {
		super();
		this.id = id;
		this.type = type;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.description = description;
		this.image = image;
		this.subType = subType;
		this.degats = degats;
		this.lieux = lieux;
		this.huissiers = huissiers;
		this.accidentinforms = accidentinforms;
		this.accidenttransports = accidenttransports;
	}
	
	
}
