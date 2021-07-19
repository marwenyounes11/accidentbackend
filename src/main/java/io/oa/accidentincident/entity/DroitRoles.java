package io.oa.accidentincident.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class DroitRoles {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @ManyToOne
	    @JoinColumn(name="droit_id")
	    private Droit droit;
	    @ManyToOne
	    @JoinColumn(name="sousrubrique_id")
	    private SousRubrique sousrubrique;
	    @ManyToOne
	    @JoinColumn(name="role_id")
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
		public DroitRoles() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DroitRoles(Long id, Droit droit, SousRubrique sousrubrique, Role role) {
			super();
			this.id = id;
			this.droit = droit;
			this.sousrubrique = sousrubrique;
			this.role = role;
		}

		public DroitRoles( Droit droit, SousRubrique sousrubrique, Role role) {
			this.droit = droit;
			this.sousrubrique = sousrubrique;
			this.role = role;
		}
		
	    
}
