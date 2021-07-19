package io.oa.accidentincident.entity;

import java.io.Serializable;
import java.util.Set;

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
public class Securite implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le type d'unité")
	private String type;
	@NotNull(message = "s'il vous plais  entrer l'address d'unité")
	private String address;
	@ManyToOne
	@JoinColumn(name = "sourceinform_id")
	private SourceInform sourceinform;
	
	@OneToMany(mappedBy = "securite",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	@JsonProperty(access=Access.WRITE_ONLY)
     Set<Huissier> huissiers;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public SourceInform getSourceinform() {
		return sourceinform;
	}

	public void setSourceinform(SourceInform sourceinform) {
		this.sourceinform = sourceinform;
	}

	public Set<Huissier> getHuissiers() {
		return huissiers;
	}

	public void setHuissiers(Set<Huissier> huissiers) {
		this.huissiers = huissiers;
	}

	public Securite(Long id, @NotNull(message = "s'il vous plais  entrer le type d'unité") String type,
			@NotNull(message = "s'il vous plais  entrer l'adress d'unité") String address, SourceInform sourceinform,
			Set<Huissier> huissiers) {
		super();
		this.id = id;
		this.type = type;
		this.address = address;
		this.sourceinform = sourceinform;
		this.huissiers = huissiers;
	}

	public Securite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
