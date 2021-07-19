package io.oa.accidentincident.entity;
import java.io.Serializable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Id;
@Entity
public class Ligne  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le nom de ligne ")
	private String nameLigne;
	@OneToMany(mappedBy="ligne",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
	private Collection<MoyenTransport> moyenTransports ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameLigne() {
		return nameLigne;
	}
	public void setNameLigne(String nameLigne) {
		this.nameLigne = nameLigne;
	}
	public Collection<MoyenTransport> getMoyenTransports() {
		return moyenTransports;
	}
	public void setMoyenTransports(Collection<MoyenTransport> moyenTransports) {
		this.moyenTransports = moyenTransports;
	}
	public Ligne() {
		
	}
	public Ligne(Long id, @NotNull(message = "s'il vous plais  entrer le nom de ligne ") String nameLigne,
			Collection<MoyenTransport> moyenTransports) {
		super();
		this.id = id;
		this.nameLigne = nameLigne;
		this.moyenTransports = moyenTransports;
	}
	

}
