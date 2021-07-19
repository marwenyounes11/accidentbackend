package io.oa.accidentincident.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentIntervention;
import io.oa.accidentincident.repository.AgentInterventionRepository;
import io.oa.accidentincident.repository.AgentRepository;
import io.oa.accidentincident.response.AccidentInformResponse;

@CrossOrigin
@RestController
public class AgentInterventionController {
	@Autowired
	AgentInterventionRepository repository;
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagentintervention')")
	@GetMapping("/agentinterventionspaginationmc")
	  public List<AgentIntervention> getAllAgentInterventionsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<AgentIntervention> agentinterventions = new ArrayList<>();
	    repository.chercherAgentIntervention(mc,PageRequest.of(page,size)).forEach(agentinterventions::add);
	 
	    return agentinterventions;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagentintervention')")
	@GetMapping("/agentinterventionspagination")
	  public List<AgentIntervention> getAllAgentInterventionsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<AgentIntervention> agentinterventions = new ArrayList<>();
	    repository.pageAgentIntervention(PageRequest.of(page,size)).forEach(agentinterventions::add);
	    return agentinterventions;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagentintervention')")
	 @GetMapping("/listagentinterventions")
	  public List<AgentIntervention> getAllAgentInterventions() {
	    System.out.println("Get all Agent interventions...");
	 
	    List<AgentIntervention> agentinterventions = new ArrayList<>();
	    repository.findAll().forEach(agentinterventions::add);
	 
	    return agentinterventions;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagentintervention')")
	@GetMapping("/agentinterventions/{id}")
	public ResponseEntity<AgentIntervention> getAgentInterventionById(@PathVariable(value = "id") Long agentId)
			throws ResourceNotFoundException {
		AgentIntervention agentintervention = repository.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent Intervention not found for this id :: " + agentId));
		return ResponseEntity.ok().body(agentintervention);
	}
	 @PreAuthorize("@authorizationSE.can('create', 'gestionagentintervention')")
	@PostMapping("/agentinterventions" )
	public AgentIntervention createAgentIntervention(@Valid @RequestBody AgentIntervention agentintervention) {
		return repository.save(agentintervention);
	}
	
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionagentintervention')")
	@DeleteMapping("/agentinterventions/{id}")
	public Map<String, Boolean> deleteAgentIntervention(@PathVariable(value = "id") Long agentId)
			throws ResourceNotFoundException {
		AgentIntervention agentintervention = repository.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent Intervention not found  id :: " + agentId));

		repository.delete(agentintervention);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionagentintervention')")
	  @DeleteMapping("/agentinterventions/delete")
	  public ResponseEntity<String> deleteAllAgentInterventions() {
	    System.out.println("Delete All Agents Intervention...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Agents Intervention have been deleted!", HttpStatus.OK);
	  }
	 
	
	 @PreAuthorize("@authorizationSE.can('update', 'gestionagentintervention')")
	  @PutMapping("/agentinterventions/{id}")
	  public ResponseEntity<AgentIntervention> updateAgentIntervention(@PathVariable("id") long id, @RequestBody AgentIntervention agentintervention) {
	    System.out.println("Update Agent Intervention with ID = " + id + "...");
	 
	    Optional<AgentIntervention> agentinterventionInfo = repository.findById(id);
	 
	    if (agentinterventionInfo.isPresent()) {
	    	AgentIntervention agenttintervention = agentinterventionInfo.get();
	          
	           agenttintervention.setName(agentintervention.getName());
	           agenttintervention.setLastName(agentintervention.getLastName());
	           agenttintervention.setEmail(agentintervention.getEmail());
	           agenttintervention.setMatricule(agentintervention.getMatricule());
	           agenttintervention.setPhone(agentintervention.getPhone());
	          
	      return new ResponseEntity<>(repository.save(agenttintervention), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}



