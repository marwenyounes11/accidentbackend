package io.oa.accidentincident.entity;
import io.oa.accidentincident.entity.SourceInform;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p5",types= {io.oa.accidentincident.entity.AccidentInform.class})
public interface AccidentInformProjection {
	public long getId();
	public Accident getAccident(); 
	public SourceInform getSourceinform(); 
	public Date getDateInformation() ;
	public Date getHeureInformation() ;
	public String getDescription() ;
}
