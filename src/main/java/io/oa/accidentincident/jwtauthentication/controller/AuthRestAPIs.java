package io.oa.accidentincident.jwtauthentication.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.oa.accidentincident.entity.*;
import io.oa.accidentincident.repository.*;
import io.oa.accidentincident.jwtauthentication.message.request.LoginForm;
import io.oa.accidentincident.jwtauthentication.message.request.SignUpForm;
import io.oa.accidentincident.jwtauthentication.message.response.JwtResponse;
import io.oa.accidentincident.jwtauthentication.security.jwt.JwtProvider;
import io.oa.accidentincident.jwtauthentication.security.services.AuthorizationSE;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder encoder;

	    @Autowired
	    JwtProvider jwtProvider;

	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
	 
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
	 
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	        String jwt = jwtProvider.generateJwtToken(authentication);
	        return ResponseEntity.ok(new JwtResponse(jwt));
	    }
	    
	   // @PreAuthorize("@authorizationSE.can('afficher', 'ADMINISTRATION')")
	    @GetMapping("/users")
		  public List<User> getAllUsers() {
		    System.out.println("Get all Users...");
		 
		    List<User> users = new ArrayList<>();
		   userRepository.findAll().forEach(users::add);
		 
		    return users;
		  }
	    
	
	    @GetMapping("/users/username/{username}")
		public ResponseEntity<User> getUtilisateurByUsername(@PathVariable(value = "username") String username)
				throws ResourceNotFoundException {
			User Utilisateur = userRepository.findByUsername(username)
					.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this username :: " + username));
			return ResponseEntity.ok().body(Utilisateur);
		}
	    
	 //   @PreAuthorize("@authorizationSE.can('afficher', 'ADMINISTRATION')")
	    @GetMapping("/users/{id}")
		public ResponseEntity<User> getUtilisateurById(@PathVariable(value = "id") Long UtilisateurId)
				throws ResourceNotFoundException {
			User Utilisateur = userRepository.findById(UtilisateurId)
					.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id :: " + UtilisateurId));
			return ResponseEntity.ok().body(Utilisateur);
		}
	    
	    @PostMapping("/signup")
	    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	    	String email;
	    	 String password;
	    	if(userRepository.existsByUsername(signUpRequest.getUsername())) {
	            return new ResponseEntity<String>("Fail -> Username is already taken!",
	                    HttpStatus.BAD_REQUEST);
	        }
	 
	        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity<String>("Fail -> Email is already in use!",
	                    HttpStatus.BAD_REQUEST);
	        } 
	email= signUpRequest.getEmail();       
	 password= signUpRequest.getPassword();
	        // Creating user's account
	        if((email.equals(signUpRequest.getConfirmEmail())) && (password.equals(signUpRequest.getConfirmPassword())) ) {
	        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
	                email, encoder.encode(password));
	 
	        Long idRole = signUpRequest.getIdrole();
	        Role role= roleRepository.findById(idRole).orElse(null);
	       user.setRole(role);
	        userRepository.save(user);
	 
	       
	        }else {
	        	 return new ResponseEntity<String>("Fail ", HttpStatus.FORBIDDEN);
	        }
	        return ResponseEntity.ok().body("User registered successfully!");
	    }
	
	   // @PreAuthorize("@authorizationSE.can('update', 'ADMINISTRATION')")
	    @PutMapping("/users/{userId}")
	    public ResponseEntity<String> updateAllChampUser(@PathVariable(value = "userId") Long userId,@Valid @RequestBody SignUpForm signUpRequest) {
	    	Optional<User> userInfo = userRepository.findById(userId);
	    	String password =signUpRequest.getPassword();
	    	 if (userInfo.isPresent()) {
	    		 User user = userInfo.get();
	    		 user.setName(signUpRequest.getName());
	    		 user.setEmail(signUpRequest.getEmail()); 
	    		 user.setUsername(signUpRequest.getUsername());
	    		 String passwordencd=user.getPassword();
	    		 String passwordencdform=encoder.encode(password);
	    		 if(!encoder.matches(passwordencdform, passwordencd)) {
	    			 user.setPassword(passwordencdform); 
	    		 }else {
	    			 System.out.println(true); 
	    		 }
	    		 Long idRole = signUpRequest.getIdrole();
	 	        Role role= roleRepository.findById(idRole).orElse(null);
	 	       user.setRole(role);
	    		 userRepository.save(user);
	    	return ResponseEntity.ok().body("User update successfully!");
	    	 }else {
	  	      return new ResponseEntity<String>("User not update ", HttpStatus.NOT_FOUND);
	  	    }
	    }
	    
	  
	   // @PreAuthorize("@authorizationSE.can('delete', 'ADMINISTRATION')")
	    @DeleteMapping("/users/{userId}")
		public Map<String, Boolean> deleteUser(@PathVariable(value = "userId") Long userId)
				throws ResourceNotFoundException {
	    	User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found  id :: " + userId));

			userRepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
	  //  @PreAuthorize("@authorizationSE.can('delete', 'ADMINISTRATION')")
	    @DeleteMapping("/users/delete")
		  public ResponseEntity<String> deleteAllUsers() {
		    System.out.println("Delete All Users...");
		 
		    userRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Users have been deleted!", HttpStatus.OK);
		  }
}
