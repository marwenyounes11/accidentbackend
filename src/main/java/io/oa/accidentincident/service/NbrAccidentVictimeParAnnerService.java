package io.oa.accidentincident.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DegatRepository;
import io.oa.accidentincident.repository.VictimeRepository;
import io.oa.accidentincident.response.EstimationPrixDegatParAnner;
import io.oa.accidentincident.response.NbrAccidentParAnner;
import io.oa.accidentincident.response.NbrAccidentVictimeParAnner;
import io.oa.accidentincident.response.NbrBlesserParAnner;
import io.oa.accidentincident.response.NbrMortsParAnner;
@Service
public class NbrAccidentVictimeParAnnerService {

	@Autowired
	DegatRepository drepository;
	
	@Autowired
	AccidentRepository arepository;
	
	@Autowired
	VictimeRepository  vrepository; 
	
	public List<NbrAccidentParAnner> listNbrAccidentBusCollisionParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentBusCollisionParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
       
		  for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		  }
		  if( result2.size()==0  ) {
				
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserBusCollisionParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserBusCollisionParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsBusCollisionParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsBusCollisionParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
		
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatBusCollisionParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatBusCollisionParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentCollisionBusParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentBusCollisionParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserBusCollisionParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsBusCollisionParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatBusCollisionParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentCollisionParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentCollisionParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentCollisionParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentCollisionParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentCollisionParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentCollisionParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentCollisionParAnner.setNbrMorts(nbrmrt);
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentCollisionParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentCollisionParAnner.add(nbrAccidentCollisionParAnner);
		 }
		 
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentCollisionParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
	
	public List<NbrAccidentParAnner> listNbrAccidentBusRouteParAnner( int d1,int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentBusRouteParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
       
		  for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		  }
		  if( result2.size()==0  ) {
				
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserBusRouteParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserBusRouteParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsBusRouteParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsBusRouteParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatBusRouteParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatBusRouteParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentRouteBusParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentBusRouteParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserBusRouteParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsBusRouteParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatBusRouteParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentRouteParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentRouteParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentRouteParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentRouteParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentRouteParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentRouteParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentRouteParAnner.setNbrMorts(nbrmrt);	
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentRouteParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentRouteParAnner.add(nbrAccidentRouteParAnner);
		 }
		
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentRouteParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
	
	public List<NbrAccidentParAnner> listNbrAccidentMetrosCollisionParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentMetrosCollisionParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserMetrosCollisionParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserMetrosCollisionParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsMetrosCollisionParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsMetrosCollisionParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatMetrosCollisionParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatMetrosCollisionParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentCollisionMetrosParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentMetrosCollisionParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserMetrosCollisionParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsMetrosCollisionParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatMetrosCollisionParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentCollisionParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentCollisionParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentCollisionParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentCollisionParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentCollisionParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentCollisionParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentCollisionParAnner.setNbrMorts(nbrmrt);	
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentCollisionParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentCollisionParAnner.add(nbrAccidentCollisionParAnner);
		 }
		
		
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentCollisionParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
	
	public List<NbrAccidentParAnner> listNbrAccidentMetrosRouteParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentMetrosRouteParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
       
		  for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		  }
		  if( result2.size()==0  ) {
				
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserMetrosRouteParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserMetrosRouteParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsMetrosRouteParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsMetrosRouteParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatMetrosRouteParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatMetrosRouteParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentRouteMetrosParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentMetrosRouteParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserMetrosRouteParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsMetrosRouteParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatMetrosRouteParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentRouteParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentRouteParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentRouteParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentRouteParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentRouteParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentRouteParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentRouteParAnner.setNbrMorts(nbrmrt);	
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentRouteParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentRouteParAnner.add(nbrAccidentRouteParAnner);
		 }
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentRouteParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
	
	public List<NbrAccidentParAnner> listNbrAccidentTgmCollisionParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentTgmCollisionParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
       
		  for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		  }
		  if( result2.size()==0  ) {
				
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserTgmCollisionParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserTgmCollisionParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsTgmCollisionParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsTgmCollisionParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatTgmCollisionParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatTgmCollisionParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentTgmCollisionParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentTgmCollisionParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserTgmCollisionParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsTgmCollisionParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatTgmCollisionParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentCollisionParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentCollisionParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentCollisionParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentCollisionParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentCollisionParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentCollisionParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentCollisionParAnner.setNbrMorts(nbrmrt);	
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentCollisionParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentCollisionParAnner.add(nbrAccidentCollisionParAnner);
		 }
		
		
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentCollisionParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
	
	public List<NbrAccidentParAnner> listNbrAccidentTgmRouteParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = arepository.listNbrAccidentTgmRouteParAnner();
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		List<NbrAccidentParAnner> result2 = new ArrayList<>(); 
		NbrAccidentParAnner nbrAccidentParAnner;
       
		  for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		  }
		  if( result2.size()==0  ) {
				
	    	   nbrAccidentParAnner = new NbrAccidentParAnner();
	    	   nbrAccidentParAnner.setAnnees(d1);
	    	   nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner);
				nbrAccidentParAnner = new NbrAccidentParAnner();
				nbrAccidentParAnner.setAnnees(d2);
				nbrAccidentParAnner.setNbrAccident((long) 0);
				result2.add(nbrAccidentParAnner); 
			} if( result2.size()==1  ) {
				nbrAccidentParAnner = new NbrAccidentParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrAccidentParAnner.setAnnees(d2);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrAccidentParAnner.setAnnees(d1);
					nbrAccidentParAnner.setNbrAccident((long) 0);
					result2.add(nbrAccidentParAnner); 
				}else {
					nbrAccidentParAnner = new NbrAccidentParAnner();
			    	   nbrAccidentParAnner.setAnnees(d1);
			    	   nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner);
						nbrAccidentParAnner = new NbrAccidentParAnner();
						nbrAccidentParAnner.setAnnees(d2);
						nbrAccidentParAnner.setNbrAccident((long) 0);
						result2.add(nbrAccidentParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrBlesserParAnner> listNbrBlesserTgmRouteParAnner( int d1, int d2){
		List<NbrBlesserParAnner> result1 = arepository.listNbrBlesserTgmRouteParAnner();
		List<NbrBlesserParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrBlesserParAnner nbrBlesserParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrBlesserParAnner = new NbrBlesserParAnner();
	    	   nbrBlesserParAnner.setAnnees(d1);
	    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner);
				nbrBlesserParAnner = new NbrBlesserParAnner();
				nbrBlesserParAnner.setAnnees(d2);
				nbrBlesserParAnner.setNbrBlesser((long) 0);
				result2.add(nbrBlesserParAnner); 
			} if( result2.size()==1  ) {
				nbrBlesserParAnner = new NbrBlesserParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrBlesserParAnner.setAnnees(d2);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrBlesserParAnner.setAnnees(d1);
					nbrBlesserParAnner.setNbrBlesser((long) 0);
					result2.add(nbrBlesserParAnner); 
				}else {
					nbrBlesserParAnner = new NbrBlesserParAnner();
			    	   nbrBlesserParAnner.setAnnees(d1);
			    	   nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner);
						nbrBlesserParAnner = new NbrBlesserParAnner();
						nbrBlesserParAnner.setAnnees(d2);
						nbrBlesserParAnner.setNbrBlesser((long) 0);
						result2.add(nbrBlesserParAnner); 
				}
			}
		return result2;
	}
	
	public List<NbrMortsParAnner> listNbrMortsTgmRouteParAnner( int d1, int d2){
		List<NbrMortsParAnner> result1 = arepository.listNbrMortsTgmRouteParAnner();
		List<NbrMortsParAnner> result2 = new ArrayList<>(); 
		result1.forEach(r1->{
			System.out.println(r1.getAnnees());
		});
		NbrMortsParAnner nbrMortsParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
	    	   nbrMortsParAnner = new NbrMortsParAnner();
	    	   nbrMortsParAnner.setAnnees(d1);
	    	   nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner);
				nbrMortsParAnner = new NbrMortsParAnner();
				nbrMortsParAnner.setAnnees(d2);
				nbrMortsParAnner.setNbrMorts((long) 0);
				result2.add(nbrMortsParAnner); 
			} if( result2.size()==1  ) {
				nbrMortsParAnner = new NbrMortsParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					nbrMortsParAnner.setAnnees(d2);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					nbrMortsParAnner.setAnnees(d1);
					nbrMortsParAnner.setNbrMorts((long) 0);
					result2.add(nbrMortsParAnner); 
				}else {
					nbrMortsParAnner = new NbrMortsParAnner();
			    	   nbrMortsParAnner.setAnnees(d1);
			    	   nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner);
						nbrMortsParAnner = new NbrMortsParAnner();
						nbrMortsParAnner.setAnnees(d2);
						nbrMortsParAnner.setNbrMorts((long) 0);
						result2.add(nbrMortsParAnner); 
				}
			}
		return result2;
	}
	
	public List<EstimationPrixDegatParAnner> listEstimationPrixDegatTgmRouteParAnner( int d1, int d2){
		List<EstimationPrixDegatParAnner> result1 = drepository.listEstimationPrixDegatTgmRouteParAnner();
		List<EstimationPrixDegatParAnner> result2 = new ArrayList<>(); 
		
		EstimationPrixDegatParAnner estimationPrixDegatParAnner;
		for (int i = 0; i < result1.size(); i++) {
			int annees =result1.get(i).getAnnees();
			if(annees ==d1 || annees==d2) {
				result2.add(result1.get(i));
				}
		}
		if( result2.size()==0  ) {
			
			estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
			estimationPrixDegatParAnner.setAnnees(d1);
			estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
				result2.add(estimationPrixDegatParAnner);
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				estimationPrixDegatParAnner.setAnnees(d2);
				estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
			} if( result2.size()==1  ) {
				estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
				 
				 if( result2.get(0).getAnnees()==d1  ) {
					 estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner); 
				}else if( result2.get(0).getAnnees()==d2  ){
					 estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}else {
					estimationPrixDegatParAnner = new EstimationPrixDegatParAnner();
					estimationPrixDegatParAnner.setAnnees(d1);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
					estimationPrixDegatParAnner.setAnnees(d2);
					 estimationPrixDegatParAnner.setEstimationPrixDegat( (double) 0);
					result2.add(estimationPrixDegatParAnner);
				}
			}
		return result2;
	}
	
	public List<NbrAccidentVictimeParAnner> evaluationNbrAccidentTgmRouteParAnner( int d1, int d2){
		List<NbrAccidentParAnner> result1 = listNbrAccidentTgmRouteParAnner(d1,d2);
		List<NbrBlesserParAnner> result2 = listNbrBlesserTgmRouteParAnner(d1,d2);
		List<NbrMortsParAnner> result3 = listNbrMortsTgmRouteParAnner(d1,d2);
		List<EstimationPrixDegatParAnner> result4 = listEstimationPrixDegatTgmRouteParAnner(d1,d2);
		List<NbrAccidentVictimeParAnner> lNbrAccidentRouteParAnner = new ArrayList<>(); 
		NbrAccidentVictimeParAnner nbrAccidentRouteParAnner ; 
		
		for (int i = 0; i < result1.size(); i++) {
		
			nbrAccidentRouteParAnner = new NbrAccidentVictimeParAnner();
			int annees =result1.get(i).getAnnees();
			nbrAccidentRouteParAnner.setAnnees(annees);
			Long nbracc =result1.get(i).getNbrAccident();
				nbrAccidentRouteParAnner.setNbrAccident(nbracc);
			Long nbrbls =result2.get(i).getNbrBlesser();
				nbrAccidentRouteParAnner.setNbrBlesser(nbrbls);
			Long nbrmrt =result3.get(i).getNbrMorts();
				nbrAccidentRouteParAnner.setNbrMorts(nbrmrt);	
				Double estimationPrixDegat =result4.get(i).getEstimationPrixDegat();
				nbrAccidentRouteParAnner.setEstimationPrixDegat(estimationPrixDegat);
			lNbrAccidentRouteParAnner.add(nbrAccidentRouteParAnner);
		 }
		List<NbrAccidentVictimeParAnner> listesorted = lNbrAccidentRouteParAnner.stream()
				.sorted(Comparator.comparingInt(NbrAccidentVictimeParAnner::getAnnees))
				.collect(Collectors.toList());
		
		return listesorted;
	}
}
