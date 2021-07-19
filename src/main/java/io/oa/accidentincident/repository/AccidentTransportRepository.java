package io.oa.accidentincident.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Query;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.response.AccidentInformResponse;
import io.oa.accidentincident.response.AccidentTransportResponse;

@RepositoryRestResource
@CrossOrigin("*")
public interface AccidentTransportRepository extends JpaRepository<AccidentTransport, Long>{
	/*@Query("SELECT new io.oa.accidentincident.dto.IncidentJournalierDto(d.nameDepot,a.dateAccident,l.nameLigne,mt.immatriculation,mt.type,li.station,ag.name,ag.lastName,a.degats )"
			+
			"FROM AccidentTransport at,Accident a,MoyenTransport mt,Depot d ,Ligne l,Agent ag,LieuxAccident li,Degat dg  WHERE a.id=at.accident and mt.id=at.moyentransport and l.id=mt.ligne and d.id=mt.depot and ag.id=mt.agent and li.id=a.lieux and a.id=dg.accident "
			) List <IncidentJournalierDto> fetchListIncidentJournalier();*/
	

	
	
	@Query("SELECT new io.oa.accidentincident.response.AccidentTransportResponse(a.id,a.accident,a.moyentransport  ) "
			  +
			  "FROM AccidentTransport a   "
			  )
	public Page<AccidentTransportResponse> pageAccidentTransport(Pageable pageable);
	

	
	

}
