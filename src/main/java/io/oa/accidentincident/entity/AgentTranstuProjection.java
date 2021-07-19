package io.oa.accidentincident.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p9",types= {io.oa.accidentincident.entity.AgentTranstu.class})
public interface AgentTranstuProjection {
	public long getId();
	public String getFunction() ;
	public String getName();
	public String getLastName() ;
	public String getMatricule() ;
	public String getPhone();
	public SourceInform getSourceinform();
	

}
