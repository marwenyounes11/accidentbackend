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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class AgentIntervention implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom d'agent")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom d'agent")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer l'email d'agent")
	private String email;
	@NotNull(message = "s'il vous plais  entrer le matricule d'agent")
	private String matricule;
	@NotNull(message = "s'il vous plais  entrer le telephone d'agent")
	private String phone;
	
	@OneToMany(mappedBy="agentintervention",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Intervention> interventions ;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Collection<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(Collection<Intervention> interventions) {
		this.interventions = interventions;
	}

	public AgentIntervention(Long id, @NotNull(message = "s'il vous plais  entrer le nom d'agent") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom d'agent") String lastName,
			@NotNull(message = "s'il vous plais  entrer l'email d'agent") String email,
			@NotNull(message = "s'il vous plais  entrer le matricule d'agent") String matricule,
			@NotNull(message = "s'il vous plais  entrer le telephone d'agent") String phone,
			Collection<Intervention> interventions) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.matricule = matricule;
		this.phone = phone;
		this.interventions = interventions;
	}

	public AgentIntervention() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
