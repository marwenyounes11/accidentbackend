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
import io.oa.accidentincident.repository.AgentRepository;
import io.oa.accidentincident.response.AccidentInformResponse;

@CrossOrigin
@RestController
public class AgentController {
	@Autowired
	AgentRepository repository;
	
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagent')")
	@GetMapping("/agentspaginationmc")
	  public List<Agent> getAllAgentsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Agent> agents = new ArrayList<>();
	    repository.chercherAgent(mc,PageRequest.of(page,size)).forEach(agents::add);
	 
	    return agents;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagent')")
	@GetMapping("/agentspagination")
	  public List<Agent> getAllAgentsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Agent> agents = new ArrayList<>();
	    repository.pageAgent(PageRequest.of(page,size)).forEach(agents::add);
	    return agents;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagent')")
	 @GetMapping("/listagents")
	  public List<Agent> getAllAgents() {
	    System.out.println("Get all Agents...");
	 
	    List<Agent> agents = new ArrayList<>();
	    repository.findAll().forEach(agents::add);
	 
	    return agents;
	  }
	 @PreAuthorize("@authorizationSE.can('afficher', 'gestionagent')")
	@GetMapping("/agents/{id}")
	public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") Long agentId)
			throws ResourceNotFoundException {
		Agent agent = repository.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));
		return ResponseEntity.ok().body(agent);
	}
	 @PreAuthorize("@authorizationSE.can('create', 'gestionagent')")
	@PostMapping("/agents" )
	public Agent createAgent(@Valid @RequestBody Agent agent) {
		return repository.save(agent);
	}
	
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionagent')")
	@DeleteMapping("/agents/{id}")
	public Map<String, Boolean> deleteAgent(@PathVariable(value = "id") Long agentId)
			throws ResourceNotFoundException {
		Agent agent = repository.findById(agentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found  id :: " + agentId));

		repository.delete(agent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 @PreAuthorize("@authorizationSE.can('delete', 'gestionagent')")
	  @DeleteMapping("/agents/delete")
	  public ResponseEntity<String> deleteAllAgents() {
	    System.out.println("Delete All Agents...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Agents have been deleted!", HttpStatus.OK);
	  }
	 
	
	 @PreAuthorize("@authorizationSE.can('update', 'gestionagent')")
	  @PutMapping("/agents/{id}")
	  public ResponseEntity<Agent> updateAgent(@PathVariable("id") long id, @RequestBody Agent agent) {
	    System.out.println("Update Agent with ID = " + id + "...");
	 
	    Optional<Agent> agentInfo = repository.findById(id);
	 
	    if (agentInfo.isPresent()) {
	    	Agent agentt = agentInfo.get();
	          
	           agentt.setName(agent.getName());
	           agentt.setLastName(agent.getLastName());
	           agentt.setAddress(agent.getAddress());
	           agentt.setEmail(agent.getEmail());
	           agentt.setPhone(agent.getPhone());
	           agentt.setMatricule(agent.getMatricule());
	           agentt.setType(agent.getType());
	          
	      return new ResponseEntity<>(repository.save(agentt), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
