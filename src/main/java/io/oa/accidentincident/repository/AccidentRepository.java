package io.oa.accidentincident.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.response.NbrAccidentParAnner;
import io.oa.accidentincident.response.NbrAccidentParDate;
import io.oa.accidentincident.response.NbrAccidentParDistrict;
import io.oa.accidentincident.response.NbrAccidentParMois;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransport;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransportDate;
import io.oa.accidentincident.response.NbrAccidentParSousTypeAccident;
import io.oa.accidentincident.response.NbrAccidentParTypeAccident;
import io.oa.accidentincident.response.NbrBlesserExterne;
import io.oa.accidentincident.response.NbrBlesserInterne;
import io.oa.accidentincident.response.NbrBlesserMineurParMois;
import io.oa.accidentincident.response.NbrBlesserParAnner;
import io.oa.accidentincident.response.NbrBlesserParMoisNiveau;
import io.oa.accidentincident.response.NbrBlesserDangerParMois;
import io.oa.accidentincident.response.NbrBlesserParNiveauBlessure;
import io.oa.accidentincident.response.NbrMortsParAnner;
import io.oa.accidentincident.response.NbrMortsParDate;
import io.oa.accidentincident.response.NbrMortsParMois;

@RepositoryRestResource
@CrossOrigin("*")
@Transactional
public interface AccidentRepository extends JpaRepository<Accident, Long> {
	List<Accident> findByDateAccident(Date dateAccident);
	 
	@Query("select a from Accident a where a.subType like :x")
	public Page<Accident> chercherAccident(@Param("x")String mc,Pageable pageable);
	
	@Query("select a from Accident a where a.type='collision'")
	public Page<Accident> pageAccidentCollision(Pageable pageable);
	
	@Query("select a from Accident a where a.type='route'")
	public Page<Accident> pageAccidentRoute(Pageable pageable);
	
	@Query("select a from Accident a where a.type='travail'")
	public Page<Accident> pageAccidentTravail(Pageable pageable);
	
