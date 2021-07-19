package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;

@Entity
public class Degat  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@NotNull(message = "s'il vous plais  entrer la description de degat ")
	private String description;
	private Double value;
	
	@ManyToOne
	@JoinColumn(name = "accident_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Accident accident;
	@OneToMany(mappedBy="degat",cascade = CascadeType.ALL)
	
	private Collection<DegatVictime> degatvictime;
	@OneToMany(mappedBy="degat",cascade = CascadeType.ALL)
	
	private Collection<DegatTransport> degattransport;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	
	
	
	public Collection<DegatVictime> getDegatvictime() {
		return degatvictime;
	}
	public void setDegatvictime(Collection<DegatVictime> degatvictime) {
		this.degatvictime = degatvictime;
	}
	public Collection<DegatTransport> getDegattransport() {
		return degattransport;
	}
	public void setDegattransport(Collection<DegatTransport> degattransport) {
		this.degattransport = degattransport;
	}
	
	
	
	public Degat(Long id, String type,
			@NotNull(message = "s'il vous plais  entrer la description de degat ") String description, Double value,
			Accident accident, Collection<DegatVictime> degatvictime, Collection<DegatTransport> degattransport) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.value = value;
		this.accident = accident;
		this.degatvictime = degatvictime;
		this.degattransport = degattransport;
	}
	public Degat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
