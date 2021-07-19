package io.oa.accidentincident.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.exception.ResourceNotFoundException;

import io.oa.accidentincident.repository.LieuxAccidentRepository;

@CrossOrigin
@RestController

public class LieuxAccidentController {
	@Autowired
	LieuxAccidentRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionlieuxaccident')")
	@GetMapping("/lieuxpaginationmc")
	  public List<LieuxAccident> getAllLieuxAccidentsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<LieuxAccident> lieux = new ArrayList<>();
	    repository.chercherLieuxAccident(mc,PageRequest.of(page,size)).forEach(lieux::add);
	 
	    return lieux;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionlieuxaccident')")
	@GetMapping("/lieuxpagination")
	  public List<LieuxAccident> getAllLieuxAccidentsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<LieuxAccident> lieux = new ArrayList<>();
	    repository.pageLieuxAccident(PageRequest.of(page,size)).forEach(lieux::add);
	    return lieux;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionlieuxaccident')")
	 @GetMapping("/getLieux")
	  public List<LieuxAccident> getAllLieuxs() {
	    System.out.println("Get all Lieux...");
	 
	    List<LieuxAccident> lieux = new ArrayList<>();
	    repository.findAll().forEach(lieux::add);
	 
	    return lieux;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionlieuxaccident')")
	@GetMapping("/getLieux/{id}")
	public ResponseEntity<LieuxAccident> getLieuxById(@PathVariable(value = "id") Long lieuxId)
			throws ResourceNotFoundException {
		LieuxAccident lieux = repository.findById(lieuxId)
				.orElseThrow(() -> new ResourceNotFoundException("Lieux not found for this id :: " + lieuxId));
		return ResponseEntity.ok().body(lieux);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestionlieuxaccident')")
@PostMapping("/addLieux")
	public LieuxAccident createLieux(@Valid @RequestBody LieuxAccident lieux) {
		return repository.save(lieux);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionlieuxaccident')")
	@DeleteMapping("/deleteLieux/{id}")
	public Map<String, Boolean> deleteLieux(@PathVariable(value = "id") Long lieuxId)
			throws ResourceNotFoundException {
		LieuxAccident lieux = repository.findById(lieuxId)
				.orElseThrow(() -> new ResourceNotFoundException("lieux not found  id :: " + lieuxId));

		repository.delete(lieux);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionlieuxaccident')")
	  @DeleteMapping("/deleteLieux/delete")
	  public ResponseEntity<String> deleteAllLieux() {
	    System.out.println("Delete All Lieux...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Lieux have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionlieuxaccident')")
  @PutMapping("/editLieux/{id}")
	  public ResponseEntity<LieuxAccident> updateLieux(@PathVariable("id") long id, @RequestBody LieuxAccident lieux) {
	    System.out.println("Update Lieux with ID = " + id + "...");
	 
	    Optional<LieuxAccident> lieuxInfo = repository.findById(id);
	 
	    if (lieuxInfo.isPresent()) {
	    	LieuxAccident lieuxx = lieuxInfo.get();
	          
	    	lieuxx.setLatitude(lieuxx.getLatitude());
	    	lieuxx.setLongitude(lieuxx.getLongitude());
	    	lieuxx.setEmplacement(lieuxx.getEmplacement());
	          
	      return new ResponseEntity<>(repository.save(lieuxx), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