	@Modifying 
	 @Query(value = "DELETE FROM Accident a WHERE a.id = :id")       
	    void deleteAccident(@Param("id") Long id);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParDate(a.dateAccident, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM Accident a  GROUP BY a.dateAccident "
			  ) List<NbrAccidentParDate> listNbrAccidentParDate();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParMois(CASE MONTH(a.dateAccident) WHEN 1 THEN 'janvier' WHEN 2 THEN 'février' WHEN 3 THEN 'mars' WHEN 4 THEN 'avril' WHEN 5 THEN 'mai' WHEN 6 THEN 'juin' WHEN 7 THEN 'juillet' WHEN 8 THEN 'août' WHEN 9 THEN 'septembre' WHEN 10 THEN 'octobre' WHEN 11 THEN 'novembre' ELSE 'décembre' END AS mois, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM Accident a WHERE YEAR( a.dateAccident )=:d1   GROUP BY MONTH( a.dateAccident ) "
			  ) List<NbrAccidentParMois> listNbrAccidentParMois(@Param("d1")int d1);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='bus'   GROUP BY YEAR( a.dateAccident )"
			  ) List<NbrAccidentParAnner> listNbrAccidentBusCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='bus'  GROUP BY YEAR( a.dateAccident )"
			  ) List<NbrAccidentParAnner> listNbrAccidentBusRouteParAnner();
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='metro'  GROUP BY YEAR( a.dateAccident ) "
			  ) List<NbrAccidentParAnner> listNbrAccidentMetrosCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='metro'  GROUP BY YEAR( a.dateAccident ) "
			  ) List<NbrAccidentParAnner> listNbrAccidentMetrosRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident ) "
			  ) List<NbrAccidentParAnner> listNbrAccidentTgmCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParAnner(YEAR( a.dateAccident ) AS annees, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident ) "
			  ) List<NbrAccidentParAnner> listNbrAccidentTgmRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParDistrict(d.nameDistrict, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a , District d  WHERE a.id=t.accident AND  m.id=t.moyentransport  AND  d.id=m.district  GROUP BY d.nameDistrict"
			  
			  ) List<NbrAccidentParDistrict> listNbrAccidentParDistrict();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParMoyenTransport(m.type, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport    GROUP BY m.type"
			  
			  ) List<NbrAccidentParMoyenTransport> listNbrAccidentParMoyenTransport();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParMoyenTransportDate(a.dateAccident, m.type, COUNT(a.id) AS nbrAccident   ) "
			  +
			  "FROM MoyenTransport m , AccidentTransport t , Accident a   WHERE a.id=t.accident AND  m.id=t.moyentransport    GROUP BY m.type , a.dateAccident"
			  
			  ) List<NbrAccidentParMoyenTransportDate> listNbrAccidentParMoyenTransportDate();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParTypeAccident( COUNT(a.id) AS nbrAccident ,a.type  ) "
			  +
			  "FROM  Accident a   GROUP BY a.type "
			  
			  ) List<NbrAccidentParTypeAccident> listNbrAccidentParTypeAccident();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrAccidentParSousTypeAccident( COUNT(a.id) AS nbrAccident ,a.subType  ) "
			  +
			  "FROM  Accident a   GROUP BY a.subType "
			  
			  ) List<NbrAccidentParSousTypeAccident> listNbrAccidentParSousTypeAccident();
	
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParDate(a.dateAccident, COUNT(v.id) AS nbrMorts   ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' GROUP BY a.dateAccident"

			  ) List<NbrMortsParDate> listNbrMortsParDate();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserDangerParMois(CASE MONTH(a.dateAccident) WHEN 1 THEN 'janvier' WHEN 2 THEN 'février' WHEN 3 THEN 'mars' WHEN 4 THEN 'avril' WHEN 5 THEN 'mai' WHEN 6 THEN 'juin' WHEN 7 THEN 'juillet' WHEN 8 THEN 'août' WHEN 9 THEN 'septembre' WHEN 10 THEN 'octobre' WHEN 11 THEN 'novembre' ELSE 'décembre' END AS mois, COUNT(v.id) AS nbrBlessureDanger  ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND v.niveauBlessure='dangereux' AND YEAR( a.dateAccident )=:d1  GROUP BY MONTH( a.dateAccident )"

			  ) List<NbrBlesserDangerParMois> listNbrBlesserDangerParMois(@Param("d1")int d1);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserMineurParMois(CASE MONTH(a.dateAccident) WHEN 1 THEN 'janvier' WHEN 2 THEN 'février' WHEN 3 THEN 'mars' WHEN 4 THEN 'avril' WHEN 5 THEN 'mai' WHEN 6 THEN 'juin' WHEN 7 THEN 'juillet' WHEN 8 THEN 'août' WHEN 9 THEN 'septembre' WHEN 10 THEN 'octobre' WHEN 11 THEN 'novembre' ELSE 'décembre' END AS mois, COUNT(v.id) AS nbrBlessureMineur  ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND v.niveauBlessure='mineures' AND YEAR( a.dateAccident )=:d1  GROUP BY MONTH( a.dateAccident )"

			  ) List<NbrBlesserMineurParMois> listNbrBlesserMineurParMois(@Param("d1")int d1);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParMoisNiveau(CASE MONTH(a.dateAccident) WHEN 1 THEN 'janvier' WHEN 2 THEN 'février' WHEN 3 THEN 'mars' WHEN 4 THEN 'avril' WHEN 5 THEN 'mai' WHEN 6 THEN 'juin' WHEN 7 THEN 'juillet' WHEN 8 THEN 'août' WHEN 9 THEN 'septembre' WHEN 10 THEN 'octobre' WHEN 11 THEN 'novembre' ELSE 'décembre' END AS mois, COUNT(v.id) AS nbrBlessureDanger,v.niveauBlessure  ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant'  AND YEAR( a.dateAccident )=:d1  GROUP BY MONTH( a.dateAccident ),v.niveauBlessure"

			  ) List<NbrBlesserParMoisNiveau> listNbrBlesserParMoisNiveau(@Param("d1")int d1);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParMois(CASE MONTH(a.dateAccident) WHEN 1 THEN 'janvier' WHEN 2 THEN 'février' WHEN 3 THEN 'mars' WHEN 4 THEN 'avril' WHEN 5 THEN 'mai' WHEN 6 THEN 'juin' WHEN 7 THEN 'juillet' WHEN 8 THEN 'août' WHEN 9 THEN 'septembre' WHEN 10 THEN 'octobre' WHEN 11 THEN 'novembre' ELSE 'décembre' END AS mois, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND YEAR( a.dateAccident )=:d1  GROUP BY MONTH( a.dateAccident )"

			  ) List<NbrMortsParMois> listNbrMortsParMois(@Param("d1")int d1);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='bus'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsBusCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='bus'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsBusRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='metro'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsMetrosCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='metro'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsMetrosRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsTgmCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrMortsParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='mort' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrMortsParAnner> listNbrMortsTgmRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='bus'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserBusCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='bus'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserBusRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='metro'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserMetrosCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='metro'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserMetrosRouteParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='collision' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserTgmCollisionParAnner();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParAnner(YEAR( a.dateAccident ) AS annees, COUNT(v.id) AS nbrMorts ) "
			  +
			  "FROM DegatVictime dv,Degat d,Victime v,MoyenTransport m , AccidentTransport t ,Accident a WHERE a.id=d.accident AND d.id=dv.degat AND v.id=dv.victime AND v.etatVictime='vivant' AND a.id=t.accident AND  m.id=t.moyentransport AND a.type='route' AND m.type='tgm'  GROUP BY YEAR( a.dateAccident )"

			  ) List<NbrBlesserParAnner> listNbrBlesserTgmRouteParAnner();
}
