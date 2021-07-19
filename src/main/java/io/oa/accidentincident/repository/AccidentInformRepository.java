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

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.response.AccidentInformResponse;
import io.oa.accidentincident.response.NbrAccidentParDate;

@RepositoryRestResource
@CrossOrigin("*")
public interface AccidentInformRepository extends JpaRepository<AccidentInform, Long>{
	List<AccidentInform> findByDateInformation(Date dateInformation);
	
	@Query("SELECT new io.oa.accidentincident.response.AccidentInformResponse(a.id,a.accident,a.sourceinform,a.dateInformation,a.heureInformation,a.description   ) "
			  +
			  "FROM AccidentInform a  where a.dateInformation = :mc "
			  )
	public Page<AccidentInformResponse> chercherAccidentInform(@Param("mc")Date mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.AccidentInformResponse(a.id,a.accident,a.sourceinform,a.dateInformation,a.heureInformation,a.description   ) "
			  +
			  "FROM AccidentInform a   "
			  )
	public Page<AccidentInformResponse> pageAccidentInform(Pageable pageable);
}

