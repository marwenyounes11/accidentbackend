package io.oa.accidentincident.response;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Accident;

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


public class DegatPhysiqueResponse  implements Serializable{
	
	private Long id;
	private String type;
	private String description;
	private Accident accident;
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
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	public DegatPhysiqueResponse(Long id, String type, String description, Accident accident) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.accident = accident;
	}
	public DegatPhysiqueResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
