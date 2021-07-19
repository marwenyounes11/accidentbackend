package io.oa.accidentincident.form;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;
import io.oa.accidentincident.entity.Securite;

public class RapportAccidentForm {
	private Long id;	
	private Set<Securite> securites ;
	private Set<AgentTranstu> agenttranstus ;
	private Set<Ambulancier> ambulanciers ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Securite> getSecurites() {
		return securites;
	}
	public void setSecurites(Set<Securite> securites) {
		this.securites = securites;
	}
	public Set<AgentTranstu> getAgenttranstus() {
		return agenttranstus;
	}
	public void setAgenttranstus(Set<AgentTranstu> agenttranstus) {
		this.agenttranstus = agenttranstus;
	}
	public Set<Ambulancier> getAmbulanciers() {
		return ambulanciers;
	}
	public void setAmbulanciers(Set<Ambulancier> ambulanciers) {
		this.ambulanciers = ambulanciers;
	}
	public RapportAccidentForm(Long id, Set<Securite> securites, Set<AgentTranstu> agenttranstus,
			Set<Ambulancier> ambulanciers) {
		super();
		this.id = id;
		this.securites = securites;
		this.agenttranstus = agenttranstus;
		this.ambulanciers = ambulanciers;
	}
	public RapportAccidentForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
