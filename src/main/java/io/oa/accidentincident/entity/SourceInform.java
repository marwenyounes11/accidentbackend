package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class SourceInform  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom  ")
	private String name;
	@NotNull(message = "s'il vous plais  entrer le prenom de ligne ")
	private String lastName;
	@NotNull(message = "s'il vous plais  entrer le nature  ")
	private String nature;
	@NotNull(message = "s'il vous plais  entrer le telephone ")
	private String phone ;
	
	@OneToMany(mappedBy = "sourceinform", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
  Set<AgentTranstu> agenttranstus;
	
	@OneToMany(mappedBy = "sourceinform",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
    Set<AccidentInform> accidentinforms;
	
	@OneToMany(mappedBy = "sourceinform",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
   Set<Securite> securites;
	
	@OneToMany(mappedBy = "sourceinform",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
   Set<Ambulancier> ambulanciers;

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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<AgentTranstu> getAgenttranstus() {
		return agenttranstus;
	}

	public void setAgenttranstus(Set<AgentTranstu> agenttranstus) {
		this.agenttranstus = agenttranstus;
	}

	public Set<AccidentInform> getAccidentinforms() {
		return accidentinforms;
	}

	public void setAccidentinforms(Set<AccidentInform> accidentinforms) {
		this.accidentinforms = accidentinforms;
	}

	public Set<Securite> getSecurites() {
		return securites;
	}

	public void setSecurites(Set<Securite> securites) {
		this.securites = securites;
	}

	public Set<Ambulancier> getAmbulanciers() {
		return ambulanciers;
	}

	public void setAmbulanciers(Set<Ambulancier> ambulanciers) {
		this.ambulanciers = ambulanciers;
	}

	public SourceInform() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SourceInform(Long id, @NotNull(message = "s'il vous plais  entrer le nom  ") String name,
			@NotNull(message = "s'il vous plais  entrer le prenom de ligne ") String lastName,
			@NotNull(message = "s'il vous plais  entrer le nature  ") String nature,
			@NotNull(message = "s'il vous plais  entrer le telephone ") String phone, Set<AgentTranstu> agenttranstus,
			Set<AccidentInform> accidentinforms, Set<Securite> securites, Set<Ambulancier> ambulanciers) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.nature = nature;
		this.phone = phone;
		this.agenttranstus = agenttranstus;
		this.accidentinforms = accidentinforms;
		this.securites = securites;
		this.ambulanciers = ambulanciers;
	}

	
	

}
