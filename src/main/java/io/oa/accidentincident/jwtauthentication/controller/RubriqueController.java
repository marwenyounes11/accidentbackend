
package io.oa.accidentincident.jwtauthentication.controller;


import java.util.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.oa.accidentincident.entity.*;
import io.oa.accidentincident.repository.RubriqueRepository;
import io.oa.accidentincident.repository.RoleRepository;
import io.oa.accidentincident.jwtauthentication.message.request.RubriqueForm;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RubriqueController {
	
    @Autowired
    RubriqueRepository rubriqueRepository;
    @Autowired
    RoleRepository roleRepository;

   /* @GetMapping("/listrubriques")
	  public List<Rubrique> getListRubriques() {
	    System.out.println("Get all Rubrique ...");
	 
	    List<Rubrique> r = new ArrayList<>();
	    rubriqueRepository.findAll().forEach(r::add);
	 
	    return r;
	  }*/
    
  //  @PreAuthorize("@authorizationRole.can('Admin')")
  @GetMapping("/rubriques")
	  public List<Rubrique> getAllRubriques() {
	    System.out.println("Get all Rubriques...");
	 
	    List<Rubrique> rubriques = new ArrayList<>();
	   rubriqueRepository.findAll().forEach(rubriques::add);
	 
	    return rubriques;
	  }

  
    
   // @PreAuthorize("@authorizationRole.can('Admin')")
   @GetMapping("/rubriques/{id}")
	public ResponseEntity<Rubrique> getRubriqueById(@PathVariable(value = "id") Long rubriqueId)
			throws ResourceNotFoundException {
    	Rubrique rubrique= rubriqueRepository.findById(rubriqueId)
				.orElseThrow(() -> new ResourceNotFoundException("Rubrique not found for this id :: " + rubriqueId));
		return ResponseEntity.ok().body(rubrique);
	}
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PostMapping("/addrubriques")
    public ResponseEntity<String> registerRubrique(@Valid @RequestBody RubriqueForm rubriqueForm) {
        
        Rubrique rubrique = new Rubrique ();
       rubrique.setLibelle(rubriqueForm.getLibelle());
      
        rubriqueRepository.save(rubrique);
        return ResponseEntity.ok().body("Rubrique registered successfully!");
    }
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PutMapping("/editrubriques/{rubriqueId}")
    public ResponseEntity<String> updateRubrique(@PathVariable(value = "rubriqueId") Long rubriqueId,@Valid @RequestBody RubriqueForm rubriqueForm) {
    	Optional<Rubrique> rubriqueInfo = rubriqueRepository.findById(rubriqueId);
    	 if (rubriqueInfo.isPresent()) {
    		 Rubrique rubrique = rubriqueInfo.get();
    		 rubrique.setLibelle(rubriqueForm.getLibelle());
    		 
    		 rubriqueRepository.save(rubrique);
    		 return ResponseEntity.ok().body("Rubrique update successfully!");
    	 }else {
     	      return new ResponseEntity<String>("Rubrique not update ", HttpStatus.NOT_FOUND);
   	    }
    	
    }
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/rubriques/{rubriqueId}")
	public Map<String, Boolean> deleteRubrique(@PathVariable(value = "rubriqueId") Long rubriqueId)
			throws ResourceNotFoundException {
    	Rubrique rubrique = rubriqueRepository.findById(rubriqueId)
				.orElseThrow(() -> new ResourceNotFoundException("Rubrique not found  id :: " + rubriqueId));

		rubriqueRepository.delete(rubrique);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/rubriques/delete")
	  public ResponseEntity<String> deleteAllRubriques() {
	    System.out.println("Delete All Rubriques...");
	 
	    rubriqueRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Rubriques have been deleted!", HttpStatus.OK);
	  }
}

