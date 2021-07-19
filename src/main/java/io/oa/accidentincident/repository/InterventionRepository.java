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
import io.oa.accidentincident.entity.Intervention;

@RepositoryRestResource
@CrossOrigin("*")
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
	@Query("select i from Intervention i where i.heureIntervention like :x")
	public Page<Intervention> chercherIntervention(@Param("x")Date mc,Pageable pageable);
	
	@Query("select i from Intervention i ")
	public Page<Intervention> pageIntervention(Pageable pageable);
	
	@Query("select i from Intervention i")
	public List<Intervention> listIntervention();
	
}
