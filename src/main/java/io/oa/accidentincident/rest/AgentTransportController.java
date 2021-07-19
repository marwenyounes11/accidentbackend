/*package io.oa.accidentincident.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AgentTransportForm;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentTransport;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.repository.AgentRepository;
import io.oa.accidentincident.repository.AgentTransportRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;

@CrossOrigin
@RestController

public class AgentTransportController {
	@Autowired 
	ServletContext context;
	@Autowired 	
	AgentTransportRepository  repository;
	@Autowired 	
	AgentRepository  arepository;
	@Autowired 	
	MoyenTransportRepository  trepository;

	 @GetMapping("/agenttransports/{id}")
		public ResponseEntity<AgentTransport> getAgentTransportById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
		 AgentTransport agenttransport = repository.findById(Id)
					.orElseThrow(() -> new ResourceNotFoundException("Agent transport not found for this id :: " + Id));
			return ResponseEntity.ok().body(agenttransport);
		}
	 
	
	
	@GetMapping("/agenttransports")
	  public List<AgentTransport> getAllAgentTransports() {
	     System.out.println("Get all AgentTransports...");
	 
	    List<AgentTransport> agenttransports = new ArrayList<>();
	    repository.findAll().forEach(agenttransports::add);
	 
	    return agenttransports;
	  }
 
	 
	@PostMapping("/saveagenttransports")
	public AgentTransport createAgentTransport(@Valid @RequestBody AgentTransportForm agenttransportForm) {
		Long idagent =agenttransportForm.getIdagent();
		Long idtransport =agenttransportForm.getIdtransport();
		Agent agt = arepository.findById(idagent).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
		AgentTransport agenttransport = new AgentTransport();
		agenttransport.setAgent(agt);
		agenttransport.setMoyentransport(transport);
		return repository.save(agenttransport);
	}
	

	@DeleteMapping("/deleteagenttransports/{id}")
	public Map<String, Boolean> deleteAgentTransport(@PathVariable(value = "id") Long agenttransportId)
			throws ResourceNotFoundException {
		AgentTransport agenttransport = repository.findById(agenttransportId)
				.orElseThrow(() -> new ResourceNotFoundException("agent Transport not found  id :: " + agenttransportId));

		repository.delete(agenttransport);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/agenttransports/delete")
	  public ResponseEntity<String> deleteAllAgentTransports() {
	    System.out.println("Delete All agent Transports...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All agent Transports have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/editagenttransports/{id}")
	  public ResponseEntity<AgentTransport> updateAgentTransport(@PathVariable("id") long id, @RequestBody AgentTransportForm agenttransportForm) {
	    System.out.println("Update Agent Transport with ID = " + id + "...");
	    Long idagent =agenttransportForm.getIdagent();
	    Long idtransport =agenttransportForm.getIdtransport();
		Agent agt = arepository.findById(idagent).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
	    Optional<AgentTransport> agentTransportInfo = repository.findById(id);
	 
	    if (agentTransportInfo.isPresent()) {
	    	AgentTransport agenttransports = agentTransportInfo.get();
	        
	    	agenttransports.setAgent(agt);
	    	agenttransports.setMoyentransport(transport);   
	      return new ResponseEntity<>(repository.save(agenttransports), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}*/
