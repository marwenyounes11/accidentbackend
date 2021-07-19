package io.oa.accidentincident.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DegatRepository;
import io.oa.accidentincident.repository.VictimeRepository;
import io.oa.accidentincident.response.EstimationPrixDegatParAnner;
import io.oa.accidentincident.response.EstimationPrixDegatParDate;
import io.oa.accidentincident.response.EstimationPrixDegatParSousTypeAccident;
import io.oa.accidentincident.response.EstimationPrixDegatParTypeAccident;
import io.oa.accidentincident.response.NbrAccidentVictimeParAnner;
import io.oa.accidentincident.response.NbrAccidentParAnner;
import io.oa.accidentincident.response.NbrAccidentParDate;
import io.oa.accidentincident.response.NbrAccidentParDistrict;
import io.oa.accidentincident.response.NbrAccidentParMois;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransport;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransportDate;
import io.oa.accidentincident.response.NbrAccidentParSousTypeAccident;
import io.oa.accidentincident.response.NbrAccidentParTypeAccident;
import io.oa.accidentincident.response.NbrBlesserExterne;
import io.oa.accidentincident.response.NbrBlesserInterne;
import io.oa.accidentincident.response.NbrBlesserParAnner;
import io.oa.accidentincident.response.NbrBlesserParMois;
import io.oa.accidentincident.response.NbrBlesserParMoisNiveau;
import io.oa.accidentincident.response.NbrBlesserParNiveauBlessure;
import io.oa.accidentincident.response.NbrMortsParAnner;
import io.oa.accidentincident.response.NbrMortsParDate;
import io.oa.accidentincident.response.NbrMortsParMois;
import io.oa.accidentincident.service.NbrAccidentVictimeParAnnerService;
import io.oa.accidentincident.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@RestController
@CrossOrigin(origins = "*")
public class RapportController {
	
	@Autowired
	DegatRepository drepository;
	
	@Autowired
	AccidentRepository arepository;
	
	@Autowired
	VictimeRepository  vrepository; 
	
	@Autowired
	private ReportService  service;  
	
	@Autowired
	private NbrAccidentVictimeParAnnerService  navservice;  
	
