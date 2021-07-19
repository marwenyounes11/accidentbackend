package io.oa.accidentincident.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.response.DegatMaterielResponse;
import io.oa.accidentincident.response.DegatPhysiqueResponse;
import io.oa.accidentincident.response.DegatResponse;
import io.oa.accidentincident.response.EstimationPrixDegatParAnner;
import io.oa.accidentincident.response.EstimationPrixDegatParDate;
import io.oa.accidentincident.response.EstimationPrixDegatParSousTypeAccident;
import io.oa.accidentincident.response.EstimationPrixDegatParTypeAccident;

@RepositoryRestResource
@CrossOrigin("*")
public interface DegatRepository extends JpaRepository<Degat, Long> {
	
	
	@Query("SELECT new io.oa.accidentincident.response.DegatPhysiqueResponse(d.id,d.type, d.description,d.accident  ) "
			  +
			  "FROM Degat d WHERE  d.type='physique'"
			  )  Page<DegatPhysiqueResponse> pageDegatPhysique(Pageable pageable);
	

	@Query("SELECT new io.oa.accidentincident.response.DegatMaterielResponse(d.id,d.type, d.description,d.value,d.accident  ) "
			  +
			  "FROM Degat d WHERE  d.type='materiel'"
			  )	public Page<DegatMaterielResponse> pageDegatMateriel(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.DegatResponse(d.id,d.type, d.description,d.value,d.accident  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident "
			  ) List<DegatResponse> listDegat();
	
	@Query("SELECT new io.oa.accidentincident.response.DegatPhysiqueResponse(d.id,d.type, d.description,d.accident  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='physique'"
			  ) List<DegatPhysiqueResponse> listDegatPhysique();
	
	@Query("SELECT new io.oa.accidentincident.response.DegatMaterielResponse(d.id,d.type, d.description,d.value,d.accident  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='materiel'"
			  ) List<DegatMaterielResponse> listDegatMateriel();
	
	@Query("SELECT new io.oa.accidentincident.response.DegatPhysiqueResponse(d.id,d.type, d.description,d.accident  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='physique' and d.id = :id "
			  ) DegatPhysiqueResponse degatPhysique(@Param("id") Long id);
	
	@Query("SELECT new io.oa.accidentincident.response.DegatMaterielResponse(d.id,d.type, d.description,d.value,d.accident  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='materiel' and d.id = :id "
			  ) DegatMaterielResponse degatMateriel(@Param("id") Long id);
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParDate(a.dateAccident, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='materiel' GROUP BY a.dateAccident "
			  ) List<EstimationPrixDegatParDate> listEstimationPrixDegatParDate();
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='collision' AND m.type='bus' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatBusCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='route' AND m.type='bus' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatBusRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='collision' AND m.type='metro' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatMetrosCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='route' AND m.type='metro' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatMetrosRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='collision' AND m.type='tgm' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatTgmCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParAnner(YEAR( a.dateAccident ) AS annees, sum( d.value) as estimationPrixDegat  ) "
			  +
			  "FROM Degat d,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.id=d.accident AND d.type='materiel' AND a.type='route' AND m.type='tgm' GROUP BY YEAR( a.dateAccident ) "
			  ) List<EstimationPrixDegatParAnner> listEstimationPrixDegatTgmRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParTypeAccident(a.type, SUM( d.value) AS estimationPrixDegat  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='materiel' GROUP BY a.type "
			  ) List<EstimationPrixDegatParTypeAccident> listEstimationPrixDegatParTypeAccident();
	
	@Query("SELECT new io.oa.accidentincident.response.EstimationPrixDegatParSousTypeAccident(a.subType,SUM( d.value) AS estimationPrixDegat  ) "
			  +
			  "FROM Degat d,Accident a WHERE a.id=d.accident and d.type='materiel' GROUP BY a.subType "
			  ) List<EstimationPrixDegatParSousTypeAccident> listEstimationPrixDegatParSousTypeAccident();
	
}
