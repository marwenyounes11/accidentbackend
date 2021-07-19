package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;

@RepositoryRestResource
@CrossOrigin("*")
@Transactional
public interface DepartementRepository extends JpaRepository<Departement, Long> {

	@Query("select d from Departement d where d.nameDepartement like :x")
	public Page<Departement> chercherDepartement(@Param("x")String mc,Pageable pageable);
	
	@Query("select d from Departement d ")
	public Page<Departement> pageDepartement(Pageable pageable);
	
	@Modifying 
	 @Query(value = "DELETE FROM Departement d WHERE d.id = :id")       
	    void deleteDepartement(@Param("id") Long id);
}
