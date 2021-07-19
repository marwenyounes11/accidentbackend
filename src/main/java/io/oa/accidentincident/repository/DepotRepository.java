package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;

@RepositoryRestResource
@CrossOrigin("*")

public interface DepotRepository extends JpaRepository<Depot, Long> {
	@Query("select d from Depot d where d.nameDepot like :x")
	public Page<Depot> chercherDepot(@Param("x")String mc,Pageable pageable);
	
	@Query("select d from Depot d ")
	public Page<Depot> pageDepot(Pageable pageable);
	
}
