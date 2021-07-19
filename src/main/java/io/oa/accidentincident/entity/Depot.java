package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class Depot  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom de depot")
	private String nameDepot;
	@OneToMany(mappedBy="depot")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<MoyenTransport> moyenTransports ;
	@OneToMany(mappedBy="depot")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Materiel> materiels ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameDepot() {
		return nameDepot;
	}
	public void setNameDepot(String nameDepot) {
		this.nameDepot = nameDepot;
	}
	public Collection<MoyenTransport> getMoyenTransports() {
		return moyenTransports;
	}
	public void setMoyenTransports(Collection<MoyenTransport> moyenTransports) {
		this.moyenTransports = moyenTransports;
	}
	public Collection<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(Collection<Materiel> materiels) {
		this.materiels = materiels;
	}
	public Depot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Depot(Long id, @NotNull(message = "s'il vous plais  entrer le nom de depot") String nameDepot,
			Collection<MoyenTransport> moyenTransports, Collection<Materiel> materiels) {
		super();
		this.id = id;
		this.nameDepot = nameDepot;
		this.moyenTransports = moyenTransports;
		this.materiels = materiels;
	}
	
	

}
