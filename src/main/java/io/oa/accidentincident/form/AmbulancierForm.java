package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class AmbulancierForm {
	private Long id;	
	@NotNull(message = "s'il vous plais  entrer le nom d'ambulancier")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom d'ambulancier")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer le matricule d'ambulancier")
	private String matricule;
	@NotNull(message = "s'il vous plais  entrer le telephone d'ambulancier")
	private String phone;
	private Long idsourceinform;
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
	public Long getIdsourceinform() {
		return idsourceinform;
	}
	public void setIdsourceinform(Long idsourceinform) {
		this.idsourceinform = idsourceinform;
	}
	public AmbulancierForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AmbulancierForm(Long id, @NotNull(message = "s'il vous plais  entrer le nom d'ambulancier") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom d'ambulancier") String lastName,
			@NotNull(message = "s'il vous plais  entrer le matricule d'ambulancier") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone d'ambulancier") String phone,
			Long idsourceinform) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.matricule = matricule;
		this.phone = phone;
		this.idsourceinform = idsourceinform;
	}
	
	
}
