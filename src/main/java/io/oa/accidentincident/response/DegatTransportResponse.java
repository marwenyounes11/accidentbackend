package io.oa.accidentincident.response;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.MoyenTransport;

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


public class DegatTransportResponse  implements Serializable{
	
	private Long id;
	private MoyenTransport moyentransport;
	private Degat degat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MoyenTransport getMoyentransport() {
		return moyentransport;
	}
	public void setMoyentransport(MoyenTransport moyentransport) {
		this.moyentransport = moyentransport;
	}
	public Degat getDegat() {
		return degat;
	}
	public void setDegat(Degat degat) {
		this.degat = degat;
	}
	
	public DegatTransportResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DegatTransportResponse(Long id, MoyenTransport moyentransport, Degat degat) {
		super();
		this.id = id;
		this.moyentransport = moyentransport;
		this.degat = degat;
	}
	
	
	
	
	
}
