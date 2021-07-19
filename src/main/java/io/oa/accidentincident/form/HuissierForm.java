package io.oa.accidentincident.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class HuissierForm {
	private Long id;	
	@NotNull(message = "s'il vous plais  entrer le numero d'huissier")
	private int number;
	@NotNull(message = "s'il vous plais  entrer la date d'huissier")
	private String dateHuissier;
	@NotNull(message = "s'il vous plais  entrer le time d'd'huissier")
	 private String heureHuissier;
	@NotNull(message = "s'il vous plais  entrer la description d'information")
	private Long idaccident;
	private Long idsourcedeclarehuissier;
	private Long idsecurite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDateHuissier() {
		return dateHuissier;
	}
	public void setDateHuissier(String dateHuissier) {
		this.dateHuissier = dateHuissier;
	}
	public String getHeureHuissier() {
		return heureHuissier;
	}
	public void setHeureHuissier(String heureHuissier) {
		this.heureHuissier = heureHuissier;
	}
	public Long getIdaccident() {
		return idaccident;
	}
	public void setIdaccident(Long idaccident) {
		this.idaccident = idaccident;
	}
	public Long getIdsourcedeclarehuissier() {
		return idsourcedeclarehuissier;
	}
	public void setIdsourcedeclarehuissier(Long idsourcedeclarehuissier) {
		this.idsourcedeclarehuissier = idsourcedeclarehuissier;
	}
	public Long getIdsecurite() {
		return idsecurite;
	}
	public void setIdsecurite(Long idsecurite) {
		this.idsecurite = idsecurite;
	}
	public HuissierForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HuissierForm(Long id, @NotNull(message = "s'il vous plais  entrer le numero d'huissier") int number,
			@NotNull(message = "s'il vous plais  entrer la date d'huissier") String dateHuissier,
			@NotNull(message = "s'il vous plais  entrer le time d'd'huissier") String heureHuissier,
			@NotNull(message = "s'il vous plais  entrer la description d'information") Long idaccident,
			Long idsourcedeclarehuissier, Long idsecurite) {
		super();
		this.id = id;
		this.number = number;
		this.dateHuissier = dateHuissier;
		this.heureHuissier = heureHuissier;
		this.idaccident = idaccident;
		this.idsourcedeclarehuissier = idsourcedeclarehuissier;
		this.idsecurite = idsecurite;
	}
	
	
}
