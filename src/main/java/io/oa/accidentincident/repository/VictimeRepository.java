package io.oa.accidentincident.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.entity.Victime;
import io.oa.accidentincident.response.NbrBlesserExterne;
import io.oa.accidentincident.response.NbrBlesserInterne;
import io.oa.accidentincident.response.NbrBlesserParNiveauBlessure;

@RepositoryRestResource
@CrossOrigin("*")
public interface VictimeRepository extends JpaRepository<Victime, Long> {
	
	@Query("select v from Victime v where v.nameVictime like :x")
	public Page<Victime> chercherVictime(@Param("x")String mc,Pageable pageable);
	
	@Query("select v from Victime v ")
	public Page<Victime> pageVictime(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserExterne( COUNT(v.id) AS nbrBlesser , v.corpBlesser ) "
			  +
			  "FROM  Victime v, DegatVictime dv WHERE v.id=dv.victime AND v.etatVictime='vivant' AND v.typeVictime='externe' GROUP BY v.corpBlesser"
			  
			  ) List<NbrBlesserExterne> listNbrBlesserExterne();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserInterne( COUNT(v.id) AS nbrBlesser , v.corpBlesser ) "
			  +
			  "FROM  Victime v, DegatVictime dv WHERE v.id=dv.victime AND v.etatVictime='vivant' AND v.typeVictime='interne' GROUP BY v.corpBlesser"
			  
			  ) List<NbrBlesserInterne> listNbrBlesserInterne();
	
	@Query("SELECT new io.oa.accidentincident.response.NbrBlesserParNiveauBlessure( v.niveauBlessure,COUNT(v.id) AS nbrBlesser  ) "
			  +
			  "FROM  Victime v, DegatVictime dv  WHERE v.id=dv.victime AND v.etatVictime='vivant' GROUP BY v.niveauBlessure"
			  
			  ) List<NbrBlesserParNiveauBlessure> listNbrBlesserParNiveauBlessure();
}
