
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
import io.oa.accidentincident.jwtauthentication.message.request.DroitForm;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationRole;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DroitController {

   

    @Autowired
    DroitRepository droitRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SousRubriqueRepository sousRubriqueRepository;
 @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/listdroits")
	  public List<Droit> getListDroits() {
	    System.out.println("Get all Droit ...");
	 
	    List<Droit> d = new ArrayList<>();
	    droitRepository.findAll().forEach(d::add);
	 
	    return d;
	  }

 //@PreAuthorize("@authorizationRole.can('Admin')")
 @GetMapping("/droits")
	  public List<Droit> getAllDroits() {
	    System.out.println("Get all Droits...");
	 
	    List<Droit> droits = new ArrayList<>();
	   droitRepository.findAll().forEach(droits::add);
	 
	    return droits;
	  }
    
 //@PreAuthorize("@authorizationRole.can('Admin')")   
 @GetMapping("/droits/{id}")
	public ResponseEntity<Droit> getDroitById(@PathVariable(value = "id") Long droitId)
			throws ResourceNotFoundException {
    	Droit droit = droitRepository.findById(droitId)
				.orElseThrow(() -> new ResourceNotFoundException("Droit not found for this id :: " + droitId));
		return ResponseEntity.ok().body(droit);
	}
 @PreAuthorize("@authorizationRole.can('Admin')")
 @PostMapping("/adddroits")
    public ResponseEntity<String> registerDroit(@Valid @RequestBody DroitForm droitForm) {
       
        
        Droit droit = new Droit();
        droit.setAction(droitForm.getAction());
        droitRepository.save(droit);

        return ResponseEntity.ok().body("droit registered successfully!");
    }
 @PreAuthorize("@authorizationRole.can('Admin')")
 @PutMapping("/droits/{droitId}")
    public ResponseEntity<String> updateDroit(@PathVariable(value = "droitId") Long droitId,@Valid @RequestBody DroitForm droitForm) {
    	Optional<Droit> droitInfo = droitRepository.findById(droitId);
    	 if (droitInfo.isPresent()) {
    		 Droit droit = droitInfo.get();
    		 droit.setAction(droitForm.getAction());
    		 droitRepository.save(droit);
    	return ResponseEntity.ok().body("Droit update successfully!");
    	 }else {
  	      return new ResponseEntity<String>("Droit not update ", HttpStatus.NOT_FOUND);
  	    }
    }
 @PreAuthorize("@authorizationRole.can('Admin')")
 @DeleteMapping("/droits/{droitId}")
	public Map<String, Boolean> deleteDroit(@PathVariable(value = "droitId") Long droitId)
			throws ResourceNotFoundException {
    	Droit droit = droitRepository.findById(droitId)
				.orElseThrow(() -> new ResourceNotFoundException("Droit not found  id :: " + droitId));

		droitRepository.delete(droit);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
 @PreAuthorize("@authorizationRole.can('Admin')")
 @DeleteMapping("/droits/delete")
	  public ResponseEntity<String> deleteAllDroits() {
	    System.out.println("Delete All Droits...");
	 
	    droitRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Droits have been deleted!", HttpStatus.OK);
	  }
}

