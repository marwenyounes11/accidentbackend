package io.oa.accidentincident.repository;

import io.oa.accidentincident.entity.Rubrique;
import io.oa.accidentincident.entity.SousRubrique;
import io.oa.accidentincident.response.SousRubriqueResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface SousRubriqueRepository extends JpaRepository<SousRubrique, Long>{
	SousRubrique findByLibelle(String libelle);
	
	
	@Query("SELECT new io.oa.accidentincident.response.SousRubriqueResponse(s.id, s.libelle,s.rubrique   ) "
			  +
			  "FROM SousRubrique s "
			  )public List<SousRubriqueResponse> listSousRubrique();
	
	@Query("SELECT new io.oa.accidentincident.response.SousRubriqueResponse(s.id,s.libelle,s.rubrique   ) "
			  +
			  "FROM SousRubrique s where s.id = :id "
			  )public SousRubriqueResponse sousRubrique(@Param("id") Long id);
}
