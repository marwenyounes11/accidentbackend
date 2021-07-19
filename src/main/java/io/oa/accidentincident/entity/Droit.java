package io.oa.accidentincident.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "droit", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "action"
        })
})


public class Droit {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String action;
	    @OneToMany(mappedBy = "droit",cascade = CascadeType.ALL , orphanRemoval = true)
	    @JsonProperty(access=Access.WRITE_ONLY)
	     Set<DroitRoles> droitroles;

	   

		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getAction() {
			return action;
		}



		public void setAction(String action) {
			this.action = action;
		}



		



		public Set<DroitRoles> getDroitroles() {
			return droitroles;
		}



		public void setDroitroles(Set<DroitRoles> droitroles) {
			this.droitroles = droitroles;
		}



		public Droit() {
			super();
			// TODO Auto-generated constructor stub
		}



		



		public Droit(Long id, String action, Set<DroitRoles> droitroles) {
			super();
			this.id = id;
			this.action = action;
			this.droitroles = droitroles;
		}



		public Droit(String action, Set<DroitRoles> droitroles) {
		
			this.action = action;
			this.droitroles = droitroles;
		}

		

		

		
	    
}
