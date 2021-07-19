package io.oa.accidentincident.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.Materiel;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.response.BoucheIncendieResponse;
import io.oa.accidentincident.response.BusResponse;
import io.oa.accidentincident.response.ExtincteurResponse;
import io.oa.accidentincident.response.PoteauxIncendieResponse;
import io.oa.accidentincident.response.RobinetIncendieResponse;

@Repository
@Transactional
public interface MaterielRepository extends JpaRepository<Materiel, Long> {
	@Modifying 
	 @Query(value = "DELETE FROM Materiel m WHERE m.id = :id")       
	    void deleteMateriel(@Param("id") Long id);
	@Query("select m from Materiel m where m.dateAcquisition like :x ")
	Page<Materiel> chercherMateriel(@Param("x")Date mc,Pageable pageable);
	
	@Query("select m from Materiel m")
	Page<Materiel> pageMateriel(Pageable pageable); 
	
	@Query("select m from Materiel m")
	List<Materiel> listMateriel();
@Query("SELECT new io.oa.accidentincident.response.ExtincteurResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.subtype,m.image,m.emplacement,m.moyentransport,m.depot,m.departement) "
			  +
			  "FROM Materiel m  where m.type='extincteur' and m.dateAcquisition like :x "
			  ) Page<ExtincteurResponse> chercherExtincteur(@Param("x")Date mc,Pageable pageable);
	
		@Query("SELECT new io.oa.accidentincident.response.ExtincteurResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.subtype,m.image,m.emplacement,m.moyentransport,m.depot,m.departement) "
			  +
			  "FROM Materiel m  where m.type='extincteur'"
			  ) Page<ExtincteurResponse> pageExtincteur(Pageable pageable); 
	
	@Query("SELECT new io.oa.accidentincident.response.ExtincteurResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.subtype,m.image,m.emplacement,m.moyentransport,m.depot,m.departement) "
			  +
			  "FROM Materiel m  where m.type='extincteur'"
			  )List<ExtincteurResponse> listExtincteur();
	
	
	
	@Query("SELECT new io.oa.accidentincident.response.PoteauxIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.coleurpoteaux,m.departement) "
			  +
			  "FROM Materiel m  where m.type='poteauxincendie' and m.dateAcquisition like :x "
			  ) Page<PoteauxIncendieResponse> chercherPoteaux(@Param("x")Date mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.PoteauxIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.coleurpoteaux,m.departement) "
			  +
			  "FROM Materiel m  where m.type='poteauxincendie'"
			  ) Page<PoteauxIncendieResponse> pagePoteaux(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.PoteauxIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.coleurpoteaux,m.departement) "
			  +
			  "FROM Materiel m  where m.type='poteauxincendie'"
			  )List<PoteauxIncendieResponse> listPoteaux();
	
	@Query("SELECT new io.oa.accidentincident.response.BoucheIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.nombrebouche,m.departement) "
			  +
			  "FROM Materiel m  where m.type='boucheincendie' and m.dateAcquisition like :x "
			  ) Page<BoucheIncendieResponse> chercherBouche(@Param("x")Date mc,Pageable pageable);
	
		@Query("SELECT new io.oa.accidentincident.response.BoucheIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.nombrebouche,m.departement) "
			  +
			  "FROM Materiel m  where m.type='boucheincendie'"
			  ) Page<BoucheIncendieResponse> pageBouche(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.BoucheIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.nombrebouche,m.departement) "
			  +
			  "FROM Materiel m  where m.type='boucheincendie'"
			  )List<BoucheIncendieResponse> listBouche();
	
	
	
	@Query("SELECT new io.oa.accidentincident.response.RobinetIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.length,m.diametre,m.departement) "
			  +
			  "FROM Materiel m  where m.type='robinetincendie' and m.dateAcquisition like :x "
			  ) Page<RobinetIncendieResponse> chercherRobinet(@Param("x")Date mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.RobinetIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.length,m.diametre,m.departement) "
			  +
			  "FROM Materiel m  where m.type='robinetincendie'"
			  ) Page<RobinetIncendieResponse> pageRobinet(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.RobinetIncendieResponse(m.id,m.description,m.numberSerie,m.dateAcquisition,m.heureAcquisition,m.dateMaintenance,m.heureMaintenance,m.datePeremption,m.heurePeremption,m.image,m.emplacement,m.length,m.diametre,m.departement) "
			  +
			  "FROM Materiel m  where m.type='robinetincendie'"
			  )List<RobinetIncendieResponse> listRobinet();
	
	
	
	
}
