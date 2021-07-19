package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentIntervention;
import io.oa.accidentincident.entity.Depot;

@RepositoryRestResource
@CrossOrigin("*")
public interface AgentInterventionRepository extends JpaRepository<AgentIntervention, Long> {
	@Query("select a from AgentIntervention a where a.name like :x")
	public Page<AgentIntervention> chercherAgentIntervention(@Param("x")String mc,Pageable pageable);
	
	@Query("select a from AgentIntervention a ")
	public Page<AgentIntervention> pageAgentIntervention(Pageable pageable);
}
