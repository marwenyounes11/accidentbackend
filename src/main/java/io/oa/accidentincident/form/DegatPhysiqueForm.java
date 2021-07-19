package io.oa.accidentincident.form;


import javax.validation.constraints.NotNull;



public class DegatPhysiqueForm {
	private Long id;
	@NotNull(message = "s'il vous plais  entrer la description de degat ")
	private String description;

	private Long idAccident;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Long getIdAccident() {
		return idAccident;
	}

	public void setIdAccident(Long idAccident) {
		this.idAccident = idAccident;
	}

	

	

	public DegatPhysiqueForm(Long id,
			@NotNull(message = "s'il vous plais  entrer la description de degat ") String description,
			Long idAccident) {
		super();
		this.id = id;
		this.description = description;
		this.idAccident = idAccident;
	}

	public DegatPhysiqueForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
