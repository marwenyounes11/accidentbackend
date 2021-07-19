package io.oa.accidentincident.rest;


import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;


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

import io.oa.accidentincident.form.DegatVictimeForm;

import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.DegatVictime;

import io.oa.accidentincident.entity.Victime;

import io.oa.accidentincident.repository.DegatRepository;

import io.oa.accidentincident.repository.DegatVictimeRepository;

import io.oa.accidentincident.repository.VictimeRepository;
import io.oa.accidentincident.response.DegatTransportResponse;
import io.oa.accidentincident.response.DegatVictimeResponse;





@CrossOrigin
@RestController

public class DegatVictimeController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	DegatVictimeRepository  repository;
	@Autowired 
	DegatRepository  drepository;
	@Autowired 	
	VictimeRepository  vrepository;
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegatvictime')")
	@GetMapping("/degatvictimespagination")
	  public List<DegatVictimeResponse> getAllDegatVictimesPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<DegatVictimeResponse> degatvictimes = new ArrayList<>();
	    repository.pageDegatVictime(PageRequest.of(page,size)).forEach(degatvictimes::add);
	    return degatvictimes;
	  }
	
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegatvictime')")
	@GetMapping("/degatvictimes/{id}")
		public ResponseEntity<DegatVictimeResponse> getDegatVictimeById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
		 DegatVictimeResponse degatvictime = repository.degatVictime(Id);
			return ResponseEntity.ok().body(degatvictime);
		}
	 
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegatvictime')")
	 @GetMapping("/degatvictimes")
	  public List<DegatVictimeResponse> getAllDegatVictimes() {
	    return repository.listDegatVictime();
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestiondegatvictime')")
	@PostMapping("/adddegatvictimes")
	public DegatVictime createDegatVictime(@Valid @RequestBody DegatVictimeForm degatvictimeForm) {
		Long iddegat =degatvictimeForm.getIddegat();
		Long idvictime =degatvictimeForm.getIdvictime();
		Degat dg = drepository.findById(iddegat).orElse(null);
		Victime victime = vrepository.findById(idvictime).orElse(null);
		DegatVictime degatvictime = new DegatVictime();
		degatvictime.setDegat(dg);
		degatvictime.setVictime(victime);
		return repository.save(degatvictime);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegatvictime')")
	@DeleteMapping("/deletedegatvictimes/{id}")
	public void deleteDegatVictime(@PathVariable(value = "id") Long degatvictimeId)
			throws ResourceNotFoundException {
		repository.deleteDegatVictime(degatvictimeId);
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegatvictime')")
	  @DeleteMapping("/degatvictimes/delete")
	  public ResponseEntity<String> deleteAllDegatVictimes() {
	    System.out.println("Delete All degat Victimes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All degat Victimes have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondegatvictime')")
	  @PutMapping("/editdegatvictimes/{id}")
	  public ResponseEntity<DegatVictime> updateDegatVictime(@PathVariable("id") long id, @RequestBody DegatVictimeForm degatvictimeForm) {
	    System.out.println("Update Degat Victime with ID = " + id + "...");
	    Long iddegat =degatvictimeForm.getIddegat();
	    Long idvictime =degatvictimeForm.getIdvictime();
		Degat degat = drepository.findById(iddegat).orElse(null);
		Victime victime = vrepository.findById(idvictime).orElse(null);
	    Optional<DegatVictime> degatVictimeInfo = repository.findById(id);
	 
	    if (degatVictimeInfo.isPresent()) {
	    	DegatVictime degatvictimes = degatVictimeInfo.get();
	        
	    	degatvictimes.setDegat(degat);
	    	degatvictimes.setVictime(victime);   
	      return new ResponseEntity<>(repository.save(degatvictimes), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
