package io.oa.accidentincident.form;

public class DegatVictimeForm {
	private Long id;	
	private Long iddegat;
	private Long idvictime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIddegat() {
		return iddegat;
	}
	public void setIddegat(Long iddegat) {
		this.iddegat = iddegat;
	}
	public Long getIdvictime() {
		return idvictime;
	}
	public void setIdvictime(Long idvictime) {
		this.idvictime = idvictime;
	}
	public DegatVictimeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DegatVictimeForm(Long id, Long iddegat, Long idvictime) {
		super();
		this.id = id;
		this.iddegat = iddegat;
		this.idvictime = idvictime;
	}
	
	
	
}
