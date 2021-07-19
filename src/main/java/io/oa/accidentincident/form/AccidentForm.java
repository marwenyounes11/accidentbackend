package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class AccidentForm {
	private Long id;	
	@NotNull(message = "s'il vous plais  entrer la date d'accident")
	private String dateAccident;
	@NotNull(message = "s'il vous plais  entrer le time d'accident")
	 private String heureAccident;
	@NotNull(message = "s'il vous plais  entrer la description")
	private String description;
	private String image;
	private String subType;
	private Long idlieux;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(String dateAccident) {
		this.dateAccident = dateAccident;
	}
	public String getHeureAccident() {
		return heureAccident;
	}
	public void setHeureAccident(String heureAccident) {
		this.heureAccident = heureAccident;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public Long getIdlieux() {
		return idlieux;
	}
	public void setIdlieux(Long idlieux) {
		this.idlieux = idlieux;
	}
	public AccidentForm(Long id, @NotNull(message = "s'il vous plais  entrer la date d'accident") String dateAccident,
			@NotNull(message = "s'il vous plais  entrer le time d'accident") String heureAccident,
			@NotNull(message = "s'il vous plais  entrer la description") String description, String image,
			String subType, Long idlieux) {
		super();
		this.id = id;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.description = description;
		this.image = image;
		this.subType = subType;
		this.idlieux = idlieux;
	}
	public AccidentForm() {
		
	}
	
	
	
	
	
}
