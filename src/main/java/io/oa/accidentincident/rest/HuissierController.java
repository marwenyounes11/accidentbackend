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
import io.oa.accidentincident.form.HuissierForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.Huissier;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceDeclareHuissier;
import io.oa.accidentincident.entity.SourceInform;

import io.oa.accidentincident.repository.AccidentInformRepository;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.HuissierRepository;
import io.oa.accidentincident.repository.SecuriteRepository;
import io.oa.accidentincident.repository.SourceDeclareHuissierRepository;
import io.oa.accidentincident.repository.SourceInformRepository;
import io.oa.accidentincident.response.AccidentInformResponse;
import io.oa.accidentincident.response.HuissierResponse;




@RestController
@CrossOrigin
public class HuissierController {
	
	@Autowired 	
	HuissierRepository  repository;
	@Autowired 	
	SourceDeclareHuissierRepository  sdhrepository;
	@Autowired 	
	AccidentRepository  arepository;
	@Autowired 
	SecuriteRepository  srepository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionhuissier')")
	@GetMapping("/huissierspaginationmc")
	  public List<HuissierResponse> getAllHuissiersPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = df1.parse(mc);
	    List<HuissierResponse> huissiers = new ArrayList<>();
	    repository.chercherHuissier(d,PageRequest.of(page,size)).forEach(huissiers::add);
	 
	    return huissiers;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionhuissier')")
	@GetMapping("/huissierspagination")
	  public List<HuissierResponse> getAllHuissiersPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<HuissierResponse> huissiers = new ArrayList<>();
	    repository.pageHuissier(PageRequest.of(page,size)).forEach(huissiers::add);
	    return huissiers;
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionhuissier')")
	@GetMapping("/listhuissiers")
	  public List<HuissierResponse> getAllHuissiers() {
	     System.out.println("Get all Huissiers...");
	 
	    List<HuissierResponse> huissiers = new ArrayList<>();
	    repository.listHuissier().forEach(huissiers::add);
	 
	    return huissiers;
	  }
	 
	@PreAuthorize("@authorizationSE.can('create', 'gestionhuissier')")
	@PostMapping("/addhuissiers")
	public Huissier createHuissier(@Valid @RequestBody HuissierForm huissierForm) throws ParseException {
		DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
		Long idaccident =huissierForm.getIdaccident();
		Long idsourcedeclarehuissier =huissierForm.getIdsourcedeclarehuissier();
		Long idsecurite =huissierForm.getIdsecurite();
		Accident acc = arepository.findById(idaccident).orElse(null);
		SourceDeclareHuissier sourcedeclarehuissier = sdhrepository.findById(idsourcedeclarehuissier).orElse(null);
		Securite securite = srepository.findById(idsecurite).orElse(null);
		Huissier huissier = new Huissier();
		huissier.setAccident(acc);
		huissier.setSourcedeclarehuissier(sourcedeclarehuissier);
		huissier.setSecurite(securite);
		huissier.setDateHuissier(df1.parse(huissierForm.getDateHuissier()));
		huissier.setHeureHuissier(df2.parse(huissierForm.getHeureHuissier()));
		huissier.setNumber(huissierForm.getNumber());
		return repository.save(huissier);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionhuissier')")
	@DeleteMapping("/deletehuissiers/{id}")
	public Map<String, Boolean> deleteHuissier(@PathVariable(value = "id") Long huissierId)
			throws ResourceNotFoundException {
		Huissier huissier = repository.findById(huissierId)
				.orElseThrow(() -> new ResourceNotFoundException("huissier not found  id :: " + huissierId));

		repository.delete(huissier);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionhuissier')")
	  @DeleteMapping("/huissiers/delete")
	  public ResponseEntity<String> deleteAllHuissiers() {
	    System.out.println("Delete All huissiers...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All accident informs have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionhuissier')")
	  @PutMapping("/edithuissiers/{id}")
	  public ResponseEntity<Huissier> updateHuissier(@PathVariable("id") long id, @RequestBody HuissierForm huissierForm) throws ParseException{
	    System.out.println("Update Huissier with ID = " + id + "...");
	    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
		  DateFormat df2= new SimpleDateFormat("HH:mm");
		Long idaccident =huissierForm.getIdaccident();
		Long idsourcedeclarehuissier =huissierForm.getIdsourcedeclarehuissier();
		Long idsecurite =huissierForm.getIdsecurite();
		Accident acc = arepository.findById(idaccident).orElse(null);
		SourceDeclareHuissier sourcedeclarehuissier = sdhrepository.findById(idsourcedeclarehuissier).orElse(null);
		Securite securite = srepository.findById(idsecurite).orElse(null);
	    Optional<Huissier> huissierInfo = repository.findById(id);
	 
	    if (huissierInfo.isPresent()) {
	    	Huissier huissier = huissierInfo.get();
	        
	    	huissier.setAccident(acc);
			huissier.setSourcedeclarehuissier(sourcedeclarehuissier);
			huissier.setSecurite(securite);
			huissier.setDateHuissier(df1.parse(huissierForm.getDateHuissier()));
			huissier.setHeureHuissier(df2.parse(huissierForm.getHeureHuissier()));
			huissier.setNumber(huissierForm.getNumber());
	      return new ResponseEntity<>(repository.save(huissier), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
