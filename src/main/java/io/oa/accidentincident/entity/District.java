package io.oa.accidentincident.entity;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class District  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom de depot")
	private String nameDistrict;
	@OneToMany(mappedBy="district",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
	private Collection<MoyenTransport> moyenTransports ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameDistrict() {
		return nameDistrict;
	}
	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}
	public Collection<MoyenTransport> getMoyenTransports() {
		return moyenTransports;
	}
	public void setMoyenTransports(Collection<MoyenTransport> moyenTransports) {
		this.moyenTransports = moyenTransports;
	}
	public District() {
		super();
		// TODO Auto-generated constructor stub
	}
	public District(Long id, @NotNull(message = "s'il vous plais  entrer le nom de depot") String nameDistrict,
			Collection<MoyenTransport> moyenTransports) {
		super();
		this.id = id;
		this.nameDistrict = nameDistrict;
		this.moyenTransports = moyenTransports;
	}
	

}
