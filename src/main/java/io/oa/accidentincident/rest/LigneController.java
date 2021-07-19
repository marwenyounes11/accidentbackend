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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.entity.Ligne;
import io.oa.accidentincident.repository.LigneRepository;
@CrossOrigin
@RestController

public class LigneController {
	@Autowired
	LigneRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionligne')")
	@GetMapping("/lignepaginationmc")
	  public List<Ligne> getAllLignesPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Ligne> ligne = new ArrayList<>();
	    repository.chercherLigne(mc,PageRequest.of(page,size)).forEach(ligne::add);
	 
	    return ligne;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionligne')")
	@GetMapping("/lignepagination")
	  public List<Ligne> getAllLignesPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Ligne> ligne = new ArrayList<>();
	    repository.pageLigne(PageRequest.of(page,size)).forEach(ligne::add);
	    return ligne;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionligne')")
	 @GetMapping("/lignes")
	  public List<Ligne> getAllLignes() {
	    System.out.println("Get all Lignes...");
	 
	    List<Ligne> lignes = new ArrayList<>();
	    repository.findAll().forEach(lignes::add);
	 
	    return lignes;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionligne')")
	@GetMapping("/lignes/{id}")
	public ResponseEntity<Ligne> getLigneById(@PathVariable(value = "id") Long ligneId)
			throws ResourceNotFoundException {
		Ligne ligne = repository.findById(ligneId)
				.orElseThrow(() -> new ResourceNotFoundException("Ligne not found for this id :: " + ligneId));
		return ResponseEntity.ok().body(ligne);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestionligne')")
	@PostMapping("/lignes")
	public Ligne createLigne(@Valid @RequestBody Ligne ligne) {
		return repository.save(ligne);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionligne')")
	@DeleteMapping("/lignes/{id}")
	public Map<String, Boolean> deleteLigne(@PathVariable(value = "id") Long ligneId)
			throws ResourceNotFoundException {
		Ligne ligne = repository.findById(ligneId)
				.orElseThrow(() -> new ResourceNotFoundException("Ligne not found  id :: " + ligneId));

		repository.delete(ligne);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionligne')")
	  @DeleteMapping("/lignes/delete")
	  public ResponseEntity<String> deleteAllLignes() {
	    System.out.println("Delete All Lignes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Lignes have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionligne')")
	  @PutMapping("/lignes/{id}")
	  public ResponseEntity<Ligne> updateLigne(@PathVariable("id") long id, @RequestBody Ligne ligne) {
	    System.out.println("Update Ligne with ID = " + id + "...");
	 
	    Optional<Ligne> ligneInfo = repository.findById(id);
	 
	    if (ligneInfo.isPresent()) {
	    	Ligne lignet = ligneInfo.get();
	          
	           lignet.setNameLigne(ligne.getNameLigne());
	           
	          
	      return new ResponseEntity<>(repository.save(lignet), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
