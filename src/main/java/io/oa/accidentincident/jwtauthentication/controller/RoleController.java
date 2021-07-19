
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
import io.oa.accidentincident.jwtauthentication.message.request.RoleForm;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class RoleController {

    

   

    @Autowired
    RoleRepository roleRepository;

   
    

   
  //  @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/roles")
	  public List<Role> getAllRoles() {
	    System.out.println("Get all Roles...");
	 
	    List<Role> roles = new ArrayList<>();
	   roleRepository.findAll().forEach(roles::add);
	 
	    return roles;
	  }
  
    @PreAuthorize("@authorizationRole.can('Admin')")
	public ResponseEntity<Role> getRoleByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		Role role = roleRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this name :: " + name));
		return ResponseEntity.ok().body(role);
	}
    
   // @PreAuthorize("@authorizationRole.can('Admin')")
    @GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId)
			throws ResourceNotFoundException {
    	Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
		return ResponseEntity.ok().body(role);
	}
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PostMapping("/roles")
    public ResponseEntity<String> registerRole(@Valid @RequestBody RoleForm roleForm) {
        
        Role role = new Role ();
       role.setName(roleForm.getName());
        roleRepository.save(role);
        return ResponseEntity.ok().body("Role registered successfully!");
    }
    
   
    
    @PreAuthorize("@authorizationRole.can('Admin')")
    @PutMapping("/roles/{roleId}")
    public ResponseEntity<String> updateRole(@PathVariable(value = "roleId") Long roleId,@Valid @RequestBody RoleForm roleForm) {
    	Optional<Role> roleInfo = roleRepository.findById(roleId);
    	 if (roleInfo.isPresent()) {
    		 Role role = roleInfo.get();
    		 role.setName(roleForm.getName());
    		 roleRepository.save(role);
    		 return ResponseEntity.ok().body("Role update successfully!");
    	 }else {
     	      return new ResponseEntity<String>("Role not update ", HttpStatus.NOT_FOUND);
   	    }
    	
    }
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/roles/{roleId}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "roleId") Long roleId)
			throws ResourceNotFoundException {
    	Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found  id :: " + roleId));

		roleRepository.delete(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
    @PreAuthorize("@authorizationRole.can('Admin')")
    @DeleteMapping("/roles/delete")
	  public ResponseEntity<String> deleteAllRoles() {
	    System.out.println("Delete All Roles...");
	 
	    roleRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Roles have been deleted!", HttpStatus.OK);
	  }
}

