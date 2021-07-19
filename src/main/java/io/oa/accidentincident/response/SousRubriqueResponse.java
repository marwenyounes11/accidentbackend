package io.oa.accidentincident.response;

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

import io.oa.accidentincident.entity.Rubrique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class SousRubriqueResponse {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	private String libelle;
	private Rubrique rubrique;
	
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
	public SousRubriqueResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SousRubriqueResponse(Long id, String libelle, Rubrique rubrique) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.rubrique = rubrique;
	}
	
	
	
}
