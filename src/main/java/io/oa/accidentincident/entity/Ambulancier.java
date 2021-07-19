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
public class Ambulancier implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom d'ambulancier")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom d'ambulancier")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer le matricule d'ambulancier")
	private String matricule;
	@NotNull(message = "s'il vous plais  entrer le telephone d'ambulancier")
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
	public Ambulancier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ambulancier(Long id, @NotNull(message = "s'il vous plais  entrer le nom d'ambulancier") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom d'ambulancier") String lastName,
			@NotNull(message = "s'il vous plais  entrer le matricule d'ambulancier") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone d'ambulancier") String phone,
			SourceInform sourceinform) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.matricule = matricule;
		this.phone = phone;
		this.sourceinform = sourceinform;
	}
	
	
	
	
}
