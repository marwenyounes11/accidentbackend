package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.oa.accidentincident.entity.District;

@RepositoryRestResource
@CrossOrigin("*")
public interface DistrictRepository extends JpaRepository<District, Long> {
	@Query("select d from District d where d.nameDistrict like :x")
	public Page<District> chercherDistrict(@Param("x")String mc,Pageable pageable);
	
	@Query("select d from District d ")
	public Page<District> pageDistrict(Pageable pageable);
}
