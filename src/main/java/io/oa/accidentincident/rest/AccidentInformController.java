package io.oa.accidentincident.rest;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AccidentInformForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.form.RapportAccidentForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceInform;

import io.oa.accidentincident.repository.AccidentInformRepository;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.SourceInformRepository;
import io.oa.accidentincident.response.AccidentInformResponse;




@RestController
@CrossOrigin
public class AccidentInformController {
	
	@Autowired 	
	AccidentInformRepository  repository;
	@Autowired 	
	AccidentRepository  arepository;
	@Autowired 
	SourceInformRepository  srepository;
	
	/* @GetMapping("/accidentinforms/{id}")
		public ResponseEntity<AccidentInform> getAccidentInfoById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
		 AccidentInform accidentinform = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Accident info not found for this id :: " + Id));
			return ResponseEntity.ok().body(accidentinform);
		}
	 */
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionaccidentinform')")
	@GetMapping("/listaccidentinforms")
	  public List<AccidentInform> getAllAccidentInforms() {
	    List<AccidentInform> accidentinfos = new ArrayList<>();
	    repository.findAll().forEach(accidentinfos::add);
	    return accidentinfos;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionaccidentinform')")
	@GetMapping("/accidentinformspaginationmc")
	  public List<AccidentInformResponse> getAllAccidentInformsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = df1.parse(mc);
	    List<AccidentInformResponse> accidentinfos = new ArrayList<>();
	    repository.chercherAccidentInform(d,PageRequest.of(page,size)).forEach(accidentinfos::add);
	 
	    return accidentinfos;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionaccidentinform')")
	@GetMapping("/accidentinformspagination")
	  public List<AccidentInformResponse> getAllAccidentInformsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<AccidentInformResponse> accidentinfos = new ArrayList<>();
	    repository.pageAccidentInform(PageRequest.of(page,size)).forEach(accidentinfos::add);
	    return accidentinfos;
	  }
	
	
	@GetMapping("/rapportaccidents/{dateInformation}")
	  public List<RapportAccidentForm> getInformationAccident(@PathVariable String dateInformation) throws ParseException{
	     System.out.println("Get Informations Accidents  ...");
	 
	    List<RapportAccidentForm> informations = new ArrayList<>(); 
	   RapportAccidentForm information = new RapportAccidentForm(); 
	            
	   DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");        
	    repository.findByDateInformation(df1.parse(dateInformation)).forEach(ai->{
	    	Set<Securite> securites =new HashSet<Securite>();
            Set<AgentTranstu> agenttranstus =new HashSet<AgentTranstu>();
            Set<Ambulancier> ambulanciers =new HashSet<Ambulancier>();
	            SourceInform s=	ai.getSourceinform();
	           agenttranstus= s.getAgenttranstus();
	            securites=s.getSecurites();
	           ambulanciers = s.getAmbulanciers();
	           information.setSecurites(securites);
	           information.setAgenttranstus(agenttranstus);
	           information.setAmbulanciers(ambulanciers);
	           informations.add(information);
	    
	      
	    });
	   
	 
	    return informations;
	  }
	
	
	/*@GetMapping("/rapportaccidentcollisionroutes/{dateInformation}")
	  public List<RapportAccidentCollisionRouteForm> getInformationAccidentCollisionRoute(@PathVariable String dateInformation) throws ParseException{
	     System.out.println("Get Informations AccidentCollision and AccidentRoutes  ...");
	 
	    List<RapportAccidentCollisionRouteForm> rapportaccidentcollisionroutes = new ArrayList<>(); 
	   RapportAccidentCollisionRouteForm rapportaccidentcollisionroute = new RapportAccidentCollisionRouteForm(); 
	            
	   DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");        
	    repository.findByDateInformation(df1.parse(dateInformation)).forEach(ai->{
	    	Set<Securite> securites =new HashSet<Securite>();
          Set<AgentTranstu> agenttranstus =new HashSet<AgentTranstu>();
          Set<Ambulancier> ambulanciers =new HashSet<Ambulancier>();
	            SourceInform s=	ai.getSourceinform();
	           agenttranstus= s.getAgenttranstus();
	            securites=s.getSecurites();
	           ambulanciers = s.getAmbulanciers();
	           information.setSecurites(securites);
	           information.setAgenttranstus(agenttranstus);
	           information.setAmbulanciers(ambulanciers);
	           informations.add(information);
	    
	      
	    });
	   
	 
	    return informations;
	  }
	*/
	@PreAuthorize("@authorizationSE.can('create', 'gestionaccidentinform')")
	@PostMapping("/saveaccidentinforms")
	public AccidentInform createAccidentInfo(@Valid @RequestBody AccidentInformForm accidentinformForm) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
		Long idaccident =accidentinformForm.getIdaccident();
		Long idsourceinform =accidentinformForm.getIdsourceinform();
		Accident acc = arepository.findById(idaccident).orElse(null);
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
		AccidentInform accidentinform = new AccidentInform();
		accidentinform.setAccident(acc);
		accidentinform.setSourceinform(sourceinform);
		accidentinform.setDateInformation(df1.parse(accidentinformForm.getDateInformation()));
		accidentinform.setHeureInformation(df2.parse(accidentinformForm.getHeureInformation()));
		accidentinform.setDescription(accidentinformForm.getDescription());
		return repository.save(accidentinform);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionaccidentinform')")
	@DeleteMapping("/deleteaccidentinforms/{id}")
	public Map<String, Boolean> deleteAccidentInform(@PathVariable(value = "id") Long accidentinformId)
			throws ResourceNotFoundException {
		AccidentInform accidentinform = repository.findById(accidentinformId)
				.orElseThrow(() -> new ResourceNotFoundException("accidentinform not found  id :: " + accidentinformId));

		repository.delete(accidentinform);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionaccidentinform')")
	  @DeleteMapping("/accidentinforms/delete")
	  public ResponseEntity<String> deleteAllAccidentInforms() {
	    System.out.println("Delete All accidentinforms...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All accident informs have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionaccidentinform')")
	  @PutMapping("/editaccidentinforms/{id}")
	  public ResponseEntity<AccidentInform> updateAccidentInform(@PathVariable("id") long id, @RequestBody AccidentInformForm accidentinformForm) throws ParseException{
	    System.out.println("Update AccidentInform with ID = " + id + "...");
	    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
	    Long idaccident =accidentinformForm.getIdaccident();
	    Long idsourceinform =accidentinformForm.getIdsourceinform();
		Accident acc = arepository.findById(idaccident).orElse(null);
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
	    Optional<AccidentInform> accidentInformInfo = repository.findById(id);
	 
	    if (accidentInformInfo.isPresent()) {
	    	AccidentInform accidentinforms = accidentInformInfo.get();
	        
	    	accidentinforms.setAccident(acc);
			accidentinforms.setSourceinform(sourceinform);   
			accidentinforms.setDateInformation(df1.parse(accidentinformForm.getDateInformation()));
			accidentinforms.setHeureInformation(df2.parse(accidentinformForm.getHeureInformation()));
			accidentinforms.setDescription(accidentinformForm.getDescription());
	      return new ResponseEntity<>(repository.save(accidentinforms), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
}
