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
public class DegatVictime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "victime_id")
	
	private Victime victime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DegatVictime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DegatVictime(Long id, Victime victime, Degat degat) {
		super();
		this.id = id;
		this.victime = victime;
		this.degat = degat;
	}
	
}
