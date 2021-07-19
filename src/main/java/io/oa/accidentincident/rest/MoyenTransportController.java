package io.oa.accidentincident.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentProjection;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.District;
import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.entity.Ligne;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AccidentByTransportForm;
import io.oa.accidentincident.form.BusForm;
import io.oa.accidentincident.form.MetrosForm;
import io.oa.accidentincident.form.TgmForm;
import io.oa.accidentincident.repository.AgentRepository;
import io.oa.accidentincident.repository.DepotRepository;
import io.oa.accidentincident.repository.DistrictRepository;
import io.oa.accidentincident.repository.LigneRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.response.BusResponse;
import io.oa.accidentincident.response.MetrosResponse;
import io.oa.accidentincident.response.TgmResponse;

@CrossOrigin
@RestController

public class MoyenTransportController {
	@Autowired
	MoyenTransportRepository repository;
	@Autowired
	DepotRepository drepository;
	@Autowired
	DistrictRepository direpository;
	@Autowired
	LigneRepository lrepository;
	@Autowired
	AgentRepository agrepository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/buspaginationmc")
	  public List<BusResponse> getAllBusPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<BusResponse> bus = new ArrayList<>();
	    repository.chercherBus(mc,PageRequest.of(page,size)).forEach(bus::add);
	 
