package io.oa.accidentincident.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class AgentTranstu implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = "sourceinform_id")
	private SourceInform sourceinform;
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
	public SourceInform getSourceinform() {
		return sourceinform;
	}
	public void setSourceinform(SourceInform sourceinform) {
		this.sourceinform = sourceinform;
	}
	public AgentTranstu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AgentTranstu(Long id, @NotNull(message = "s'il vous plais  entrer la fonction d'agent") String function,
			@NotNull(message = "s'il vous plais  entrer le nom d'agent") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom d'agent") String lastName,
			@NotNull(message = "s'il vous plais  entrer le matricule d'agent") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone d'agent") String phone,
			SourceInform sourceinform) {
		super();
		this.id = id;
		this.function = function;
		this.name = name;
		this.lastName = lastName;
		this.matricule = matricule;
		this.phone = phone;
		this.sourceinform = sourceinform;
	}
	
	
}
