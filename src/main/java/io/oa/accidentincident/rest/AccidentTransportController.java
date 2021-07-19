package io.oa.accidentincident.rest;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AccidentTransportForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.response.AccidentInformResponse;
import io.oa.accidentincident.response.AccidentTransportResponse;





@CrossOrigin
@RestController

public class AccidentTransportController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	AccidentTransportRepository  repository;
	@Autowired 
	AccidentRepository  arepository;
	@Autowired 	
	MoyenTransportRepository  trepository;
	
	/* @GetMapping("/accidenttransports/{id}")
		public ResponseEntity<AccidentTransport> getAccidentTransportById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
		 AccidentTransport accidenttransport = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Accident transport not found for this id :: " + Id));
			return ResponseEntity.ok().body(accidenttransport);
		}
	 
	
	
	@GetMapping("/accidenttransports")
	  public List<AccidentTransport> getAllAccidentTransports() {
	     System.out.println("Get all AccidentTransports...");
	 
	    List<AccidentTransport> accidenttransports = new ArrayList<>();
	    repository.findAll().forEach(accidenttransports::add);
	 
	    return accidenttransports;
	  }
	 */
	
	/*@GetMapping("/incidentJournaliers")
	  public List<IncidentJournalierForm> getIncidentJournalier() {
	     System.out.println("Get all IncidentJournaliers ...");
	 
	    List<IncidentJournalierForm> incidentJournaliers = new ArrayList<>(); 
	    repository.findAll().forEach(at->{
			Accident a = at.getAccident();
			MoyenTransport mt = at.getMoyentransport();
			String nameDistrict=mt.getDistrict().getNameDistrict();
			Date dateAccident=a.getDateAccident();
			Date heureAccident=a.getHeureAccident();
			String nameLigne=mt.getLigne().getNameLigne();
			String immatriculation=mt.getImmatriculation() ;
			String numTransport=mt.getNumTransport() ;
			String type=mt.getType() ;
			String emplacement=a.getLieux().getEmplacement();
			String name=mt.getAgent().getName();
			String lastName=mt.getAgent().getLastName();
			Set<Degat> degatmateriels=new HashSet<Degat>();
			Set<Degat> degatphysiques=new HashSet<Degat>();
			a.getDegats().forEach(d->{
			if(d.getType().equals("materiel")){
			degatmateriels.add(d);
			}
			else{
			degatphysiques.add(d);
			}
			});
			IncidentJournalierForm incidentJournalierForm = new IncidentJournalierForm();
			incidentJournalierForm.setNameDistrict(nameDistrict);
			incidentJournalierForm.setDateAccident(dateAccident);
			incidentJournalierForm.setHeureAccident(heureAccident);
			incidentJournalierForm.setNameLigne(nameLigne);
			incidentJournalierForm.setImmatriculation(immatriculation);
			incidentJournalierForm.setNumTransport(numTransport);
			incidentJournalierForm.setType(type);
			incidentJournalierForm.setEmplacement(emplacement);
			incidentJournalierForm.setName(name);
			incidentJournalierForm.setLastName(lastName);
			incidentJournalierForm.setDegatmateriels(degatmateriels);
			incidentJournalierForm.setDegatphysiques(degatphysiques);
			incidentJournaliers.add(incidentJournalierForm);
		});
	    
	 
	    return incidentJournaliers;
	  }*/
	
	
	/*@GetMapping("/incidentJournaliers/metro")
	  public List<IncidentJournalierForm> getIncidentJournalierMetros() {
	     System.out.println("Get  IncidentJournaliers metro...");
	 
	    List<IncidentJournalierForm> incidentJournaliers = new ArrayList<>(); 
	    repository.findAll().forEach(at->{
			Accident a = at.getAccident();
			MoyenTransport mt = at.getMoyentransport();
			String nameDepot=mt.getDepot().getNameDepot();
			Date dateAccident=a.getDateAccident();
			String nameLigne=mt.getLigne().getNameLigne();
			String immatriculation=mt.getImmatriculation() ;
			String type=mt.getType() ;
			String station=a.getLieux().getStation();
			String name=mt.getAgent().getName();
			String lastName=mt.getAgent().getLastName();
			Set<Degat> degats=a.getDegats();
			IncidentJournalierForm incidentJournalierForm = new IncidentJournalierForm();
			incidentJournalierForm.setNameDepot(nameDepot);
			incidentJournalierForm.setDateAccident(dateAccident);
			incidentJournalierForm.setNameLigne(nameLigne);
			incidentJournalierForm.setImmatriculation(immatriculation);
			incidentJournalierForm.setType(type);
			incidentJournalierForm.setStation(station);
			incidentJournalierForm.setName(name);
			incidentJournalierForm.setLastName(lastName);
			incidentJournalierForm.setDegats(degats);
			incidentJournaliers.add(incidentJournalierForm);
		});
	    List<IncidentJournalierForm> incidentJournalierMetros = incidentJournaliers.stream()
	              .filter(i -> i.getType()== "metro")
	              .collect(Collectors.toList());
	 
	    return incidentJournalierMetros;
	  }*/
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionaccidenttransport')")
	@GetMapping("/accidenttransportspagination")
	  public List<AccidentTransportResponse> getAllAccidentTransportsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<AccidentTransportResponse> accidenttransport = new ArrayList<>();
	    repository.pageAccidentTransport(PageRequest.of(page,size)).forEach(accidenttransport::add);
	    return accidenttransport;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionaccidenttransport')")
	@GetMapping("/listaccidenttransports")
	  public List<AccidentTransport> getAllAccidentTransports() {
	    List<AccidentTransport> accidenttransport = new ArrayList<>();
	    repository.findAll().forEach(accidenttransport::add);
	    return accidenttransport;
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestionaccidenttransport')")
	@PostMapping("/addaccidenttransports")
	public AccidentTransport createAccidentTransport(@Valid @RequestBody AccidentTransportForm accidenttransportForm) {
		Long idaccident =accidenttransportForm.getIdaccident();
		Long idtransport =accidenttransportForm.getIdtransport();
		Accident acc = arepository.findById(idaccident).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
		AccidentTransport accidenttransport = new AccidentTransport();
		accidenttransport.setAccident(acc);
		accidenttransport.setMoyentransport(transport);
		return repository.save(accidenttransport);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionaccidenttransport')")
	@DeleteMapping("/deleteaccidenttransports/{id}")
	public Map<String, Boolean> deleteAccidentTransport(@PathVariable(value = "id") Long accidenttransportId)
			throws ResourceNotFoundException {
		AccidentTransport accidenttransport = repository.findById(accidenttransportId)
				.orElseThrow(() -> new ResourceNotFoundException("accident Transport not found  id :: " + accidenttransportId));

		repository.delete(accidenttransport);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionaccidenttransport')")
	  @DeleteMapping("/accidenttransports/delete")
	  public ResponseEntity<String> deleteAllAccidentTransports() {
	    System.out.println("Delete All accident Transports...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All accident Transports have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionaccidenttransport')")
	  @PutMapping("/editaccidenttransports/{id}")
	  public ResponseEntity<AccidentTransport> updateAccidentTransport(@PathVariable("id") long id, @RequestBody AccidentTransportForm accidenttransportForm) {
	    System.out.println("Update Accident Transport with ID = " + id + "...");
	    Long idaccident =accidenttransportForm.getIdaccident();
	    Long idtransport =accidenttransportForm.getIdtransport();
		Accident acc = arepository.findById(idaccident).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
	    Optional<AccidentTransport> accidentTransportInfo = repository.findById(id);
	 
	    if (accidentTransportInfo.isPresent()) {
	    	AccidentTransport accidenttransports = accidentTransportInfo.get();
	        
	    	accidenttransports.setAccident(acc);
	    	accidenttransports.setMoyentransport(transport);   
	      return new ResponseEntity<>(repository.save(accidenttransports), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
