package io.oa.accidentincident.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p6",types= {io.oa.accidentincident.entity.AccidentTransport.class})
public interface AccidentTransportProjection {
	public long getId();
	public Accident getAccident(); 
	public MoyenTransport getMoyentransport(); 
	

}
