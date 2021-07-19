package io.oa.accidentincident.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Droit;
import io.oa.accidentincident.entity.Ligne;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Rubrique;
import io.oa.accidentincident.response.BusResponse;
import io.oa.accidentincident.response.MetrosResponse;
import io.oa.accidentincident.response.TgmResponse;

@Repository
@Transactional
public interface MoyenTransportRepository extends JpaRepository<MoyenTransport, Long> {
	Set<MoyenTransport> findMoyenTransportByType(String type);
	
	@Query("SELECT new io.oa.accidentincident.response.BusResponse(m.id,m.gage,m.immatriculation,m.mark,m.model,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='bus' and m.numTransport like :x "
			  ) Page<BusResponse> chercherBus(@Param("x")String mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.BusResponse(m.id,m.gage,m.immatriculation,m.mark,m.model,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='bus'"
			  )Page<BusResponse> pageBus(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.BusResponse(m.id,m.gage,m.immatriculation,m.mark,m.model,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='bus'"
			  )List<BusResponse> listBus();
	 
	
	@Query("SELECT new io.oa.accidentincident.response.MetrosResponse(m.id,m.gage,m.immatriculation,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='metro' and m.numTransport like :x "
			  )Page<MetrosResponse> chercherMetros(@Param("x")String mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.MetrosResponse(m.id,m.gage,m.immatriculation,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='metro'"
			  ) Page<MetrosResponse> pageMetros(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.MetrosResponse(m.id,m.gage,m.immatriculation,m.numTransport,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='metro'"
			  ) List<MetrosResponse> listMetros();
	
	@Query("SELECT new io.oa.accidentincident.response.TgmResponse(m.id,m.gage,m.immatriculation,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='tgm' and m.numTransport like :x "
			  ) Page<TgmResponse> chercherTgm(@Param("x")String mc,Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.TgmResponse(m.id,m.gage,m.immatriculation,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='tgm' "
			  ) Page<TgmResponse> pageTgm(Pageable pageable);
	
	@Query("SELECT new io.oa.accidentincident.response.TgmResponse(m.id,m.gage,m.immatriculation,m.ligne,m.district,m.depot,m.agent   ) "
			  +
			  "FROM MoyenTransport m  where m.type='tgm' "
			  ) List<TgmResponse> listTgm();
	
	@Modifying 
	 @Query(value = "DELETE FROM MoyenTransport m WHERE m.id = :id")       
	    void deleteMoyenTransport(@Param("id") Long id);
}


