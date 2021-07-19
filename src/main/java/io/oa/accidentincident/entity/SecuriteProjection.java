package io.oa.accidentincident.entity;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p10",types= {io.oa.accidentincident.entity.Securite.class})
public interface SecuriteProjection {
	public long getId();
	public String getType();
	public String getAddress();
	public SourceInform getSourceinform();
	public Set<Huissier> getHuissiers();
	

}
