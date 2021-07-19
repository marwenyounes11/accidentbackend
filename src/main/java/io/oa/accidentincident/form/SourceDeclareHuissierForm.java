package io.oa.accidentincident.form;

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


public class SourceDeclareHuissierForm {

	
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom de la source de delaration de huissier")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom de la source de delaration de huissier")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer le matricule de la source de delaration de huissier")
	private String matricule;
	@NotNull(message = "s'il vous plais  entrer le telephone de la source de delaration de huissier")
	private String phone;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public SourceDeclareHuissierForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SourceDeclareHuissierForm(Long id,
			@NotNull(message = "s'il vous plais  entrer le nom de la source de delaration de huissier") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom de la source de delaration de huissier") String lastName,
			@NotNull(message = "s'il vous plais  entrer le matricule de la source de delaration de huissier") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone de la source de delaration de huissier") String phone) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.matricule = matricule;
		this.phone = phone;
	}
	
	

	
	
	
}
