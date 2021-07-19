package io.oa.accidentincident.entity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;

import java.util.Collection;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class LieuxAccident  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "s'il vous plais  entrer la longitude")
	private String longitude;
	@NotNull(message = "s'il vous plais  entrer la latitude")
	private String latitude;
	@NotNull(message = "s'il vous plais  entrer la station")
	private String emplacement;
	@OneToMany(mappedBy = "lieux", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
   Set<Accident> accidents;
	@OneToMany(mappedBy="lieux",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 @JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Departement> departements ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public Set<Accident> getAccidents() {
		return accidents;
	}
	public void setAccidents(Set<Accident> accidents) {
		this.accidents = accidents;
	}
	public LieuxAccident() {
		
	}
	public LieuxAccident(Long id, @NotNull(message = "s'il vous plais  entrer la longitude") String longitude,
			@NotNull(message = "s'il vous plais  entrer la latitude") String latitude,
			@NotNull(message = "s'il vous plais  entrer la station") String emplacement, Set<Accident> accidents) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.emplacement = emplacement;
		this.accidents = accidents;
	}
	
}
