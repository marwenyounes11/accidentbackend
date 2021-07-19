package io.oa.accidentincident.entity;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p11",types= {io.oa.accidentincident.entity.Ambulancier.class})
public interface AmbulancierProjection {
	public long getId();
	public String getName();
	public String getLastName();
	public String getMatricule() ;
	public String getPhone();
	public SourceInform getSourceinform();

}