	@PreAuthorize("@authorizationSE.can('afficher', 'degatdate') or @authorizationSE.can('afficher', 'degatdatestat')")
	@GetMapping ("/estimationPrixDegatParDate") 
	public List<EstimationPrixDegatParDate> listEstimationPrixDegatParDate(){
		List<EstimationPrixDegatParDate> result = drepository.listEstimationPrixDegatParDate();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'degatdate')")
	@GetMapping ("/reportEstimationPrixDegatParDate/{format}") 
	public String genereteReportEstimationPrixDegatParDate(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportEstimationprixdegatpardate(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'degattypeaccident') or @authorizationSE.can('afficher', 'degattypeaccidentstat')")
	@GetMapping ("/estimationPrixDegatParTypeAccident") 
	public List<EstimationPrixDegatParTypeAccident> listEstimationPrixDegatParTypeAccident(){
		List<EstimationPrixDegatParTypeAccident> result = drepository.listEstimationPrixDegatParTypeAccident();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'degattypeaccident')")
	@GetMapping ("/reportEstimationPrixDegatParTypeAccident/{format}") 
	public String genereteReportEstimationPrixDegatParTypeAccident(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportEstimationprixdegatpartypeaccident(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'degatstypeaccident') or @authorizationSE.can('afficher', 'degatstypeaccidentstat')")
	@GetMapping ("/estimationPrixDegatParSousTypeAccident") 
	public List<EstimationPrixDegatParSousTypeAccident> listEstimationPrixDegatParSousTypeAccident(){
		List<EstimationPrixDegatParSousTypeAccident> result = drepository.listEstimationPrixDegatParSousTypeAccident();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'degatstypeaccident')")
	@GetMapping ("/reportEstimationPrixDegatParSousTypeAccident/{format}") 
	public String genereteReportEstimationPrixDegatParSousTypeAccident(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportEstimationprixdegatparsoustypeaccident(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentdate') or @authorizationSE.can('afficher', 'nbraccidentdatestat')")
	@GetMapping ("/nbrAccidentParDate") 
	public List<NbrAccidentParDate> listNbrAccidentParDate(){
		List<NbrAccidentParDate> result = arepository.listNbrAccidentParDate();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentdate')")
	@GetMapping ("/reportNbrAccidentParDate/{format}") 
	public String genereteReportNbrAccidentParDate(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentpardate(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/nbrAccidentParMois") 
	public List<NbrAccidentParMois> listNbrAccidentParAnner(@RequestParam("d1") int d1){
		List<NbrAccidentParMois> result = arepository.listNbrAccidentParMois(d1);
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/reportNbrAccidentParMois/{format}") 
	public String genereteReportNbrAccidentParMois(@PathVariable String format,@RequestParam("d1") int d1) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentparmois(format,d1);
	}
	
	
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentCollisionBusParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentCollisionBusParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		
		
		return navservice.evaluationNbrAccidentCollisionBusParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentCollisionBusParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentCollisionBusParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentCollisionBusParAnner(format, d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentRouteBusParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentRouteBusParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		return navservice.evaluationNbrAccidentRouteBusParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentRouteBusParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentRouteBusParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentRouteBusParAnner(format, d1, d2);
	}
	
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentCollisionMetrosParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentCollisionMetrosParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		return navservice.evaluationNbrAccidentCollisionMetrosParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentCollisionMetrosParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentCollisionMetrosParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentCollisionMetrosParAnner(format, d1, d2);
	}

	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentRouteMetrosParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentRouteMetrosParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		return navservice.evaluationNbrAccidentRouteMetrosParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentRouteMetrosParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentRouteMetrosParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentRouteMetrosParAnner(format, d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentCollisionTgmParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentTgmCollisionParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		return navservice.evaluationNbrAccidentTgmCollisionParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentCollisionTgmParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentCollisionTgmParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentCollisionTgmParAnner(format, d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/evaluationNbrAccidentRouteTgmParAnner") 
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentTgmRouteParAnner(@RequestParam("d1") int d1,@RequestParam("d2") int d2){
		return navservice.evaluationNbrAccidentTgmRouteParAnner(d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccidentreseaux')")
	@GetMapping ("/reportEvaluationNbrAccidentRouteTgmParAnner/{format}") 
	public String genereteReportEvaluationNbrAccidentRouteTgmParAnner(@PathVariable String format,@RequestParam("d1") int d1,@RequestParam("d2") int d2) throws FileNotFoundException, JRException {
		
		return service.exportReportEvaluationNbrAccidentRouteTgmParAnner(format, d1, d2);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentdistrict') or @authorizationSE.can('afficher', 'nbraccidentdistrictstat')")
	@GetMapping ("/nbrAccidentParDistrict") 
	public List<NbrAccidentParDistrict> listNbrAccidentParDistrict(){
		List<NbrAccidentParDistrict> result = arepository.listNbrAccidentParDistrict();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentdistrict')")
	@GetMapping ("/reportNbrAccidentParDistrict/{format}") 
	public String genereteReportNbrAccidentParDistrict(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentpardistrict(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttransport') or @authorizationSE.can('afficher', 'nbraccidenttransportstat')")
	@GetMapping ("/nbrAccidentParMoyenTransport") 
	public List<NbrAccidentParMoyenTransport> listNbrAccidentParMoyenTransport(){
		List<NbrAccidentParMoyenTransport> result = arepository.listNbrAccidentParMoyenTransport();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttransport')")
	@GetMapping ("/reportNbrAccidentParMoyenTransport/{format}") 
	public String genereteReportNbrAccidentParMoyenTransport(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentparmoyentransport(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttransportdate')")
	@GetMapping ("/nbrAccidentParMoyenTransportDate") 
	public List<NbrAccidentParMoyenTransportDate> listNbrAccidentParMoyenTransportDate(){
		List<NbrAccidentParMoyenTransportDate> result = arepository.listNbrAccidentParMoyenTransportDate();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttransportdate')")
	@GetMapping ("/reportNbrAccidentParMoyenTransportDate/{format}") 
	public String genereteReportNbrAccidentParMoyenTransportDate(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentparmoyentransportdate(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttypeaccident') or @authorizationSE.can('afficher', 'nbraccidenttypeaccidentstat')")
	@GetMapping ("/nbrAccidentParTypeAccident") 
	public List<NbrAccidentParTypeAccident> listNbrAccidentParTypeAccident(){
		List<NbrAccidentParTypeAccident> result = arepository.listNbrAccidentParTypeAccident();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbraccidenttypeaccident')")
	@GetMapping ("/reportNbrAccidentParTypeAccident/{format}") 
	public String genereteReportNbrAccidentParTypeAccident(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrAccidentpartypeaccident(format);
	}
	 @PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentstypeaccident') or @authorizationSE.can('afficher', 'nbraccidentstypeaccidentstat')")
	@GetMapping ("/nbrAccidentParSousTypeAccident") 
	public List<NbrAccidentParSousTypeAccident> listNbrAccidentParSousTypeAccident(){
		List<NbrAccidentParSousTypeAccident> result = arepository.listNbrAccidentParSousTypeAccident();
		return result;
	}
	 @PreAuthorize("@authorizationSE.can('afficher', 'nbraccidentstypeaccident')")
	@GetMapping ("/reportNbrAccidentParSousTypeAccident/{format}") 
	public String genereteReportNbrAccidentParSousTypeAccident(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.exportReportNbrAccidentparsoustypeaccident(format);
	}
	 @PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserexterne') or @authorizationSE.can('afficher', 'nbrblesserexternestat')")
	@GetMapping ("/nbrBlesserExterne") 
	public List<NbrBlesserExterne> listNbrBlesserExterne(){
		List<NbrBlesserExterne> result = vrepository.listNbrBlesserExterne();
		return result;
	}
	 @PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserexterne')")
	@GetMapping ("/reportNbrBlesserExterne/{format}") 
	public String genereteReportNbrBlesserExterne(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrBlesserExterne(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserinterne') or @authorizationSE.can('afficher', 'nbrblesserinternestat')")
	@GetMapping ("/nbrBlesserInterne") 
	public List<NbrBlesserInterne> listNbrBlesserInterne(){
		List<NbrBlesserInterne> result = vrepository.listNbrBlesserInterne();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserinterne')")
	@GetMapping ("/reportNbrBlesserInterne/{format}") 
	public String genereteReportNbrBlesserInterne(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrBlesserInterne(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserniveaublessure')or@authorizationSE.can('afficher', 'nbrblesserniveaublessurestat')")
	@GetMapping ("/nbrBlesserParNiveauBlessure") 
	public List<NbrBlesserParNiveauBlessure> listNbrBlesserParNiveauBlessure(){
		List<NbrBlesserParNiveauBlessure> result = vrepository.listNbrBlesserParNiveauBlessure();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrblesserniveaublessure')")
	@GetMapping ("/reportNbrBlesserParNiveauBlessure/{format}") 
	public String genereteReportNbrBlesserParNiveauBlessure(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrBlesserParNiveauBlessure(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrmortspardate')or@authorizationSE.can('afficher', 'nbrmortsdatestat')")
	@GetMapping ("/nbrMortsParDate") 
	public List<NbrMortsParDate> listNbrMortsParDate(){
		List<NbrMortsParDate> result = arepository.listNbrMortsParDate();
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'nbrmortspardate')")
	@GetMapping ("/reportNbrMortsParDate/{format}") 
	public String genereteReportNbrMortsParDate(@PathVariable String format) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrMortsParDate(format);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/nbrMortsParMois") 
	public List<NbrMortsParMois> listNbrMortsParAnner(@RequestParam("d1") int d1){
		List<NbrMortsParMois> result = arepository.listNbrMortsParMois(d1);
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/reportNbrMortsParMois/{format}") 
	public String genereteReportNbrMortsParMois(@PathVariable String format,@RequestParam("d1") int d1) throws IOException, FileNotFoundException, JRException {
		
		return service.exportReportNbrMortsParMois(format,d1);
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/nbrBlesserParMois") 
	public List<NbrBlesserParMois> listNbrBlesseParMoiss(@RequestParam("d1") int d1){
		List<NbrBlesserParMois> result = new ArrayList<>(); 
		NbrBlesserParMois nbrBlesserParMois = new NbrBlesserParMois(); 
		List<NbrBlesserParMoisNiveau> result1 = arepository.listNbrBlesserParMoisNiveau(d1);
		for(int i=0;i<result1.size();i++) {
			NbrBlesserParMoisNiveau nbpmn = new NbrBlesserParMoisNiveau();
			if(i-1>=0)
			 {
			nbpmn.setMois(result1.get(i-1).getMois());
			 }
			if(!result1.get(i).getMois().equals(nbpmn.getMois()) )
			{
			nbrBlesserParMois = new NbrBlesserParMois(); 
			}
			// nbrBlesserParMois.setMois(r.getMois()); 
			if(nbrBlesserParMois.getMois()==null) {
				nbrBlesserParMois.setMois(result1.get(i).getMois()); 
			}
			if(result1.get(i).getNiveauBlessure().equals("dangereux"))
			 {
				nbrBlesserParMois.setNbrBlessureDanger(result1.get(i).getNbrBlesser());	
				
			 }else{
					nbrBlesserParMois.setNbrBlessureMineur(result1.get(i).getNbrBlesser());	
					
				 }
			 nbpmn = new NbrBlesserParMoisNiveau();
			if(i+1<result1.size())
			 {
			nbpmn.setMois(result1.get(i+1).getMois());
			 }
			if(result1.get(i).getMois().equals(nbpmn.getMois()) )
			 {	
				continue;
			 }else
			 {
						result.add(nbrBlesserParMois);		
			}
		}
		result.forEach(r1->{
			if(r1.getNbrBlessureDanger()== null) {
				r1.setNbrBlessureDanger((long) 0);
			}
			if(r1.getNbrBlessureMineur()== null) {
				r1.setNbrBlessureMineur((long) 0);
			}
		});
		return result;
	}
	@PreAuthorize("@authorizationSE.can('afficher', 'evaluationaccident')")
	@GetMapping ("/reportNbrBlesserParMois/{format}") 
	public String genereteReportNbrBlesserParMois(@PathVariable String format,@RequestParam("d1") int d1) throws FileNotFoundException, JRException {
		
		return service.exportReportNbrBlesserParMois(format,d1);
	}
	
	
	

	
	
	 
	
	
	 
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
