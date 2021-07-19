package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class DepartementForm {
	private Long id;	
	private String nameDepartement;
	private Long idlieux;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameDepartement() {
		return nameDepartement;
	}
	public void setNameDepartement(String nameDepartement) {
		this.nameDepartement = nameDepartement;
	}
	public Long getIdlieux() {
		return idlieux;
	}
	public void setIdlieux(Long idlieux) {
		this.idlieux = idlieux;
	}
	public DepartementForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartementForm(Long id, String nameDepartement, Long idlieux) {
		super();
		this.id = id;
		this.nameDepartement = nameDepartement;
		this.idlieux = idlieux;
	}
	
	
	
	
	
}
