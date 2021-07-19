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
import io.oa.accidentincident.entity.SourceDeclareHuissier;
import io.oa.accidentincident.entity.SourceInform;
import io.oa.accidentincident.repository.SourceInformRepository;
@CrossOrigin
@RestController
public class SourceInformController {
	@Autowired
	SourceInformRepository repository;
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourceinfo')")
	@GetMapping("/sourceinformpaginationmc")
	  public List<SourceInform> getAllSourceInformsPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	    List<SourceInform> sourceinform = new ArrayList<>();
	    repository.chercherSourceInform(mc,PageRequest.of(page,size)).forEach(sourceinform::add);
	 
	    return sourceinform;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourceinfo')")
	@GetMapping("sourceinformpagination")
	  public List<SourceInform> getAllSourceInformsPagination(@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="2")int size) {
	    List<SourceInform> sourceinform = new ArrayList<>();
	    repository.pageSourceInform(PageRequest.of(page,size)).forEach(sourceinform::add);
	    return sourceinform;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourceinfo')")
	 @GetMapping("/listsourceInforms")
	  public List<SourceInform> getAllSourceInfos() {
	    System.out.println("Get all SourceInfos...");
	 
	    List<SourceInform> sourceInfos = new ArrayList<>();
	    repository.findAll().forEach(sourceInfos::add);
	 
	    return sourceInfos;
	  }
	@PreAuthorize("@authorizationSE.can('afficher', 'gestionsourceinfo')")
	@GetMapping("/sourceInfos/{id}")
	public ResponseEntity<SourceInform> getSourceInfoById(@PathVariable(value = "id") Long sourceInfoId)
			throws ResourceNotFoundException {
		SourceInform sourceInfo = repository.findById(sourceInfoId)
				.orElseThrow(() -> new ResourceNotFoundException("SourceInfo not found for this id :: " + sourceInfoId));
		return ResponseEntity.ok().body(sourceInfo);
	}
	@PreAuthorize("@authorizationSE.can('create', 'gestionsourceinfo')")
	@PostMapping("/sourceInfos")
	public SourceInform createSourceInfo(@Valid @RequestBody SourceInform sourceInfo) {
		return repository.save(sourceInfo);
	}
	
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsourceinfo')")
	@DeleteMapping("/sourceInfos/{id}")
	public Map<String, Boolean> deleteSourceInfo(@PathVariable(value = "id") Long sourceInfoId)
			throws ResourceNotFoundException {
		SourceInform sourceInfo = repository.findById(sourceInfoId)
				.orElseThrow(() -> new ResourceNotFoundException("SourceInfo not found  id :: " + sourceInfoId));

		repository.delete(sourceInfo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	@PreAuthorize("@authorizationSE.can('delete', 'gestionsourceinfo')")
	  @DeleteMapping("/sourceInfos/delete")
	  public ResponseEntity<String> deleteAllSourceInfos() {
	    System.out.println("Delete All SourceInfos...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All SourceInfos have been deleted!", HttpStatus.OK);
	  }
	 
	
	@PreAuthorize("@authorizationSE.can('update', 'gestionsourceinfo')")
	  @PutMapping("/sourceInfos/{id}")
	  public ResponseEntity<SourceInform> updateSourceInfo(@PathVariable("id") long id, @RequestBody SourceInform sourceInfo) {
	    System.out.println("Update SourceInfo with ID = " + id + "...");
	 
	    Optional<SourceInform> sourceInfoInfo = repository.findById(id);
	 
	    if (sourceInfoInfo.isPresent()) {
	    	SourceInform sourceInfos = sourceInfoInfo.get();
	          
	           sourceInfos.setLastName(sourceInfo.getLastName());
	           sourceInfos.setName(sourceInfo.getName());
	           sourceInfos.setNature(sourceInfo.getNature());
	           sourceInfos.setPhone(sourceInfo.getPhone());
	          
	      return new ResponseEntity<>(repository.save(sourceInfos), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
