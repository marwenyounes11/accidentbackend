package io.oa.accidentincident.rest;


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
import io.oa.accidentincident.form.DegatTransportForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.DegatTransport;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AccidentTransportRepository;
import io.oa.accidentincident.repository.DegatRepository;
import io.oa.accidentincident.repository.DegatTransportRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.response.DegatTransportResponse;





@CrossOrigin(origins = "*")
@RestController

public class DegatTransportController {
	@Autowired  
	ServletContext context;
	@Autowired 	
	DegatTransportRepository  repository;
	@Autowired 
	DegatRepository  drepository;
	@Autowired 	
	MoyenTransportRepository  trepository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegattransport')")
	@GetMapping("/degattransportspagination")
	  public List<DegatTransportResponse> getAllDegatTransportsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<DegatTransportResponse> degattransports = new ArrayList<>();
	    repository.pageDegatTransport(PageRequest.of(page,size)).forEach(degattransports::add);
	    return degattransports;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegattransport')")
	 @GetMapping("/degattransport/{id}")
		public ResponseEntity<DegatTransportResponse> getDegatTransportById(@PathVariable(value = "id") Long Id)
				throws ResourceNotFoundException {
		 DegatTransportResponse degattransport = repository.degatTransport(Id);
			return ResponseEntity.ok().body(degattransport);
		}
	 
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegattransport')")
	@GetMapping("/degattransports")
	  public List<DegatTransportResponse> getAllDegatTransports() {
	    return repository.listDegatTransport();
	  }
	
	
	@PreAuthorize("@authorizationSE.can('create', 'gestiondegattransport')")
	@PostMapping("/adddegattransports")
	public DegatTransport createDegatTransport(@Valid @RequestBody DegatTransportForm degattransportForm) {
		Long iddegat =degattransportForm.getIddegat();
		Long idtransport =degattransportForm.getIdtransport();
		Degat dg = drepository.findById(iddegat).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
		DegatTransport degattransport = new DegatTransport();
		degattransport.setDegat(dg);
		degattransport.setMoyentransport(transport);
		return repository.save(degattransport);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegattransport')")
	@DeleteMapping("/deletedegattransports/{id}")
	public Map<String, Boolean> deleteDegatTransport(@PathVariable(value = "id") Long degattransportId)
			throws ResourceNotFoundException {
		DegatTransport degattransport = repository.findById(degattransportId)
				.orElseThrow(() -> new ResourceNotFoundException("degat Transport not found  id :: " + degattransportId));

		repository.delete(degattransport);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegattransport')")
	  @DeleteMapping("/degattransports/delete")
	  public ResponseEntity<String> deleteAllDegatTransports() {
	    System.out.println("Delete All degat Transports...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All degat Transports have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondegattransport')")
	  @PutMapping("/editdegattransports/{id}")
	  public ResponseEntity<DegatTransport> updateDegatTransport(@PathVariable("id") long id, @RequestBody DegatTransportForm degattransportForm) {
	    System.out.println("Update Degat Transport with ID = " + id + "...");
	    Long iddegat =degattransportForm.getIddegat();
	    Long idtransport =degattransportForm.getIdtransport();
		Degat degat = drepository.findById(iddegat).orElse(null);
		MoyenTransport transport = trepository.findById(idtransport).orElse(null);
	    Optional<DegatTransport> degatTransportInfo = repository.findById(id);
	 
	    if (degatTransportInfo.isPresent()) {
	    	DegatTransport degattransports = degatTransportInfo.get();
	        
	    	degattransports.setDegat(degat);
	    	degattransports.setMoyentransport(transport);   
	      return new ResponseEntity<>(repository.save(degattransports), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
