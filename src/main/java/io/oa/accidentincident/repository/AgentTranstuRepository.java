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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;

@RepositoryRestResource
@CrossOrigin("*")
@Transactional
public interface AgentTranstuRepository extends JpaRepository<AgentTranstu, Long> {

	@Modifying 
	 @Query(value = "DELETE FROM AgentTranstu a WHERE a.id = :id")       
	    void deleteAgentTranstu(@Param("id") Long id);
	
	@Query("select a from AgentTranstu a where a.name like :x")
	public Page<AgentTranstu> chercherAgentTranstu(@Param("x")String mc,Pageable pageable);
	
	@Query("select a from AgentTranstu a ")
	public Page<AgentTranstu> pageAgentTranstu(Pageable pageable);
}
