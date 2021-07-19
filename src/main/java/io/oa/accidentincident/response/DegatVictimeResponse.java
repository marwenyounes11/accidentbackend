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
import io.oa.accidentincident.entity.Victime;

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


public class DegatVictimeResponse  implements Serializable{
	
	private Long id;
	private Victime victime;
	private Degat degat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Victime getVictime() {
		return victime;
	}
	public void setVictime(Victime victime) {
		this.victime = victime;
	}
	public Degat getDegat() {
		return degat;
	}
	public void setDegat(Degat degat) {
		this.degat = degat;
	}
	public DegatVictimeResponse(Long id, Victime victime, Degat degat) {
		super();
		this.id = id;
		this.victime = victime;
		this.degat = degat;
	}
	public DegatVictimeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
