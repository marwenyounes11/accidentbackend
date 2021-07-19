package io.oa.accidentincident.entity;
import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p3",types= {io.oa.accidentincident.entity.Degat.class})
public interface DegatProjection {
	public long getId();
	public String getType();
	public String getDescription(); 
	public String getValue();
	public int getNumberWounded() ;
	public int getNumberDead() ;
	public Accident getAccident();
	public Collection<DegatVictime> getDegatvictime() ;
	public Collection<DegatTransport> getDegattransport() ;
	
	
}
