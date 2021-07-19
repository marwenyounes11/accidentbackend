package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class SecuriteForm {
	private Long id;	
	@NotNull(message = "s'il vous plais  entrer le type d'unité")
	private String type;
	@NotNull(message = "s'il vous plais  entrer l'adress d'unité")
	private String address;
	private Long idsourceinform;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public Long getIdsourceinform() {
		return idsourceinform;
	}
	public void setIdsourceinform(Long idsourceinform) {
		this.idsourceinform = idsourceinform;
	}
	public SecuriteForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SecuriteForm(Long id, @NotNull(message = "s'il vous plais  entrer le type d'unité") String type,
			@NotNull(message = "s'il vous plais  entrer l'adress d'unité") String address, Long idsourceinform) {
		super();
		this.id = id;
		this.type = type;
		this.address = address;
		this.idsourceinform = idsourceinform;
	}
	
	
	
	
}
