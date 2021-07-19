
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
import io.oa.accidentincident.response.DroitRolesResponse;
import io.oa.accidentincident.jwtauthentication.message.request.DroitRolesForm;
import io.oa.accidentincident.jwtauthentication.message.request.RoleForm;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DroitRolesController {

    

   

    @Autowired
    DroitRolesRepository droitrolesRepository;
    @Autowired
   RoleRepository roleRepository;
    @Autowired
    DroitRepository droitRepository;
    @Autowired
    SousRubriqueRepository sousrubriqueRepository;

 /*   @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/listdroitroles")
	  public List<DroitRolesResponse> getListDroitRoles() {
	    System.out.println("Get all Droit Roles...");
	 
	    List<DroitRolesResponse> dr = new ArrayList<>();
	    droitrolesRepository.listDroitRoles().forEach(dr::add);
	 
	    return dr;
	  }
*/
   
   // @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/droitroles")
	  public List<DroitRoles> getAllDroitRoles() {
	    System.out.println("Get all Droit Roles...");
	 
	    List<DroitRoles> droitroles = new ArrayList<>();
	   droitrolesRepository.findAll().forEach(droitroles::add);
	 
	    return droitroles;
	  }
    
   
    
   // @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/droitroles/{id}")
	public ResponseEntity<DroitRoles> getDroitRolesById(@PathVariable(value = "id") Long droitrolesId)
			throws ResourceNotFoundException {
    	DroitRoles droitroles = droitrolesRepository.findById(droitrolesId)
				.orElseThrow(() -> new ResourceNotFoundException("Droit Role not found for this id :: " + droitrolesId));
		return ResponseEntity.ok().body(droitroles);
	}
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/droitrolesparam")
	public DroitRoles getDroitRolesByDroitSousRubriqueRole(@RequestParam("droitid") Long droitid,@RequestParam("sousrubriqueid") Long sousrubriqueid,@RequestParam("roleid")Long roleid )
			throws ResourceNotFoundException {
    	Droit droit;
    	Optional<Droit> droitinfo= droitRepository.findById(droitid);
    	if (droitinfo.isPresent()) {
   		 droit = droitinfo.get();
   		
   	}
   	else{
  		 droit = null;
		
  	}
    	SousRubrique sousrubrique;
    	Optional<SousRubrique> sousrubriqueinfo= sousrubriqueRepository.findById(sousrubriqueid);
    	if (sousrubriqueinfo.isPresent()) {
    		sousrubrique = sousrubriqueinfo.get();
      		
      	}
      	else{
      		sousrubrique = null;
   		
     	}
    	Role role;
    	Optional<Role> roleinfo= roleRepository.findById(roleid);
    	if (roleinfo.isPresent()) {
    		role = roleinfo.get();
      		
      	}
      	else{
      		role = null;
   		
     	}
    	DroitRoles droitroles=new DroitRoles();
    	Optional<DroitRoles> droitroleInfo = droitrolesRepository.findDroitRolesByDroitAndSousrubriqueAndRole(droit, sousrubrique, role);
    	if (droitroleInfo.isPresent()) {
    		 droitroles = droitroleInfo.get();
    		
    	}
    	else{
   		 droitroles = null;
 		
   	}
    	return droitroles;
	}
  
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PostMapping("/droitroles")
    public ResponseEntity<String> registerDroitRoles(@Valid @RequestBody DroitRolesForm droitrolesForm) {
    	Long idRole = droitrolesForm.getIdrole();
        Role role= roleRepository.findById(idRole).orElse(null);
        Long idDroit = droitrolesForm.getIddroit();
        Droit droit= droitRepository.findById(idDroit).orElse(null);
        Long idsousrubrique = droitrolesForm.getIdsousrubrique();
        SousRubrique sousrubrique= sousrubriqueRepository.findById(idsousrubrique).orElse(null);
        DroitRoles droitroles = new DroitRoles();
       droitroles.setDroit(droit);
       droitroles.setRole(role);
       droitroles.setSousrubrique(sousrubrique);
        droitrolesRepository.save(droitroles);
        return ResponseEntity.ok().body("Droit Roles registered successfully!");
    }
    
   
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PutMapping("/droitroles/{droitroleId}")
    public ResponseEntity<String> updateDroitRoles(@PathVariable(value = "droitroleId") Long droitroleId,@Valid @RequestBody DroitRolesForm droitroleForm) {
    	Optional<DroitRoles> droitroleInfo = droitrolesRepository.findById(droitroleId);
    	Droit droit= droitRepository.findById(droitroleForm.getIddroit()).orElse(null);
    	Role role= roleRepository.findById(droitroleForm.getIdrole()).orElse(null);
    	 Long idsousrubrique = droitroleForm.getIdsousrubrique();
         SousRubrique sousrubrique= sousrubriqueRepository.findById(idsousrubrique).orElse(null);
    	 if (droitroleInfo.isPresent()) {
    		 DroitRoles droitroles = droitroleInfo.get();
    		 droitroles.setDroit(droit);
    	       droitroles.setRole(role);
    	       droitroles.setSousrubrique(sousrubrique);
    	       droitrolesRepository.save(droitroles);
    		 return ResponseEntity.ok().body(" droit Role update successfully!");
    	 }else {
     	      return new ResponseEntity<String>("droit Role not update ", HttpStatus.NOT_FOUND);
   	    }
    	
    }
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/droitroles/{droitroleId}")
	public Map<String, Boolean> deleteDroitRole(@PathVariable(value = "droitroleId") Long droitroleId)
			throws ResourceNotFoundException {
    	DroitRoles droitrole = droitrolesRepository.findById(droitroleId)
				.orElseThrow(() -> new ResourceNotFoundException("Droit Role not found  id :: " + droitroleId));

		droitrolesRepository.delete(droitrole);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/droitroles/byroledroit")
	public ResponseEntity<String> deleteByDroitRole(@Valid @RequestBody DroitRolesForm droitrolesForm)
			{
    	Long idRole = droitrolesForm.getIdrole();
        Role role= roleRepository.findById(idRole).orElse(null);
        Long idDroit = droitrolesForm.getIddroit();
        Droit droit= droitRepository.findById(idDroit).orElse(null);
        Long idsousrubrique = droitrolesForm.getIdsousrubrique();
        SousRubrique sousrubrique= sousrubriqueRepository.findById(idsousrubrique).orElse(null);
    	Optional<DroitRoles> droitroleinfo = droitrolesRepository.findDroitRolesByDroitAndSousrubriqueAndRole(droit, sousrubrique, role);
    	 if (droitroleinfo.isPresent()) {
    		 DroitRoles droitrole = droitroleinfo.get();
		droitrolesRepository.delete(droitrole);}
		return ResponseEntity.ok().body("Droit Roles deleted successfully!");
	}
			
    @PreAuthorize("@authorizationRole.can('Admin')")  
    @DeleteMapping("/droitroles/delete")
	  public ResponseEntity<String> deleteAllDroitRoles() {
	    System.out.println("Delete All Droit Roles...");
	 
	    droitrolesRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Droit Roles have been deleted!", HttpStatus.OK);
	  }
}

