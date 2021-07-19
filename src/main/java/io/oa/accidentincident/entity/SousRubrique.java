package io.oa.accidentincident.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class SousRubrique {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	private String libelle;
	@ManyToOne
	@JoinColumn(name="rubrique_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Rubrique rubrique;
	@OneToMany(mappedBy = "sousrubrique")
	@JsonProperty(access=Access.WRITE_ONLY)
     Set<DroitRoles> droitroles;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Rubrique getRubrique() {
		return rubrique;
	}


	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}


	public Set<DroitRoles> getDroitroles() {
		return droitroles;
	}


	public void setDroitroles(Set<DroitRoles> droitroles) {
		this.droitroles = droitroles;
	}


	public SousRubrique() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SousRubrique(Long id, String libelle, Rubrique rubrique, Set<DroitRoles> droitroles) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.rubrique = rubrique;
		this.droitroles = droitroles;
	}


	public SousRubrique( String libelle, Rubrique rubrique) {
		this.libelle = libelle;
		this.rubrique = rubrique;
	}
	
	
}
