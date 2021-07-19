package io.oa.accidentincident.form;

public class AccidentTransportForm {
	private Long id;	
	private Long idaccident;
	private Long idtransport;
	
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
	public Long getIdtransport() {
		return idtransport;
	}
	public void setIdtransport(Long idtransport) {
		this.idtransport = idtransport;
	}
	
	public AccidentTransportForm() {
		
	}
	public AccidentTransportForm(Long id, Long idaccident, Long idtransport) {
		super();
		this.id = id;
		this.idaccident = idaccident;
		this.idtransport = idtransport;
	}
	
	
	
}
