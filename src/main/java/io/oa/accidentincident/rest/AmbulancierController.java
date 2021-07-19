package io.oa.accidentincident.rest;


import java.text.ParseException;
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
import io.oa.accidentincident.form.AgentTranstuForm;
import io.oa.accidentincident.form.AmbulancierForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.AgentTranstuRepository;
import io.oa.accidentincident.repository.AmbulancierRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.repository.SourceInformRepository;





@CrossOrigin
@RestController

public class AmbulancierController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	AmbulancierRepository  repository;
	@Autowired 
	SourceInformRepository  srepository;
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionambulancier')")
	@GetMapping("/ambulancierspaginationmc")
	  public List<Ambulancier> getAllAmbulanciersPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Ambulancier> ambulanciers = new ArrayList<>();
	    repository.chercherAmbulancier(mc,PageRequest.of(page,size)).forEach(ambulanciers::add);
	 
	    return ambulanciers;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionambulancier')")
	@GetMapping("/ambulancierspagination")
	  public List<Ambulancier> getAllAmbulanciersPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Ambulancier> ambulanciers = new ArrayList<>();
	    repository.pageAmbulancier(PageRequest.of(page,size)).forEach(ambulanciers::add);
	    return ambulanciers;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionambulancier')")
	 @GetMapping("/listambulanciers")
	  public List<Ambulancier> getAllAmbulanciers() {
	    System.out.println("Get all Ambulanciers...");
	 
	    List<Ambulancier> ambulanciers = new ArrayList<>();
	    repository.findAll().forEach(ambulanciers::add);
	 
	    return ambulanciers;
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestionambulancier')")
	@PostMapping("/addambulanciers")
	public Ambulancier createAmbulancier(@Valid @RequestBody AmbulancierForm ambulancierForm) {
		Long idsourceinform =ambulancierForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
		Ambulancier ambulancier = new Ambulancier();
		ambulancier.setName(ambulancierForm.getName());
		ambulancier.setLastName(ambulancierForm.getLastName());
		ambulancier.setMatricule(ambulancierForm.getMatricule());
		ambulancier.setPhone(ambulancierForm.getPhone());
		ambulancier.setSourceinform(sourceinform);
		
		return repository.save(ambulancier);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionambulancier')")
	@DeleteMapping("/deleteambulancier/{id}")
	public Map<String, Boolean> deleteAmbulancier(@PathVariable(value = "id") Long ambulancierId)
			throws ResourceNotFoundException {
		Ambulancier ambulancier = repository.findById(ambulancierId)
				.orElseThrow(() -> new ResourceNotFoundException("ambulancier not found  id :: " + ambulancierId));

		repository.delete(ambulancier);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionambulancier')")
	  @DeleteMapping("/ambulanciers/delete")
	  public ResponseEntity<String> deleteAllAmbulanciers() {
	    System.out.println("Delete All ambulanciers...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All ambulanciers have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionambulancier')")
	  @PutMapping("/editambulanciers/{id}")
	  public ResponseEntity<Ambulancier> updateAmbulancier(@PathVariable("id") long id, @RequestBody AmbulancierForm ambulancierForm) {
	    System.out.println("Update Ambulancier with ID = " + id + "...");
	    Long idsourceinform =ambulancierForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
	    Optional<Ambulancier> ambulancierInfo = repository.findById(id);
	 
	    if (ambulancierInfo.isPresent()) {
	    	Ambulancier ambulancier = ambulancierInfo.get();
	    	ambulancier.setName(ambulancierForm.getName());
			ambulancier.setLastName(ambulancierForm.getLastName());
			ambulancier.setMatricule(ambulancierForm.getMatricule());
			ambulancier.setPhone(ambulancierForm.getPhone());
			ambulancier.setSourceinform(sourceinform);  
	      return new ResponseEntity<>(repository.save(ambulancier), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
