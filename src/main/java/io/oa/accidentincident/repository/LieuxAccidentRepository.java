package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.LieuxAccident;

@RepositoryRestResource
@CrossOrigin("*")
public interface LieuxAccidentRepository extends JpaRepository<LieuxAccident, Long> {
	@Query("select l from LieuxAccident l where l.emplacement like :x")
	public Page<LieuxAccident> chercherLieuxAccident(@Param("x")String mc,Pageable pageable);
	
	@Query("select l from LieuxAccident l ")
	public Page<LieuxAccident> pageLieuxAccident(Pageable pageable);
}
