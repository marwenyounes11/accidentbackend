package io.oa.accidentincident.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;

@RepositoryRestResource
@CrossOrigin("*")
public interface SecuriteRepository extends JpaRepository<Securite, Long> {
	@Query("select s from Securite s where s.type like :x")
	public Page<Securite> chercherSecurite(@Param("x")String mc,Pageable pageable);
	
	@Query("select s from Securite s ")
	public Page<Securite> pageSecurite(Pageable pageable);

}
