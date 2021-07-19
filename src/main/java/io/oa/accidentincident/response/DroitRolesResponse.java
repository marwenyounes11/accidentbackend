package io.oa.accidentincident.response;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.oa.accidentincident.entity.Droit;
import io.oa.accidentincident.entity.Role;
import io.oa.accidentincident.entity.SousRubrique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class DroitRolesResponse {
	 
	    private Long id;
	   
	    private Droit droit;
	   
	    private SousRubrique sousrubrique;
	    
	    private Role role;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Droit getDroit() {
			return droit;
		}

		public void setDroit(Droit droit) {
			this.droit = droit;
		}

		public SousRubrique getSousrubrique() {
			return sousrubrique;
		}

		public void setSousrubrique(SousRubrique sousrubrique) {
			this.sousrubrique = sousrubrique;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public DroitRolesResponse(Long id, Droit droit, SousRubrique sousrubrique, Role role) {
			super();
			this.id = id;
			this.droit = droit;
			this.sousrubrique = sousrubrique;
			this.role = role;
		}

		public DroitRolesResponse() {
			super();
			// TODO Auto-generated constructor stub
		}

		
	
	    
}
