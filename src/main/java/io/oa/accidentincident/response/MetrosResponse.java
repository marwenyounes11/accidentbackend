package io.oa.accidentincident.response;
import javax.validation.constraints.NotNull;

import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.District;
import io.oa.accidentincident.entity.Ligne;
public class MetrosResponse {
	private Long id;
	private String gage;
    private String immatriculation ;
    private String numTransport ;
	private Ligne ligne;
   	private District district;
   	private Depot depot;
	private Agent agent;
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
	public String getNumTransport() {
		return numTransport;
	}
	public void setNumTransport(String numTransport) {
		this.numTransport = numTransport;
	}
	public Ligne getLigne() {
		return ligne;
	}
	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public MetrosResponse(Long id, String gage, String immatriculation, String numTransport, Ligne ligne,
			District district, Depot depot, Agent agent) {
		super();
		this.id = id;
		this.gage = gage;
		this.immatriculation = immatriculation;
		this.numTransport = numTransport;
		this.ligne = ligne;
		this.district = district;
		this.depot = depot;
		this.agent = agent;
	}
	public MetrosResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