	    return bus;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/buspagination")
	  public List<BusResponse> getAllBusPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<BusResponse> bus= new ArrayList<>();
	    repository.pageBus(PageRequest.of(page,size)).forEach(bus::add);
	    return bus;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/listbus")
	  public List<BusResponse> getAllBus() {
	    System.out.println("Get all Bus...");
	 
	    List<BusResponse> bus = new ArrayList<>();
	    repository.listBus().forEach(bus::add);
	 
	    return bus;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/metrospaginationmc")
	  public List<MetrosResponse> getAllMetrosPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<MetrosResponse> metro = new ArrayList<>();
	    repository.chercherMetros(mc,PageRequest.of(page,size)).forEach(metro::add);
	 
	    return metro;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/metrospagination")
	  public List<MetrosResponse> getAllMetrosPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<MetrosResponse> metro = new ArrayList<>();
	    repository.pageMetros(PageRequest.of(page,size)).forEach(metro::add);
	    return metro;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/listmetros")
	  public List<MetrosResponse> getAllMetros() {
	    System.out.println("Get all Metros...");
	 
	    List<MetrosResponse> metros = new ArrayList<>();
	    repository.listMetros().forEach(metros::add);
	 
	    return metros;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/tgmpaginationmc")
	  public List<TgmResponse> getAllTgmsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<TgmResponse> tgm = new ArrayList<>();
	    repository.chercherTgm(mc,PageRequest.of(page,size)).forEach(tgm::add);
	 
	    return tgm;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/tgmpagination")
	  public List<TgmResponse> getAllTgmsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<TgmResponse> tgm = new ArrayList<>();
	    repository.pageTgm(PageRequest.of(page,size)).forEach(tgm::add);
	    return tgm;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/listtgms")
	  public List<TgmResponse> getAllTgms() {
	    System.out.println("Get all Tgm...");
	 
	    List<TgmResponse> tgms = new ArrayList<>();
	    repository.listTgm().forEach(tgms::add);
	 
	    return tgms;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	 @GetMapping("/listMoyenTransports")
	  public List<MoyenTransport> getAllMoyenTransports() {
	    System.out.println("Get all MoyenTransports...");
	 
	    List<MoyenTransport> moyenTransports = new ArrayList<>();
	    repository.findAll().forEach(moyenTransports::add);
	 
	    return moyenTransports;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionmoyentransport')")
	@GetMapping("/moyenTransports/{id}")
	public ResponseEntity<MoyenTransport> getMoyenTransportById(@PathVariable(value = "id") Long moyenTransportId)
			throws ResourceNotFoundException {
		MoyenTransport moyenTransport = repository.findById(moyenTransportId)
				.orElseThrow(() -> new ResourceNotFoundException("MoyenTransport not found for this id :: " + moyenTransportId));
		return ResponseEntity.ok().body(moyenTransport);
	}

	
	 @PreAuthorize("@authorizationSE.can('afficher', 'rapportaccidentmetros')")
	 @GetMapping("/accidentsmetros")
	  public List<Accident> getAccidentMetros() {
		  System.out.println("accidentsMetros ...");
		     List<Accident> accidentmetros = new ArrayList<>();
		    
		     Set<MoyenTransport>mts=repository.findMoyenTransportByType("metro");
		    mts.forEach(mt->{
		    
		    	mt.getAccidenttransports().forEach(at->{
		    		Accident ap= new Accident();
		    		Accident a=at.getAccident();
		    		if((a.getType().equals("collision"))||(a.getType().equals("route"))) {
		    			System.out.println(a.getId());
		    			ap.setId(a.getId());
		    			ap.setType(a.getType());
		    			ap.setDateAccident(a.getDateAccident());
		    			ap.setHeureAccident(a.getHeureAccident());
		    			ap.setDescription(a.getDescription());
		    			ap.setImage(a.getImage());
		    			ap.setSubType(a.getSubType());
		    			ap.setDegats(a.getDegats());
		    			ap.setLieux(a.getLieux());
		    			ap.setHuissiers(a.getHuissiers());
		    			ap.setAccidentinforms(a.getAccidentinforms());
		    			ap.setAccidenttransports(a.getAccidenttransports());
		    			
		    			accidentmetros.add(ap);
		    		}
		    	});
		    });
		    
		 
		    return accidentmetros;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'rapportaccidentbus')")
	 @GetMapping("/accidentsbus")
	  public List<Accident> getAccidentBus() {
		  System.out.println("accidentsBus ...");
		     List<Accident> accidentbuss = new ArrayList<>();
		    
		     Set<MoyenTransport>mts=repository.findMoyenTransportByType("bus");
		    mts.forEach(mt->{
		    
		    	mt.getAccidenttransports().forEach(at->{
		    		Accident ap= new Accident();
		    		Accident a=at.getAccident();
		    		if((a.getType().equals("collision"))||(a.getType().equals("route"))) {
		    			System.out.println(a.getId());
		    			ap.setId(a.getId());
		    			ap.setType(a.getType());
		    			ap.setDateAccident(a.getDateAccident());
		    			ap.setHeureAccident(a.getHeureAccident());
		    			ap.setDescription(a.getDescription());
		    			ap.setImage(a.getImage());
		    			ap.setSubType(a.getSubType());
		    			ap.setDegats(a.getDegats());
		    			ap.setLieux(a.getLieux());
		    			ap.setHuissiers(a.getHuissiers());
		    			ap.setAccidentinforms(a.getAccidentinforms());
		    			ap.setAccidenttransports(a.getAccidenttransports());
		    			
		    			accidentbuss.add(ap);
		    		}
		    	});
		    });
		    
		 
		    return accidentbuss;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'rapportaccidenttgm')")
	 @GetMapping("/accidentstgm")
	  public List<Accident> getAccidentTgm() {
		  System.out.println("accidentsTgm ...");
		     List<Accident> accidenttgms = new ArrayList<>();
		    
		     Set<MoyenTransport>mts=repository.findMoyenTransportByType("tgm");
		    mts.forEach(mt->{
		    
		    	mt.getAccidenttransports().forEach(at->{
		    		Accident ap= new Accident();
		    		Accident a=at.getAccident();
		    		if((a.getType().equals("collision"))||(a.getType().equals("route"))) {
		    			System.out.println(a.getId());
		    			ap.setId(a.getId());
		    			ap.setType(a.getType());
		    			ap.setDateAccident(a.getDateAccident());
		    			ap.setHeureAccident(a.getHeureAccident());
		    			ap.setDescription(a.getDescription());
		    			ap.setImage(a.getImage());
		    			ap.setSubType(a.getSubType());
		    			ap.setDegats(a.getDegats());
		    			ap.setLieux(a.getLieux());
		    			ap.setHuissiers(a.getHuissiers());
		    			ap.setAccidentinforms(a.getAccidentinforms());
		    			ap.setAccidenttransports(a.getAccidenttransports());
		    			
		    			accidenttgms.add(ap);
		    		}
		    	});
		    });
		    
		 
		    return accidenttgms;
	  }
	
		
	 @PreAuthorize("@authorizationSE.can('create', 'gestionmoyentransport')")
	@PostMapping("/addbus")
	public MoyenTransport createBus(@Valid @RequestBody BusForm moyentransportForm) {
		Long iddistrict = moyentransportForm.getIddistrict();
		Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		Depot dept = drepository.findById(iddepot).orElse(null);
		District district = direpository.findById(iddistrict).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
		MoyenTransport moyentransport = new MoyenTransport();
		moyentransport.setGage(moyentransportForm.getGage());
		moyentransport.setImmatriculation(moyentransportForm.getImmatriculation());
		moyentransport.setMark(moyentransportForm.getMark());
		moyentransport.setModel(moyentransportForm.getModel());
		moyentransport.setNumTransport(moyentransportForm.getNumTransport());
		moyentransport.setType("bus");
		moyentransport.setLigne(ligne);
		moyentransport.setAgent(agent);
		moyentransport.setDepot(dept);
		moyentransport.setDistrict(district);
		return repository.save(moyentransport);
	}
	 @PreAuthorize("@authorizationSE.can('create', 'gestionmoyentransport')")
	@PostMapping("/addmetro")
	public MoyenTransport createMetro(@Valid @RequestBody MetrosForm moyentransportForm) {
		Long iddistrict = moyentransportForm.getIddistrict();
		Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		Depot dept = drepository.findById(iddepot).orElse(null);
		District district = direpository.findById(iddistrict).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
		MoyenTransport moyentransport = new MoyenTransport();
		moyentransport.setGage(moyentransportForm.getGage());
		moyentransport.setImmatriculation(moyentransportForm.getImmatriculation());
		moyentransport.setNumTransport(moyentransportForm.getNumTransport());
		moyentransport.setType("metro");
		moyentransport.setLigne(ligne);
		moyentransport.setAgent(agent);
		moyentransport.setDepot(dept);
		moyentransport.setDistrict(district);
		return repository.save(moyentransport);
	}
	 @PreAuthorize("@authorizationSE.can('create', 'gestionmoyentransport')")
	@PostMapping("/addtgm")
	public MoyenTransport createTgm(@Valid @RequestBody TgmForm moyentransportForm) {
		Long iddistrict = moyentransportForm.getIddistrict();
		Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		Depot dept = drepository.findById(iddepot).orElse(null);
		District district = direpository.findById(iddistrict).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
		MoyenTransport moyentransport = new MoyenTransport();
		moyentransport.setGage(moyentransportForm.getGage());
		moyentransport.setImmatriculation(moyentransportForm.getImmatriculation());
		moyentransport.setType("tgm");
		moyentransport.setLigne(ligne);
		moyentransport.setAgent(agent);
		moyentransport.setDepot(dept);
		moyentransport.setDistrict(district);
		return repository.save(moyentransport);
	}
	
	
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionmoyentransport')")
	@DeleteMapping("/deletemoyenTransports/{id}")
	public void deleteMoyenTransport(@PathVariable(value = "id") Long moyenTransportId)
			throws ResourceNotFoundException {
		 repository.deleteMoyenTransport(moyenTransportId);
	}
	  
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionmoyentransport')")
	  @DeleteMapping("/moyenTransports/delete")
	  public ResponseEntity<String> deleteAllMoyenTransports() {
	    System.out.println("Delete All MoyenTransports...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All MoyenTransports have been deleted!", HttpStatus.OK);
	  }
	 
	
	 @PreAuthorize("@authorizationSE.can('update', 'gestionmoyentransport')")
	  @PutMapping("/editbus/{id}")
	  public ResponseEntity<MoyenTransport> updateBus(@PathVariable("id") long id, @RequestBody BusForm moyentransportForm) {
	    System.out.println("Update MoyenTransport with ID = " + id + "...");
	    Long iddistrict = moyentransportForm.getIddistrict();
	    Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		District district = direpository.findById(iddistrict).orElse(null);
		Depot dept = drepository.findById(iddepot).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
	    Optional<MoyenTransport> moyenTransportInfo = repository.findById(id);
	 
	    if (moyenTransportInfo.isPresent()) {
	    	MoyenTransport moyenTransportt = moyenTransportInfo.get();
	          
	           moyenTransportt.setGage(moyentransportForm.getGage());
	           moyenTransportt.setImmatriculation(moyentransportForm.getImmatriculation());
	           moyenTransportt.setMark(moyentransportForm.getMark());
	           moyenTransportt.setModel(moyentransportForm.getModel());
	           moyenTransportt.setNumTransport(moyentransportForm.getNumTransport());
	           moyenTransportt.setType("bus");
	           moyenTransportt.setDistrict(district);
	           moyenTransportt.setDepot(dept);
	           moyenTransportt.setAgent(agent);
	           moyenTransportt.setLigne(ligne);
	           
	          
	      return new ResponseEntity<>(repository.save(moyenTransportt), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	 @PreAuthorize("@authorizationSE.can('update', 'gestionmoyentransport')")
	  @PutMapping("/editmetro/{id}")
	  public ResponseEntity<MoyenTransport> updateMetro(@PathVariable("id") long id, @RequestBody MetrosForm moyentransportForm) {
	    System.out.println("Update MoyenTransport with ID = " + id + "...");
	    Long iddistrict = moyentransportForm.getIddistrict();
	    Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		District district = direpository.findById(iddistrict).orElse(null);
		Depot dept = drepository.findById(iddepot).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
	    Optional<MoyenTransport> moyenTransportInfo = repository.findById(id);
	 
	    if (moyenTransportInfo.isPresent()) {
	    	MoyenTransport moyenTransportt = moyenTransportInfo.get();
	          
	           moyenTransportt.setGage(moyentransportForm.getGage());
	           moyenTransportt.setImmatriculation(moyentransportForm.getImmatriculation());
	           moyenTransportt.setNumTransport(moyentransportForm.getNumTransport());
	           moyenTransportt.setType("metro");
	           moyenTransportt.setDistrict(district);
	           moyenTransportt.setDepot(dept);
	           moyenTransportt.setAgent(agent);
	           moyenTransportt.setLigne(ligne);
	           
	          
	      return new ResponseEntity<>(repository.save(moyenTransportt), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	 @PreAuthorize("@authorizationSE.can('update', 'gestionmoyentransport')")
	  @PutMapping("/edittgm/{id}")
	  public ResponseEntity<MoyenTransport> updateTgm(@PathVariable("id") long id, @RequestBody TgmForm moyentransportForm) {
	    System.out.println("Update MoyenTransport with ID = " + id + "...");
	    Long iddistrict = moyentransportForm.getIddistrict();
	    Long iddepot = moyentransportForm.getIddepot();
		Long idligne = moyentransportForm.getIdligne();
		Long idagent = moyentransportForm.getIdagent();
		District district = direpository.findById(iddistrict).orElse(null);
		Depot dept = drepository.findById(iddepot).orElse(null);
		Ligne ligne = lrepository.findById(idligne).orElse(null);
		Agent agent = agrepository.findById(idagent).orElse(null);
	    Optional<MoyenTransport> moyenTransportInfo = repository.findById(id);
	 
	    if (moyenTransportInfo.isPresent()) {
	    	MoyenTransport moyenTransportt = moyenTransportInfo.get();
	          
	           moyenTransportt.setGage(moyentransportForm.getGage());
	           moyenTransportt.setImmatriculation(moyentransportForm.getImmatriculation());
	           moyenTransportt.setType("tgm");
	           moyenTransportt.setDistrict(district);
	           moyenTransportt.setDepot(dept);
	           moyenTransportt.setAgent(agent);
	           moyenTransportt.setLigne(ligne);
	           
	          
	      return new ResponseEntity<>(repository.save(moyenTransportt), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
