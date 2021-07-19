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
import io.oa.accidentincident.form.SourceDeclareHuissierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceDeclareHuissier;
import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.AgentTranstuRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.repository.SecuriteRepository;
import io.oa.accidentincident.repository.SourceDeclareHuissierRepository;
import io.oa.accidentincident.repository.SourceInformRepository;





@CrossOrigin
@RestController

public class SourceDeclareHuissierController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	SourceDeclareHuissierRepository  repository;
	@Autowired 
	SourceInformRepository  srepository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourcedeclarehuissier')")
	@GetMapping("/sourcedeclarehuissierpaginationmc")
	  public List<SourceDeclareHuissier> getAllSourceDeclareHuissiersPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<SourceDeclareHuissier> sourcedeclarehuissier = new ArrayList<>();
	    repository.chercherSourceDeclareHuissier(mc,PageRequest.of(page,size)).forEach(sourcedeclarehuissier::add);
	 
	    return sourcedeclarehuissier;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourcedeclarehuissier')")
	@GetMapping("sourcedeclarehuissierpagination")
	  public List<SourceDeclareHuissier> getAllSourceDeclareHuissiersPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<SourceDeclareHuissier> sourcedeclarehuissier = new ArrayList<>();
	    repository.pageSourceDeclareHuissier(PageRequest.of(page,size)).forEach(sourcedeclarehuissier::add);
	    return sourcedeclarehuissier;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourcedeclarehuissier')")
	 @GetMapping("/listsourcedeclarehuissiers")
	  public List<SourceDeclareHuissier> getAllSourceDeclareHuissiers() {
	    System.out.println("Get all source declare huissier...");
	 
	    List<SourceDeclareHuissier> sourcedeclarehuissiers = new ArrayList<>();
	    repository.findAll().forEach(sourcedeclarehuissiers::add);
	 
	    return sourcedeclarehuissiers;
	  }
	 
	@PreAuthorize("@authorizationSE.can('create', 'gestionsourcedeclarehuissier')")
	@PostMapping("/addsourcedeclarehuissiers")
	public SourceDeclareHuissier createSourceDeclareHuissier(@Valid @RequestBody SourceDeclareHuissierForm sourcedeclarehuissierForm) {
		
		
		SourceDeclareHuissier sourcedeclarehuissier = new SourceDeclareHuissier();
		sourcedeclarehuissier.setLastName(sourcedeclarehuissierForm.getLastName());
		sourcedeclarehuissier.setMatricule(sourcedeclarehuissierForm.getMatricule());
		sourcedeclarehuissier.setName(sourcedeclarehuissierForm.getName());
		sourcedeclarehuissier.setPhone(sourcedeclarehuissierForm.getPhone());
		
		
		return repository.save(sourcedeclarehuissier);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsourcedeclarehuissier')")
	@DeleteMapping("/deletesourcedeclarehuissiers/{id}")
	public Map<String, Boolean> deleteSourceDeclareHuissier(@PathVariable(value = "id") Long sourcedeclarehuissierId)
			throws ResourceNotFoundException {
		SourceDeclareHuissier sourcedeclarehuissier = repository.findById(sourcedeclarehuissierId)
				.orElseThrow(() -> new ResourceNotFoundException("source declare huissier not found  id :: " + sourcedeclarehuissierId));

		repository.delete(sourcedeclarehuissier);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsourcedeclarehuissier')")
	  @DeleteMapping("/sourcedeclarehuissiers/delete")
	  public ResponseEntity<String> deleteAllSourceDeclareHuissiers() {
	    System.out.println("Delete All source declare huissier...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All source declare huissier have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionsourcedeclarehuissier')")
	  @PutMapping("/editsourcedeclarehuissiers/{id}")
	  public ResponseEntity<SourceDeclareHuissier> updateSourceDeclareHuissier(@PathVariable("id") long id, @RequestBody SourceDeclareHuissierForm sourcedeclarehuissierForm) {
	    System.out.println("Update SourceDeclareHuissier with ID = " + id + "...");
	    Optional<SourceDeclareHuissier> sourcedeclarehuissierInfo = repository.findById(id);
	 
	    if (sourcedeclarehuissierInfo.isPresent()) {
	    	SourceDeclareHuissier sourcedeclarehuissier = sourcedeclarehuissierInfo.get();
	    	sourcedeclarehuissier.setLastName(sourcedeclarehuissierForm.getLastName());
			sourcedeclarehuissier.setMatricule(sourcedeclarehuissierForm.getMatricule());
			sourcedeclarehuissier.setName(sourcedeclarehuissierForm.getName());
			sourcedeclarehuissier.setPhone(sourcedeclarehuissierForm.getPhone());
	      return new ResponseEntity<>(repository.save(sourcedeclarehuissier), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
