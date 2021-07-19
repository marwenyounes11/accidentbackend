package io.oa.accidentincident.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.MoyenTransport;


public class AccidentTransportResponse implements Serializable {

	private Long id;
	private Accident accident;
	private MoyenTransport moyentransport;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	public MoyenTransport getMoyentransport() {
		return moyentransport;
	}
	public void setMoyentransport(MoyenTransport moyentransport) {
		this.moyentransport = moyentransport;
	}
	public AccidentTransportResponse(Long id, Accident accident, MoyenTransport moyentransport) {
		super();
		this.id = id;
		this.accident = accident;
		this.moyentransport = moyentransport;
	}
	public AccidentTransportResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
