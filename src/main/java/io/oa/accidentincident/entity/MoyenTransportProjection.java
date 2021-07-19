package io.oa.accidentincident.entity;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p4",types= {io.oa.accidentincident.entity.MoyenTransport.class})
public interface MoyenTransportProjection {
	public long getId();
	public String getGage(); 
	public String getImmatriculation(); 
	public String getMark();
	public String getModel(); 
	public String getNumTransport(); 
	public String getType(); 
	public Ligne getLigne(); 
	public District getDistrict(); 
	public Depot getDepot(); 
	public Agent getAgent();
	public Set<AccidentTransport> getAccidenttransports();
	public Collection<Materiel> getMateriels();
	public Collection<DegatTransport> getDegattransport() ;
	
}
