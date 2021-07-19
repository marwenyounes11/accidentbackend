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
import io.oa.accidentincident.entity.District;
import io.oa.accidentincident.repository.DistrictRepository;

@RestController
@CrossOrigin(origins = "*")
public class DistrictController {
	@Autowired
	DistrictRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondistrict')")
	@GetMapping("/districtspaginationmc")
	  public List<District> getAllDistrictsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<District> districts = new ArrayList<>();
	    repository.chercherDistrict(mc,PageRequest.of(page,size)).forEach(districts::add);
	 
	    return districts;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondistrict')")
	@GetMapping("/districtspagination")
	  public List<District> getAllDistrictsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<District> districts = new ArrayList<>();
	    repository.pageDistrict(PageRequest.of(page,size)).forEach(districts::add);
	    return districts;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondistrict')")
	 @GetMapping("/listdistricts")
	  public List<District> getAllDistricts() {
	    System.out.println("Get all Districts...");
	 
	    List<District> districts = new ArrayList<>();
	    repository.findAll().forEach(districts::add);
	 
	    return districts;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestiondistrict')")
	@GetMapping("/districts/{id}")
	public ResponseEntity<District> getDistrictById(@PathVariable(value = "id") Long districtId)
			throws ResourceNotFoundException {
		District district = repository.findById(districtId)
				.orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + districtId));
		return ResponseEntity.ok().body(district);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestiondistrict')")
	@PostMapping("/districts")
	public District createDistrict(@Valid @RequestBody District district) {
		return repository.save(district);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondistrict')")
	@DeleteMapping("/districts/{id}")
	public Map<String, Boolean> deleteDistrict(@PathVariable(value = "id") Long districtId)
			throws ResourceNotFoundException {
		District district = repository.findById(districtId)
				.orElseThrow(() -> new ResourceNotFoundException("District not found  id :: " + districtId));

		repository.delete(district);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestiondistrict')")
	  @DeleteMapping("/districts/delete")
	  public ResponseEntity<String> deleteAllDistricts() {
	    System.out.println("Delete All Districts...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Districts have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestiondistrict')")
	  @PutMapping("/districts/{id}")
	  public ResponseEntity<District> updateDistrict(@PathVariable("id") long id, @RequestBody District district) {
	    System.out.println("Update District with ID = " + id + "...");
	 
	    Optional<District> districtInfo = repository.findById(id);
	 
	    if (districtInfo.isPresent()) {
	    	District districtt = districtInfo.get();
	          
	           districtt.setNameDistrict(district.getNameDistrict());
	           
	          
	      return new ResponseEntity<>(repository.save(districtt), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
