  package io.oa.accidentincident.rest;
  
  import java.io.File; 
  import java.io.FileInputStream;
  import java.nio.file.Files; 
  import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
  import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
  import java.util.Map;
  import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext; 
  import javax.validation.Valid;
  
  import org.apache.commons.io.FileUtils; 
  import org.apache.commons.io.FilenameUtils;
  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin; 
  import org.springframework.web.bind.annotation.DeleteMapping; 
  import org.springframework.web.bind.annotation.GetMapping; 
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping; 
  import org.springframework.web.bind.annotation.PutMapping; 
  import org.springframework.web.bind.annotation.RequestBody; 
  import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.multipart.MultipartFile;
  import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.JsonParseException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import com.fasterxml.jackson.databind.ObjectMapper; 
  import io.oa.accidentincident.domaine.Response;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.AccidentByTransportForm;
import io.oa.accidentincident.form.AccidentForm;
import io.oa.accidentincident.form.IncidentJournalierForm;
import io.oa.accidentincident.form.IncidentJournalierTravailForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.Degat;

import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DegatVictimeRepository;
import io.oa.accidentincident.repository.LieuxAccidentRepository;
import io.oa.accidentincident.response.AccidentResponse;
import io.oa.accidentincident.response.DegatResponse; 
  

