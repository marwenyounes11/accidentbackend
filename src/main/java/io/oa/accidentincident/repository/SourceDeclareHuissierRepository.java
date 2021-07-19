package io.oa.accidentincident.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.oa.accidentincident.entity.SourceDeclareHuissier;

@RepositoryRestResource
@CrossOrigin("*")
public interface SourceDeclareHuissierRepository extends JpaRepository<SourceDeclareHuissier, Long> {
	@Query("select s from SourceDeclareHuissier s where s.name like :x")
	public Page<SourceDeclareHuissier> chercherSourceDeclareHuissier(@Param("x")String mc,Pageable pageable);
	
	@Query("select s from SourceDeclareHuissier s ")
	public Page<SourceDeclareHuissier> pageSourceDeclareHuissier(Pageable pageable);
}
