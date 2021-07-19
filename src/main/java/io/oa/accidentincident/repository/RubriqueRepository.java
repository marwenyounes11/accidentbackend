package io.oa.accidentincident.repository;

import java.util.List;
import java.util.Optional;

import io.oa.accidentincident.entity.Droit;
import io.oa.accidentincident.entity.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface RubriqueRepository extends JpaRepository<Rubrique, Long>{
	Rubrique findByLibelle(String libelle);
	@Query("select r from Rubrique r ")
	public List<Rubrique> listRubrique();
}