@CrossOrigin
@RestController

  public class AccidentController {
  
  @Autowired
  ServletContext context;
  @Autowired 
  AccidentRepository repository;
 
  @Autowired 
 DegatVictimeRepository dvrepository;
  @Autowired 
  LieuxAccidentRepository lrepository;
  
  
  
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/accident/{id}") 
 public ResponseEntity<Accident>getAccidentById(@PathVariable(value = "id") Long Id) throws
  ResourceNotFoundException { 
	 Accident accident = repository.findById(Id).orElseThrow(() -> new
  ResourceNotFoundException("Accident not found for this id :: " + Id)); 
	 return ResponseEntity.ok().body(accident); 
	 }
  @PreAuthorize("@authorizationSE.can('afficher', 'incidentjournalierreseaux')")
  @GetMapping("/incidentJournaliers/route/{dateAccident}")
  public List<IncidentJournalierForm> getIncidentJournalierRoutes(@PathVariable String dateAccident) {
     System.out.println("Get all IncidentJournaliers ...");
 
    List<IncidentJournalierForm> incidentJournaliers = new ArrayList<>(); 
    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
    try {
    repository.findByDateAccident(df1.parse(dateAccident)).forEach(a->{
    if(a.getType().equals("route")) {
    	a.getAccidenttransports().forEach(at->{
    		MoyenTransport mt = at.getMoyentransport();
    		String nameDistrict=mt.getDistrict().getNameDistrict();
    		Date heureAccident=a.getHeureAccident();
    		String subType=a.getSubType();
    		String nameLigne=mt.getLigne().getNameLigne();
    		String immatriculation=mt.getImmatriculation() ;
    		String numTransport=mt.getNumTransport() ;
    		String type=mt.getType() ;
    		String emplacement=a.getLieux().getEmplacement();
    		String name=mt.getAgent().getName();
    		String lastName=mt.getAgent().getLastName();
    		Set<Degat> degatmateriels=new HashSet<Degat>();
    		Set<Degat> degatphysiques=new HashSet<Degat>();
    		a.getDegats().forEach(d->{
    		if(d.getType().equals("materiel")){
    		degatmateriels.add(d);
    		}
    		else{
    		degatphysiques.add(d);
    		}
    		});
    		IncidentJournalierForm incidentJournalierForm = new IncidentJournalierForm();
    		incidentJournalierForm.setNameDistrict(nameDistrict);
    		try {
    		incidentJournalierForm.setDateAccident(df1.parse(dateAccident));
    		}catch(ParseException e) {
				e.printStackTrace();
			}
    		incidentJournalierForm.setHeureAccident(heureAccident);
    		incidentJournalierForm.setSubType(subType);
    		incidentJournalierForm.setNameLigne(nameLigne);
    		incidentJournalierForm.setImmatriculation(immatriculation);
    		incidentJournalierForm.setNumTransport(numTransport);
    		incidentJournalierForm.setType(type);
    		incidentJournalierForm.setEmplacement(emplacement);
    		incidentJournalierForm.setName(name);
    		incidentJournalierForm.setLastName(lastName);
    		incidentJournalierForm.setDegatmateriels(degatmateriels);
    		incidentJournalierForm.setDegatphysiques(degatphysiques);
    		incidentJournaliers.add(incidentJournalierForm);
    	});
        
    } 
      
    });
    }catch(ParseException e) {
		e.printStackTrace();
	}
 
    return incidentJournaliers;
  }
  @PreAuthorize("@authorizationSE.can('afficher', 'incidentjournaliertravail')")
  @GetMapping("/incidentJournaliers/travail/{dateAccident}")
  public List<IncidentJournalierTravailForm> getIncidentJournalierTravails(@PathVariable String dateAccident) {
     System.out.println("Get all IncidentJournaliers ...");
 
    List<IncidentJournalierTravailForm> incidentJournaliers = new ArrayList<>(); 
    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
    try {
    repository.findByDateAccident(df1.parse(dateAccident)).forEach(a->{
    if(a.getType().equals("travail")) {
    		Date heureAccident=a.getHeureAccident();
    		String subType=a.getSubType();
    		String emplacement=a.getLieux().getEmplacement();
    		Set<Degat> degats=new HashSet<Degat>();
    		Set<Degat> degatmateriels=new HashSet<Degat>();
    		Set<Degat> degatphysiques=new HashSet<Degat>();
    		degats=a.getDegats();
    		for(Degat d:degats){
    		if(d.getType().equals("materiel")){
    		degatmateriels.add(d);
    		}
    		else{
    		degatphysiques.add(d);
    		}
    		}
    		IncidentJournalierTravailForm incidentJournalierForm = new IncidentJournalierTravailForm();
    		try {
    		incidentJournalierForm.setDateAccident(df1.parse(dateAccident));
    		}catch(ParseException e) {
				e.printStackTrace();
			}
    		incidentJournalierForm.setHeureAccident(heureAccident);
    		incidentJournalierForm.setSubType(subType);
    		incidentJournalierForm.setEmplacement(emplacement);
    		incidentJournalierForm.setDegatmateriels(degatmateriels);
    		incidentJournalierForm.setDegatphysiques(degatphysiques);
    		incidentJournaliers.add(incidentJournalierForm);   
    }   
    });
    }catch(ParseException e) {
		e.printStackTrace();
	}
    return incidentJournaliers;
  }
  
 
  
  
  @PreAuthorize("@authorizationSE.can('afficher', 'incidentjournalierreseaux')")
  @GetMapping("/incidentJournaliers/collision/{dateAccident}")
  public List<IncidentJournalierForm> getIncidentJournalierCollisions(@PathVariable String dateAccident) {
     System.out.println("Get all IncidentJournaliers ...");
 
    List<IncidentJournalierForm> incidentJournaliers = new ArrayList<>(); 
    DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
    try {
    repository.findByDateAccident(df1.parse(dateAccident)).forEach(a->{
    if(a.getType().equals("collision")) {
    	a.getAccidenttransports().forEach(at->{
    		MoyenTransport mt = at.getMoyentransport();
    		String nameDistrict=mt.getDistrict().getNameDistrict();
    		Date heureAccident=a.getHeureAccident();
    		String subType=a.getSubType();
    		String nameLigne=mt.getLigne().getNameLigne();
    		String immatriculation=mt.getImmatriculation() ;
    		String numTransport=mt.getNumTransport() ;
    		String type=mt.getType() ;
    		String emplacement=a.getLieux().getEmplacement();
    		String name=mt.getAgent().getName();
    		String lastName=mt.getAgent().getLastName();
    		Set<Degat> degatmateriels=new HashSet<Degat>();
    		Set<Degat> degatphysiques=new HashSet<Degat>();
    		a.getDegats().forEach(d->{
    		if(d.getType().equals("materiel")){
    		degatmateriels.add(d);
    		}
    		else{
    		degatphysiques.add(d);
    		}
    		});
    		IncidentJournalierForm incidentJournalierForm = new IncidentJournalierForm();
    		incidentJournalierForm.setNameDistrict(nameDistrict);
    		try {
    		incidentJournalierForm.setDateAccident(df1.parse(dateAccident));
    		}catch(ParseException e) {
				e.printStackTrace();
			}
    		incidentJournalierForm.setHeureAccident(heureAccident);
    		incidentJournalierForm.setSubType(subType);
    		incidentJournalierForm.setNameLigne(nameLigne);
    		incidentJournalierForm.setImmatriculation(immatriculation);
    		incidentJournalierForm.setNumTransport(numTransport);
    		incidentJournalierForm.setType(type);
    		incidentJournalierForm.setEmplacement(emplacement);
    		incidentJournalierForm.setName(name);
    		incidentJournalierForm.setLastName(lastName);
    		incidentJournalierForm.setDegatmateriels(degatmateriels);
    		incidentJournalierForm.setDegatphysiques(degatphysiques);
    		incidentJournaliers.add(incidentJournalierForm);
    	});
        
    } 
      
    });
    }catch(ParseException e) {
		e.printStackTrace();
	}
 
    return incidentJournaliers;
  }

  public byte[] getPhoto( Long id) throws Exception{ 
   Accident accident = repository.findById(id).get();
   return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+accident.getImage())); }
  
  
  public String getExtensionimg(File file) throws Exception{ 
	  String extension= FilenameUtils.getExtension(file.getName()); 
	   return extension;
	   }
  
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')") 
 @GetMapping("/listaccidentcollision")
  public List<AccidentResponse> getAllAccidentsCollision() throws Exception{
	 List<Accident> accidents = new ArrayList<>();
 	  List<AccidentResponse> accidentres = new ArrayList<>();
  	
  		System.out.println("Get all accidents collision ...");
  		 
  	 
  	  repository.findAll().forEach(accidents::add);
  	  accidents.forEach(a->{
  		  
		AccidentResponse ar = new AccidentResponse();
		 if(a.getType().equals("collision")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		 }
	  });
	
	  
  return accidentres; 
  }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/listaccidentroute")
 public List<AccidentResponse> getAllAccidentsRoute() throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
 	
 		System.out.println("Get all accidents route ...");
 		 
 	 
 	  repository.findAll().forEach(accidents::add);
 	  accidents.forEach(a->{
 		  
		AccidentResponse ar = new AccidentResponse();
		if(a.getType().equals("route")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		}
	  });
	
	  
 return accidentres; 
 } 
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/listaccidenttravail")
 public List<AccidentResponse> getAllAccidentsTravail() throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
 	
 		System.out.println("Get all accidents travail ...");
 		 
 	 
 	  repository.findAll().forEach(accidents::add);
 	  accidents.forEach(a->{
 		  
		AccidentResponse ar = new AccidentResponse();
		if(a.getType().equals("travail")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		}
	  });
	
	  
 return accidentres; 
 }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/listaccidentcollisionpaginationmc")
 public List<AccidentResponse> getAllAccidentsCollisionPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
 	
 		System.out.println("Get all accidents collision ...");
 		 
 	 
 	  repository.chercherAccident("%"+mc+"%",PageRequest.of(page,size)).forEach(accidents::add);
 	  accidents.forEach(a->{
 		  
		AccidentResponse ar = new AccidentResponse();
		 if(a.getType().equals("collision")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		 }
	  });
	
	  
 return accidentres; 
 }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/listaccidentcollisionpagination")
 public List<AccidentResponse> getAllAccidentsCollisionPagination(
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
 	
 		System.out.println("Get all accidents collision ...");
 		 
 	 
 	  repository.pageAccidentCollision(PageRequest.of(page,size)).forEach(accidents::add);
 	  accidents.forEach(a->{
 		  
		AccidentResponse ar = new AccidentResponse();
		 
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		
	  });
	
	  
 return accidentres; 
 }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
