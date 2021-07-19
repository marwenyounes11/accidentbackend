package io.oa.accidentincident.form;


import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.Huissier;
import io.oa.accidentincident.entity.LieuxAccident;



public class AccidentByTransportForm {
	Long id;
	String type;
	 Date dateAccident;
	 Date heureAccident;
	 String description; 
	 String image;
	String subType;
	Set<Degat> degats;
	LieuxAccident lieux;
	Set<Huissier> huissiers;
	Set<AccidentInform> accidentinforms;
	Set<AccidentTransport> accidenttransports;
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
	public Date getDateAccident() {
		return dateAccident;
	}
	public void setDateAccident(Date dateAccident) {
		this.dateAccident = dateAccident;
	}
	public Date getHeureAccident() {
		return heureAccident;
	}
	public void setHeureAccident(Date heureAccident) {
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
	public Set<Degat> getDegats() {
		return degats;
	}
	public void setDegats(Set<Degat> degats) {
		this.degats = degats;
	}
	public LieuxAccident getLieux() {
		return lieux;
	}
	public void setLieux(LieuxAccident lieux) {
		this.lieux = lieux;
	}
	public Set<Huissier> getHuissiers() {
		return huissiers;
	}
	public void setHuissiers(Set<Huissier> huissiers) {
		this.huissiers = huissiers;
	}
	public Set<AccidentInform> getAccidentinforms() {
		return accidentinforms;
	}
	public void setAccidentinforms(Set<AccidentInform> accidentinforms) {
		this.accidentinforms = accidentinforms;
	}
	public Set<AccidentTransport> getAccidenttransports() {
		return accidenttransports;
	}
	public void setAccidenttransports(Set<AccidentTransport> accidenttransports) {
		this.accidenttransports = accidenttransports;
	}
	public AccidentByTransportForm(Long id, String type, Date dateAccident, Date heureAccident, String description, String image,
			String subType, Set<Degat> degats, LieuxAccident lieux, Set<Huissier> huissiers,
			Set<AccidentInform> accidentinforms, Set<AccidentTransport> accidenttransports) {
		super();
		this.id = id;
		this.type = type;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.description = description;
		this.image = image;
		this.subType = subType;
		this.degats = degats;
		this.lieux = lieux;
		this.huissiers = huissiers;
		this.accidentinforms = accidentinforms;
		this.accidenttransports = accidenttransports;
	}
	public AccidentByTransportForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
