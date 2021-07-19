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
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.form.SecuriteForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.AgentTranstuRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.repository.SecuriteRepository;
import io.oa.accidentincident.repository.SourceInformRepository;





@CrossOrigin
@RestController

public class SecuriteController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	SecuriteRepository  repository;
	@Autowired 
	SourceInformRepository  srepository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsecurite')")
	@GetMapping("/securitepaginationmc")
	  public List<Securite> getAllSecuritesPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Securite> securite = new ArrayList<>();
	    repository.chercherSecurite(mc,PageRequest.of(page,size)).forEach(securite::add);
	 
	    return securite;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsecurite')")
	@GetMapping("securitepagination")
	  public List<Securite> getAllSecuritesPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Securite> securite = new ArrayList<>();
	    repository.pageSecurite(PageRequest.of(page,size)).forEach(securite::add);
	    return securite;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsecurite')")
	 @GetMapping("/listsecurites")
	  public List<Securite> getAllSecurites() {
	    System.out.println("Get all Securites...");
	 
	    List<Securite> securites = new ArrayList<>();
	    repository.findAll().forEach(securites::add);
	 
	    return securites;
	  }
	 
	@PreAuthorize("@authorizationSE.can('create', 'gestionsecurite')")
	@PostMapping("/addsecurites")
	public Securite createSecurite(@Valid @RequestBody SecuriteForm securiteForm) {
		Long idsourceinform =securiteForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
		Securite securite = new Securite();
		securite.setAddress(securiteForm.getAddress());
		securite.setType(securiteForm.getType());
		securite.setSourceinform(sourceinform);
		
		return repository.save(securite);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsecurite')")
	@DeleteMapping("/deletesecurites/{id}")
	public Map<String, Boolean> deleteSecurite(@PathVariable(value = "id") Long securiteId)
			throws ResourceNotFoundException {
		Securite securite = repository.findById(securiteId)
				.orElseThrow(() -> new ResourceNotFoundException("securite not found  id :: " + securiteId));

		repository.delete(securite);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsecurite')")
	  @DeleteMapping("/securites/delete")
	  public ResponseEntity<String> deleteAllSecurites() {
	    System.out.println("Delete All securite...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All securite have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionsecurite')")
	  @PutMapping("/editsecurites/{id}")
	  public ResponseEntity<Securite> updateSecurite(@PathVariable("id") long id, @RequestBody SecuriteForm securiteForm) {
	    System.out.println("Update Securite with ID = " + id + "...");
	    Long idsourceinform =securiteForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
	    Optional<Securite> securiteInfo = repository.findById(id);
	 
	    if (securiteInfo.isPresent()) {
	    	Securite securite = securiteInfo.get();
	    	securite.setAddress(securiteForm.getAddress());
			securite.setType(securiteForm.getType());
			securite.setSourceinform(sourceinform);  
	      return new ResponseEntity<>(repository.save(securite), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
