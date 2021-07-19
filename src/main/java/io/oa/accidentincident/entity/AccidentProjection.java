package io.oa.accidentincident.entity;
import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types= {io.oa.accidentincident.entity.Accident.class})
public interface AccidentProjection {
	public long getId();
	public void setId(Long id); 
	public String getType();
	public void setType(String type) ;
	public Date getDateAccident();
	public void setDateAccident(Date dateAccident);
	public Date getHeureAccident();
	public void setHeureAccident(Date heureAccident);
	public String getDescription() ;
	public void setDescription(String description);
	public String getImage() ;
	public void setImage(String image);
	public String getSubType();
	public void setSubType(String subType);
	public Set<Degat> getDegats(); 
	public void setDegats(Set<Degat> degats);
	public LieuxAccident getLieux(); 
	public void setLieux(LieuxAccident lieux);
	public Set<Huissier> getHuissiers() ;
	public void setHuissiers(Set<Huissier> huissiers);
	public Set<AccidentInform> getAccidentinforms() ;
	public void setAccidentinforms(Set<AccidentInform> accidentinforms);
	public Set<AccidentTransport> getAccidenttransports() ;
	public void setAccidenttransports(Set<AccidentTransport> accidenttransports);
}
