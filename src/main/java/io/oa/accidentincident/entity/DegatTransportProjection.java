package io.oa.accidentincident.entity;
import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p22",types= {io.oa.accidentincident.entity.DegatTransport.class})
public interface DegatTransportProjection {
	public long getId();
	public MoyenTransport getMoyentransport();
	public Degat getDegat() ;
	
	
}
