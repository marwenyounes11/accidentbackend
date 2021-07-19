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
import io.oa.accidentincident.form.InterventionForm;
import io.oa.accidentincident.form.RapportAccidentForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentIntervention;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.Intervention;
import io.oa.accidentincident.entity.Materiel;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceInform;

import io.oa.accidentincident.repository.AccidentInformRepository;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AgentInterventionRepository;
import io.oa.accidentincident.repository.InterventionRepository;
import io.oa.accidentincident.repository.MaterielRepository;
import io.oa.accidentincident.repository.SourceInformRepository;
import io.oa.accidentincident.response.AccidentInformResponse;




@RestController
@CrossOrigin
public class InterventionController {
	
	@Autowired 	
	InterventionRepository  repository;
	@Autowired 	
	AgentInterventionRepository  airepository;
	@Autowired 
	MaterielRepository  mrepository;
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionintervention')")
	@GetMapping("/interventionpaginationmc")
	  public List<Intervention> getAllInterventionsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = df1.parse(mc);
	    List<Intervention> interventions = new ArrayList<>();
	    repository.chercherIntervention(d,PageRequest.of(page,size)).forEach(interventions::add);
	    return interventions;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionintervention')")
	@GetMapping("/interventionpagination")
	  public List<Intervention> getAllInterventionsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Intervention> interventions = new ArrayList<>();
	    repository.pageIntervention(PageRequest.of(page,size)).forEach(interventions::add);
	    return interventions;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionintervention')")
	@GetMapping("/listinterventions")
	  public List<Intervention> getAllInterventions() {
	    System.out.println("Get all interventions...");
	    List<Intervention> interventions = new ArrayList<>();
	    repository.findAll().forEach(interventions::add);
	    return interventions;
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestionintervention')")
	@PostMapping("/saveinterventions")
	public Intervention createIntervention(@Valid @RequestBody InterventionForm interventionForm) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
		Long idagentintervention =interventionForm.getIdagentintervention();
		Long idmateriel =interventionForm.getIdmateriel();
		AgentIntervention agentintervention = airepository.findById(idagentintervention).orElse(null);
		Materiel materiel = mrepository.findById(idmateriel).orElse(null);
		Intervention intervention = new Intervention();
		intervention.setAgentintervention(agentintervention);
		intervention.setMateriel(materiel);
		intervention.setDateIntervention(df1.parse(interventionForm.getDateIntervention()));
		intervention.setHeureIntervention(df2.parse(interventionForm.getHeureIntervention()));
		intervention.setAction(interventionForm.getAction());
		return repository.save(intervention);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionintervention')")
	@DeleteMapping("/deleteinterventions/{id}")
	public Map<String, Boolean> deleteInterventions(@PathVariable(value = "id") Long interventionId)
			throws ResourceNotFoundException {
		Intervention intervention = repository.findById(interventionId)
				.orElseThrow(() -> new ResourceNotFoundException("intervention not found  id :: " + interventionId));

		repository.delete(intervention);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionintervention')")
	  @DeleteMapping("/interventions/delete")
	  public ResponseEntity<String> deleteAllIntervention() {
	    System.out.println("Delete All interventions...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All interventions have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionintervention')")
	  @PutMapping("/editinterventions/{id}")
	  public ResponseEntity<Intervention> updateIntervention(@PathVariable("id") long id, @RequestBody InterventionForm interventionForm) throws ParseException{
	    System.out.println("Update intervention with ID = " + id + "...");
	    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
		  Long idagentintervention =interventionForm.getIdagentintervention();
			Long idmateriel =interventionForm.getIdmateriel();
			AgentIntervention agentintervention = airepository.findById(idagentintervention).orElse(null);
			Materiel materiel = mrepository.findById(idmateriel).orElse(null);
	    Optional<Intervention> interventionInfo = repository.findById(id);
	 
	    if (interventionInfo.isPresent()) {
	    	Intervention intervention = interventionInfo.get();
	        
	    	intervention.setAgentintervention(agentintervention);
			intervention.setMateriel(materiel);
			intervention.setDateIntervention(df1.parse(interventionForm.getDateIntervention()));
			intervention.setHeureIntervention(df2.parse(interventionForm.getHeureIntervention()));
			intervention.setAction(interventionForm.getAction());
	      return new ResponseEntity<>(repository.save(intervention), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
}
