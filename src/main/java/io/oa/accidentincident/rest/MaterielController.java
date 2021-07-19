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
import org.springframework.data.domain.Page;
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
  import org.springframework.web.multipart.MultipartFile; 
  import com.fasterxml.jackson.core.JsonParseException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import com.fasterxml.jackson.databind.ObjectMapper; 
  import io.oa.accidentincident.domaine.Response;
import io.oa.accidentincident.exception.ResourceNotFoundException;
import io.oa.accidentincident.form.BoucheIncendieForm;
import io.oa.accidentincident.form.ExtincteurForm;
import io.oa.accidentincident.form.PoteauxIncendieForm;
import io.oa.accidentincident.form.RobinetIncendieForm;
import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.entity.Materiel;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DepartementRepository;
import io.oa.accidentincident.repository.DepotRepository;
import io.oa.accidentincident.repository.LieuxAccidentRepository;
import io.oa.accidentincident.repository.MaterielRepository;
import io.oa.accidentincident.repository.MoyenTransportRepository;
import io.oa.accidentincident.response.AccidentResponse;
import io.oa.accidentincident.response.BoucheIncendieResponse;
import io.oa.accidentincident.response.BusResponse;
import io.oa.accidentincident.response.ExtincteurResponse;
import io.oa.accidentincident.response.MaterielResponse;
import io.oa.accidentincident.response.PoteauxIncendieResponse;
import io.oa.accidentincident.response.RobinetIncendieResponse; 
  
  
  @RestController
  
  @CrossOrigin
  public class MaterielController {
  
  @Autowired
  ServletContext context;
  
  @Autowired 
  MaterielRepository repository;
  
  
  @Autowired 
  MoyenTransportRepository mtrepository;
  
  @Autowired 
  DepotRepository drepository;
  
  @Autowired 
  DepartementRepository dtrepository;
  
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
 @GetMapping("/extincteurpaginationmc")
  public List<ExtincteurResponse> getAllExtincteurPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	 DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = df1.parse(mc);
		 ExtincteurResponse ex = new ExtincteurResponse();
    List<ExtincteurResponse> extincteur = new ArrayList<>();
 repository.chercherMateriel(d,PageRequest.of(page,size)).forEach(mt->{
	 if(mt.getType().equals("extincteur")){
	 ex.setId(mt.getId());
	 ex.setDescription(mt.getDescription());
	 ex.setNumberSerie(mt.getNumberSerie());
	 ex.setDateAcquisition(mt.getDateAcquisition());
	 ex.setHeureAcquisition(mt.getHeureAcquisition());
	 ex.setDateMaintenance(mt.getDateMaintenance());
	 ex.setHeureMaintenance(mt.getHeureMaintenance());
	 ex.setDatePeremption(mt.getDatePeremption());
	 ex.setHeurePeremption(mt.getHeurePeremption());
	 ex.setSubtype(mt.getSubtype());
	 ex.setImage(mt.getImage());
	 ex.setEmplacement(mt.getEmplacement());
	 ex.setMoyentransport(mt.getMoyentransport());
	 ex.setDepot(mt.getDepot());
	 ex.setDepartement(mt.getDepartement());
	 extincteur.add(ex);
	 }
		});
    return extincteur;
  }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/extincteurpagination")
  public List<ExtincteurResponse> getAllExtincteurPagination(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) {
	  ExtincteurResponse ex = new ExtincteurResponse();
	  List<ExtincteurResponse> extincteur= new ArrayList<>();
    repository.pageMateriel(PageRequest.of(page,size)).forEach(mt->{
    	if(mt.getType().equals("extincteur")){
    
   	 ex.setId(mt.getId());
   	 ex.setDescription(mt.getDescription());
   	 ex.setNumberSerie(mt.getNumberSerie());
   	 ex.setDateAcquisition(mt.getDateAcquisition());
   	 ex.setHeureAcquisition(mt.getHeureAcquisition());
   	 ex.setDateMaintenance(mt.getDateMaintenance());
   	 ex.setHeureMaintenance(mt.getHeureMaintenance());
   	 ex.setDatePeremption(mt.getDatePeremption());
   	 ex.setHeurePeremption(mt.getHeurePeremption());
   	 ex.setSubtype(mt.getSubtype());
   	 ex.setImage(mt.getImage());
   	 ex.setEmplacement(mt.getEmplacement());
   	 ex.setMoyentransport(mt.getMoyentransport());
   	 ex.setDepot(mt.getDepot());
   	 ex.setDepartement(mt.getDepartement());
   	 extincteur.add(ex);
    	}
   		});
       return extincteur;
  }
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/listextincteur")
  public List<ExtincteurResponse> getAllExtincteur() {
    System.out.println("Get all Extincteur...");
    ExtincteurResponse ex = new ExtincteurResponse();
    List<ExtincteurResponse> extincteur = new ArrayList<>();
    repository.listMateriel().forEach(mt->{
    	if(mt.getType().equals("extincteur")){
      	 ex.setId(mt.getId());
       	 ex.setDescription(mt.getDescription());
       	 ex.setNumberSerie(mt.getNumberSerie());
       	 ex.setDateAcquisition(mt.getDateAcquisition());
       	 ex.setHeureAcquisition(mt.getHeureAcquisition());
       	 ex.setDateMaintenance(mt.getDateMaintenance());
       	 ex.setHeureMaintenance(mt.getHeureMaintenance());
       	 ex.setDatePeremption(mt.getDatePeremption());
       	 ex.setHeurePeremption(mt.getHeurePeremption());
       	 ex.setSubtype(mt.getSubtype());
       	 ex.setImage(mt.getImage());
       	 ex.setEmplacement(mt.getEmplacement());
       	 ex.setMoyentransport(mt.getMoyentransport());
       	 ex.setDepot(mt.getDepot());
       	 ex.setDepartement(mt.getDepartement());
       	 extincteur.add(ex);
    	}
       		});
           return extincteur;
  }
  
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
 @GetMapping("/poteauxpaginationmc")
