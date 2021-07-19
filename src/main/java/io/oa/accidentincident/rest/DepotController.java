package io.oa.accidentincident.rest;

import java.text.ParseException;
import java.util.ArrayList;
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
import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.repository.DepotRepository;

@RestController
@CrossOrigin(origins = "*")
public class DepotController {
	@Autowired
	DepotRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepot')")
	@GetMapping("/depotspaginationmc")
	  public List<Depot> getAllDepotsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Depot> depots = new ArrayList<>();
	    repository.chercherDepot(mc,PageRequest.of(page,size)).forEach(depots::add);
	 
	    return depots;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepot')")
	@GetMapping("/depotspagination")
	  public List<Depot> getAllDepotsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Depot> depots = new ArrayList<>();
	    repository.pageDepot(PageRequest.of(page,size)).forEach(depots::add);
	    return depots;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepot')")
	 @GetMapping("/listdepots")
	  public List<Depot> getAllDepots() {
	    System.out.println("Get all Depots...");
	 
	    List<Depot> depots = new ArrayList<>();
	    repository.findAll().forEach(depots::add);
	 
	    return depots;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepot')")
	@GetMapping("/depots/{id}")
	public ResponseEntity<Depot> getDepotById(@PathVariable(value = "id") Long depotId)
			throws ResourceNotFoundException {
		Depot depot = repository.findById(depotId)
				.orElseThrow(() -> new ResourceNotFoundException("Depot not found for this id :: " + depotId));
		return ResponseEntity.ok().body(depot);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestiondepot')")
	@PostMapping("/depots")
	public Depot createDepot(@Valid @RequestBody Depot depot) {
		return repository.save(depot);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondepot')")
	@DeleteMapping("/depots/{id}")
	public Map<String, Boolean> deleteDepot(@PathVariable(value = "id") Long depotId)
			throws ResourceNotFoundException {
		Depot depot = repository.findById(depotId)
				.orElseThrow(() -> new ResourceNotFoundException("Depot not found  id :: " + depotId));

		repository.delete(depot);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondepot')")
	  @DeleteMapping("/depots/delete")
	  public ResponseEntity<String> deleteAllDepots() {
	    System.out.println("Delete All Depots...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Depots have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondepot')")
	  @PutMapping("/depots/{id}")
	  public ResponseEntity<Depot> updateDepot(@PathVariable("id") long id, @RequestBody Depot depot) {
	    System.out.println("Update Depot with ID = " + id + "...");
	 
	    Optional<Depot> depotInfo = repository.findById(id);
	 
	    if (depotInfo.isPresent()) {
	    	Depot depott = depotInfo.get();
	          
	           depott.setNameDepot(depot.getNameDepot());
	           
	          
	      return new ResponseEntity<>(repository.save(depott), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
