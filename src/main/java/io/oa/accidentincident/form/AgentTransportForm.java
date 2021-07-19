package io.oa.accidentincident.form;

public class AgentTransportForm {
	private Long id;	
	private Long idagent;
	private Long idtransport;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdagent() {
		return idagent;
	}
	public void setIdagent(Long idagent) {
		this.idagent = idagent;
	}
	public Long getIdtransport() {
		return idtransport;
	}
	public void setIdtransport(Long idtransport) {
		this.idtransport = idtransport;
	}
	
	public AgentTransportForm() {

	}
	
	public AgentTransportForm(Long id, Long idagent, Long idtransport) {
		super();
		this.id = id;
		this.idagent = idagent;
		this.idtransport = idtransport;
	}
	
	
	
}