public List<PoteauxIncendieResponse> getAllPoteauxPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	 DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = df1.parse(mc);
  List<PoteauxIncendieResponse> poteaux = new ArrayList<>();
  repository.chercherPoteaux(d,PageRequest.of(page,size)).forEach(poteaux::add);

  return poteaux;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/poteauxpagination")
public List<PoteauxIncendieResponse> getAllPoteauxPagination(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) {
  List<PoteauxIncendieResponse> poteaux= new ArrayList<>();
  repository.pagePoteaux(PageRequest.of(page,size)).forEach(poteaux::add);
  return poteaux;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/listpoteaux")
public List<PoteauxIncendieResponse> getAllPoteaux() {
  System.out.println("Get all Poteaux...");

  List<PoteauxIncendieResponse> poteaux = new ArrayList<>();
  repository.listPoteaux().forEach(poteaux::add);

  return poteaux;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/robinetpaginationmc")
public List<RobinetIncendieResponse> getAllRobinetPaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
	Date d = df1.parse(mc);
  List<RobinetIncendieResponse> robinet = new ArrayList<>();
  repository.chercherRobinet(d,PageRequest.of(page,size)).forEach(robinet::add);
  return robinet;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/robinetpagination")
public List<RobinetIncendieResponse> getAllRobinetPagination(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) {
  List<RobinetIncendieResponse> robinet= new ArrayList<>();
  repository.pageRobinet(PageRequest.of(page,size)).forEach(robinet::add);
  return robinet;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/listrobinet")
public List<RobinetIncendieResponse> getAllRobinet() {
  System.out.println("Get all Robinet...");

  List<RobinetIncendieResponse> robinet = new ArrayList<>();
  repository.listRobinet().forEach(robinet::add);

  return robinet;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/bouchepaginationmc")
public List<BoucheIncendieResponse> getAllBouchePaginationMc(@RequestParam(name="mc",defaultValue="")String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) throws ParseException {
	DateFormat df1= new SimpleDateFormat("yyyy-MM-dd"); 
	Date d = df1.parse(mc);
  List<BoucheIncendieResponse> bouche = new ArrayList<>();
  repository.chercherBouche(d,PageRequest.of(page,size)).forEach(bouche::add);
  return bouche;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/bouchepagination")
public List<BoucheIncendieResponse> getAllBouchePagination(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="2")int size) {
  List<BoucheIncendieResponse> bouche= new ArrayList<>();
  repository.pageBouche(PageRequest.of(page,size)).forEach(bouche::add);
  return bouche;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
@GetMapping("/listbouche")
public List<BoucheIncendieResponse> getAllBouche() {
  System.out.println("Get all Bouche...");

  List<BoucheIncendieResponse> bouche = new ArrayList<>();
  repository.listBouche().forEach(bouche::add);

  return bouche;
}
  @PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
  @GetMapping("/materiels/{id}") 
  public ResponseEntity<Materiel>getMaterielById(@PathVariable(value = "id") Long Id) throws
   ResourceNotFoundException { 
	  Materiel materiel = repository.findById(Id).orElseThrow(() -> new
   ResourceNotFoundException("Materiel not found for this id :: " + Id)); 
 	 return ResponseEntity.ok().body(materiel); 
 	 }
  

  public byte[] getPhoto( Long id) throws Exception{ 
   Materiel materiel = repository.findById(id).get();
   return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+materiel.getImage())); }
  
public String getExtensionimg(File file) throws Exception{ 
	  String extension= FilenameUtils.getExtension(file.getName()); 
	   return extension;
	   }
 
@PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")
  @GetMapping ("/getAllMateriel") 
  public ResponseEntity<List<String>> getAllMateriel() {
  List<String> listMat= new ArrayList<String>(); 
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
  listMat.add("data:image/"+extension+";base64,"+encodeBase64);
  fileInputStream.close();
  
  
  }catch (Exception e){
  
  } 
  } 
  }
	  } return new ResponseEntity<List<String>>(listMat,HttpStatus.OK);
	  }
