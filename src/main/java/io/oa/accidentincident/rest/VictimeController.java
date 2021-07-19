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
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.Victime;
import io.oa.accidentincident.repository.AgentRepository;
import io.oa.accidentincident.repository.VictimeRepository;

@CrossOrigin
@RestController
public class VictimeController {
	@Autowired
	VictimeRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionvictime')")
	@GetMapping("/victimepaginationmc")
	  public List<Victime> getAllVictimesPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Victime> victime = new ArrayList<>();
	    repository.chercherVictime(mc,PageRequest.of(page,size)).forEach(victime::add);
	 
	    return victime;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionvictime')")
	@GetMapping("victimepagination")
	  public List<Victime> getAllVictimesPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Victime> victime = new ArrayList<>();
	    repository.pageVictime(PageRequest.of(page,size)).forEach(victime::add);
	    return victime;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionvictime')")
	 @GetMapping("/listvictimes")
	  public List<Victime> getAllVictimes() {
	    System.out.println("Get all Victimes...");
	 
	    List<Victime> victimes = new ArrayList<>();
	    repository.findAll().forEach(victimes::add);
	 
	    return victimes;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionvictime')")
	@GetMapping("/victime/{id}")
	public ResponseEntity<Victime> getVictimeById(@PathVariable(value = "id") Long victimeId)
			throws ResourceNotFoundException {
		Victime victime = repository.findById(victimeId)
				.orElseThrow(() -> new ResourceNotFoundException("Victime not found for this id :: " + victimeId));
		return ResponseEntity.ok().body(victime);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestionvictime')")
	@PostMapping("/addvictimes" )
	public Victime createVictime(@Valid @RequestBody Victime victime) {
		return repository.save(victime);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionvictime')")
	@DeleteMapping("/deletevictimes/{id}")
	public Map<String, Boolean> deleteVictime(@PathVariable(value = "id") Long victimeId)
			throws ResourceNotFoundException {
		Victime victime = repository.findById(victimeId)
				.orElseThrow(() -> new ResourceNotFoundException("Victime not found  id :: " + victimeId));

		repository.delete(victime);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionvictime')")
	  @DeleteMapping("/victimes/delete")
	  public ResponseEntity<String> deleteAllVictimes() {
	    System.out.println("Delete All Victimes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Victimes have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionvictime')")
	  @PutMapping("/editvictimes/{id}")
	  public ResponseEntity<Victime> updateVictime(@PathVariable("id") long id, @RequestBody Victime victime) {
	    System.out.println("Update Victime with ID = " + id + "...");
	 
	    Optional<Victime> victimeInfo = repository.findById(id);
	 
	    if (victimeInfo.isPresent()) {
	    	Victime victimee = victimeInfo.get();
	          
	           victimee.setNameVictime(victime.getNameVictime());
	           victimee.setLastNameVictime(victime.getLastNameVictime());
	           victimee.setNiveauBlessure(victime.getNiveauBlessure());
	           victimee.setTypeVictime(victime.getTypeVictime());
	           victimee.setEtatVictime(victime.getEtatVictime());
	           victimee.setCorpBlesser(victime.getCorpBlesser());
	          
	      return new ResponseEntity<>(repository.save(victimee), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
