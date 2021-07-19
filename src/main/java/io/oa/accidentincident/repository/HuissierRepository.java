package io.oa.accidentincident.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Huissier;
import io.oa.accidentincident.response.HuissierResponse;

@RepositoryRestResource
@CrossOrigin("*")
public interface HuissierRepository extends JpaRepository<Huissier, Long> {
	@Query("SELECT new io.oa.accidentincident.response.HuissierResponse(h.id,h.number,h.dateHuissier,h.heureHuissier,h.securite,h.accident,h.sourcedeclarehuissier   ) "
			  +
			  "FROM Huissier h  where h.dateHuissier = :mc "
			  )
	public Page<HuissierResponse> chercherHuissier(@Param("mc")Date mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.HuissierResponse(h.id,h.number,h.dateHuissier,h.heureHuissier,h.securite,h.accident,h.sourcedeclarehuissier   ) "
			  +
			  "FROM Huissier h   "
			  )
	public Page<HuissierResponse> pageHuissier(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.HuissierResponse(h.id,h.number,h.dateHuissier,h.heureHuissier,h.securite,h.accident,h.sourcedeclarehuissier   ) "
			  +
			  "FROM Huissier h  "
			  )
	public List<HuissierResponse> listHuissier();
	
}

