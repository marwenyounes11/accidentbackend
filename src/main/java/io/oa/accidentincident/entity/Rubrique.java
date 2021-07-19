package io.oa.accidentincident.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "rubrique", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "libelle"
        })
})

public class Rubrique {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	private String libelle;
	@OneToMany(mappedBy="rubrique")
    private Set<SousRubrique> sousRubriques = new HashSet<SousRubrique>();
	
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
	
	public Set<SousRubrique> getSousRubriques() {
		return sousRubriques;
	}
	public void setSousRubriques(Set<SousRubrique> sousRubriques) {
		this.sousRubriques = sousRubriques;
	}
	
	
	
	public Rubrique(Long id, String libelle, Set<SousRubrique> sousRubriques) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.sousRubriques = sousRubriques;
	}
	public Rubrique() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rubrique( String libelle, Set<SousRubrique> sousRubriques) {
		
		this.libelle = libelle;
		this.sousRubriques = sousRubriques;
	}
	
	
	
}
