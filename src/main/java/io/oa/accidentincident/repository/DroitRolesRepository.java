package io.oa.accidentincident.repository;



import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.Droit;
import io.oa.accidentincident.entity.DroitRoles;
import io.oa.accidentincident.entity.Role;
import io.oa.accidentincident.entity.SousRubrique;
import io.oa.accidentincident.response.DroitRolesResponse;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DroitRolesRepository extends JpaRepository<DroitRoles, Long>{
	Optional<DroitRoles> findDroitRolesByDroitAndSousrubriqueAndRole(Droit droit,SousRubrique sousrubrique, Role role);
	@Query("SELECT new io.oa.accidentincident.response.DroitRolesResponse(dr.id, dr.droit,dr.sousrubrique,dr.role   ) "
			  +
			  "FROM DroitRoles dr "
			  )public List<DroitRolesResponse> listDroitRoles();
	
	@Query("SELECT new io.oa.accidentincident.response.DroitRolesResponse(dr.id, dr.droit,dr.sousrubrique,dr.role   ) "
			  +
			  "FROM DroitRoles dr where dr.id = :id "
			  )public DroitRolesResponse droitRole(@Param("id") Long id);
	
	
}
