package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class MoyenTransport  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le gabarit  ")
	private String gage;
	@NotNull(message = "s'il vous plais  entrer l'immatriculation ")
    private String immatriculation ;
    private String mark ; 
    private String model ;
    private String numTransport ;
	@NotNull(message = "s'il vous plais  entrer le type de transport ")
    private String type ;
    @ManyToOne
   	@JoinColumn(name = "ligne_id")
   	private Ligne ligne;
    @ManyToOne
   	@JoinColumn(name = "depot_id")
   	private Depot depot;
    @ManyToOne
   	@JoinColumn(name = "district_id")
   	private District district;
    @ManyToOne
   	@JoinColumn(name = "agent_id")
   	private Agent agent;
    
    @OneToMany(mappedBy = "moyentransport",cascade = CascadeType.REMOVE,orphanRemoval = true)
	 @JsonProperty(access=Access.WRITE_ONLY)
    Set<AccidentTransport> accidenttransports;

    @OneToMany(mappedBy="moyentransport",cascade = CascadeType.REMOVE,orphanRemoval = true)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Materiel> materiels ;
    
    @OneToMany(mappedBy="moyentransport",cascade = CascadeType.REMOVE,orphanRemoval = true)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<DegatTransport> degattransport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGage() {
		return gage;
	}

	public void setGage(String gage) {
		this.gage = gage;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Set<AccidentTransport> getAccidenttransports() {
		return accidenttransports;
	}

	public void setAccidenttransports(Set<AccidentTransport> accidenttransports) {
		this.accidenttransports = accidenttransports;
	}

	public Collection<Materiel> getMateriels() {
		return materiels;
	}

	public void setMateriels(Collection<Materiel> materiels) {
		this.materiels = materiels;
	}

	public Collection<DegatTransport> getDegattransport() {
		return degattransport;
	}

	public void setDegattransport(Collection<DegatTransport> degattransport) {
		this.degattransport = degattransport;
	}

	public MoyenTransport(Long id, @NotNull(message = "s'il vous plais  entrer le gabarit  ") String gage,
			@NotNull(message = "s'il vous plais  entrer l'immatriculation ") String immatriculation, String mark,
			String model, String numTransport,
			@NotNull(message = "s'il vous plais  entrer le type de transport ") String type, Ligne ligne, Depot depot,
			District district, Agent agent, Set<AccidentTransport> accidenttransports, Collection<Materiel> materiels,
			Collection<DegatTransport> degattransport) {
		super();
		this.id = id;
		this.gage = gage;
		this.immatriculation = immatriculation;
		this.mark = mark;
		this.model = model;
		this.numTransport = numTransport;
		this.type = type;
		this.ligne = ligne;
		this.depot = depot;
		this.district = district;
		this.agent = agent;
		this.accidenttransports = accidenttransports;
		this.materiels = materiels;
		this.degattransport = degattransport;
	}

	public MoyenTransport() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
	
}
