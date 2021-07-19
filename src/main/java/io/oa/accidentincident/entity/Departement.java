package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Collection;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class Departement  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom de departement")
	private String nameDepartement;
	@OneToMany(mappedBy="departement",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Materiel> materiels ;
	@ManyToOne
	@JoinColumn(name = "lieux_id")
	private LieuxAccident lieux;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameDepartement() {
		return nameDepartement;
	}
	public void setNameDepartement(String nameDepartement) {
		this.nameDepartement = nameDepartement;
	}
	public Collection<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(Collection<Materiel> materiels) {
		this.materiels = materiels;
	}
	public LieuxAccident getLieux() {
		return lieux;
	}
	public void setLieux(LieuxAccident lieux) {
		this.lieux = lieux;
	}
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Departement(Long id,
			@NotNull(message = "s'il vous plais  entrer le nom de departement") String nameDepartement,
			Collection<Materiel> materiels, LieuxAccident lieux) {
		super();
		this.id = id;
		this.nameDepartement = nameDepartement;
		this.materiels = materiels;
		this.lieux = lieux;
	}
	
	
}
