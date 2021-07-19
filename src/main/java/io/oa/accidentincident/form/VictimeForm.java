package io.oa.accidentincident.form;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class VictimeForm {
	private Long id;
	private String corpBlesser ;
	private String niveauBlessure ;
	private String etatVictime;
	private String typeVictime;
	private String lastNameVictime ;
	private String nameVictime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorpBlesser() {
		return corpBlesser;
	}
	public void setCorpBlesser(String corpBlesser) {
		this.corpBlesser = corpBlesser;
	}
	public String getNiveauBlessure() {
		return niveauBlessure;
	}
	public void setNiveauBlessure(String niveauBlessure) {
		this.niveauBlessure = niveauBlessure;
	}
	public String getEtatVictime() {
		return etatVictime;
	}
	public void setEtatVictime(String etatVictime) {
		this.etatVictime = etatVictime;
	}
	public String getTypeVictime() {
		return typeVictime;
	}
	public void setTypeVictime(String typeVictime) {
		this.typeVictime = typeVictime;
	}
	public String getLastNameVictime() {
		return lastNameVictime;
	}
	public void setLastNameVictime(String lastNameVictime) {
		this.lastNameVictime = lastNameVictime;
	}
	public String getNameVictime() {
		return nameVictime;
	}
	public void setNameVictime(String nameVictime) {
		this.nameVictime = nameVictime;
	}
	public VictimeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VictimeForm(Long id, String corpBlesser, String niveauBlessure, String etatVictime, String typeVictime,
			String lastNameVictime, String nameVictime) {
		super();
		this.id = id;
		this.corpBlesser = corpBlesser;
		this.niveauBlessure = niveauBlessure;
		this.etatVictime = etatVictime;
		this.typeVictime = typeVictime;
		this.lastNameVictime = lastNameVictime;
		this.nameVictime = nameVictime;
	}	
	
	
	
	
	
}
