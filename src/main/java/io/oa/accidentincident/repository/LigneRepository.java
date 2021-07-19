package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.oa.accidentincident.entity.Ligne;

@RepositoryRestResource
@CrossOrigin("*")
public interface LigneRepository extends JpaRepository<Ligne, Long> {
	@Query("select l from Ligne l where l.nameLigne like :x")
	public Page<Ligne> chercherLigne(@Param("x")String mc,Pageable pageable);
	
	@Query("select l from Ligne l ")
	public Page<Ligne> pageLigne(Pageable pageable);
}
