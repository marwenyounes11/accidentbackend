package io.oa.accidentincident.rest;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AccidentTransportForm;
import io.oa.accidentincident.form.AgentTranstuForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.AgentTranstuRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.repository.SourceInformRepository;





@CrossOrigin
@RestController

public class AgentTranstuController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	AgentTranstuRepository  repository;
	@Autowired 
	SourceInformRepository  srepository;
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionagenttranstu')")
	@GetMapping("/agenttranstuspaginationmc")
	  public List<AgentTranstu> getAllAgentTranstusPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<AgentTranstu> agenttranstus = new ArrayList<>();
	    repository.chercherAgentTranstu(mc,PageRequest.of(page,size)).forEach(agenttranstus::add);
	 
	    return agenttranstus;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionagenttranstu')")
	@GetMapping("/agenttranstuspagination")
	  public List<AgentTranstu> getAllAgentTranstusPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<AgentTranstu> agenttranstus = new ArrayList<>();
	    repository.pageAgentTranstu(PageRequest.of(page,size)).forEach(agenttranstus::add);
	    return agenttranstus;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionagenttranstu')")
	 @GetMapping("/listagenttranstus")
	  public List<AgentTranstu> getAllAgentTranstus() {
	    System.out.println("Get all AgentTranstus...");
	 
	    List<AgentTranstu> agenttranstus = new ArrayList<>();
	    repository.findAll().forEach(agenttranstus::add);
	 
	    return agenttranstus;
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestionagenttranstu')")
	@PostMapping("/addagenttranstus")
	public AgentTranstu createAgentTranstu(@Valid @RequestBody AgentTranstuForm agenttranstuForm) {
		Long idsourceinform =agenttranstuForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
		AgentTranstu agenttranstu = new AgentTranstu();
		agenttranstu.setName(agenttranstuForm.getName());
		agenttranstu.setLastName(agenttranstuForm.getLastName());
		agenttranstu.setMatricule(agenttranstuForm.getMatricule());
		agenttranstu.setFunction(agenttranstuForm.getFunction());
		agenttranstu.setPhone(agenttranstuForm.getPhone());
		agenttranstu.setSourceinform(sourceinform);
		
		return repository.save(agenttranstu);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionagenttranstu')")
	@DeleteMapping("/deleteagenttranstus/{id}")
	public void deleteAgentTranstu(@PathVariable(value = "id") Long agenttranstuId)
			throws ResourceNotFoundException {
		 repository.deleteAgentTranstu(agenttranstuId);
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionagenttranstu')")
	  @DeleteMapping("/agenttranstus/delete")
	  public ResponseEntity<String> deleteAllAgentTranstus() {
	    System.out.println("Delete All Agent Transtus...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Agent Transtus have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionagenttranstu')")
	  @PutMapping("/editagenttranstus/{id}")
	  public ResponseEntity<AgentTranstu> updateAgentTranstu(@PathVariable("id") long id, @RequestBody AgentTranstuForm agenttranstuForm) {
	    System.out.println("Update Agent Transtu with ID = " + id + "...");
	    Long idsourceinform =agenttranstuForm.getIdsourceinform();
		SourceInform sourceinform = srepository.findById(idsourceinform).orElse(null);
	    Optional<AgentTranstu> agenttranstuInfo = repository.findById(id);
	 
	    if (agenttranstuInfo.isPresent()) {
	    	AgentTranstu agenttranstu = agenttranstuInfo.get();
	    	agenttranstu.setName(agenttranstuForm.getName());
			agenttranstu.setLastName(agenttranstuForm.getLastName());
			agenttranstu.setMatricule(agenttranstuForm.getMatricule());
			agenttranstu.setFunction(agenttranstuForm.getFunction());
			agenttranstu.setPhone(agenttranstuForm.getPhone());
			agenttranstu.setSourceinform(sourceinform);  
	      return new ResponseEntity<>(repository.save(agenttranstu), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
