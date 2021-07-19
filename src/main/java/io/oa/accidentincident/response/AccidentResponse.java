package io.oa.accidentincident.response;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.oa.accidentincident.entity.LieuxAccident;

public class AccidentResponse {
	
	private Long id;
	private String type;
	@Temporal(TemporalType.DATE) 
	private Date dateAccident;
	@Temporal(TemporalType.TIME)
	 private Date heureAccident;
	private String description;
	private byte[] image;
	String extension;
	private String subType;
	private LieuxAccident lieux;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public LieuxAccident getLieux() {
		return lieux;
	}
	public void setLieux(LieuxAccident lieux) {
		this.lieux = lieux;
	}
	public AccidentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccidentResponse(Long id, String type, Date dateAccident, Date heureAccident, String description,
			byte[] image, String extension, String subType, LieuxAccident lieux) {
		super();
		this.id = id;
		this.type = type;
		this.dateAccident = dateAccident;
		this.heureAccident = heureAccident;
		this.description = description;
		this.image = image;
		this.extension = extension;
		this.subType = subType;
		this.lieux = lieux;
	}
	
	
	


}
