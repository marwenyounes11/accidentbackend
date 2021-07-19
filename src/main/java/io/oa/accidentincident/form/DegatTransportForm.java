package io.oa.accidentincident.form;

public class DegatTransportForm {
	private Long id;	
	private Long iddegat;
	private Long idtransport;
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
	public Long getIdtransport() {
		return idtransport;
	}
	public void setIdtransport(Long idtransport) {
		this.idtransport = idtransport;
	}
	public DegatTransportForm(Long id, Long iddegat, Long idtransport) {
		super();
		this.id = id;
		this.iddegat = iddegat;
		this.idtransport = idtransport;
	}
	public DegatTransportForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