@PreAuthorize("@authorizationSE.can('afficher', 'gestionmateriel')")  
  @GetMapping("/listmateriels")
  public List<MaterielResponse> getAllMateriels() throws Exception{
	 List<Materiel> materiels = new ArrayList<>();
 	  List<MaterielResponse> materielres = new ArrayList<>();
  	
  		System.out.println("Get all materiels...");
  		 
  	 
  	  repository.findAll().forEach(materiels::add);
  	  materiels.forEach(m->{
  		  
		MaterielResponse mr = new MaterielResponse();
		  mr.setId(m.getId());
		  mr.setType(m.getType());
		  mr.setDescription(m.getDescription());
		  mr.setNumberSerie(m.getNumberSerie());
		  mr.setDateAcquisition(m.getDateAcquisition());
		  mr.setDateMaintenance(m.getDateMaintenance());
		  mr.setDatePeremption(m.getDatePeremption());
		  mr.setHeureAcquisition(m.getHeureAcquisition());
		  mr.setHeureMaintenance(m.getHeureMaintenance());
		  mr.setHeurePeremption(m.getHeurePeremption());
		  mr.setSubtype(m.getSubtype());
		  byte[] imgbyte;
	  		 try { 
			imgbyte = getPhoto(m.getId());
			mr.setImage(imgbyte);
	  		}catch (Exception e){
	  		  
	  	  } 
	  		String filesPath =context.getRealPath("/Images/")+m.getImage();
	  		File file = new File(filesPath);
	  		String extension= FilenameUtils.getExtension(file.getName()); 
	  		mr.setExtension(extension);
	  		 mr.setEmplacement(m.getEmplacement());
			  mr.setLength(m.getLength());
			  mr.setDiametre(m.getDiametre());
			  mr.setNombrebouche(m.getNombrebouche());
			  mr.setColeurpoteaux(m.getColeurpoteaux());
			  mr.setMoyentransport(m.getMoyentransport());
			  mr.setDepot(m.getDepot());
			  mr.setDepartement(m.getDepartement());
		  materielres.add(mr);
	  });
	
	  
  return materielres; 
  }
  
