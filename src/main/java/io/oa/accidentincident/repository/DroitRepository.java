package io.oa.accidentincident.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.oa.accidentincident.entity.Droit;
import io.oa.accidentincident.entity.DroitRoles;
import io.oa.accidentincident.entity.SousRubrique;
import io.oa.accidentincident.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroitRepository extends JpaRepository<Droit, Long>{
	Droit findDroitByAction(String action);
	@Query("select d from Droit d ")
	public List<Droit> listDroit();
}
