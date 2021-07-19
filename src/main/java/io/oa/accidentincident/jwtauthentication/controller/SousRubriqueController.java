
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
import io.oa.accidentincident.repository.*;
import io.oa.accidentincident.response.SousRubriqueResponse;
import io.oa.accidentincident.jwtauthentication.message.request.SousRubriqueForm;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SousRubriqueController {

   

    @Autowired
    SousRubriqueRepository sousRubriqueRepository;

    @Autowired
    RubriqueRepository rubriqueRepository;
    
    //@PreAuthorize("@authorizationRole.can('Admin')")
   @GetMapping("/listsousrubriques")
	  public List<SousRubriqueResponse> getListSousRubriques() {
	    System.out.println("Get all SousRubrique ...");
	 
	    List<SousRubriqueResponse> r = new ArrayList<>();
	    sousRubriqueRepository.listSousRubrique().forEach(r::add);
	 
	    return r;
	  }
    
   // @PreAuthorize("@authorizationSE.can('afficher', 'gestionsousrubrique')")
   /* @GetMapping("/sousRubriques")
	  public List<SousRubrique> getAllSousRubrique() {
	    System.out.println("Get all SousRubriques...");
	 
	    List<SousRubrique> sousRubriques = new ArrayList<>();
	   sousRubriqueRepository.findAll().forEach(sousRubriques::add);
	 
	    return sousRubriques;
	  }*/
    
   // @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/sousRubriques/{id}")
	public ResponseEntity<SousRubriqueResponse> getSousRubriqueById(@PathVariable(value = "id") Long sousRubriqueId)
			throws ResourceNotFoundException {
    	SousRubriqueResponse sousRubrique= sousRubriqueRepository.sousRubrique(sousRubriqueId);
		return ResponseEntity.ok().body(sousRubrique);
	}
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PostMapping("/addsousRubriques")
    public ResponseEntity<String> registerSousRubrique(@Valid @RequestBody SousRubriqueForm sousRubriqueForm) {
       
        Long idRubrique = sousRubriqueForm.getIdrubrique();
        Rubrique  rubrique = rubriqueRepository.findById(idRubrique).orElse(null);
        SousRubrique sousRubrique = new SousRubrique();
        sousRubrique.setLibelle(sousRubriqueForm.getLibelle());
        sousRubrique.setRubrique(rubrique);
        sousRubriqueRepository.save(sousRubrique);

        return ResponseEntity.ok().body("SousRubrique registered successfully!");
    }
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PutMapping("/sousRubriques/{sousRubriqueId}")
    public ResponseEntity<String> updateSousRubrique(@PathVariable(value = "sousRubriqueId") Long sousRubriqueId,@Valid @RequestBody SousRubriqueForm sousRubriqueForm) {
    	Optional<SousRubrique> sousRubriqueInfo = sousRubriqueRepository.findById(sousRubriqueId);
    	 Rubrique  rubrique = rubriqueRepository.findById(sousRubriqueForm.getIdrubrique()).orElse(null);
    	 if (sousRubriqueInfo.isPresent()) {
    		 SousRubrique sousRubrique = sousRubriqueInfo.get();
    		 sousRubrique.setLibelle(sousRubriqueForm.getLibelle());
    		 sousRubrique.setRubrique(rubrique);
    		 sousRubriqueRepository.save(sousRubrique);
    	return ResponseEntity.ok().body("sousRubrique update successfully!");
    	 }else {
  	      return new ResponseEntity<String>("sousRubrique not update ", HttpStatus.NOT_FOUND);
  	    }
    }
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/sousRubriques/{sousRubriqueId}")
	public Map<String, Boolean> deleteSousRubrique(@PathVariable(value = "sousRubriqueId") Long sousRubriqueId)
			throws ResourceNotFoundException {
    	SousRubrique sousRubrique = sousRubriqueRepository.findById(sousRubriqueId)
				.orElseThrow(() -> new ResourceNotFoundException("SousRubrique not found  id :: " + sousRubriqueId));

		sousRubriqueRepository.delete(sousRubrique);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/sousRubriques/delete")
	  public ResponseEntity<String> deleteAllSousRubriques() {
	    System.out.println("Delete All SousRubriques...");
	 
	    sousRubriqueRepository.deleteAll();
	 
	    return new ResponseEntity<>("All SousRubriques have been deleted!", HttpStatus.OK);
	  }
}

