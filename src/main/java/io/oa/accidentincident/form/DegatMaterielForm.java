package io.oa.accidentincident.form;


import javax.validation.constraints.NotNull;



public class DegatMaterielForm {
	private Long id;
	private Double value;
	@NotNull(message = "s'il vous plais  entrer la description de degat ")
	private String description;
	private Long idAccident;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
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
	public DegatMaterielForm() {
		
	}
	public DegatMaterielForm(Long id, Double value,
			@NotNull(message = "s'il vous plais  entrer la description de degat ") String description,
			Long idAccident) {
		super();
		this.id = id;
		this.value = value;
		this.description = description;
		this.idAccident = idAccident;
	}
	
	
	
	
}
