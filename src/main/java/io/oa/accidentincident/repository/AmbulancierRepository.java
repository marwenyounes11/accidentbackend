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
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;

@RepositoryRestResource
@CrossOrigin("*")
public interface AmbulancierRepository extends JpaRepository<Ambulancier, Long> {
	@Query("select a from Ambulancier a where a.name like :x")
	public Page<Ambulancier> chercherAmbulancier(@Param("x")String mc,Pageable pageable);
	
	@Query("select a from Ambulancier a ")
	public Page<Ambulancier> pageAmbulancier(Pageable pageable);
}