@GetMapping("/listaccidentroutepaginationmc")
public List<AccidentResponse> getAllAccidentsRoutePaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
	
		System.out.println("Get all accidents route ...");
		 
	 
	  repository.chercherAccident("%"+mc+"%",PageRequest.of(page,size)).forEach(accidents::add);
	  accidents.forEach(a->{
		  
		AccidentResponse ar = new AccidentResponse();
		if(a.getType().equals("route")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		}
	  });
	
	  
return accidentres; 
} 
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
@GetMapping("/listaccidentroutepagination")
public List<AccidentResponse> getAllAccidentsRoutePagination(
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
	
		System.out.println("Get all accidents route ...");
		 
	 
	  repository.pageAccidentRoute(PageRequest.of(page,size)).forEach(accidents::add);
	  accidents.forEach(a->{
		  
		AccidentResponse ar = new AccidentResponse();
		
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		
	  });
	
	  
return accidentres; 
} 
  
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
@GetMapping("/listaccidenttravailpaginationmc")
public List<AccidentResponse> getAllAccidentsTravailPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
	
		System.out.println("Get all accidents travail ...");
		 
	 
	  repository.chercherAccident("%"+mc+"%",PageRequest.of(page,size)).forEach(accidents::add);
	  accidents.forEach(a->{
		  
		AccidentResponse ar = new AccidentResponse();
		if(a.getType().equals("travail")) {
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		}
	  });
	
	  
return accidentres; 
}

  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