@PreAuthorize("@authorizationSE.can('create', 'gestionmateriel')")
  @PostMapping("/addextincteur")
  public ResponseEntity<Response> createExtincteur(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("extincteur") String extinctForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  ExtincteurForm extincteurForm= new ObjectMapper().readValue(extinctForm, ExtincteurForm.class);
	  String emplacement = extincteurForm.getEmplacement();
   Materiel mat = new Materiel();
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
	  mat.setImage(newFileName);
   }
  }
  mat.setType("extincteur");
  mat.setDateAcquisition(df1.parse(extincteurForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(extincteurForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(extincteurForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(extincteurForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(extincteurForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(extincteurForm.getHeurePeremption()));
  mat.setDescription(extincteurForm.getDescription()); 
  mat.setEmplacement(emplacement);
  mat.setNumberSerie(extincteurForm.getNumberSerie());
  mat.setSubtype(extincteurForm.getSubtype());
  
  if(emplacement.equals("departement")) {
	  Long iddepartement =extincteurForm.getIddepartement(); 
	  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
	  mat.setDepartement(departement);
  }
  else if(emplacement.equals("moyen transport")) {
	  Long idmoyentransport =extincteurForm.getIdmoyentransport();
	  MoyenTransport moyentransport =mtrepository.findById(idmoyentransport ).orElse(null); 
	  mat.setMoyentransport(moyentransport);
  }
  else if(emplacement.equals("depot")) {
	  Long iddepot =extincteurForm.getIddepot();
	  Depot depot =drepository.findById(iddepot ).orElse(null); 
	  mat.setDepot(depot);
  }
  else  {
	  System.out.println("emplacement invalide");
	  }
 Materiel mt = repository.save(mat); 
  if (mt != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("Materiel not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 }
 
@PreAuthorize("@authorizationSE.can('create', 'gestionmateriel')")
  @PostMapping("/addboucheincendie")
  public ResponseEntity<Response> createBoucheIncendie(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("boucheincendie") String boucheForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  BoucheIncendieForm boucheincendieForm= new ObjectMapper().readValue(boucheForm,  BoucheIncendieForm.class);
  Long iddepartement =boucheincendieForm.getIddepartement();
  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Materiel mat = new Materiel();
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
	  mat.setImage(newFileName);
  }
  }
  mat.setType("boucheincendie");
  mat.setDateAcquisition(df1.parse(boucheincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(boucheincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(boucheincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(boucheincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(boucheincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(boucheincendieForm.getHeurePeremption()));
  mat.setDescription(boucheincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(boucheincendieForm.getNumberSerie());
  mat.setNombrebouche(boucheincendieForm.getNombrebouche());
  mat.setDepartement(departement);
 Materiel mt = repository.save(mat); 
  if (mt != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("bouche incendie not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 }
@PreAuthorize("@authorizationSE.can('create', 'gestionmateriel')")
  @PostMapping("/addrobinetincendie")
  public ResponseEntity<Response> createRobinetIncendie(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("robinetincendie") String robinetForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  RobinetIncendieForm robinetincendieForm= new ObjectMapper().readValue(robinetForm,  RobinetIncendieForm.class);
  Long iddepartement =robinetincendieForm.getIddepartement();
  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Materiel mat = new Materiel();
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
	  mat.setImage(newFileName);
  }
  }
  mat.setType("robinetincendie");
  mat.setDateAcquisition(df1.parse(robinetincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(robinetincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(robinetincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(robinetincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(robinetincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(robinetincendieForm.getHeurePeremption()));
  mat.setDescription(robinetincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(robinetincendieForm.getNumberSerie());
  mat.setDiametre(robinetincendieForm.getDiametre());
  mat.setLength(robinetincendieForm.getLength());
  mat.setDepartement(departement);
 Materiel mt = repository.save(mat); 
  if (mt != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("robinet incendie not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 } 
@PreAuthorize("@authorizationSE.can('create', 'gestionmateriel')")
  @PostMapping("/addpoteauxincendie")
  public ResponseEntity<Response> createPoteauxIncendie(@RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("poteauxincendie") String poteauxForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  PoteauxIncendieForm poteauxincendieForm= new ObjectMapper().readValue(poteauxForm,  PoteauxIncendieForm.class);
  Long iddepartement =poteauxincendieForm.getIddepartement();
  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Materiel mat = new Materiel();
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
	  mat.setImage(newFileName);
  }
  }
  mat.setType("poteauxincendie");
  mat.setDateAcquisition(df1.parse(poteauxincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(poteauxincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(poteauxincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(poteauxincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(poteauxincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(poteauxincendieForm.getHeurePeremption()));
  mat.setDescription(poteauxincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(poteauxincendieForm.getNumberSerie());
  mat.setColeurpoteaux(poteauxincendieForm.getColeurpoteaux());
  mat.setDepartement(departement);
 Materiel mt = repository.save(mat); 
  if (mt != null) {
	  return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
  } else
  { return new ResponseEntity<Response>(new Response("poteaux incendie not saved"),HttpStatus.BAD_REQUEST); 
  } 
  
 } 
  
@PreAuthorize("@authorizationSE.can('delete', 'gestionmateriel')")
  @DeleteMapping("/deletemateriels/{id}") 
  public  void deleteMateriel(@PathVariable(value = "id") Long materielId) throws ResourceNotFoundException { 
	  repository.deleteMateriel(materielId);
  }
@PreAuthorize("@authorizationSE.can('delete', 'gestionmateriel')")
  @DeleteMapping("/materiels/delete")
  public ResponseEntity<String> deleteAllMateriels() {
   System.out.println("Delete All materiels...");
  repository.deleteAll(); 
  return new ResponseEntity<>("All materiels have been deleted!", HttpStatus.OK);
  }
@PreAuthorize("@authorizationSE.can('update', 'gestionmateriel')")
  @PutMapping("/editextincteur/{id}") public ResponseEntity<Materiel>updateExtincteur(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("extincteur") String extinctForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
  
	  System.out.println("Update Extincteur with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  ExtincteurForm extincteurForm= new ObjectMapper().readValue(extinctForm, ExtincteurForm.class);
	  String emplacement = extincteurForm.getEmplacement();
  Optional<Materiel> materielInfo = repository.findById(id);
  if (materielInfo.isPresent()) {
	  Materiel materiel = materielInfo.get();
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
			  materiel.setImage(newFileName);
		  }
	  }
	 
 
  materiel.setType("extincteur");
  materiel.setDateAcquisition(df1.parse(extincteurForm.getDateAcquisition()));
  materiel.setDateMaintenance(df1.parse(extincteurForm.getDateMaintenance()));
  materiel.setDatePeremption(df1.parse(extincteurForm.getDatePeremption()));
  materiel.setHeureAcquisition(df2.parse(extincteurForm.getHeureAcquisition()));
  materiel.setHeureMaintenance(df2.parse(extincteurForm.getHeureMaintenance()));
  materiel.setHeurePeremption(df2.parse(extincteurForm.getHeurePeremption()));
  materiel.setDescription(extincteurForm.getDescription()); 
  materiel.setEmplacement(emplacement);
  materiel.setNumberSerie(extincteurForm.getNumberSerie());
  materiel.setSubtype(extincteurForm.getSubtype());
 
  if(emplacement.equals("departement")) {
	  Long iddepartement =extincteurForm.getIddepartement(); 
	  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
	  materiel.setDepartement(departement);
  }
  else if(emplacement.equals("moyen transport")) {
	  Long idmoyentransport =extincteurForm.getIdmoyentransport();
	  MoyenTransport moyentransport =mtrepository.findById(idmoyentransport ).orElse(null); 
	  materiel.setMoyentransport(moyentransport);
  }
  else if(emplacement.equals("depot")) {
	  Long iddepot =extincteurForm.getIddepot();
	  Depot depot =drepository.findById(iddepot ).orElse(null); 
	  materiel.setDepot(depot);
  }
  else  {
	  System.out.println("emplacement invalide");
	  }
  return new ResponseEntity<>(repository.save(materiel), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
@PreAuthorize("@authorizationSE.can('update', 'gestionmateriel')")
  @PutMapping("/editboucheincendie/{id}") public ResponseEntity<Materiel>updateBoucheIncendie(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("boucheincendie") String boucheForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 

	  System.out.println("Update bouche incendie with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  BoucheIncendieForm boucheincendieForm= new ObjectMapper().readValue(boucheForm,  BoucheIncendieForm.class);
	  Long iddepartement =boucheincendieForm.getIddepartement();
	  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Optional<Materiel> materielInfo = repository.findById(id);
  if (materielInfo.isPresent()) {
  Materiel mat = materielInfo.get();
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
	  mat.setImage(newFileName);
  }
}

  mat.setType("boucheincendie");
  mat.setDateAcquisition(df1.parse(boucheincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(boucheincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(boucheincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(boucheincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(boucheincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(boucheincendieForm.getHeurePeremption()));
  mat.setDescription(boucheincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(boucheincendieForm.getNumberSerie());
  mat.setNombrebouche(boucheincendieForm.getNombrebouche());
  mat.setDepartement(departement);
  return new ResponseEntity<>(repository.save(mat), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
@PreAuthorize("@authorizationSE.can('update', 'gestionmateriel')")
  @PutMapping("/editrobinetincendie/{id}") public ResponseEntity<Materiel>updateRobinetIncendie(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("robinetincendie") String robinetForm) throws JsonParseException , JsonMappingException ,ParseException, Exception { 
  
	  System.out.println("Update bouche incendie with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  RobinetIncendieForm robinetincendieForm= new ObjectMapper().readValue(robinetForm,  RobinetIncendieForm.class);
	  Long iddepartement =robinetincendieForm.getIddepartement();
	  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Optional<Materiel> materielInfo = repository.findById(id);
  if (materielInfo.isPresent()) {
  Materiel mat = materielInfo.get();
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
	  mat.setImage(newFileName);
  }
}
  mat.setType("robinetincendie");
  mat.setDateAcquisition(df1.parse(robinetincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(robinetincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(robinetincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(robinetincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(robinetincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(robinetincendieForm.getHeurePeremption()));
  mat.setDescription(robinetincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(robinetincendieForm.getNumberSerie());
  mat.setDiametre(robinetincendieForm.getDiametre());
  mat.setLength(robinetincendieForm.getLength());
  mat.setDepartement(departement);
  return new ResponseEntity<>(repository.save(mat), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
@PreAuthorize("@authorizationSE.can('update', 'gestionmateriel')")
  @PutMapping("/editpoteauxincendie/{id}") public ResponseEntity<Materiel>updatePoteauxIncendie(@PathVariable("id") long id, @RequestParam(value="file", required=false) MultipartFile file,@Valid @RequestParam("poteauxincendie") String poteauxForm) throws JsonParseException , JsonMappingException ,ParseException, Exception {
   
	  System.out.println("Update Poteaux Incendie with ID = " + id + "...");
	  DateFormat df1= new SimpleDateFormat("yyyy-MM-dd");
	  DateFormat df2= new SimpleDateFormat("HH:mm");
	  PoteauxIncendieForm poteauxincendieForm= new ObjectMapper().readValue(poteauxForm,  PoteauxIncendieForm.class);
	  Long iddepartement =poteauxincendieForm.getIddepartement();
	  Departement departement = dtrepository.findById(iddepartement).orElse(null); 
  Optional<Materiel> materielInfo = repository.findById(id);
  if (materielInfo.isPresent()) {
  Materiel mat = materielInfo.get();
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
	  mat.setImage(newFileName);
  }
}
  mat.setType("poteauxincendie");
  mat.setDateAcquisition(df1.parse(poteauxincendieForm.getDateAcquisition()));
  mat.setDateMaintenance(df1.parse(poteauxincendieForm.getDateMaintenance()));
  mat.setDatePeremption(df1.parse(poteauxincendieForm.getDatePeremption()));
  mat.setHeureAcquisition(df2.parse(poteauxincendieForm.getHeureAcquisition()));
  mat.setHeureMaintenance(df2.parse(poteauxincendieForm.getHeureMaintenance()));
  mat.setHeurePeremption(df2.parse(poteauxincendieForm.getHeurePeremption()));
  mat.setDescription(poteauxincendieForm.getDescription()); 
  mat.setEmplacement("departement");
  mat.setNumberSerie(poteauxincendieForm.getNumberSerie());
  mat.setColeurpoteaux(poteauxincendieForm.getColeurpoteaux());
  mat.setDepartement(departement);
  return new ResponseEntity<>(repository.save(mat), HttpStatus.OK); }
  else
  { return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
 } 
}
 