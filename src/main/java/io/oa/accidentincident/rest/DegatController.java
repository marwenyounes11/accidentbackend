package io.oa.accidentincident.rest;

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
import io.oa.accidentincident.form.DegatMaterielForm;
import io.oa.accidentincident.form.DegatPhysiqueForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DegatRepository;
import io.oa.accidentincident.response.DegatMaterielResponse;
import io.oa.accidentincident.response.DegatPhysiqueResponse;
import io.oa.accidentincident.response.DegatResponse;


@CrossOrigin
@RestController

public class DegatController {
	@Autowired
	DegatRepository repository;
	
	@Autowired
	AccidentRepository arepository;
	 
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	 @GetMapping("/listdegats")
	  public List<DegatResponse> getAllDegats() {

	    return repository.listDegat();
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	 @GetMapping("/degatmaterielspagination")
	  public List<DegatMaterielResponse> getAllDegatMaterielPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<DegatMaterielResponse> degatmateriels = new ArrayList<>();
	    repository.pageDegatMateriel(PageRequest.of(page,size)).forEach(degatmateriels::add);
	    return degatmateriels;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	 @GetMapping("/degatphysiquespagination")
	  public List<DegatPhysiqueResponse> getAllDegatPhysiquePagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<DegatPhysiqueResponse> degatphysiques = new ArrayList<>();
	    repository.pageDegatPhysique(PageRequest.of(page,size)).forEach(degatphysiques::add);
	    return degatphysiques;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	 @GetMapping("/degatmateriels")
	  public List<DegatMaterielResponse> getDegatMateriels() {
	    return repository.listDegatMateriel();
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	  @GetMapping("/degatphysiques")
	  public List<DegatPhysiqueResponse> getDegatphysiques() {
	    return repository.listDegatPhysique();
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	@GetMapping("/degatphysique/{id}")
	public ResponseEntity<DegatPhysiqueResponse> getDegatPhysique(@PathVariable(value = "id") Long degatId)
			throws ResourceNotFoundException {
		DegatPhysiqueResponse degat = repository.degatPhysique(degatId);
		return ResponseEntity.ok().body(degat);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondegat')")
	@GetMapping("/degatmateriel/{id}")
		public ResponseEntity<DegatMaterielResponse> getDegatMateriel(@PathVariable(value = "id") Long degatId)
				throws ResourceNotFoundException {
			DegatMaterielResponse degat = repository.degatMateriel(degatId);
			return ResponseEntity.ok().body(degat);
		}
	@PreAuthorize("@authorizationSE.can('create', 'gestiondegat')")
	@PostMapping("/adddegatmateriels")
	public Degat createDegatMateriel(@Valid @RequestBody DegatMaterielForm degatForm) {
		Long idAccident =degatForm.getIdAccident();
		Accident acc = arepository.findById(idAccident).orElse(null);
		Degat degatmateriel = new Degat();
		degatmateriel.setValue(degatForm.getValue());
		degatmateriel.setType("materiel");
		degatmateriel.setDescription(degatForm.getDescription());
		degatmateriel.setAccident(acc);
		return repository.save(degatmateriel);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestiondegat')")
	@PostMapping("/adddegatphysiques")
	public Degat createDegatPhysique(@Valid @RequestBody DegatPhysiqueForm degatForm) {
		Long idAccident =degatForm.getIdAccident();
		Accident acc = arepository.findById(idAccident).orElse(null);
		Degat degatphysique = new Degat();
		degatphysique.setDescription(degatForm.getDescription());
		degatphysique.setType("physique");
		degatphysique.setAccident(acc);
		return repository.save(degatphysique);
	}
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegat')")
	@DeleteMapping("/deletedegats/{id}")
	public Map<String, Boolean> deleteDegat(@PathVariable(value = "id") Long degatId)
			throws ResourceNotFoundException {
		Degat degat = repository.findById(degatId)
				.orElseThrow(() -> new ResourceNotFoundException("Degat  not found  id :: " + degatId));

		repository.delete(degat);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondegat')")
	  @DeleteMapping("/degats/delete")
	  public ResponseEntity<String> deleteAllDegats() {
	    System.out.println("Delete All Degats...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Degats have been deleted!", HttpStatus.OK);
	  }
	 
	  
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondegat')")
	  @PutMapping("/editdegatmateriels/{id}")
	  public ResponseEntity<Degat> updateDegat(@PathVariable("id") long id, @RequestBody DegatMaterielForm degatForm) {
	    System.out.println("Update Degat with ID = " + id + "...");
	    Long idAccident =degatForm.getIdAccident();
		Accident acc = arepository.findById(idAccident).orElse(null);
	    Optional<Degat> degatInfo = repository.findById(id);
	 
	    if (degatInfo.isPresent()) {
	    	Degat degatmat = degatInfo.get();
	          
	           degatmat.setDescription(degatForm.getDescription());
	           degatmat.setType("materiel");
	           degatmat.setValue(degatForm.getValue());
	           degatmat.setAccident(acc);
	           
	          
	      return new ResponseEntity<>(repository.save(degatmat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	@PreAuthorize("@authorizationSE.can('update', 'gestiondegat')")
	  @PutMapping("/editdegatphysiques/{id}")
	  public ResponseEntity<Degat> updateDegat(@PathVariable("id") long id, @RequestBody DegatPhysiqueForm degatForm) {
	    System.out.println("Update Degat with ID = " + id + "...");
	    Long idAccident =degatForm.getIdAccident();
		Accident acc = arepository.findById(idAccident).orElse(null);
	    Optional<Degat> degatInfo = repository.findById(id);
	 
	    if (degatInfo.isPresent()) {
	    	Degat degatph = degatInfo.get();
	          
	           degatph.setDescription(degatForm.getDescription());
	           degatph.setType("physique");
	           degatph.setAccident(acc);
	           
	          
	      return new ResponseEntity<>(repository.save(degatph), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
