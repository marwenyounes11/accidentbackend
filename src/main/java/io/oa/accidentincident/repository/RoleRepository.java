package io.oa.accidentincident.repository;

import java.util.Optional;

import io.oa.accidentincident.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	 Optional<Role> findByName(String name);
}
