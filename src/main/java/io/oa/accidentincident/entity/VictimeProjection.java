package io.oa.accidentincident.entity;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p20",types= {io.oa.accidentincident.entity.Victime.class})
public interface VictimeProjection {
	public Long getId() ;
		
	public String getCorpBlesser() ;
	public String getNiveauBlessure() ;
	public String getEtatVictime() ;
	public String getTypeVictime() ;
	public String getLastNameVictime() ;
	public String getNameVictime() ;
	public Collection<DegatVictime> getDegatvictime() ;
}
