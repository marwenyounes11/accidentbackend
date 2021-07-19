package io.oa.accidentincident.entity;
import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p14",types= {io.oa.accidentincident.entity.Departement.class})
public interface DepartementProjection {
    public Long getId() ;
	public String getNameDepartement() ;
	public Collection<Materiel> getMateriels() ;
	public LieuxAccident getLieux() ;
}
