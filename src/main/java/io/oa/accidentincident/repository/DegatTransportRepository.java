package io.oa.accidentincident.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.oa.accidentincident.entity.DegatTransport;
import io.oa.accidentincident.response.DegatTransportResponse;

@RepositoryRestResource 
@CrossOrigin("*")
public interface DegatTransportRepository extends JpaRepository<DegatTransport, Long> {
	
	
	@Query("SELECT new io.oa.accidentincident.response.DegatTransportResponse(dt.id,dt.moyentransport,dt.degat  ) "
			  +
			  "FROM DegatTransport dt  "
			  ) Page<DegatTransportResponse> pageDegatTransport(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.DegatTransportResponse(dt.id,dt.moyentransport,dt.degat  ) "
			  +
			  "FROM Degat d,MoyenTransport m,DegatTransport dt WHERE m.id=dt.moyentransport and d.id=dt.degat "
			  ) List<DegatTransportResponse> listDegatTransport();
	
	@Query("SELECT new io.oa.accidentincident.response.DegatTransportResponse(dt.id,dt.moyentransport,dt.degat  ) "
			  +
			  "FROM Degat d,MoyenTransport m,DegatTransport dt WHERE m.id=dt.moyentransport and d.id=dt.degat and dt.id = :id"
			  ) DegatTransportResponse degatTransport(@Param("id") Long id);
	
	
}
