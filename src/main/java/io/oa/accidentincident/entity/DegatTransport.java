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
public class DegatTransport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "transport_id")
	@JsonProperty(access=Access.WRITE_ONLY)
	private MoyenTransport moyentransport;
	@ManyToOne
	@JoinColumn(name = "degat_id")
	@JsonProperty(access=Access.WRITE_ONLY)
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DegatTransport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DegatTransport(Long id, MoyenTransport moyentransport, Degat degat) {
		super();
		this.id = id;
		this.moyentransport = moyentransport;
		this.degat = degat;
	}
	
	
}
