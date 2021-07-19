package io.oa.accidentincident.entity;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p15",types= {io.oa.accidentincident.entity.Materiel.class})
public interface MaterielProjection {
	public Long getId(); 
	public String getType();
	public String getDescription();
	public String getNumberSerie() ;
	public Date getDateAcquisition() ;
	public Date getHeureAcquisition() ;
	public Date getDateMaintenance() ;
	public Date getHeureMaintenance(); 
	public Date getDatePeremption() ;
	public Date getHeurePeremption() ;
	public String getSubtype() ;
	public String getImage() ;
	public String getEmplacement() ;
	public String getLength() ;
	public String getDiametre() ;
	public int getNombrebouche() ;
	public String getColeurpoteaux() ;
	public MoyenTransport getMoyentransport() ;
	public Depot getDepot() ;
	public Departement getDepartement();
	public Collection<Intervention> getInterventions() ;
	
}
