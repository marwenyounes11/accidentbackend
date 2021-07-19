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
import io.oa.accidentincident.form.DepartementForm;
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
import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Intervention;
import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.entity.Materiel;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceInform;

import io.oa.accidentincident.repository.AccidentInformRepository;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.AgentInterventionRepository;
import io.oa.accidentincident.repository.DepartementRepository;
import io.oa.accidentincident.repository.InterventionRepository;
import io.oa.accidentincident.repository.LieuxAccidentRepository;
import io.oa.accidentincident.repository.MaterielRepository;
import io.oa.accidentincident.repository.SourceInformRepository;




@RestController
@CrossOrigin
public class DepartementController {
	
	@Autowired 	
	DepartementRepository  repository;
	
	@Autowired 
	LieuxAccidentRepository  lrepository;
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepartement')")
	@GetMapping("/departementspaginationmc")
	  public List<Departement> getAllDepartementsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<Departement> departements = new ArrayList<>();
	    repository.chercherDepartement(mc,PageRequest.of(page,size)).forEach(departements::add);
	 
	    return departements;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepartement')")
	@GetMapping("/departementspagination")
	  public List<Departement> getAllDepartementsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<Departement> departements = new ArrayList<>();
	    repository.pageDepartement(PageRequest.of(page,size)).forEach(departements::add);
	    return departements;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondepartement')")
	@GetMapping("/listdepartements")
	  public List<Departement> getAllDepartements() {
	    System.out.println("Get all Departements...");
	 
	    List<Departement> departements = new ArrayList<>();
	    repository.findAll().forEach(departements::add);
	 
	    return departements;
	  }
	@PreAuthorize("@authorizationSE.can('create', 'gestiondepartement')")
	@PostMapping("/savedepartement")
	public Departement createDepartement(@Valid @RequestBody DepartementForm departementForm) throws ParseException {
		Long idlieux =departementForm.getIdlieux();
		LieuxAccident lieux = lrepository.findById(idlieux).orElse(null);
		Departement departement = new Departement();
		departement.setLieux(lieux);
		departement.setNameDepartement(departementForm.getNameDepartement());
		return repository.save(departement);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondepartement')")
	@DeleteMapping("/deletedepartement/{id}")
	public void deleteDepartements(@PathVariable(value = "id") Long departementId)
			throws ResourceNotFoundException {
		 repository.deleteDepartement(departementId);
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondepartement')")
	  @DeleteMapping("/departements/delete")
	  public ResponseEntity<String> deleteAllDepartement() {
	    System.out.println("Delete All departements...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All departements have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondepartement')")
	  @PutMapping("/editdepartement/{id}")
	  public ResponseEntity<Departement> updateDepartement(@PathVariable("id") long id, @RequestBody DepartementForm departementForm) throws ParseException{
	    System.out.println("Update departement with ID = " + id + "...");
	    Long idlieux =departementForm.getIdlieux();
		LieuxAccident lieux = lrepository.findById(idlieux).orElse(null);
		 Optional<Departement> departementInfo = repository.findById(id);
		
	   
	 
	    if (departementInfo.isPresent()) {
	    	Departement departement = departementInfo.get();
	        
	    	departement.setLieux(lieux);
			departement.setNameDepartement(departementForm.getNameDepartement());
	      return new ResponseEntity<>(repository.save(departement), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
}
