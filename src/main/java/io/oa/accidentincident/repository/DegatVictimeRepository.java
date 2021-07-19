package io.oa.accidentincident.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.DegatVictime;
import io.oa.accidentincident.response.DegatTransportResponse;
import io.oa.accidentincident.response.DegatVictimeResponse;

@RepositoryRestResource
@CrossOrigin("*")
@Transactional
public interface DegatVictimeRepository extends JpaRepository<DegatVictime, Long> {
	
	 List<DegatVictime> findByDegat(Degat degat);
	 
	 @Query("SELECT new io.oa.accidentincident.response.DegatVictimeResponse(dv.id,dv.victime,dv.degat) "
			  +
			  "FROM DegatVictime dv  "
			  ) Page<DegatVictimeResponse> pageDegatVictime(Pageable pageable);
	
	 
	 @Query("SELECT new io.oa.accidentincident.response.DegatVictimeResponse(dv.id,dv.victime,dv.degat  ) "
			  +
			  "FROM Degat d,Victime v,DegatVictime dv WHERE v.id=dv.victime and d.id=dv.degat "
			  ) List<DegatVictimeResponse> listDegatVictime();
	
	@Query("SELECT new io.oa.accidentincident.response.DegatVictimeResponse(dv.id,dv.victime,dv.degat ) "
			  +
			  "FROM Degat d,Victime v,DegatVictime dv WHERE v.id=dv.victime and d.id=dv.degat and dv.id = :id"
			  ) DegatVictimeResponse degatVictime(@Param("id") Long id);
	
	@Modifying 
	 @Query(value = "DELETE FROM DegatVictime d WHERE d.id = :id")       
	    void deleteDegatVictime(@Param("id") Long id);
	
}
