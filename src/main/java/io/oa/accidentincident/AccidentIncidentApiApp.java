package io.oa.accidentincident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import io.oa.accidentincident.entity.Accident;
import io.oa.accidentincident.entity.AccidentInform;
import io.oa.accidentincident.entity.AccidentTransport;
import io.oa.accidentincident.entity.Agent;
import io.oa.accidentincident.entity.AgentIntervention;
import io.oa.accidentincident.entity.AgentTranstu;
import io.oa.accidentincident.entity.Ambulancier;
import io.oa.accidentincident.entity.Degat;
import io.oa.accidentincident.entity.Departement;
import io.oa.accidentincident.entity.Depot;
import io.oa.accidentincident.entity.District;
import io.oa.accidentincident.entity.Huissier;
import io.oa.accidentincident.entity.Intervention;
import io.oa.accidentincident.entity.LieuxAccident;
import io.oa.accidentincident.entity.Ligne;
import io.oa.accidentincident.entity.Materiel;
import io.oa.accidentincident.entity.MoyenTransport;
import io.oa.accidentincident.entity.Securite;
import io.oa.accidentincident.entity.SourceDeclareHuissier;
import io.oa.accidentincident.entity.SourceInform;




@SpringBootApplication
public class AccidentIncidentApiApp implements CommandLineRunner{
	 @Autowired 
	    private RepositoryRestConfiguration restConfiguration; 
	
	public static  void main (String[] args) {
		SpringApplication.run(AccidentIncidentApiApp.class, args);
		System.out.println("_____________run spring boot application 2.0.0_______________");
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Accident.class,AccidentInform.class,AccidentTransport.class,AgentTranstu.class,Securite.class,Huissier.class,Ambulancier.class,SourceDeclareHuissier.class,Agent.class,Degat.class,Depot.class,Departement.class,Materiel.class,District.class,LieuxAccident.class,Ligne.class,MoyenTransport.class,SourceInform.class,Intervention.class,AgentIntervention.class);
		
	}

}
