package io.oa.accidentincident.entity;
import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p21",types= {io.oa.accidentincident.entity.DegatVictime.class})
public interface DegatVictimeProjection {
	public long getId();
	public Victime getVictime();
	public Degat getDegat() ;
	
	
}
