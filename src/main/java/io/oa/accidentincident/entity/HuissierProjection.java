package io.oa.accidentincident.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p12",types= {io.oa.accidentincident.entity.Huissier.class})
public interface HuissierProjection {
	public long getId();
	public int getNumber() ;
	public Date getDateHuissier() ;
	public Date getHeureHuissier();
	public Securite getSecurite();
	public Accident getAccident() ;
	public SourceDeclareHuissier getSourcedeclarehuissier();
}
