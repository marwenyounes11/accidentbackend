package io.oa.accidentincident.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "name"
        })
})
public class Role {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; 
	    private String name;
	    @OneToMany(mappedBy = "role",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.EAGER)
	    @JsonProperty(access=Access.WRITE_ONLY)
	     Set<DroitRoles> droitroles;
	    @OneToMany(mappedBy="role")
	    @JsonProperty(access=Access.WRITE_ONLY)
	    private Set<User> users = new HashSet<User>();
	   
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		


		

		
		


		public Set<DroitRoles> getDroitroles() {
			return droitroles;
		}


		public void setDroitroles(Set<DroitRoles> droitroles) {
			this.droitroles = droitroles;
		}


		public Set<User> getUsers() {
			return users;
		}


		public void setUsers(Set<User> users) {
			this.users = users;
		}


		

		


		


		
		
		


		

		

		public Role(Long id, String name, Set<DroitRoles> droitroles, Set<User> users) {
			super();
			this.id = id;
			this.name = name;
			this.droitroles = droitroles;
			this.users = users;
		}


		public Role( String name, Set<DroitRoles> droitroles, Set<User> users) {
			this.name = name;
			this.droitroles = droitroles;
			this.users = users;
		}
		


		public Role() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
		
	    
		
}