@GetMapping("/listaccidenttravailpagination")
public List<AccidentResponse> getAllAccidentsTravailPagination(
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws Exception{
	 List<Accident> accidents = new ArrayList<>();
	  List<AccidentResponse> accidentres = new ArrayList<>();
	
		System.out.println("Get all accidents travail ...");
		 
	 
	  repository.pageAccidentTravail(PageRequest.of(page,size)).forEach(accidents::add);
	  accidents.forEach(a->{
		  
		AccidentResponse ar = new AccidentResponse();
		
		  ar.setId(a.getId());
		  ar.setType(a.getType());
		  ar.setDateAccident(a.getDateAccident());
		  ar.setHeureAccident(a.getHeureAccident());
		  ar.setDescription(a.getDescription());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(a.getId());
			ar.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+a.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		ar.setExtension(extension);
		  ar.setSubType(a.getSubType());
		  ar.setLieux(a.getLieux());
		  accidentres.add(ar);
		
	  });
	
	  
return accidentres; 
}

  @PreAuthorize("@authorizationSE.can('afficher', 'gestionaccident')")
 @GetMapping("/listaccidents")
 public List<Accident> getAllAccidents() {

   return repository.findAll();
 }
  
  @GetMapping ("/getAll") 
  public ResponseEntity<List<String>> getAll() {
  List<String> listArt = new ArrayList<String>(); 
  String filesPath = context.getRealPath("/Images"); 
  File filefolder = new File(filesPath);
  if(filefolder != null) { 
	  for (File file :filefolder.listFiles()) {
  if(!file.isDirectory()) { 
   String encodeBase64 = null; 
  try { 
	  String extension= FilenameUtils.getExtension(file.getName()); 
	  FileInputStream fileInputStream = new FileInputStream(file);
	  byte[] bytes = new byte[(int)file.length()];
  fileInputStream.read(bytes);
  encodeBase64 = Base64.getEncoder().encodeToString(bytes);
  listArt.add("data:image/"+extension+";base64,"+encodeBase64);
  fileInputStream.close();
  
  
  }catch (Exception e){
  
  } 
  } 
  }
	  } return new ResponseEntity<List<String>>(listArt,HttpStatus.OK);
	  }
  
  
  @PreAuthorize("@authorizationSE.can('create', 'gestionaccident')")
  @PostMapping("/addaccidentroutes")
  public ResponseEntity<Response> createAccidentRoute(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("accidentroute") String accidenttForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux(); 
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null); 
  
  Accident accr = new Accident();
  if(file==null) {System.out.println(true);}
  else {
  System.out.println("Ok ............."); 
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  accr.setImage(newFileName);
	  }
   }
  accr.setType("route");
  accr.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  accr.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  accr.setDescription(accidentForm.getDescription()); 
  accr.setLieux(lacc);
  
  accr.setSubType(accidentForm.getSubType());
  Accident art = repository.save(accr); 
  if (art != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("Accident not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 }
  @PreAuthorize("@authorizationSE.can('create', 'gestionaccident')")
  @PostMapping("/addaccidenttravails")
  public ResponseEntity<Response> createAccidentTravail(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("accidenttravail") String accidenttForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux(); 
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null); 
  
  Accident acct = new Accident();
  if(file==null) {System.out.println(true);}
  else {
  System.out.println("Ok ............."); 
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  acct.setImage(newFileName); 
	  }
   }
  acct.setType("travail");
  acct.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  acct.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  acct.setDescription(accidentForm.getDescription()); 
  acct.setLieux(lacc);
  
  acct.setSubType(accidentForm.getSubType());
  Accident art = repository.save(acct); 
  if (art != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("Accident not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 }

  
  @PreAuthorize("@authorizationSE.can('create', 'gestionaccident')")
  @PostMapping("/addaccidentcollisions")
  public ResponseEntity<Response> createAccidentCollision(@RequestParam(value="file", required=false) MultipartFile file, @RequestParam("accidentcollision") String accidenttForm) throws JsonParseException , JsonMappingException , Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux(); 
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null); 
  Accident acc = new Accident();
  if(file==null) {System.out.println(true);}
  else {
  System.out.println("Ok ............."); 
  if (!file.isEmpty()) {
	  System.out.println("ok.............");
 
  }
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  acc.setImage(newFileName); 
	  }
  }
  acc.setType("collision");
  acc.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  acc.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  acc.setDescription(accidentForm.getDescription()); 
  acc.setLieux(lacc);
  acc.setSubType(accidentForm.getSubType());
  Accident art = repository.save(acc); 
  if (art != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("Accident not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 }
  @PreAuthorize("@authorizationSE.can('delete', 'gestionaccident')")
  @DeleteMapping("/deleteaccidents/{id}") 
  public void deleteAccident(@PathVariable(value = "id") Long accidentId) throws ResourceNotFoundException { 
	 repository.deleteAccident(accidentId);
  }
  @PreAuthorize("@authorizationSE.can('delete', 'gestionaccident')")
  @DeleteMapping("/accidents/delete")
  public ResponseEntity<String> deleteAllAccidents() {
   System.out.println("Delete All accidents...");
  repository.deleteAll(); 
  return new ResponseEntity<>("All accidents have been deleted!", HttpStatus.OK);
  }
  
  @PreAuthorize("@authorizationSE.can('update', 'gestionaccident')")
  @PutMapping("/editaccidenttravails/{id}") public ResponseEntity<Accident>updateAccidentTravail(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file, @RequestParam("accidenttravail") String accidenttForm) throws JsonParseException , JsonMappingException , Exception{
  
	  System.out.println("Update Accident with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux();
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null);
  Optional<Accident> accidentInfo = repository.findById(id);
  if (accidentInfo.isPresent()) {
  Accident accidenttr = accidentInfo.get();
  if(file==null) {System.out.println(true);}
  else {
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  accidenttr.setImage(newFileName); 
	  }
  }
  accidenttr.setType("travail");
  accidenttr.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  accidenttr.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  accidenttr.setDescription(accidentForm.getDescription());
  accidenttr.setSubType(accidentForm.getSubType());
  accidenttr.setLieux(lacc);  
  return new ResponseEntity<>(repository.save(accidenttr), HttpStatus.OK);
  }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
  
  @PreAuthorize("@authorizationSE.can('update', 'gestionaccident')")
  @PutMapping("/editaccidentroutes/{id}") public ResponseEntity<Accident>updateAccidentRoute(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file, @RequestParam("accidentroute") String accidenttForm) throws JsonParseException , JsonMappingException , Exception{

	  System.out.println("Update Accident with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux();
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null);
  Optional<Accident> accidentInfo = repository.findById(id);
  if (accidentInfo.isPresent()) {
  Accident accidentr = accidentInfo.get();
  if(file==null) {System.out.println(true);}
  else {
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  accidentr.setImage(newFileName);
	  }
   }
  accidentr.setType("route");
  accidentr.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  accidentr.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  accidentr.setDescription(accidentForm.getDescription());
  accidentr.setSubType(accidentForm.getSubType());
  accidentr.setLieux(lacc);
  return new ResponseEntity<>(repository.save(accidentr), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
  @PreAuthorize("@authorizationSE.can('update', 'gestionaccident')")
  @PutMapping("/editaccidentcollisions/{id}") public ResponseEntity<Accident>updateAccidentCollision(@PathVariable("id") long id, @RequestParam(value="file", required=false ) MultipartFile file, @RequestParam("accidentcollision") String accidenttForm) throws JsonParseException , JsonMappingException , Exception{
	  System.out.println("Update Accident with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  AccidentForm accidentForm= new ObjectMapper().readValue(accidenttForm, AccidentForm.class);
  Long idLieux =accidentForm.getIdlieux();
  LieuxAccident lacc = lrepository.findById(idLieux).orElse(null);
  Optional<Accident> accidentInfo = repository.findById(id);
  if (accidentInfo.isPresent()) {
  Accident accidentc = accidentInfo.get();
  if(file==null) {System.out.println(true);}
  else {
  if (!file.isEmpty()) {
	  System.out.println("ok.............");
 
  }
  boolean isExit = new File(context.getRealPath("/Images/")).exists();
  if (!isExit) {
	  new File(context.getRealPath("/Images/")).mkdir();
  System.out.println("mk dir.............");
  } 
  String filename = file.getOriginalFilename();
  String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
  File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFileName)); 
  try {
  System.out.println("Image");
  FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
  }catch(Exception e) { 
	  e.printStackTrace(); 
	  }
  if(newFileName != null) {
	  accidentc.setImage(newFileName);
	  }
   }
  accidentc.setType("collision");
  accidentc.setDateAccident(df1.parse(accidentForm.getDateAccident()));
  accidentc.setHeureAccident(df2.parse(accidentForm.getHeureAccident()));
  accidentc.setDescription(accidentForm.getDescription()); 
  accidentc.setSubType(accidentForm.getSubType());
  accidentc.setLieux(lacc);
  return new ResponseEntity<>(repository.save(accidentc), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
  
}
 