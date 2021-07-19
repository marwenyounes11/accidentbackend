package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class AgentTranstuForm {
	private Long id;	
	@NotNull(message = "s'il vous plais  entrer la fonction d'agent")
	private String function;
	@NotNull(message = "s'il vous plais  entrer le nom d'agent")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom d'agent")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer le matricule d'agent")
	private String matricule;
	@NotNull(message = "s'il vous plais  entrer le telephone d'agent")
	private String phone;
	private Long idsourceinform;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
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
	public AgentTranstuForm(Long id, @NotNull(message = "s'il vous plais  entrer la fonction d'agent") String function,
			@NotNull(message = "s'il vous plais  entrer le nom d'agent") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom d'agent") String lastName,
			@NotNull(message = "s'il vous plais  entrer le matricule d'agent") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone d'agent") String phone, Long idsourceinform) {
		super();
		this.id = id;
		this.function = function;
		this.name = name;
		this.lastName = lastName;
		this.matricule = matricule;
		this.phone = phone;
		this.idsourceinform = idsourceinform;
	}
	public AgentTranstuForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
