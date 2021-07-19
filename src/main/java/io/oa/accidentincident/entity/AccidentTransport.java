package io.oa.accidentincident.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class AccidentTransport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "accident_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Accident accident;
	@ManyToOne
	@JoinColumn(name = "moyentransport_id")
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AccidentTransport() {
		
	}
	public AccidentTransport(Long id, Accident accident, MoyenTransport moyentransport) {
		super();
		this.id = id;
		this.accident = accident;
		this.moyentransport = moyentransport;
	}
	
}
