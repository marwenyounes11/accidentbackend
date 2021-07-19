package io.oa.accidentincident.entity;

import java.io.Serializable;
import java.util.Collection;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Victime implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String corpBlesser ;
	private String niveauBlessure ;
	private String etatVictime;
	private String typeVictime;
	private String lastNameVictime ;
	private String nameVictime;	
	@OneToMany(mappedBy="victime",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<DegatVictime> degatvictime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorpBlesser() {
		return corpBlesser;
	}
	public void setCorpBlesser(String corpBlesser) {
		this.corpBlesser = corpBlesser;
	}
	public String getNiveauBlessure() {
		return niveauBlessure;
	}
	public void setNiveauBlessure(String niveauBlessure) {
		this.niveauBlessure = niveauBlessure;
	}
	public String getEtatVictime() {
		return etatVictime;
	}
	public void setEtatVictime(String etatVictime) {
		this.etatVictime = etatVictime;
	}
	public String getTypeVictime() {
		return typeVictime;
	}
	public void setTypeVictime(String typeVictime) {
		this.typeVictime = typeVictime;
	}
	public String getLastNameVictime() {
		return lastNameVictime;
	}
	public void setLastNameVictime(String lastNameVictime) {
		this.lastNameVictime = lastNameVictime;
	}
	public String getNameVictime() {
		return nameVictime;
	}
	public void setNameVictime(String nameVictime) {
		this.nameVictime = nameVictime;
	}
	
	public Collection<DegatVictime> getDegatvictime() {
		return degatvictime;
	}
	public void setDegatvictime(Collection<DegatVictime> degatvictime) {
		this.degatvictime = degatvictime;
	}
	
	public Victime(Long id, String corpBlesser, String niveauBlessure, String etatVictime, String typeVictime,
			String lastNameVictime, String nameVictime, Collection<DegatVictime> degatvictime) {
		super();
		this.id = id;
		this.corpBlesser = corpBlesser;
		this.niveauBlessure = niveauBlessure;
		this.etatVictime = etatVictime;
		this.typeVictime = typeVictime;
		this.lastNameVictime = lastNameVictime;
		this.nameVictime = nameVictime;
		this.degatvictime = degatvictime;
	}
	public Victime() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
	
	

}
