package io.oa.accidentincident.form;
import javax.validation.constraints.NotNull;
public class BusForm {
	private Long id;
	@NotNull(message = "s'il vous plais  entrer le gabarit  ")
	private String gage;
	@NotNull(message = "s'il vous plais  entrer l'immatriculation ")
    private String immatriculation ;
    private String mark ; 
    private String model ;
	@NotNull(message = "s'il vous plais  entrer le numero de transport ")
    private String numTransport ;
	@NotNull(message = "s'il vous plais  entrer le type de transport ")
   	private Long idligne;
   	private Long iddistrict;
   	private Long iddepot;
	private Long idagent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGage() {
		return gage;
	}
	public void setGage(String gage) {
		this.gage = gage;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNumTransport() {
		return numTransport;
	}
	public void setNumTransport(String numTransport) {
		this.numTransport = numTransport;
	}
	public Long getIdligne() {
		return idligne;
	}
	public void setIdligne(Long idligne) {
		this.idligne = idligne;
	}
	public Long getIddistrict() {
		return iddistrict;
	}
	public void setIddistrict(Long iddistrict) {
		this.iddistrict = iddistrict;
	}
	public Long getIddepot() {
		return iddepot;
	}
	public void setIddepot(Long iddepot) {
		this.iddepot = iddepot;
	}
	public Long getIdagent() {
		return idagent;
	}
	public void setIdagent(Long idagent) {
		this.idagent = idagent;
	}
	public BusForm(Long id, @NotNull(message = "s'il vous plais  entrer le gabarit  ") String gage,
			@NotNull(message = "s'il vous plais  entrer l'immatriculation ") String immatriculation, String mark,
			String model, @NotNull(message = "s'il vous plais  entrer le numero de transport ") String numTransport,
			@NotNull(message = "s'il vous plais  entrer le type de transport ") Long idligne, Long iddistrict,
			Long iddepot, Long idagent) {
		super();
		this.id = id;
		this.gage = gage;
		this.immatriculation = immatriculation;
		this.mark = mark;
		this.model = model;
		this.numTransport = numTransport;
		this.idligne = idligne;
		this.iddistrict = iddistrict;
		this.iddepot = iddepot;
		this.idagent = idagent;
	}
	public BusForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
