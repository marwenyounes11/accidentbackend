package io.oa.accidentincident.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class AccidentInformForm {
	private Long id;	
	private Long idaccident;
	private Long idsourceinform;
	@NotNull(message = "s'il vous plais  entrer la date d'information")
	private String dateInformation;
	@NotNull(message = "s'il vous plais  entrer le time d'information")
	 private String heureInformation;
	@NotNull(message = "s'il vous plais  entrer la description d'information")
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdaccident() {
		return idaccident;
	}
	public void setIdaccident(Long idaccident) {
		this.idaccident = idaccident;
	}
	public Long getIdsourceinform() {
		return idsourceinform;
	}
	public void setIdsourceinform(Long idsourceinform) {
		this.idsourceinform = idsourceinform;
	}
	public String getDateInformation() {
		return dateInformation;
	}
	public void setDateInformation(String dateInformation) {
		this.dateInformation = dateInformation;
	}
	public String getHeureInformation() {
		return heureInformation;
	}
	public void setHeureInformation(String heureInformation) {
		this.heureInformation = heureInformation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AccidentInformForm(Long id, Long idaccident, Long idsourceinform,
			@NotNull(message = "s'il vous plais  entrer la date d'information") String dateInformation,
			@NotNull(message = "s'il vous plais  entrer le time d'information") String heureInformation,
			@NotNull(message = "s'il vous plais  entrer la description d'information") String description) {
		super();
		this.id = id;
		this.idaccident = idaccident;
		this.idsourceinform = idsourceinform;
		this.dateInformation = dateInformation;
		this.heureInformation = heureInformation;
		this.description = description;
	}
	public AccidentInformForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
